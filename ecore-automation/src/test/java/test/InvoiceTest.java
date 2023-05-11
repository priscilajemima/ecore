package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.FormPage;
import pages.LoginPage;

public class InvoiceTest {
    private WebDriver browser;

    @BeforeEach
    public void beforeEach(){
        //define your path driver
        System.setProperty("webdriver.chrome.driver", "/Users/priscilajemima/Drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        this.browser = new ChromeDriver(options);
        this.browser.manage().window().maximize();

        //wait;
        this.browser.get("https://automation-sandbox-python-mpywqjbdza-uc.a.run.app/");

    }

    @Test
    public void testValidLogin(){
        new LoginPage(browser)
                .sendValidUser()
                .sendValidPassword()
                .submit()
                .validateTitle();
    }


    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(browser);
        String[] invalidUsernames = {"Demouser", "demouser_", "demouser", "demouser"};
        String[] invalidPasswords = {"abc123", "xyz", "nananana", "Abc123"};
        for (int i = 0; i < invalidUsernames.length; i++) {
            loginPage.sendInvalidUser(invalidUsernames[i])
                    .sendInvalidPassword(invalidPasswords[i])
                    .submit();
            Assertions.assertTrue(loginPage.isErrorMessageDisplayed());
            Assertions.assertEquals("Wrong username or password.", loginPage.getErrorMessage());
        }
    }

    @Test
    public void testValidateInvoiceDetails(){
        testValidLogin();
        new FormPage(browser)
                .goToInvoiceFormPage()
                .validateInvoiceForm();
    }

    @AfterEach
    public void afterEach(){browser.quit();}

}