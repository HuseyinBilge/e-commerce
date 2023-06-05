package kodlama.io.ecommerce.business.dto.response.update;

import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.entities.Product;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSaleResponse {
    private double totalPrice;
    private int discountRate;
    private UUID userId;
    private List<Product> products;
    private Invoice invoice;
}
