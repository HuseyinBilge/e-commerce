package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product add(Product product) {
        validateProduct(product);
        return productRepository.add(product);
    }


    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product update(int id, Product product) {
        validateProduct(product);
        return productRepository.update(id, product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
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

}

