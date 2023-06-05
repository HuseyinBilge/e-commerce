package kodlama.io.ecommerce.business.dto.request.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kodlama.io.ecommerce.common.constants.Regex;
import kodlama.io.ecommerce.common.utils.annotations.CreditCardExpirationYear;
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
public class UpdateCreditCardRequest {
    @Pattern(regexp = Regex.CreditCardNumber, message = "This is not a valid credit card number!")
    private String cardNumber;

    @Length(min = 3, max = 50, message = "Card holder must be between 3 to 50 characters!")
    private String cardHolder;

    @CreditCardExpirationYear
    private int cardExpirationYear;

    @Min(1)
    @Max(12)
    private int cardExpirationMonth;

    @Pattern(regexp = Regex.CreditCardCVV, message = "This can't be a credit card CVV!")
    private String cardCvv;

    @Min(0)
    private double balance;
    private UUID userId;
}
