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

public class EventTest {

    private WebDriver driver;

    EventPage eventPage;

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
        eventPage = PageFactory.initElements(driver, EventPage.class);
    }

    @Test
    public void textEventOK() throws InterruptedException {
        eventPage.getNewEventBtn().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement dialog = driver.findElement(By.id("mat-dialog-0"));
        assertThat(dialog.isDisplayed());
        eventPage.setEventName("KONCERT_NOVI");
        eventPage.setStartDate("2020-10-20");
        eventPage.setEndDate("2020-10-25");
        WebElement select = driver.findElement(By.id("mat-select-0"));
        select.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//mat-option/span[contains(.,'CONCERT')]")).click();
        WebElement select2 = driver.findElement(By.id("mat-select-1"));
        select2.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//mat-option/span[contains(.,'KUPOLA')]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        eventPage.getSubmitbtn().click();
        new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText().equals("Hall successfully created!"));
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
        assertThat(dialog.isDisplayed());
        driver.close();
    }

    @Test
    public void textEventBadDate() throws InterruptedException {
        eventPage.getNewEventBtn().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement dialog = driver.findElement(By.id("mat-dialog-0"));
        assertThat(dialog.isDisplayed());
        eventPage.setEventName("KONCERT_NOVI");
        eventPage.setStartDate("2010-10-10");
        eventPage.setEndDate("2010-10-15");
        WebElement select = driver.findElement(By.id("mat-select-0"));
        select.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//mat-option/span[contains(.,'CONCERT')]")).click();
        WebElement select2 = driver.findElement(By.id("mat-select-1"));
        select2.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//mat-option/span[contains(.,'KUPOLA')]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        eventPage.getSubmitbtn().click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText().equals("Error occured"));
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        assertThat(dialog.isDisplayed());
        driver.close();
    }

}
