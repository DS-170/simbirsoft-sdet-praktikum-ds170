package com.demoqa.stakhanovda;

import com.demoqa.stakhanovda.pages.MainPage;
import com.demoqa.stakhanovda.pages.SubmittingFormSummary;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Feature("Тестовый сценарий для проверки знаний")
class MainTest {
    private final String firstName = ConfPropertiesReader.getProperty("firstname");
    private final String lastName = ConfPropertiesReader.getProperty("lastname");
    private final String email = ConfPropertiesReader.getProperty("email");
    private final String gender = ConfPropertiesReader.getProperty("gender");
    private final String mobile = ConfPropertiesReader.getProperty("mobile");
    private final String dateOfBirth = ConfPropertiesReader.getProperty("date_of_birth");
    private final String subjects = ConfPropertiesReader.getProperty("subjects");
    private final String hobbies = ConfPropertiesReader.getProperty("hobbies");
    private final String avatar = ConfPropertiesReader.getProperty("avatar");
    private final String currentAddress = ConfPropertiesReader.getProperty("current_address");
    private final String stateAndCity = ConfPropertiesReader.getProperty("state_and_city");
    private WebDriver driver;
    private MainPage mainPage;
    private SubmittingFormSummary submittingFormSummary;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        submittingFormSummary = new SubmittingFormSummary(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("То что заполнено совпадает с тем что сохранилось в базу")
    @Story("Проверка соответствия введенных данных и того что сохранилось по итогу заполнения формы")
    public void mainScenario() {
        mainPage.open();
        Assertions.assertEquals("DEMOQA", mainPage.getTitle());

        mainPage.doRegistration
                (
                        firstName,
                        lastName,
                        email,
                        gender,
                        mobile,
                        dateOfBirth,
                        subjects,
                        hobbies,
                        avatar,
                        currentAddress,
                        stateAndCity
                );

        doPropertiesAsserts("Equals");
    }

    @Test
    @DisplayName("Проверка на то что введены данные не из conf.properties")
    @Story("Проверка несоответствия введенных данных и того что имеется в conf.properties")
    public void mainScenarioFail() {
        mainPage.open();
        Assertions.assertEquals("DEMOQA", mainPage.getTitle());

        mainPage.doRegistration
                (
                        "FailTest",
                        "FailTest",
                        "ftest@gmail.com",
                        "other",
                        "0000000000",
                        "02 March,2000",
                        "no",
                        "no",
                        "pic1",
                        "notMyAddress",
                        "statecity"
                );

        doPropertiesAsserts("notEquals");
    }

    private void doPropertiesAsserts(String assertion) {
        Assertions.assertEquals("Thanks for submitting the form", submittingFormSummary.getHeader());

        if ("EQUALS".equalsIgnoreCase(assertion)) {
            Assertions.assertEquals(firstName + " " + lastName, submittingFormSummary.getNameRow());
            Assertions.assertEquals(email, submittingFormSummary.getEmailRow());
            Assertions.assertEquals(gender, submittingFormSummary.getGenderRow());
            Assertions.assertEquals(mobile, submittingFormSummary.getPhoneNumberRow());
            Assertions.assertEquals(dateOfBirth, submittingFormSummary.getDOBRow());
            Assertions.assertEquals(subjects, submittingFormSummary.getSubjectsRow());
            Assertions.assertEquals(hobbies, submittingFormSummary.getHobbyRow());
            Assertions.assertEquals(avatar, submittingFormSummary.getAvatarRow());
            Assertions.assertEquals(currentAddress, submittingFormSummary.getAddressRow());
            Assertions.assertEquals(stateAndCity, submittingFormSummary.getStateCityRow());
        } else {
            Assertions.assertNotEquals(firstName + " " + lastName, submittingFormSummary.getNameRow());
            Assertions.assertNotEquals(email, submittingFormSummary.getEmailRow());
            Assertions.assertNotEquals(gender, submittingFormSummary.getGenderRow());
            Assertions.assertNotEquals(mobile, submittingFormSummary.getPhoneNumberRow());
            Assertions.assertNotEquals(dateOfBirth, submittingFormSummary.getDOBRow());
            Assertions.assertNotEquals(subjects, submittingFormSummary.getSubjectsRow());
            Assertions.assertNotEquals(hobbies, submittingFormSummary.getHobbyRow());
            Assertions.assertNotEquals(avatar, submittingFormSummary.getAvatarRow());
            Assertions.assertNotEquals(currentAddress, submittingFormSummary.getAddressRow());
            Assertions.assertNotEquals(stateAndCity, submittingFormSummary.getStateCityRow());
        }
    }
}