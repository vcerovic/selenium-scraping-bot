package com.veljkocerovic;

import com.veljkocerovic.config.WebDriverConfig;
import com.veljkocerovic.repository.InMemoryProductRepository;
import com.veljkocerovic.repository.ProductRepository;
import org.openqa.selenium.WebDriver;

public class Main {

    public static void main(String[] args) {
        WebDriver webDriver = WebDriverConfig.getWebDriver();
        ProductRepository productRepository = new InMemoryProductRepository();

        ProductScraper productScraper = new ProductScraper(webDriver, productRepository);

        productScraper.scrape();
    }
}