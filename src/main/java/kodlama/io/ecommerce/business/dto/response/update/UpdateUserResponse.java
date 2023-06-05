package kodlama.io.ecommerce.business.dto.response.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private long nationalIdentity;
    private int birthYear;
}
