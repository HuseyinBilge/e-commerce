package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.CreditCard;
import kodlama.io.ecommerce.entities.Payment;
import kodlama.io.ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
