package com.ftn.eventsorganizatione2e.tests;

import com.ftn.eventsorganizatione2e.pages.RegisterPage;
import com.ftn.eventsorganizatione2e.selenium.WebDriverService;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.junit.Before;


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
        registerPage.setDateOfBirth("2000-10-10");
        registerPage.setPhoneNumber("06444445");
        registerPage.getRegisterButton().click();
    }



}
