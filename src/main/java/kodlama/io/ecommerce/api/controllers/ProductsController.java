package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.request.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;
import kodlama.io.ecommerce.entities.enums.Activeness;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll(@RequestParam(defaultValue = "true")boolean showPassiveProducts){
        return service.getAll(showPassiveProducts);
    }

    @GetMapping("{id}")
    public GetProductResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@Valid @RequestBody CreateProductRequest request){
        return service.add(request);
    }

    @PutMapping("{id}")
    public UpdateProductResponse update(@PathVariable UUID  id, @Valid @RequestBody UpdateProductRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID  id){
        service.delete(id);
    }

    @PutMapping("/activeness")
    public void activenessChanger(@RequestParam UUID  id, @RequestParam("activeness")Activeness activeness){
        service.activenessChanger(id,activeness);
    }

    @GetMapping("/byCategory/{categoryId}")
    public List<CreateProductResponse> getProductsByCategory(@PathVariable UUID categoryId){
        return service.getProductsByCategory(categoryId);
    }
}
