package com.boxcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {

    private final By popUpOkBtn = By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/bc-alert-dialog/bc-dialog-container/div/div[3]/button/span/span/span");
    private final By signUpBtn = By.xpath("/html/body/bc-root/div/bc-auth/div/bc-sign-up/div/div[2]/form/bc-section-container/div/div[2]/bc-social-login/div[1]/button[1]");
    private final By firstName = By.xpath("//input[@formcontrolname='firstName']");
    private final By lastName = By.xpath("//input[@formcontrolname='lastName']");
    private final By country = By.className("mat-mdc-select-value");
    private final By countrySelected = By.id("mat-option-5");
    private final By phone = By.xpath("//input[@type='tel']");
    private final By email = By.xpath("//input[@formcontrolname='email']");                 
    private final By password = By.xpath("//input[@formcontrolname='password']");                 
    private final By confirmPassword = By.xpath("//input[@formcontrolname='confirmPassword']");
    private final By coupon = By.xpath("//input[@formcontrolname='couponCode']");
    private final By signUpSubmit = By.xpath("/html/body/bc-root/div/bc-auth/div/bc-sign-up/div/div[2]/form/bc-section-container/div/div[4]/bc-form-submit-button/button/span/span");
    private final By welcomeMsg = By.xpath("//div[@class='title']");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage open() {
        driver.get("https://dashboard-uat.boxcommerce.com/en-GB/auth/sign-up");
        return this;
    }

    public void clickOk() {
        click(popUpOkBtn);
    }

    public void clickSignUp() {
        click(signUpBtn);
    }

    public void fillBasicInfo(String f, String l, String mail, String pass, String phoneNumber, String couponCode) {
        type(firstName, f);
        type(lastName, l);
        click(country);
        click(countryUAE);
        type(phone, phoneNumber);
        type(email, mail);
        type(password, pass);
        type(confirmPassword, pass);
        type(coupon, couponCode);
    }

    public void submitSignUp() {
        click(signUpSubmit);
    }

    public boolean assertWelcomeMessage() {
        wait.until(driver -> driver.findElement(welcomeMsg).isDisplayed());
        return true;
    }
}