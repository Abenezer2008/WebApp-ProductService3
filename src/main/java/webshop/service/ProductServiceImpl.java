package webshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.domain.Product;
import webshop.dto.ProductAdapter;
import webshop.dto.ProductDTO;
import webshop.dto.ProductOrderedEventDTO;
import webshop.respository.ProductRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO add(ProductDTO productDTO) {
        logger.info("Calling add product");
        Product product = ProductAdapter.fromDTO(productDTO);
        productRepository.save(product);
        logger.info("Adding new product");
        return ProductAdapter.toDTO(product);
    }

    @Override
    public void remove(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO update(String id, ProductDTO productDTO) {
        logger.info("Calling update");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = ProductAdapter.fromDTO(productDTO);
            productRepository.save(product);
            logger.info("Updating product");
            return ProductAdapter.toDTO(product);
        }
        logger.error("Product update with invalid id");
        return null;
    }

    @Override
    public ProductDTO addToStock(String id, int addedQuantity) {
        logger.info("Calling add To Stock");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setNumberInStock(product.getNumberInStock() + addedQuantity);
            productRepository.save(product);
            logger.info("Adding quantity to stock");
            return ProductAdapter.toDTO(product);
        }
        logger.error("Add to stock with invalid id");
        return null;
    }

    @Override
    public ProductDTO removeFromStock(String id, int soldQuantity) {
        logger.info("Calling remove from Stock");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setNumberInStock(product.getNumberInStock() - soldQuantity);
            productRepository.save(product);
            logger.info("Removing quantity from stock");
            return ProductAdapter.toDTO(product);
        }
        logger.error("Remove from stock with invalid id");
        return null;
    }

    @Override
    public void handle(ProductOrderedEventDTO productOrderedEventDTO) {
        logger.info("Calling handle");
        Map<String,Integer> productMap = productOrderedEventDTO.getOrderedProductsMap();
        for (String key: productMap.keySet()){
            removeFromStock(key,productMap.get(key));
        }
        logger.info("Handling Product Ordered Event");
    }

    @Override
    public ProductDTO getProduct(String id) {
        logger.info("Calling get product");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            logger.info("Getting product");
            return ProductAdapter.toDTO(optionalProduct.get());
        }
        logger.error("Invalid Id.");
        return null;
    }
}
