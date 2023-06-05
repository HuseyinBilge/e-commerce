package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.AddProductToCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CreateCategoryResponse add(CreateCategoryRequest request);
    void delete(UUID id);
    UpdateCategoryResponse update(UUID  id, UpdateCategoryRequest request);
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(UUID  id);



}
