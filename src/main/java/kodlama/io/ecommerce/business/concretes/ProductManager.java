package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public Product add(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }


    @Override
    public void delete(int id) {
        checkIfProductExists(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product update(int id, Product product) {
        checkIfProductExists(id);
        product.setId(id);
        validateProduct(product);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        checkIfProductExists(id);
        return productRepository.findById(id).orElseThrow();
    }

    // Business rules
    private void validateProduct(Product product) {
        checkIfPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }

    private void checkIfDescriptionLengthValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            throw new IllegalArgumentException("Description Length Must Be Between 10 to 50 Characters.");
        }
    }

    private void checkIfQuantityValid(Product product) {
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity Can Not Be Less Than Zero.");
        }
    }

    private void checkIfPriceValid(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price Can Not Be Equal or Less Than Zero.");
        }
    }

    private void checkIfProductExists(int id) {
        if (!productRepository.existsById(id))
            throw new RuntimeException("Ürün Bulunamadı.");
    }

}

