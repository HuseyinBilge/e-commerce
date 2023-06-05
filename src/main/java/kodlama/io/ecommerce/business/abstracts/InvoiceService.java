package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoiceResponse> getAll();
    GetInvoiceResponse getById(UUID id);
    CreateInvoicesResponse add(CreateInvoiceRequest request);

    UpdateInvoiceResponse update(UUID  id, UpdateInvoiceRequest request);

    void delete (UUID  id);
}
