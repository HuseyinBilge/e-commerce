package kodlama.io.ecommerce.common.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreditCardExpirationYearValidator .class)
public @interface CreditCardExpirationYear {
    String message() default "Credit card expiration year must be at least current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
