package com.example.catalog.service;

import com.example.catalog.event.ProductEventPublisher;
import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService service;
    private ProductRepository repository;
    private ProductEventPublisher publisher;

    @BeforeEach
    void setup() {
        repository = mock(ProductRepository.class);
        publisher = mock(ProductEventPublisher.class);
        service = new ProductService(repository, publisher);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(new BigDecimal("99.99"));

        when(repository.save(any(Product.class))).thenReturn(product);

        Product saved = service.addProduct(product);
        assertNotNull(saved);
        verify(repository, times(1)).save(product);
        verify(publisher, times(1)).publishProductCreated(product);
    }

    @Test
    void testGetAllProducts() {
        List<Product> list = Arrays.asList(new Product(), new Product());
        when(repository.findAll()).thenReturn(list);

        List<Product> result = service.getAllProducts();
        assertEquals(2, result.size());
    }
}
