package com.demoqa.stakhanovda;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class WebDriverInit {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    @BeforeAll
    private static void setUp() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    @AfterAll
    private static void quit() {
        driver.quit();
    }
}
