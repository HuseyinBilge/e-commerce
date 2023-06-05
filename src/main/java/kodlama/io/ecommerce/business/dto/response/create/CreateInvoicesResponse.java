package kodlama.io.ecommerce.business.dto.response.create;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CreateInvoicesResponse {
    private UUID id;
    private LocalDateTime creationDate;
    private double amount;
    private String userFirstName;
    private String userLastName;
    private String userEMail;
    private String cardNumber;
    private String cardHolder;

    @Override
    public String toString( ){
        return "Invoice Date :" + creationDate.getDayOfMonth()+" " + creationDate.getMonth()+" "+ creationDate.getYear()
             +"\n"   +"Sale Total Amaount: "+ amount
             +"\n"   + "Customer Name: " + userFirstName+" " + userLastName
             +"\n"   + "Credit Card Number of Payment: " + cardNumber
             +"\n"   + "Credit Card Holder: " + cardHolder;
    }
}
