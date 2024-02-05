package com.demoqa.stakhanovda;

import com.demoqa.stakhanovda.pages.MainPage;
import com.demoqa.stakhanovda.pages.SubmittingFormSummary;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

@Feature("Тестовый сценарий для проверки знаний")
class MainTest extends WebDriverInit {
    private final MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
    private final SubmittingFormSummary submittingFormSummary = PageFactory.initElements(driver, SubmittingFormSummary.class);

    @Test
    @DisplayName("Успешное заполнение формы")
    @Story("Проверка соответствия введенных данных и того что сохранилось по итогу заполнения формы")
    public void mainScenario() {
        mainPage.open();
        Assertions.assertEquals("DEMOQA", mainPage.getTitle());

        mainPage.doRegistration
                (
                        "FirstName", "LastName", "test@gmail.com", "Male", "1234567890"
                );

        Assertions.assertEquals("Thanks for submitting the form", submittingFormSummary.getHeader());

        Assertions.assertEquals("FirstName LastName", submittingFormSummary.getNameRow());
        Assertions.assertEquals("test@gmail.com", submittingFormSummary.getEmailRow());
        Assertions.assertEquals("Male", submittingFormSummary.getGenderRow());
        Assertions.assertEquals("1234567890", submittingFormSummary.getPhoneNumberRow());
        Assertions.assertEquals("01 January,1900", submittingFormSummary.getDOBRow());
        Assertions.assertEquals("English, Computer Science", submittingFormSummary.getSubjectsRow());
        Assertions.assertEquals("Sports, Reading, Music", submittingFormSummary.getHobbyRow());
        Assertions.assertEquals("pic0.jpg", submittingFormSummary.getAvatarRow());
        Assertions.assertEquals("USA, New Mexico, Navajo Nation", submittingFormSummary.getAddressRow());
        Assertions.assertEquals("NCR Delhi", submittingFormSummary.getStateCityRow());
    }
}