package webshop.service;

import webshop.dto.ProductDTO;
import webshop.dto.ProductOrderedEventDTO;

public interface ProductService {
    ProductDTO add(ProductDTO productDTO);
    void remove(String id);
    ProductDTO update(String id, ProductDTO productDTO);
    ProductDTO addToStock(String id, int addedQuantity);
    ProductDTO removeFromStock(String id, int soldQuantity);
    void handle(ProductOrderedEventDTO productOrderedEventDTO);
    ProductDTO getProduct(String id);
}
