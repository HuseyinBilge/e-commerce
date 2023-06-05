package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.validation.constraints.Min;
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
public class CreateSaleRequest {

    @Min(0)
    private int discountRate;
    private UUID userId;
    private List<UUID> productsId;
    private UUID creditCardId;
}
