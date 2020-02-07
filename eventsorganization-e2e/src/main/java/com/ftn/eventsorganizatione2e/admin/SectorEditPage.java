package com.ftn.eventsorganizatione2e.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectorEditPage {

    private WebDriver driver;

    @FindBy(id = "mat-input-0")
    private WebElement sectorMark;

    @FindBy(id = "mat-input-1")
    private WebElement numOfRows;

    @FindBy(id = "mat-input-2")
    private WebElement numOfCols;

    @FindBy(id = "list_sector")
    private WebElement listSectorBtn;

    @FindBy(id = "halls")
    private WebElement listHallsBtn;

    @FindBy(className = "edit_sector")
    private WebElement editSectorBtn;

    @FindBy(id = "create_btn")
    private WebElement submitbtn;

    public SectorEditPage (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSectorMark() {
        return sectorMark;
    }

    public void setSectorMark(String value) {
        WebElement element = getSectorMark();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String value) {
        WebElement element = getNumOfRows();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getNumOfCols() {
        return numOfCols;
    }

    public void setNumOfCols(String value) {
        WebElement element = getNumOfCols();
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getListSectorBtn() {
        return listSectorBtn;
    }

    public WebElement getListHallsBtn() {
        return listHallsBtn;
    }

    public WebElement getEditSectorBtn() {
        return editSectorBtn;
    }

    public WebElement getSubmitbtn() {
        return submitbtn;
    }
}
