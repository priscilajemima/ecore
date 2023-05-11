package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver browser;
    private By errorMessage = By.cssSelector(".alert");

    public LoginPage(WebDriver browser) {
        this.browser = browser;
    }

    public LoginPage sendValidUser() {
        browser.findElement(By.cssSelector("[name='username']")).sendKeys("demouser");
        return this;
    }

    public LoginPage sendValidPassword() {
        browser.findElement(By.cssSelector("input[name=password]")).sendKeys("abc123");
        return this;
    }

    public LoginPage submit(){
        browser.findElement(By.id("btnLogin")).click();
        return this;
    }

    public LoginPage validateTitle(){
        String titlePage = browser.findElement(By.tagName("h2")).getText();
        Assertions.assertEquals("Invoice List", titlePage);
        return this;
    }

    public LoginPage sendInvalidUser(String username){
        browser.findElement(By.cssSelector("[name='username']")).sendKeys(username);
        return this;
    }

    public LoginPage sendInvalidPassword(String password){
        browser.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        return this;
    }

    public boolean isErrorMessageDisplayed(){
        return browser.findElements(errorMessage).size() > 0;
    }

    public String getErrorMessage() {
        return browser.findElement(errorMessage).getText();
    }

}



