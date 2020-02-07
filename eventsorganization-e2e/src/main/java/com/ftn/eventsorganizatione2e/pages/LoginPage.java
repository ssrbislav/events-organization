package com.ftn.eventsorganizatione2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "user")
    private WebElement username;

    @FindBy(id = "pass")
    private WebElement password;

    @FindBy(className = "submitbtn")
    private WebElement loginButton;

    @FindBy(className = "cancelbtn")
    private WebElement cancelButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.className("login-form"))
        );
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }


    public WebElement getPassword() {
        return password;
    }

    public void setPassword(String value) {
        WebElement element = getPassword();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }
}
