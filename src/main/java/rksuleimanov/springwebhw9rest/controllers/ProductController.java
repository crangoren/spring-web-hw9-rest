package rksuleimanov.springwebhw9rest.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import rksuleimanov.springwebhw9rest.dto.ProductDto;
import rksuleimanov.springwebhw9rest.entities.Product;
import rksuleimanov.springwebhw9rest.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
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
        return productService.find(page, minPrice, maxPrice, titlePart).map(
                product -> new ProductDto(product));
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id){
        return productService.findById(id);
    }

//    @PutMapping("/{id}")
//    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta){
//        productService.changePrice(productId, delta);
//    }


    @PostMapping
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }


//    @GetMapping("/products/price_between")
//    public List<Product> findProductsByPriceBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
//        return productService.findByPriceBetween(min, max);
//    }
//
//    @GetMapping("/products/filter")
//    public List<Product> filterByPriceBetween(@RequestParam(defaultValue = "0") Integer min, Integer max) {
//        return productService.filterByPriceBetween(min, max);
//    }

}