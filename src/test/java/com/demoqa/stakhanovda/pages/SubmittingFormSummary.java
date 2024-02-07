package com.demoqa.stakhanovda.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubmittingFormSummary {
    public WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement formHeader;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]")
    private WebElement nameRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]")
    private WebElement emailRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]")
    private WebElement genderRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]")
    private WebElement phoneNumberRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]")
    private WebElement DOBRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[6]/td[2]")
    private WebElement subjectsRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[7]/td[2]")
    private WebElement hobbyRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[8]/td[2]")
    private WebElement avatarRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[9]/td[2]")
    private WebElement addressRow;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[10]/td[2]")
    private WebElement stateCityRow;

    public SubmittingFormSummary(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeader() {
        wait.until(ExpectedConditions.visibilityOf(formHeader));
        return formHeader.getText();
    }

    public String getNameRow() {
        return nameRow.getText();
    }

    public String getEmailRow() {
        return emailRow.getText();
    }

    public String getGenderRow() {
        return genderRow.getText();
    }

    public String getPhoneNumberRow() {
        return phoneNumberRow.getText();
    }

    public String getDOBRow() {
        return DOBRow.getText();
    }

    public String getSubjectsRow() {
        return subjectsRow.getText();
    }

    public String getHobbyRow() {
        return hobbyRow.getText();
    }

    public String getAvatarRow() {
        return avatarRow.getText();
    }

    public String getAddressRow() {
        return addressRow.getText();
    }

    public String getStateCityRow() {
        return stateCityRow.getText();
    }
}
