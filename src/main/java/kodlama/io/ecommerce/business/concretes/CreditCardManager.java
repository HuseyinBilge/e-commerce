package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CreditCardService;
import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.request.create.CreateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCreditCardResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCreditCardsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCreditCardResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCreditCardResponse;
import kodlama.io.ecommerce.entities.CreditCard;
import kodlama.io.ecommerce.repository.CreditCardRepository;
import kodlama.io.ecommerce.entities.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardManager implements CreditCardService {

        private final CreditCardRepository repository;
        private final ModelMapper mapper;
        private final UserService userService;
    @Override
    public CreateCreditCardResponse add(CreateCreditCardRequest request) {
        CreditCard creditCard = mapper.map(request,CreditCard.class);

        creditCard.setUser(mapper.map(userService.getById(request.getUserId()), User.class));
        repository.save(creditCard);
        CreateCreditCardResponse response = mapper.map(creditCard,CreateCreditCardResponse.class);
        return response;
    }

    @Override
    public UpdateCreditCardResponse update(UUID  id, UpdateCreditCardRequest request) {
        CreditCard creditCard = mapper.map(request,CreditCard.class);
        creditCard.setId(id);
        repository.save(creditCard);
        UpdateCreditCardResponse response = mapper.map(creditCard,UpdateCreditCardResponse.class);
        return response;
    }

    @Override
    public void delete(UUID  id) {
        repository.deleteById(id);
    }

    @Override
    public List<GetAllCreditCardsResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(creditCard -> mapper.map(creditCard, GetAllCreditCardsResponse.class))
                .toList();
    }

    @Override
    public GetCreditCardResponse getById(UUID  id) {
        CreditCard creditCard = repository.findById(id).orElseThrow();
        GetCreditCardResponse response = mapper.map(creditCard,GetCreditCardResponse.class);
        return response;
    }
}
