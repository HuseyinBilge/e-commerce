package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.request.AddProductToCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.Activeness;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final CategoryService categoryService;


    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setCategory(mapper.map(categoryService.getById(request.getCategoryId()), Category.class));
        product.setId(null);
        productRepository.save(product);

        return mapper.map(getById(product.getId()),CreateProductResponse.class);
    }

    @Override
    public void delete(UUID  id) {
        productRepository.deleteById(id);
    }

    @Override
    public UpdateProductResponse update(UUID  id, UpdateProductRequest request) {
        Product product = mapper.map(request,Product.class);
        product.setId(id);
        product.setCategory(mapper.map(categoryService.getById(request.getCategoryId()), Category.class));
        productRepository.save(product);
        UpdateProductResponse response = mapper.map(product,UpdateProductResponse.class);
        return response;
    }

    @Override
    public List<GetAllProductsResponse> getAll(boolean showPassiveProducts) {
        if(showPassiveProducts)
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();
        else return filterByActiveness();
    }

    @Override
    public GetProductResponse getById(UUID  id) {
       Product product = productRepository.findById(id).orElseThrow();
       GetProductResponse response = mapper.map(product,GetProductResponse.class);
        return response;
    }

    @Override
    public void activenessChanger(UUID  id, Activeness activeness) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setId(id);
        product.setActiveness(activeness);
        productRepository.save(product);
    }

    @Override
    public List<CreateProductResponse> getProductsByCategory(UUID categoryId) {
       return productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(product -> mapper.map(product, CreateProductResponse.class))
                .toList();
    }

    private List<GetAllProductsResponse> filterByActiveness(){
       return productRepository.findAllByActiveness(Activeness.ACTİVE)
               .stream()
               .map(product -> mapper.map(product, GetAllProductsResponse.class))
               .toList();
    }
}

