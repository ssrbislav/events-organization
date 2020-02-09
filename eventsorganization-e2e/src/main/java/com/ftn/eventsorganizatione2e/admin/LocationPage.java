package com.ftn.eventsorganizatione2e.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPage {

    private WebDriver driver;

    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "streetName")
    private WebElement streetName;

    @FindBy(name = "number")
    private WebElement number;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "zipCode")
    private WebElement zipCode;

    @FindBy(name = "country")
    private WebElement country;

    @FindBy(className = "new_loc")
    private WebElement newLocBtn;

    @FindBy(className = "create_btn")
    private WebElement submitBtn;

    public LocationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.className("login-form"))
        );
    }

    public WebElement getName() {
        return name;
    }

    public void setName(String value) {
        WebElement element = getName();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getStreetName() {
        return streetName;
    }

    public void setStreetName(String value) {
        WebElement element = getStreetName();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getNumber() {
        return number;
    }

    public void setNumber(String value) {
        WebElement element = getNumber();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getCity() {
        return city;
    }

    public void setCity(String value) {
        WebElement element = getCity();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getZipCode() {
        return zipCode;
    }

    public void setZipCode(String value) {
        WebElement element = getZipCode();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getCountry() {
        return country;
    }

    public void setCountry(String value) {
        WebElement element = getCountry();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getNewLocBtn() {
        return newLocBtn;
    }

    public WebElement getSubmitBtn() {
        return submitBtn;
    }

}
