package com.veljkocerovic.repository;

import com.veljkocerovic.Product;

import java.util.List;

public interface ProductRepository {
    void saveAll(List<Product> products);
}