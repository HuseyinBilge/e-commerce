package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {
    private final SaleService service;

    @PostMapping
    public CreateSaleResponse add(CreateSaleRequest request){
        return service.add(request);
    }
}
