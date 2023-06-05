package kodlama.io.ecommerce.common.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class CreditCardExpirationYearValidator implements ConstraintValidator<CreditCardExpirationYear,Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int currentYear = Year.now().getValue();
        return value>=currentYear;
    }
}
