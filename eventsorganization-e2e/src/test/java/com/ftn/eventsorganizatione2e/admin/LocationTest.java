package com.ftn.eventsorganizatione2e.admin;

import com.ftn.eventsorganizatione2e.admin.LocationPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationTest {

    private WebDriver driver;

    LocationPage locationPage;

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
        locationPage = PageFactory.initElements(driver, LocationPage.class);
    }

    @Test
    public void testNavigationSuccessfull() {
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/admin");
        driver.close();
    }

    @Test
    public void testLocationErrorSameName() throws InterruptedException {
        locationPage.getNewLocBtn().click();
        WebElement dialog = driver.findElement(By.className("cdk-global-scrollblock"));
        assertThat(dialog.isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertThat(dialog.getSize() == new Dimension(244, 388));
        locationPage.setName("Lokacija");
        locationPage.setStreetName("Tolstojeva");
        locationPage.setNumber("34");
        locationPage.setCity("Novi Sad");
        locationPage.setZipCode("22331");
        locationPage.setCountry("Srbija");
        locationPage.getSubmitBtn().click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        Thread.sleep(1000);
        assertThat(driver.switchTo().alert().getText().equals("Error occured!"));
        driver.close();
    }

    @Test
    public void testLocationOK() throws InterruptedException {
        locationPage.getNewLocBtn().click();
        WebElement dialog = driver.findElement(By.className("cdk-global-scrollblock"));
        assertThat(dialog.isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertThat(dialog.getSize() == new Dimension(244, 388));
        locationPage.setName("Lokacija2s55");
        locationPage.setStreetName("Tolstojeva");
        locationPage.setNumber("34");
        locationPage.setCity("Novi Sad");
        locationPage.setZipCode("22331");
        locationPage.setCountry("Srbija");
        locationPage.getSubmitBtn().click();
        new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText().equals("Location successfully added!"));
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        assertThat(!dialog.isDisplayed());
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/admin");
        driver.close();
    }
}
