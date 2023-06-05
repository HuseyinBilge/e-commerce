package kodlama.io.ecommerce.entities;

import jakarta.persistence.*;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentForSaleWithRegisteredCreditCardRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double totalPrice;
    private int discountRate;
    @ManyToMany
    private List<Product> products;
    @ManyToOne
    private User user;
    @OneToOne
    private Invoice invoice;

}