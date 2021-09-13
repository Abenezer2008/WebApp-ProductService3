package webshop.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webshop.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String > {}
