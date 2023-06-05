package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateUserRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateUserResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllUsersResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetUserResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateUserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    CreateUserResponse add(CreateUserRequest request) throws Exception;
    UpdateUserResponse update(UUID id, UpdateUserRequest request) throws Exception;
    GetUserResponse getById(UUID  id);
   List<GetAllUsersResponse> getAll();
    void delete(UUID  id);
}
