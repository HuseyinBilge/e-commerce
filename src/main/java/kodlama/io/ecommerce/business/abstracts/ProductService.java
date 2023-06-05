package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;
import kodlama.io.ecommerce.entities.enums.Activeness;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    CreateProductResponse add(CreateProductRequest request);
    void delete(UUID id);
    UpdateProductResponse update(UUID  id, UpdateProductRequest request);
    List<GetAllProductsResponse> getAll(boolean showPassiveProducts);
    GetProductResponse getById(UUID  id);

    void activenessChanger(UUID  id, Activeness activeness);

    List<CreateProductResponse> getProductsByCategory(UUID categoryId);
}
