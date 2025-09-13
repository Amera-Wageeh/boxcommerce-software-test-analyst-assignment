package com.boxcommerce.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement (By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void type(By locator, String text) {
        WebElement element = (locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        (locator).click();
    }

    protected void setCheckbox(By locator, boolean value) {
        WebElement element = (locator);
        if (element.isSelected() != value) {
            element.click();
        }
    }

    protected void selectByVisibleText(By locator, String text) {
        WebElement element = (locator);
        element.click();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }
}