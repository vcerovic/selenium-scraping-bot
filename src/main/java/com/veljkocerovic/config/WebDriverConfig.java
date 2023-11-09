package com.veljkocerovic.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebDriverConfig {

    public static WebDriver getWebDriver() {
        Proxy proxy = new Proxy();

        String proxyIp = "your-proxy-address";
        int proxyPort = 8080;

        proxy.setAutodetect(false);
        proxy.setHttpProxy(String.format("%s:%d", proxyIp, proxyPort));
        proxy.setSslProxy(String.format("%s:%d", proxyIp, proxyPort));

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        chromeOptions.setCapability(CapabilityType.PROXY, proxy);

        return new ChromeDriver(chromeOptions);
    }
}