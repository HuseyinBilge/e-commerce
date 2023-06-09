package kodlama.io.ecommerce.entities;

import jakarta.persistence.*;
import kodlama.io.ecommerce.entities.enums.Activeness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private double price;
    private String description;
    private int stockAmount;
    @Enumerated(value = STRING)
    private Activeness activeness;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



}
