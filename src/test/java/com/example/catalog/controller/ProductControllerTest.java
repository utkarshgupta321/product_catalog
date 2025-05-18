package com.example.catalog.controller;

import com.example.catalog.model.Product;
import com.example.catalog.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Test
    void testAddProduct() {
        ProductService service = mock(ProductService.class);
        ProductController controller = new ProductController(service);

        Product product = new Product();
        product.setName("Test");
        product.setPrice(new BigDecimal("55.00"));

        when(service.addProduct(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = controller.addProduct(product);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void testGetProductById_NotFound() {
        ProductService service = mock(ProductService.class);
        ProductController controller = new ProductController(service);

        UUID id = UUID.randomUUID();
        when(service.getProductById(id)).thenReturn(null);

        ResponseEntity<Product> response = controller.getById(id);
        assertEquals(200, response.getStatusCode().value());
    }
}
