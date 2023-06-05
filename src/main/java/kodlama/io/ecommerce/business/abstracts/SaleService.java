package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateSaleResponse;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(UUID id);
    CreateSaleResponse add(CreateSaleRequest request);
    UpdateSaleResponse update (UUID  id, UpdateSaleRequest request);
    void delete (UUID  id);
}
