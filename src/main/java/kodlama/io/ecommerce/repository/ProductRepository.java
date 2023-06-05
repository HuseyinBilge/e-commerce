package kodlama.io.ecommerce.repository;


import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.Activeness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByActiveness(Activeness activeness);

    List<Product> findAllByCategoryId(UUID categoryId);

}

