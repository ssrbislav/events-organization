package com.ftn.eventsorganizatione2e.admin;

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

public class HallTest {

    private WebDriver driver;

    HallPage hallPage;

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
        hallPage = PageFactory.initElements(driver, HallPage.class);
    }

    @Test
    public void testHallSuccess() throws InterruptedException {
        hallPage.getListHallsBtn().click();
        WebElement dialog = driver.findElement(By.className("cdk-global-scrollblock"));
        assertThat(dialog.isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertThat(dialog.getSize() == new Dimension(244, 388));
        hallPage.getHewHallBtn().click();
        WebElement dialog2 = driver.findElement(By.id("mat-dialog-1"));
        assertThat(dialog2.isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        hallPage.setHallName("HALA 4");
        hallPage.getSubmitbtn().click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        assertThat(driver.switchTo().alert().getText().equals("Hall successfully created!"));
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        assertThat(!dialog2.isDisplayed());
        driver.close();
    }

    @Test
    public void hallCancel() {
        hallPage.getListHallsBtn().click();
        WebElement dialog = driver.findElement(By.className("cdk-global-scrollblock"));
        assertThat(dialog.isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertThat(dialog.getSize() == new Dimension(244, 388));
        hallPage.getHewHallBtn().click();
        WebElement dialog2 = driver.findElement(By.id("mat-dialog-1"));
        assertThat(dialog2.isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        hallPage.getSubmitbtn().click();
        assertThat(dialog2.isDisplayed());
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/admin");
        driver.close();
    }
}
