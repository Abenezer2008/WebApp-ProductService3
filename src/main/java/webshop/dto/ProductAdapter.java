package webshop.dto;

import webshop.domain.Product;

public class ProductAdapter {
    public static ProductDTO toDTO(Product product){
        return new ProductDTO(
                product.getProductNumber(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getNumberInStock());
    }

    public static Product fromDTO(ProductDTO productDTO){
        return new Product(
                productDTO.getProductNumber(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getNumberInStock());
    }
}
