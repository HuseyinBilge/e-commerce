package kodlama.io.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime creationDate;
    private double amount;
    private String userFirstName;
    private String userLastName;
    private String userEMail;
    private String cardNumber;
    private String cardHolder;




}
