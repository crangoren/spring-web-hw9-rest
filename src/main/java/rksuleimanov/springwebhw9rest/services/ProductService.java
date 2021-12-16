package rksuleimanov.springwebhw9rest.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import rksuleimanov.springwebhw9rest.dto.ProductDto;
import rksuleimanov.springwebhw9rest.entities.Product;
import rksuleimanov.springwebhw9rest.repositories.ProductRepository;
import rksuleimanov.springwebhw9rest.repositories.specifications.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<Product> find(Integer page, Integer minPrice, Integer maxPrice, String titlePart) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecification.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }

    @Transactional
    public void changePrice(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new rksuleimanov.springwebhw9rest.exceptions.ResourceNotFoundException("Unable to change product's price, product not found " + productId));
        product.setPrice(product.getPrice() + delta);
        productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

}
