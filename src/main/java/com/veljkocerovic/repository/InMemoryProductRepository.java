package com.veljkocerovic.repository;

import com.veljkocerovic.Product;

import java.util.List;

public class InMemoryProductRepository implements ProductRepository {
    @Override
    public void saveAll(List<Product> products) {
        // Integrate storage
        products.forEach(product -> System.out.println(product.toString()));
    }
}