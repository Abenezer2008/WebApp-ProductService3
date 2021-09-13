package webshop.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import webshop.dto.ProductOrderedEventDTO;
import webshop.service.ProductService;

@Service
public class Receiver {
    @Autowired
    private ProductService productService;

    @KafkaListener(topics = {"productOrdered"})
    public void receiveProductOrderedEvent(@Payload ProductOrderedEventDTO eventDTO){
        System.out.println("Received Message : " + eventDTO);
        productService.handle(eventDTO);
    }
}
