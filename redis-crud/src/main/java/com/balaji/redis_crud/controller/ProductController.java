package com.balaji.redis_crud.controller;

import com.balaji.redis_crud.entity.Product;
import com.balaji.redis_crud.repository.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    public static final String HASH_KEY_PRODUCT = "Product";
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Product> products=productDao.findAll();
        if(products.isEmpty()) {
            return new ResponseEntity<>("Products Empty!", HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>( products, HttpStatus.OK);
        }

    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productDao.save(product);
        return new ResponseEntity<>("Successfully Saved!",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productDao.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#Id", value = HASH_KEY_PRODUCT, condition = "#id!=null")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updateProduct=productDao.findById(id);
        if(updateProduct.getId()!=null){
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            productDao.save(updateProduct);
        }else {
            return new ResponseEntity<>("Update Failed!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successfully Updated!",HttpStatus.OK);

    }
    @CacheEvict(key = "#id", value = HASH_KEY_PRODUCT)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productDao.delete(id);
        return new ResponseEntity<>("Successfully Deleted!", HttpStatus.OK);
    }


}
