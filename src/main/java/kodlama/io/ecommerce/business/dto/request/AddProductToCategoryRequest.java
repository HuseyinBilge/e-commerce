package kodlama.io.ecommerce.business.dto.request;

import kodlama.io.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddProductToCategoryRequest {
    private UUID categoryId;
    private Product product;
}
