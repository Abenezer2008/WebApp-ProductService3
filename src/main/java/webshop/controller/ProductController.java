package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.dto.ProductDTO;
import webshop.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> registerProduct(@RequestBody ProductDTO productDTO){
        ProductDTO productDTO1 = productService.add(productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.OK);
    }

    @PutMapping("/products/{productNumber}")
    public ResponseEntity<?> updateProduct(@PathVariable String productNumber,@RequestBody ProductDTO productDTO){
        ProductDTO productDTO1 = productService.update(productNumber,productDTO);
        return new ResponseEntity<>(productDTO1,HttpStatus.OK);
    }

    @DeleteMapping("/products/{productNumber}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productNumber){
        productService.remove(productNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT,HttpStatus.OK);
    }

    @GetMapping("/products/{productNumber}")
    public ResponseEntity<?> getProduct(@PathVariable String productNumber){
        ProductDTO productDTO = productService.getProduct(productNumber);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @GetMapping("/products/{productNumber}/stock")
    public Integer getProductQuantityStock(@PathVariable String productNumber){
        ProductDTO productDTO = productService.getProduct(productNumber);
        Integer quantity = productDTO.getNumberInStock();
        return quantity;
    }

}
