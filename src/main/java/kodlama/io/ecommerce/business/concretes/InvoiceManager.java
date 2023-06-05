package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateInvoiceResponse;
import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;


    @Override
    public CreateInvoicesResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        repository.save(invoice);
        return mapper.map(invoice, CreateInvoicesResponse.class);
    }

    @Override
    public UpdateInvoiceResponse update(UUID  id, UpdateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice,UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(UUID  id) {
            repository.deleteById(id);
    }

    @Override
    public List<GetAllInvoiceResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoiceResponse.class))
                .toList();
    }

    @Override
    public GetInvoiceResponse getById(UUID  id) {
        return mapper.map(repository.findById(id), GetInvoiceResponse.class);
    }
}
