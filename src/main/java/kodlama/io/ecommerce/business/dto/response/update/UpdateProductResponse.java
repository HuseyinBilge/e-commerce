package kodlama.io.ecommerce.business.dto.response.update;

import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.enums.Activeness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductResponse {
    private UUID id;
    private String name;

    private double price;
    private String description;
    private Activeness activeness;
    private int stockAmount;

    private UUID categoryId;
    private String categoryName;


}
