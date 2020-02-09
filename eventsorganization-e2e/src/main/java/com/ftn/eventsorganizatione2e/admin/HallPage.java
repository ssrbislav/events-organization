package com.ftn.eventsorganizatione2e.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HallPage {

    private WebDriver driver;

    @FindBy(id = "mat-input-0")
    private WebElement hallName;

    @FindBy(className = "create_btn")
    private WebElement submitbtn;

    @FindBy(className = "new_hall")
    private WebElement hewHallBtn;

    @FindBy(id = "halls")
    private WebElement listHallsBtn;

    public HallPage (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHallName() {
        return hallName;
    }

    public void setHallName(String value) {
        WebElement element = getHallName();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getSubmitbtn() {
        return submitbtn;
    }

    public WebElement getHewHallBtn() {
        return hewHallBtn;
    }

    public WebElement getListHallsBtn() {
        return listHallsBtn;
    }
}
