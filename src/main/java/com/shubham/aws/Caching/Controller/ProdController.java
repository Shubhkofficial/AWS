package com.shubham.aws.Caching.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.shubham.aws.Caching.Entity.Product;
import com.shubham.aws.Caching.Repository.ProductDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@ResponseBody
public class ProdController {

    @Autowired
    private ProductDAO productDAO;

    @PostMapping(name = "/product")
    public Product save(@RequestBody Product product) {
        return productDAO.save(product);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @GetMapping(name="/getProductById/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}, produces = {MediaType.ALL_VALUE})
    @Cacheable(key = "#id", value = "Product")
    public Object getById(@PathVariable long id) {
        return productDAO.findProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        return productDAO.delroductById(id);
    }

}
