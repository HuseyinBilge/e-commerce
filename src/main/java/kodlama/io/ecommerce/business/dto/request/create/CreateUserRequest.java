package kodlama.io.ecommerce.business.dto.request.create;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kodlama.io.ecommerce.common.constants.Regex;
import kodlama.io.ecommerce.common.utils.annotations.MinimumAge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @Length(min = 3,max = 50,message = "First name must be between 3 to 50 characters!")
    private String firstName;
    @Length(min = 3,max = 50,message = "Last name must be between 3 to 50 characters!")
    private String lastName;

    @Email//jakarta
    @Pattern(regexp = Regex.EMail, message = "This can't be an e-mail address")//custom regex
    //This regular expression validates email addresses that meet the following criteria:
    //
    //The email address must start with one or more letters, numbers, dots, underscores, percent signs, plus signs, or hyphens.
    //After the initial characters, there must be an "@" symbol.
    //After the "@" symbol, there must be one or more letters, numbers, dots, or hyphens, followed by a dot.
    //Finally, there must be two or more letters at the end of the email address.
    //Here are some examples of valid email addresses that match this regular expression:
    //
    //example123@email.com
    //john.doe+test@example.co.uk
    //jennifer_smith123@example.com
    //And here are some examples of invalid email addresses that do not match this regular expression:
    //
    //@example.com (missing initial characters)
    //example@com (missing dot after "@" symbol)
    //example@com. (only one letter after last dot)
    //@org.hibernate.validator.constraints.UniqueElements(message = "E-mail is already used!")
    private String eMail;
    @Pattern(regexp =Regex.Password)
    //"Start with a string that contains AT LEAST one digit (?=.\d),
    // one lowercase letter (?=.[a-z]),
    // one uppercase letter (?=.[A-Z]),
    // one special character from !@#$%^&()+\-={}\[\]\|:;"'<>,.?\/ (?=.[!@#$%^&()+\-={}\[\]\|:;"'<>,.?\/]
    // and has a length between 8 and 20 characters ({8,20}).
    // It cannot contain any whitespace characters (?!.*\s)."
    private String password;
    @Digits(integer = 11,fraction = 0,message = "National identity must have 11 digits!" )
    private long nationalIdentity;

    @Min(1920)
    @MinimumAge
    private int birthYear;

}

