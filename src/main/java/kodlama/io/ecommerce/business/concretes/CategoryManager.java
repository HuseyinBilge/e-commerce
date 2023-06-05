package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.request.AddProductToCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;


    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        Category category = mapper.map(request, Category.class);

      categoryRepository.save(category);
        return mapper.map(category,CreateCategoryResponse.class);
    }

    @Override
    public void delete(UUID  id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public UpdateCategoryResponse update(UUID  id, UpdateCategoryRequest request) {
        Category Category = mapper.map(request,Category.class);
        Category.setId(id);
        categoryRepository.save(Category);
        UpdateCategoryResponse response = mapper.map(Category,UpdateCategoryResponse.class);
        return response;
    }

    @Override
    public List<GetAllCategoriesResponse> getAll() {
            return categoryRepository.findAll()
                    .stream()
                    .map(category -> mapper.map(category, GetAllCategoriesResponse.class))
                    .toList();
    }

    @Override
    public GetCategoryResponse getById(UUID  id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        GetCategoryResponse response = mapper.map(category,GetCategoryResponse.class);
        return response;
    }


}
