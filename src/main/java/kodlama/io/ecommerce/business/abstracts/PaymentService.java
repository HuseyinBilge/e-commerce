package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentForSaleWithRegisteredCreditCardRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import kodlama.io.ecommerce.entities.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();

    GetPaymentResponse getById(UUID id);

    CreatePaymentResponse add(CreatePaymentRequest request);

    UpdatePaymentResponse update(UUID  id, UpdatePaymentRequest request);

    void delete(UUID  id);
    Payment paymentForSaleWithRegisteredCreditCard(CreatePaymentForSaleWithRegisteredCreditCardRequest request);
    //If user doesn't have a registered credit card, must add one first

}
