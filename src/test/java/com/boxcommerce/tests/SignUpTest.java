package com.boxcommerce.tests;

import com.boxcommerce.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.boxcommerce.pages.utils.TestDataGenerator;

public class SignUpTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        String headless = System.getenv().getOrDefault("HEADLESS", "false");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1280,900");
        driver = new ChromeDriver(options);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    @Description("Basic smoke test that covers UAT sign-up form with coupon code.")
    public void canSignUpWithCoupon() {
        SignUpPage page = new SignUpPage(driver).open();
        //Generate random test data
        String email = TestDataGenerator.generateEmail();
        String phone = TestDataGenerator.generatePhoneNumber();
        page.clickOk();
        page.clickSignUp();
        page.fillBasicInfo(
                "Fname", "Lname",
                email,
                "Qwerty@123", phone,
                "UATQA-DEMO"
        );
        page.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("/welcome"));

        Assert.assertTrue(driver.getCurrentUrl().contains("welcome"),
                "Expect to leave the sign-up page after successful submission");

        Assert.assertTrue(page.assertWelcomeMessage(), "Welcome message is not displayed");
    }
}