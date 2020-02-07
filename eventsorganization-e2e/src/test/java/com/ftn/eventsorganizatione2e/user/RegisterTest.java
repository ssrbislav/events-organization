package com.ftn.eventsorganizatione2e.user;

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


public class RegisterTest {

    private WebDriver driver;

    RegisterPage registerPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/main");
        WebElement registerButton = driver.findElement(By.id("register"));
        registerButton.click();
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
    }

    @Test
    public void testRegisterEmailExist() throws InterruptedException {
        registerPage.setUsername("korost");
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
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Visitor with email: koros@gmail.com already exists!");
        driver.close();
    }

    @Test
    public void testRegisterUsernameExist() throws InterruptedException {
        registerPage.setUsername("korost");
        registerPage.setEmail("korosadfs@gmail.com");
        registerPage.setPassword("lozinka");
        registerPage.setPassword2("lozinka");
        registerPage.setFirstName("Prvo ime");
        registerPage.setLastName("Poslednje ime");
        registerPage.setAddress("Alekse Santica");
        registerPage.setDateOfBirth("1955-10-01");
        registerPage.setPhoneNumber("06444445");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registerPage.getRegisterButton().click();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Passenger with username: korost already exists!");
        driver.close();
    }

    @Test
    public void testRegisterPasswordNotCorrect() throws InterruptedException {
        registerPage.setUsername("korostgf");
        registerPage.setEmail("korosadfsgf@gmail.com");
        registerPage.setPassword("lozi");
        registerPage.setPassword2("lozinka");
        registerPage.setFirstName("Prvo ime");
        registerPage.setLastName("Poslednje ime");
        registerPage.setAddress("Alekse Santica");
        registerPage.setDateOfBirth("2010-10-01");
        registerPage.setPhoneNumber("06444445");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registerPage.getRegisterButton().click();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Password must be at least 6 characters");
        driver.close();
    }

    @Test
    public void testRegisterWrongDate() throws InterruptedException {
        registerPage.setUsername("koroshgft");

        registerPage.setEmail("korosadfdhgfhss@gmail.com");
        registerPage.setPassword("lozinka");
        registerPage.setPassword2("lozinka");
        registerPage.setFirstName("Prvo ime");
        registerPage.setLastName("Poslednje ime");
        registerPage.setAddress("Alekse Santica");
        registerPage.setDateOfBirth("");
        registerPage.setPhoneNumber("06444445");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registerPage.getRegisterButton().click();
        WebElement element = driver.findElement(By.className("error"));
        assertThat(element.getText()).isEqualTo("Date of birth is required");
        driver.close();
    }

    @Test
    public void testRegisterOk() throws InterruptedException {
        registerPage.setUsername("korofdssfdsdsat");

        registerPage.setEmail("korosadfddsasffdss@gmail.com");
        registerPage.setPassword("lozinka");
        registerPage.setPassword2("lozinka");
        registerPage.setFirstName("Prvo ime");
        registerPage.setLastName("Poslednje ime");
        registerPage.setAddress("Alekse Santica");
        registerPage.setDateOfBirth("1955-10-01");
        registerPage.setPhoneNumber("06444445");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registerPage.getRegisterButton().click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4200/login");
        driver.close();
    }


}
