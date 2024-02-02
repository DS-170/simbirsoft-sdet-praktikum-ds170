package com.demoqa.stakhanovda;

import com.demoqa.stakhanovda.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

class MainTest extends WebDriverInit {
    private MainPage mainPage = new MainPage();
    private JavascriptExecutor jse = (JavascriptExecutor) driver;

    @Test
    @DisplayName("Успешное заполнение формы регистрации")
    public void mainScenario() {
        mainPage.open();
        Assertions.assertEquals("DEMOQA", driver.getTitle());

        /*Этот баннер заслоняет нужные формы*/
        hideBanner();

        mainPage.fillUpNameSurname();
        mainPage.fillUpEmail();
        mainPage.chooseGender("Male");
        mainPage.fillUpMobileNumber();
        mainPage.chooseDateOfBirth();

        actions.sendKeys(Keys.PAGE_DOWN).perform();

        mainPage.fillUpSubjects();
        mainPage.chooseHobbies();
        mainPage.uploadImage();
        mainPage.fillUpCurrentAddress();

        mainPage.chooseStateCity();

        Assertions.assertEquals("FirstName LastName", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]")).getText());
        Assertions.assertEquals("test@gmail.com", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]")).getText());
        Assertions.assertEquals("Male", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]")).getText());
        Assertions.assertEquals("1234567890", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]")).getText());
        Assertions.assertEquals("01 January,1900", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]")).getText());
        Assertions.assertEquals("English, Computer Science", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[6]/td[2]")).getText());
        Assertions.assertEquals("Sports, Reading, Music", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[7]/td[2]")).getText());
        Assertions.assertEquals("pic0.jpg", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[8]/td[2]")).getText());
        Assertions.assertEquals("USA, New Mexico, Navajo Nation", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[9]/td[2]")).getText());
        Assertions.assertEquals("NCR Delhi", driver.findElement(
                By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[10]/td[2]")).getText());
    }

    private void hideBanner() {
        if (driver.findElement(By.id("adplus-anchor")).isDisplayed()) {
            jse.executeScript("document.getElementById('adplus-anchor').style.display = 'none';");
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("adplus-anchor")));
            jse.executeScript("document.getElementById('adplus-anchor').style.display = 'none';");
        }
    }
}