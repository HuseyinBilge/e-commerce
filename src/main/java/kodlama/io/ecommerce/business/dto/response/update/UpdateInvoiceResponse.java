package kodlama.io.ecommerce.business.dto.response.update;

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
public class UpdateInvoiceResponse {
    private UUID id;
    private LocalDateTime creationDate;
    private double amount;
    private String userFirstName;
    private String userLastName;
    private String userEMail;
    private String cardNumber;
    private String cardHolder;
}
