package webshop.dto;

import java.util.Map;

public class ProductOrderedEventDTO {
    private Map<String ,Integer> orderedProductsMap;

    public ProductOrderedEventDTO(){}
    public ProductOrderedEventDTO(Map<String,Integer> orderedProductsMap){
        this.orderedProductsMap = orderedProductsMap;
    }

    public Map<String, Integer> getOrderedProductsMap() {
        return orderedProductsMap;
    }

    @Override
    public String toString() {
        return "ProductOrderedEventDTO{" +
                "orderedProductsMap=" + orderedProductsMap +
                '}';
    }
}
