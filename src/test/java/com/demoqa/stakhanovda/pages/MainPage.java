package com.demoqa.stakhanovda.pages;

import com.demoqa.stakhanovda.WebDriverInit;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends WebDriverInit {
    private final String pageAddress = "https://demoqa.com/automation-practice-form";
    private JavascriptExecutor jse = (JavascriptExecutor) driver;
    @FindBy(xpath = "//*[@id=\"app\"]/header/a")
    private WebElement mainLogoXpath;
    @FindBy(id = "firstName")
    private WebElement firstNamePath;
    @FindBy(id = "lastName")
    private WebElement lastNamePath;
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy(xpath = "//*[@id=\"userNumber\"]")
    private WebElement mobileNumberFormXpath;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;
    @FindBy(xpath = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select/option[1]")
    private WebElement DOBJanuary;
    @FindBy(xpath = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select/option[1]")
    private WebElement DOBYear;
    @FindBy(xpath = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]")
    private WebElement DOBDay;
    @FindBy(xpath = "//*[@id=\"subjectsWrapper\"]/div[2]")
    private WebElement subjectsForm;
    @FindBy(css = "#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label")
    private WebElement maleChoose;
    @FindBy(css = "#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(2) > label")
    private WebElement femaleChoose;
    @FindBy(css = "#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(3) > label")
    private WebElement otherChoose;
    @FindBy(id = "userForm")
    private WebElement userForm;
    @FindBy(id = "adplus-anchor")
    private WebElement adplusBanner;
    @FindBy(xpath = "//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label")
    private WebElement hobbySports;
    @FindBy(xpath = "//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label")
    private WebElement hobbyReading;
    @FindBy(xpath = "//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]/label")
    private WebElement hobbyMusic;
    @FindBy(xpath = "//*[@id=\"subjectsContainer\"]/div")
    private WebElement subjectContainer;

    public String getTitle() {
        return driver.getTitle();
    }

    @Step("Открытие страницы")
    public void open() {
        driver.get(pageAddress);
        wait.until(ExpectedConditions.visibilityOf(mainLogoXpath));
    }

    @Step("Заполнение имени и фамилии")
    private void fillUpNameSurname(String firstName, String lastName) {
        firstNamePath.sendKeys(firstName);
        lastNamePath.sendKeys(lastName);
    }

    @Step("Заполнение Email")
    private void fillUpEmail(String email) {
        userEmail.sendKeys(email);
    }

    @Step("Выбор пола")
    private void chooseGender(String gender) {
        switch (gender) {
            case "Male" -> maleChoose.click();
            case "Female" -> femaleChoose.click();
            default -> otherChoose.click();
        }
    }

    @Step("Заполнение номера телефона")
    private void fillUpMobileNumber(String mobileNumber) {
        mobileNumberFormXpath.sendKeys(mobileNumber);
    }

    @Step("Выбор даты рождения в календаре")
    private void chooseDateOfBirth() {
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthInput)).click();
        wait.until(ExpectedConditions.visibilityOf(DOBJanuary)).click();
        wait.until(ExpectedConditions.visibilityOf(DOBYear)).click();
        wait.until(ExpectedConditions.visibilityOf(DOBDay)).click();
    }

    @Step("Выбор занятий/уроков")
    private void fillUpSubjects() {
        subjectsForm.click();
        wait.until(ExpectedConditions.visibilityOf(subjectContainer));
        actions.sendKeys("English").perform();
        wait.until(ExpectedConditions.visibilityOf(subjectContainer));
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys("Comp").perform();
        wait.until(ExpectedConditions.visibilityOf(subjectContainer));
        actions.sendKeys(Keys.TAB).perform();
    }

    @Step("Выбор хобби")
    private void chooseHobbies() {
        wait.until(ExpectedConditions.visibilityOf(hobbySports)).click();
        wait.until(ExpectedConditions.visibilityOf(hobbyReading)).click();
        wait.until(ExpectedConditions.visibilityOf(hobbyMusic)).click();
    }

    @Step("Загрузка фото")
    private void uploadImage() {
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/pic0.jpg";
        WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"uploadPicture\"]"));
        chooseFile.sendKeys(imagePath);
    }

    @Step("Введение адреса")
    private void fillUpCurrentAddress() {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated
                        (By.id("currentAddress"))).sendKeys("USA, New Mexico, Navajo Nation");
    }

    @Step("Выбор штата и города")
    private void chooseStateCity() {
        WebElement state = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"state\"]/div/div[1]")));
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"city\"]/div/div[1]")));

        actions.click(state).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-3-input"))).sendKeys("NCR");
        actions.sendKeys(Keys.TAB).perform();

        actions.click(city).sendKeys(city, Keys.TAB).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-4-input"))).sendKeys("Delhi");
        actions.sendKeys(Keys.TAB).perform();

    }

    @Step("Подтверждение всей формы")
    private void formSubmit() {
        userForm.submit();
    }

    @Step("Скрытие рекламного баннера")
    private void hideBanner() {
        if (adplusBanner.isDisplayed()) {
            jse.executeScript("document.getElementById('adplus-anchor').style.display = 'none';");
        } else {
            wait.until(ExpectedConditions.visibilityOf(adplusBanner));
            jse.executeScript("document.getElementById('adplus-anchor').style.display = 'none';");
        }
    }

    public void doRegistration(String firstName, String lastName, String email, String gender, String mobileNumber) {
        /*Этот баннер заслоняет нужные формы*/
        hideBanner();

        fillUpNameSurname(firstName, lastName);
        fillUpEmail(email);
        chooseGender(gender);
        fillUpMobileNumber(mobileNumber);
        chooseDateOfBirth();
        fillUpSubjects();
        chooseHobbies();
        uploadImage();
        fillUpCurrentAddress();
        chooseStateCity();
        formSubmit();
    }
}
