package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.entities.User;

public interface UserCheckService {
    boolean checkIfRealPerson(User user) throws Exception;
}
