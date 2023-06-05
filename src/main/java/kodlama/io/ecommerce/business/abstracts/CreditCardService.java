package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCreditCardRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCreditCardResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCreditCardsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCreditCardResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCreditCardResponse;

import java.util.List;
import java.util.UUID;

public interface CreditCardService {
    CreateCreditCardResponse add (CreateCreditCardRequest request);
    UpdateCreditCardResponse update(UUID id, UpdateCreditCardRequest request);
    void delete(UUID  id);
    List<GetAllCreditCardsResponse> getAll();
    GetCreditCardResponse getById(UUID  id);
}
