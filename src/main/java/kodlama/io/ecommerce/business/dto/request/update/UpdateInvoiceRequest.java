package kodlama.io.ecommerce.business.dto.request.update;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateInvoiceRequest {

    private LocalDateTime creationDate;

    @Min(0)
    private double amount;
    private String userFirstName;
    private String userLastName;
    private String userEMail;
    private String cardNumber;
    private String cardHolder;
}
