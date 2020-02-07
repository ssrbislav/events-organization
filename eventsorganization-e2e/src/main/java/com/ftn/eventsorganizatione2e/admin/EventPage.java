package com.ftn.eventsorganizatione2e.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EventPage {

    private WebDriver driver;

    @FindBy(className = "eventName")
    private WebElement eventName;

    @FindBy(className = "startDate")
    private WebElement startDate;

    @FindBy(className = "endDate")
    private WebElement endDate;

    @FindBy(className = "new_event")
    private WebElement newEventBtn;

    @FindBy(className = "create_btn")
    private WebElement submitbtn;

    public EventPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEventName() {
        return eventName;
    }

    public void setEventName(String value) {
        WebElement element = getEventName();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getStartDate() {
        return startDate;
    }

    public void setStartDate(String value) {
        WebElement element = getStartDate();
        element.sendKeys(value);
    }

    public WebElement getEndDate() {
        return endDate;
    }

    public void setEndDate(String value) {
        WebElement element = getEndDate();
        element.sendKeys(value);
    }

    public WebElement getNewEventBtn() {
        return newEventBtn;
    }

    public WebElement getSubmitbtn() {
        return submitbtn;
    }
}
