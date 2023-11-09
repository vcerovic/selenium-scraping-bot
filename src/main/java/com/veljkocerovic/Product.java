package com.veljkocerovic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private String title;
    private String link;
    private String desc;
    private double price;
    private int rating;
    private int reviewCount;
}