package com.veljkocerovic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCreator {

    public static Product createProduct(WebElement productEl) {
        String title = productEl
                .findElement(By.cssSelector("a.title"))
                .getText();

        String link = productEl
                .findElement(By.cssSelector("a.title"))
                .getAttribute("href");

        String desc = productEl
                .findElement(By.cssSelector("p.description"))
                .getText();

        double price = Double.parseDouble(productEl
                .findElement(By.cssSelector(".price"))
                .getText().substring(1)
        );

        int reviewCount = Integer.parseInt(productEl
                .findElement(By.cssSelector(".review-count"))
                .getText().split(" ")[0]
        );

        int rating = productEl
                .findElements(By.cssSelector(".ws-icon-star"))
                .size();

        return Product.builder()
                .title(title)
                .link(link)
                .desc(desc)
                .price(price)
                .reviewCount(reviewCount)
                .rating(rating)
                .build();
    }
}