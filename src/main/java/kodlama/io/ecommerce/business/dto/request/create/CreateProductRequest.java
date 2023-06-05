package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.enums.Activeness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequest {

    @Length(min = 3,max = 50,message = "Product name must be between 10 to 500 characters!")
    @NotBlank
    private String name;
    @Min(value = 1,message = "Price Can Not Be Equal or Less Than Zero.")
    private double price;
    @Length(min = 10,max = 500, message = "Description must be between 10 to 500 characters!")
    private String description;

    @Min(value = 0,message = "Stock Amount Can Not Be Less Than Zero.")
    private int stockAmount;
    private Activeness activeness;
    private UUID categoryId;
}
