package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.CreditCardService;
import kodlama.io.ecommerce.business.dto.request.create.CreateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCreditCardResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCreditCardsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCreditCardResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCreditCardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/creditcards")
@AllArgsConstructor

public class CreditCardsController {
    private final CreditCardService service;

    @GetMapping
    public List<GetAllCreditCardsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public GetCreditCardResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCreditCardResponse add(@Valid @RequestBody CreateCreditCardRequest request) {
        return service.add(request);
    }

    @PutMapping("{id}")
    public UpdateCreditCardResponse update(@PathVariable UUID  id, @Valid @RequestBody UpdateCreditCardRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID  id) {
        service.delete(id);
    }
}
