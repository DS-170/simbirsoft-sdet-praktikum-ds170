package com.demoqa.stakhanovda.pages;

import com.demoqa.stakhanovda.ConfPropertiesReader;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final String pageAddress = ConfPropertiesReader.getProperty("mainpage");
    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;
    private Actions actions;
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
    @FindBy(id = "currentAddress")
    private WebElement currentAddress;
    @FindBy(xpath = "//*[@id=\"state\"]/div/div[1]")
    private WebElement state;
    @FindBy(xpath = "//*[@id=\"city\"]/div/div[1]")
    private WebElement city;
    @FindBy(className = "react-datepicker__month-select")
    private WebElement monthSelect;
    @FindBy(className = "react-datepicker__year-select")
    private WebElement yearSelect;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    @Step("Открытие страницы")
    private void open() {
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
    private void chooseDateOfBirth(String dateOfBirth /*dd Mmmm,YYYY (example: 01 January,1900)*/) {
        String dayPartPath = "react-datepicker__day--0";
        StringBuilder builder = new StringBuilder(dateOfBirth);

        String[] dateArray = new String[3];
        dateArray[0] = builder.substring(0, 2);
        dateArray[1] = builder.substring(3, builder.indexOf(","));
        dateArray[2] = builder.substring(builder.length() - 4, builder.length());

        actions.moveToElement(dateOfBirthInput).perform();
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthInput)).click();

        wait.until(ExpectedConditions.visibilityOf(monthSelect));

        Select sMonth = new Select(monthSelect);
        sMonth.selectByVisibleText(dateArray[1]);

        Select sYear = new Select(yearSelect);
        sYear.selectByValue(dateArray[2]);

        WebElement daySelect = driver.findElement(By.className(dayPartPath + dateArray[0]));
        wait.until(ExpectedConditions.visibilityOf(daySelect)).click();
    }

    @Step("Выбор занятий/уроков")
    private void fillUpSubjects(String subjects) {
        subjectsForm.click();
        wait.until(ExpectedConditions.visibilityOf(subjectContainer));

        if (ConfPropertiesReader.getProperty("subjects").equalsIgnoreCase(subjects)) {
            actions.sendKeys("English").perform();
            wait.until(ExpectedConditions.visibilityOf(subjectContainer));
            actions.sendKeys(Keys.TAB).perform();
            actions.sendKeys("Comp").perform();
            wait.until(ExpectedConditions.visibilityOf(subjectContainer));
        } else {
            actions.sendKeys("Maths").perform();
            wait.until(ExpectedConditions.visibilityOf(subjectContainer));
            actions.sendKeys(Keys.TAB).perform();
            actions.sendKeys("Biology").perform();
            wait.until(ExpectedConditions.visibilityOf(subjectContainer));
        }
        actions.sendKeys(Keys.TAB).perform();
    }

    @Step("Выбор хобби")
    private void chooseHobbies(String myHobbies) {
        boolean isMyHobbies = myHobbies.equalsIgnoreCase(ConfPropertiesReader.getProperty("hobbies"));

        if (isMyHobbies) {
            wait.until(ExpectedConditions.visibilityOf(hobbySports)).click();
            wait.until(ExpectedConditions.visibilityOf(hobbyReading)).click();
            wait.until(ExpectedConditions.visibilityOf(hobbyMusic)).click();
        }
    }

    @Step("Загрузка фото")
    private void uploadImage(String myAvatar) {
        boolean isMyAvatar = myAvatar.equalsIgnoreCase(ConfPropertiesReader.getProperty("avatar"));

        String imagePath;
        if (isMyAvatar) {
            imagePath = System.getProperty("user.dir") + "/src/test/resources/pic0.jpg";
        } else {
            imagePath = System.getProperty("user.dir") + "/src/test/resources/pic1.png";
        }

        WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"uploadPicture\"]"));
        chooseFile.sendKeys(imagePath);
    }

    @Step("Введение адреса")
    private void fillUpCurrentAddress(String myAddress) {
        boolean isMyAddress = myAddress.equalsIgnoreCase(ConfPropertiesReader.getProperty("current_address"));
        String output;

        if (isMyAddress) {
            output = "USA, New Mexico, Navajo Nation";
        } else output = "Russia";

        actions.moveToElement(currentAddress).perform();
        wait.until(ExpectedConditions
                .visibilityOf(currentAddress)).sendKeys(output);
    }

    @Step("Выбор штата и города")
    private void chooseStateCity(String stateCity /*format: "State City" (ex: "NCR Delhi")*/) {
        String[] stArray = new String[2];
        String myState;
        String myCity;

        if (stateCity.contains(" ")) {
            StringBuilder builder = new StringBuilder(stateCity);
            stArray[0] = builder.substring(0, builder.indexOf(" "));
            stArray[1] = builder.substring(builder.indexOf(" "), builder.length());

            myState = stArray[0];
            myCity = stArray[1];
        } else {
            myState = " ";
            myCity = " ";
        }

        actions.moveToElement(state).perform();

        actions.click(state).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-3-input"))).sendKeys(myState);
        actions.sendKeys(Keys.TAB).perform();

        actions.click(city).sendKeys(city, Keys.TAB).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-4-input"))).sendKeys(myCity);
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

    public void doRegistration(
            String firstName,
            String lastName,
            String email,
            String gender,
            String mobileNumber,
            String specificDateInMethod,
            String subjects,
            String myHobbies,
            String myAvatar,
            String myAddress,
            String stateCity
    ) {
        /*Этот баннер заслоняет нужные формы*/
        hideBanner();

        fillUpNameSurname(firstName, lastName);
        fillUpEmail(email);
        chooseGender(gender);
        fillUpMobileNumber(mobileNumber);
        chooseDateOfBirth(specificDateInMethod);
        fillUpSubjects(subjects);
        chooseHobbies(myHobbies);
        uploadImage(myAvatar);
        fillUpCurrentAddress(myAddress);
        chooseStateCity(stateCity);
        formSubmit();
    }
}
