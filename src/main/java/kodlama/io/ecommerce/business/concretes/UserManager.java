package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CreditCardService;
import kodlama.io.ecommerce.business.abstracts.UserCheckService;
import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.request.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateUserRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateUserResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllUsersResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetUserResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateUserResponse;
import kodlama.io.ecommerce.entities.CreditCard;
import kodlama.io.ecommerce.entities.User;
import kodlama.io.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final UserCheckService userCheckService;

    @Override
    public CreateUserResponse add(CreateUserRequest request) throws Exception {
        User user = mapper.map(request, User.class);
        // TODO: Bussines ruleslar yapılacak
        checkIfUserIsRealPerson(user);
        user.setUsername(user.getEMail());
        repository.save(user);
        return mapper.map(user, CreateUserResponse.class);
    }

    @Override
    public UpdateUserResponse update(UUID  id, UpdateUserRequest request) throws Exception {
        User user = mapper.map(request, User.class);
        user.setId(id);
        checkIfUserIsRealPerson(user);
        repository.save(user);
        return mapper.map(user, UpdateUserResponse.class);
    }

    @Override
    public GetUserResponse getById(UUID  id) {
        User user = repository.findById(id).orElseThrow();
        GetUserResponse response = mapper.map(user,GetUserResponse.class);
        return response;
    }

    @Override
    public List<GetAllUsersResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class))
                .toList();
    }

    @Override
    public void delete(UUID  id) {
        // It will delete all user credit cars data too
        User user = repository.findById(id).orElseThrow();

        repository.deleteById(id);
    }
    private void checkIfUserIsRealPerson(User user) throws Exception {
        if(!userCheckService.checkIfRealPerson(user))
            // TODO: ingilizceye çevirilem
            // TODO: Exception handler yazalım
            throw new RuntimeException("Kullanıcı doğrulanamadı");
    }
}
