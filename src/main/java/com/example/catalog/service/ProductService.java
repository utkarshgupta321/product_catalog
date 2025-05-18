package com.example.catalog.service;

import com.example.catalog.event.ProductEventPublisher;
import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductEventPublisher publisher;

    public ProductService(ProductRepository repository, ProductEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public Product addProduct(Product product) {
        Product saved = repository.save(product);
        publisher.publishProductCreated(saved);
        return saved;
    }

    public Product updateProduct(UUID id, Product updatedProduct) {
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setAvailableStock(updatedProduct.getAvailableStock());
            product.setLastUpdated(updatedProduct.getLastUpdated());
            Product saved = repository.save(product);
            publisher.publishProductUpdated(saved);
            return saved;
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
