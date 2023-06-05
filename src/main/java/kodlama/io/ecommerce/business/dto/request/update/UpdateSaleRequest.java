package kodlama.io.ecommerce.business.dto.request.update;

import jakarta.validation.constraints.Min;
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
public class UpdateSaleRequest {
    private List<UUID> productsId;
    private UUID invoiceId;
    @Min(0)
    private int discountRate;
    private UUID saleId;
    private UUID creditCardId;
}
