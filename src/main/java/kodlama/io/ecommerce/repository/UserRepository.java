package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
