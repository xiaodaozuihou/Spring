package com.spring.service;

import com.spring.model.Product;


public interface ProductService {
    Product add(Product product);
    Product get(long id);
}
