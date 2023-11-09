package com.veljkocerovic;

import com.veljkocerovic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
public class ProductScraper {
    public static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 5;
    public static final int DELAY_BETWEEN_PAGES_MILLIS = 1500;
    public static final String TARGET_WEBSITE = "https://webscraper.io/test-sites/e-commerce/ajax/computers/laptops";

    private final WebDriver driver;
    private final ProductRepository productRepository;

    public void scrape() {
        driver.get(TARGET_WEBSITE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS));

        while (shouldContinueScraping()) {
            List<WebElement> webProducts = wait.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(By.cssSelector(".product-wrapper")));

            List<Product> products = webProducts.stream()
                    .map(ProductCreator::createProduct)
                    .toList();

            productRepository.saveAll(products);

            nextPage();
        }

        driver.quit();
    }

    private boolean shouldContinueScraping() {
        WebElement nextBtn = driver.findElement(By.cssSelector("button.next"));
        return nextBtn.getAttribute("disabled") == null;
    }

    private void nextPage() {
        WebElement nextBtn = driver.findElement(By.cssSelector("button.next"));
        Actions actions = new Actions(driver);

        actions.moveToElement(nextBtn).click().perform();

        try {
            Thread.sleep(DELAY_BETWEEN_PAGES_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
