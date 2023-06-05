package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.request.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateUserRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateUserResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllUsersResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetUserResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UserService service;

    @GetMapping
    public List<GetAllUsersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public GetUserResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@Valid @RequestBody CreateUserRequest request) throws Exception {
        return service.add(request);
    }

    @PutMapping("{id}")
    public UpdateUserResponse update(@PathVariable UUID  id, @Valid @RequestBody UpdateUserRequest request) throws Exception {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID  id) {
        service.delete(id);
    }
}
