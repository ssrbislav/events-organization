package com.ftn.eventsorganizatione2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id  = "pass")
    private WebElement password;

    @FindBy(id = "pass2")
    private WebElement password2;

    @FindBy(id  = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id  = "dateOfBirth")
    private WebElement dateOfBirth;

    @FindBy(id  = "address")
    private WebElement address;

    @FindBy(id  = "phoneNumber")
    private WebElement phoneNumber;

    @FindBy(className = "submitbtn")
    private WebElement registerButton;

    @FindBy(className = "cancelbtn")
    private WebElement cancelButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getUsername() {
        return username;
    }

    public void setUsername(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getEmail() {
        return email;
    }

    public void setEmail(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getPassword2() {
        return password2;
    }

    public void setPassword2(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getAddress() {
        return address;
    }

    public void setAddress(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String value) {
        WebElement element = getUsername();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }
}
