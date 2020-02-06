package com.ftn.eventsorganizatione2e.tests;

import static org.assertj.core.api.Assertions.assertThat;


import com.ftn.eventsorganizatione2e.pages.RegisterPage;
import com.ftn.eventsorganizatione2e.selenium.WebDriverService;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.junit.Before;

import java.util.concurrent.TimeUnit;


public class RegisterTest {

    private WebDriver driver;

    RegisterPage registerPage;

    @Before
    public void setup() {
        driver = WebDriverService.startDriver("firefox");
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/main");
        WebElement registerButton = driver.findElement(By.id("register"));
        registerButton.click();
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
    }

    @Test
    public void testRegisterUserOk() throws InterruptedException {
        registerPage.setUsername("koros");

        registerPage.setEmail("koros@gmail.com");
        registerPage.setPassword("lozinka");
        registerPage.setPassword2("lozinka");
        registerPage.setFirstName("Prvo ime");
        registerPage.setLastName("Poslednje ime");
        registerPage.setAddress("Alekse Santica");
        registerPage.setDateOfBirth("1955-10-01");
        registerPage.setPhoneNumber("06444445");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registerPage.getRegisterButton().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/mainPage");
        //driver.close();
    }

}
