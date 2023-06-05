package kodlama.io.ecommerce.business.dto.response.get;

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
public class GetAllProductsResponse {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private Activeness activeness;
    private int stockAmount;
    private UUID categoryId;
    private String categoryName;

}
