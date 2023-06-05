package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.*;
import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentForSaleWithRegisteredCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateSaleResponse;
import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.entities.Payment;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.Sale;
import kodlama.io.ecommerce.entities.enums.Activeness;
import kodlama.io.ecommerce.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final CreditCardService creditCardService;
    private final UserService userService;
    private final EMailSenderService eMailSenderService;
    private final CategoryService categoryService;
    @Override
    public List<GetAllSalesResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(sale -> mapper.map(sale, GetAllSalesResponse.class))
                .toList();
    }

    @Override
    public GetSaleResponse getById(UUID id) {
        return mapper.map(repository.findById(id), GetSaleResponse.class);
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        Sale sale = mapper.map(request, Sale.class);



        List<Product> products = productsIdInRequestToProductList(request.getProductsId());
        checkIfProductInStock(products);
        checkIfProductActiveness(products);



        //Payment
        double totalPrice = getTotalPrice(products, request.getDiscountRate());
        CreatePaymentForSaleWithRegisteredCreditCardRequest paymentWithCreditCard =
                new CreatePaymentForSaleWithRegisteredCreditCardRequest
                        (totalPrice, request.getCreditCardId());
        Payment payment= paymentService.paymentForSaleWithRegisteredCreditCard(paymentWithCreditCard);
        UpdatePaymentRequest updatePaymentRequest = mapper.map(payment, UpdatePaymentRequest.class);
        updatePaymentRequest.setAmount(totalPrice);

        //Stock Amount Decrease After Payment
        productsStockAmountDecrease(products);


        //Invoice
        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequestAfterSale(request, totalPrice, invoiceRequest);
        CreateInvoicesResponse invoiceResponse = invoiceService.add(invoiceRequest);

        //Send Invoice as EMAİL
        eMailSenderService.sendEmail(invoiceRequest.getUserEMail(), "Invoice", invoiceResponse.toString());


        //Repository
        sale.setTotalPrice(totalPrice);
        sale.setInvoice(mapper.map(invoiceResponse, Invoice.class));
        sale.setProducts(products);
        sale.setId(null);
        repository.save(sale);

        updatePaymentRequest.setSaleId(sale.getId());
        paymentService.update(payment.getId(),updatePaymentRequest);
        // before update payment, sale must be added

        return mapper.map(sale, CreateSaleResponse.class);
    }

    @Override
    public UpdateSaleResponse update(UUID  id, UpdateSaleRequest request) {
        Sale sale = mapper.map(request, Sale.class);
        sale.setId(id);
        List<Product> products = productsIdInRequestToProductList(request.getProductsId());
        sale.setProducts(products);
        sale.setInvoice(mapper.map(invoiceService.getById(request.getInvoiceId()), Invoice.class));
        repository.save(sale);
        return mapper.map(sale, UpdateSaleResponse.class);
    }

    @Override
    public void delete(UUID  id) {
        repository.deleteById(id);

    }

    private void createInvoiceRequestAfterSale(CreateSaleRequest request, double totalPrice, CreateInvoiceRequest invoiceRequest) {
        invoiceRequest.setAmount(totalPrice);
        invoiceRequest.setCardNumber(creditCardService.getById(request.getCreditCardId()).getCardNumber());
        invoiceRequest.setCardHolder(creditCardService.getById(request.getCreditCardId()).getCardHolder());
        invoiceRequest.setCreationDate(LocalDateTime.now());
        invoiceRequest.setUserEMail(userService.getById(request.getUserId()).getEMail());
        invoiceRequest.setUserFirstName(userService.getById(request.getUserId()).getFirstName());
        invoiceRequest.setUserLastName(userService.getById(request.getUserId()).getLastName());
    }

    private void productsStockAmountDecrease(List<Product> products) {
        for (Product product : products) {
            product.setStockAmount(productService.getById(product.getId()).getStockAmount() - 1);
            UpdateProductRequest updateProductRequest =  mapper.map(product, UpdateProductRequest.class);
            updateProductRequest.setCategoryId(productService.getById(product.getId()).getCategoryId());
            productService.update(product.getId(),updateProductRequest );
        }
    }

    private void checkIfProductInStock(List<Product> products) {
        boolean stockAmountCheck = products
                .stream()
                .anyMatch(product -> product.getStockAmount() <= 0);
        if (stockAmountCheck)
            throw new RuntimeException("Not Enough Stock Amount");
    }

    private void checkIfProductActiveness(List<Product> products) {
        boolean productActivenessCheck = products
                .stream()
                .anyMatch(product -> product.getActiveness().equals(Activeness.PASSIVE));
        if (productActivenessCheck)
            throw new RuntimeException("One of the product is not active");
    }

    private List<Product> productsIdInRequestToProductList(List<UUID> productsId) {
        return productsId.stream()
                .map(UUID -> productService.getById(UUID))
                .map(getProductResponse -> mapper.map(getProductResponse, Product.class))
                .toList();
    }

    private double getTotalPrice(List<Product> products, double discountRate) {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        if (discountRate == 0)
            return totalPrice;
        else
            return totalPrice - (totalPrice / discountRate);
    }
}
