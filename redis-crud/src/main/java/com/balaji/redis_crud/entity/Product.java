package com.balaji.redis_crud.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product implements Serializable {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
}
