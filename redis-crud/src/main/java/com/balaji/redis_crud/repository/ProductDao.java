package com.balaji.redis_crud.repository;

import com.balaji.redis_crud.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ProductDao {
    public static final String HASH_KEY_PRODUCT = "Product";

    private final RedisTemplate<String, Object> redisTemplate;

    ProductDao(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public Product save(Product product){
        System.out.println("saved!");
        redisTemplate.opsForHash().put(HASH_KEY_PRODUCT,product.getId().toString(),product);
        return product;
    }

    public List<Product> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY_PRODUCT)
                .stream()
                .map(product->(Product) product)
                .collect(Collectors.toList());
    }

    public Product findById(Long id){
        System.out.println("findById!");
        return (Product) redisTemplate.opsForHash().get(HASH_KEY_PRODUCT,id.toString());
    }

    public void delete(Long id){
        redisTemplate.opsForHash().delete(HASH_KEY_PRODUCT,id.toString());
    }
}
