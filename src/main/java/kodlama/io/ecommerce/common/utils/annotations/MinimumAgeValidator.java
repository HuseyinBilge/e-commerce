package kodlama.io.ecommerce.common.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class MinimumAgeValidator implements ConstraintValidator<MinimumAge,Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int minimumAge = 18;
        int currentYear = Year.now().getValue();
        int minimumBirthYear = currentYear-minimumAge;
        return minimumBirthYear>value;
    }
}
