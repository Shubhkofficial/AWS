package com.shubham.aws.Caching.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.shubham.aws.Caching.Entity.Product;

@Repository
public class ProductDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String HASH_KEY = "Product";

    public Product save(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Object findProductById(long id) {
        System.out.println("Fteching from DB");
        return redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String delroductById(long id) {
        return "Product with id: " + redisTemplate.opsForHash().delete(HASH_KEY, id) + " deleted";
    }

}
