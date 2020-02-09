package com.ftn.eventsorganizatione2e.user;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private WebDriver driver;

    LoginPage loginPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/main");
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void testWrongUsername() {
        loginPage.setUsername("a");
        loginPage.setPassword("lorrrrr");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText()).isEqualTo("Wrong credentials!");
        alert.accept();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Login error -> Wrong credentials!");
        driver.close();
    }

    @Test
    public void testNoUsername() {
        loginPage.setUsername("");
        loginPage.setPassword("lorrrrr");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Username is required");
        driver.close();
    }

    @Test
    public void testNoPassword() {
        loginPage.setUsername("dsa");
        loginPage.setPassword("");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Password is required");
        driver.close();
    }

    @Test
    public void testPasswordTooShort() {
        loginPage.setUsername("dsa");
        loginPage.setPassword("gfd");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Password must be at least 6 characters");
        driver.close();
    }

    @Test
    public void testPasswordInvalid() {
        loginPage.setUsername("admin");
        loginPage.setPassword("nije_admin");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText()).isEqualTo("Wrong credentials!");
        alert.accept();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Login error -> Wrong credentials!");
        driver.close();
    }

    @Test
    public void testLoginOKAdmin() {
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/admin");
        driver.close();
    }

    @Test
    public void testLoginOKUser() {
        loginPage.setUsername("srbislav");
        loginPage.setPassword("lozinka");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.getLoginButton().click();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/events");
        driver.close();
    }
}
