package com.demoqa.stakhanovda.pages;

import com.demoqa.stakhanovda.WebDriverInit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends WebDriverInit {
    private final String pageAddress = "https://demoqa.com/automation-practice-form";
    private final String mainLogoXpath = "//*[@id=\"app\"]/header/a";
    private final String mobileNumberFormXpath = "//*[@id=\"userNumber\"]";
    private final String DOBJanuary = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select/option[1]";
    private final String DOBYear = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select/option[1]";
    private final String DOBDay = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]";

    public void open() {
        driver.get(pageAddress);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(mainLogoXpath)));
    }

    public void fillUpNameSurname() {
        driver.findElement(By.id("firstName")).sendKeys("FirstName");
        driver.findElement(By.id("lastName")).sendKeys("LastName");
    }

    public void fillUpEmail() {
        driver.findElement(By.id("userEmail")).sendKeys("test@gmail.com");
    }

    public void chooseGender(String gender) {
        switch (gender) {
            case "Male" -> driver.findElement
                    (By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label")).click();
            case "Female" -> driver.findElement
                    (By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(2) > label")).click();
            default -> driver.findElement
                    (By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(3) > label")).click();
        }
    }

    public void fillUpMobileNumber() {
        driver.findElement(By.xpath(mobileNumberFormXpath)).sendKeys("1234567890");
    }

    public void chooseDateOfBirth() {
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DOBJanuary))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DOBYear))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DOBDay))).click();
    }

    public void fillUpSubjects() {
        driver.findElement(By.xpath("//*[@id=\"subjectsWrapper\"]/div[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"subjectsContainer\"]/div")));
        actions.sendKeys("English").perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"subjectsContainer\"]/div")));
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys("Comp").perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"subjectsContainer\"]/div")));
        actions.sendKeys(Keys.TAB).perform();
    }

    public void chooseHobbies() {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated
                        (By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label"))).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated
                        (By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label"))).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated
                        (By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]/label"))).click();
    }

    public void uploadImage() {
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/pic0.jpg";
        WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"uploadPicture\"]"));
        chooseFile.sendKeys(imagePath);
    }

    public void fillUpCurrentAddress() {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated
                        (By.id("currentAddress"))).sendKeys("USA, New Mexico, Navajo Nation");
    }

//    public void chooseStateCity() {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("document.body.style.zoom = '2.5'"); /*Потому что мешает выбирать State и City не убираемый рекламный баннер*/
//
//        WebElement stateCity = driver.findElement(By.id("stateCity-wrapper"));
//
//        for (int i = 0; i < 10; i++) {
//            if (!stateCity.isDisplayed()) {
//                actions.sendKeys(Keys.PAGE_DOWN).perform();
//            }
//        }
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"state\"]/div/div[1]"))).click();
//        actions.sendKeys(Keys.TAB).perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"city\"]/div/div[1]"))).click();
//        actions.sendKeys(Keys.TAB).perform();
//    }

    public void chooseStateCity() {
        WebElement state = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"state\"]/div/div[1]")));
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"city\"]/div/div[1]")));

        actions.click(state).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-3-input"))).sendKeys("NCR");
        actions.sendKeys(Keys.TAB).perform();

        actions.click(city).sendKeys(city, Keys.TAB).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-4-input"))).sendKeys("Delhi");
        actions.sendKeys(Keys.TAB).perform();

        city.submit();
    }
}
