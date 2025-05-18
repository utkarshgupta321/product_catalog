package com.example.catalog.event;

import com.example.catalog.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductEventPublisher {

    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    public ProductEventPublisher(PubSubTemplate pubSubTemplate,
                                 ObjectMapper objectMapper,
                                 @Value("${spring.cloud.gcp.pubsub.topic}") String topicName) {
        this.pubSubTemplate = pubSubTemplate;
        this.objectMapper = objectMapper;
        this.topicName = topicName;
    }

    public void publishProductCreated(Product product) {
        publishEvent("PRODUCT_CREATED", product);
    }

    public void publishProductUpdated(Product product) {
        publishEvent("PRODUCT_UPDATED", product);
    }

    private void publishEvent(String eventType, Product product) {
        try {
            Map<String, Object> event = new HashMap<>();
            event.put("eventType", eventType);
            event.put("timestamp", Instant.now().toString());
            event.put("product", product);

            String message = objectMapper.writeValueAsString(event);
            pubSubTemplate.publish(topicName, message);
            System.out.println("Event published to Pub/Sub: " + message);
        } catch (Exception e) {
            System.err.println("Failed to publish event: " + e.getMessage());
        }
    }
}
