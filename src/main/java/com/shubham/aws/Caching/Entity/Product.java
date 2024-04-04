package com.shubham.aws.Caching.Entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product implements Serializable{
    @Id
    private long id;
    private String name;
    private int quantity;
    private double price;

}
