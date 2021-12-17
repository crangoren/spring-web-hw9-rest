package rksuleimanov.springwebhw9rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import rksuleimanov.springwebhw9rest.dto.ProductDto;
import rksuleimanov.springwebhw9rest.entities.Product;
import rksuleimanov.springwebhw9rest.services.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public Page<ProductDto> findAllProds(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "name_part", required = false) String titlePart

    ){
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(page, minPrice, maxPrice, titlePart).map(
                product -> new ProductDto(product));
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id){
        Product product = new Product();
        product.setId(id);
        return productService.findById(id);
    }

//    @PutMapping("/{id}")
//    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta){
//        productService.changePrice(productId, delta);
//    }


    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }


}