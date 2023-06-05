package kodlama.io.ecommerce.adapters;

import kodlama.io.ecommerce.business.abstracts.UserCheckService;


import kodlama.io.ecommerce.core.mernis.DDEKPSPublicSoap;
import kodlama.io.ecommerce.entities.User;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter implements UserCheckService {
    @Override
    public boolean checkIfRealPerson(User user) throws Exception {
        DDEKPSPublicSoap publicSoap = new DDEKPSPublicSoap();
        return publicSoap.TCKimlikNoDogrula(user.getNationalIdentity(), user.getFirstName(), user.getLastName(), user.getBirthYear());
    }
}
