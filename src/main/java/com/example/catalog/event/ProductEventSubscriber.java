package com.example.catalog.event;

import com.example.catalog.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

@Component
public class ProductEventSubscriber {

    private final ObjectMapper objectMapper;

    public ProductEventSubscriber(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Description("product-events-sub")
    public void onMessage(String message) {
        System.out.println("[Subscriber] Received message: " + message);

        try {
            // Parse message into JSON
            JsonNode eventJson = objectMapper.readTree(message);
            String eventType = eventJson.get("eventType").asText();
            JsonNode productNode = eventJson.get("product");

            // Convert "product" field into a Product object
            Product product = objectMapper.treeToValue(productNode, Product.class);

            System.out.printf("Event Type: %s\n", eventType);
            System.out.printf("Product: %s\n", product);

        } catch (Exception e) {
            System.err.println("Failed to process incoming message: " + e.getMessage());
        }
    }
}
