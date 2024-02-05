package com.demoqa.stakhanovda.pages;

import com.demoqa.stakhanovda.WebDriverInit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubmittingFormSummary extends WebDriverInit {
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

    public String getHeader() {
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
