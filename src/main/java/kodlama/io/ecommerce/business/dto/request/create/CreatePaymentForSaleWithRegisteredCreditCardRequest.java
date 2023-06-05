package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import javax.print.attribute.IntegerSyntax;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePaymentForSaleWithRegisteredCreditCardRequest {

    @Min(0)
    private double totalPrice;
    private UUID creditCardId;


}
