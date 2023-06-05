package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CreditCardService;
import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.PosService;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentForSaleWithRegisteredCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import kodlama.io.ecommerce.entities.CreditCard;
import kodlama.io.ecommerce.entities.Payment;
import kodlama.io.ecommerce.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final PosService posService;
    private final CreditCardService creditCardService;


    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream()
                .map(payment -> modelMapper.map(payment, GetAllPaymentsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElseThrow();
        GetPaymentResponse response = modelMapper.map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        Payment payment = modelMapper.map(request, Payment.class);

        paymentRepository.save(payment);
        CreatePaymentResponse response = modelMapper.map(payment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public UpdatePaymentResponse update(UUID  id, UpdatePaymentRequest request) {
        Payment payment = modelMapper.map(request, Payment.class);
        payment.setId(id);
        paymentRepository.save(payment);
        UpdatePaymentResponse response = modelMapper.map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(UUID  id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment paymentForSaleWithRegisteredCreditCard(CreatePaymentForSaleWithRegisteredCreditCardRequest request){
        CreditCard creditCard = modelMapper.map(creditCardService.getById(request.getCreditCardId()),CreditCard.class);
        checkIfBalanceIsEnough(creditCard.getBalance(), request.getTotalPrice());
        posService.pay();
        creditCard.setBalance(creditCard.getBalance()- request.getTotalPrice());
        creditCardService.update(creditCard.getId(), modelMapper.map(creditCard, UpdateCreditCardRequest.class));
        Payment payment = modelMapper.map(creditCard, Payment.class);
        payment.setAmount(request.getTotalPrice());
        paymentRepository.save(payment);
        return payment;
    }

    private void checkIfBalanceIsEnough(double balance, double price) {
        if (balance < price) {
            throw new RuntimeException("Insufficient balance!");
        }
    }

}
