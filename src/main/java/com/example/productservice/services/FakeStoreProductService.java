package com.example.productservice.services;

import org.springframework.stereotype.Service;

import com.example.productservice.models.Product;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    @Override
    public String getProductById(long id) {
        return "Here is the product id: " + id;
        // return "Hello";
    }
    
}
