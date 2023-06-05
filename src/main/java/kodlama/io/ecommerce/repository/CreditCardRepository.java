package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
}
