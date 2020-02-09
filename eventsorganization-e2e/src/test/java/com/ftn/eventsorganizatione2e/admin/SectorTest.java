package com.ftn.eventsorganizatione2e.admin;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SectorTest {

    private WebDriver driver;

    SectorCreatePage sectorCreatePage;

    SectorEditPage sectorEditPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/login");
        WebElement username = driver.findElement(By.id("user"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("admin");
        WebElement submitbtn = driver.findElement(By.className("submitbtn"));
        submitbtn.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        sectorCreatePage = PageFactory.initElements(driver, SectorCreatePage.class);
        sectorEditPage = PageFactory.initElements(driver, SectorEditPage.class);
    }

    @Test
    public void testSectorCreateNoInput() {
        sectorCreatePage.getListHallsBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        sectorCreatePage.getListSectorBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        sectorCreatePage.getNewSectorBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement dialog = driver.findElement(By.id("mat-dialog-2"));
        assertThat(dialog.isDisplayed());
        sectorCreatePage.getSubmitbtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertThat(dialog.isDisplayed());
        driver.close();
    }

    @Test
    public void testSectorCreateOK() {
        sectorCreatePage.getListHallsBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        sectorCreatePage.getListSectorBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        sectorCreatePage.getNewSectorBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement dialog = driver.findElement(By.id("mat-dialog-2"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertThat(dialog.isDisplayed());
        sectorCreatePage.setSectorMark("LLK5O");
        sectorCreatePage.setNumOfRows("3");
        sectorCreatePage.setNumOfCols("3");
        sectorCreatePage.getSubmitbtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText().equals("Sector successfully created!"));
        driver.switchTo().alert().accept();
        driver.close();
    }

    @Test
    public void testSectorEditOK() {
        sectorEditPage.getListHallsBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        sectorEditPage.getListSectorBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        sectorEditPage.getEditSectorBtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement dialog = driver.findElement(By.id("mat-dialog-2"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertThat(dialog.isDisplayed());
        sectorEditPage.setSectorMark("LLK5O");
        sectorEditPage.getSubmitbtn().click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText().equals("Sector successfully edited!"));
        driver.switchTo().alert().accept();
        driver.close();
    }
}
