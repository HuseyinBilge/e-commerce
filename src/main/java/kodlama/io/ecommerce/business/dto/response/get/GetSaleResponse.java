package kodlama.io.ecommerce.business.dto.response.get;

import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetSaleResponse {
    private UUID id;
    private double totalPrice;
    private int discountRate;
    private UUID userId;
    private List<Product> products;
    private Invoice invoice;
}
