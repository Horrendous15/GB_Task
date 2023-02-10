package org.example.WebTests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class TestAuth {
    static WebDriver driver;

    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @BeforeEach
    void getPage(){
        driver.get("https://test-stand.gb.ru/login");
    }


    @Test
    void AuthInvalidLoginMinLength() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement btn = driver.findElement(By.cssSelector("button"));

//        ввод в поле username строки меньше 2-х символов
        username.clear();
        username.sendKeys(ConfProp.getProperty("12"));
        btn.click();
        WebElement errorBlock = driver.findElement(By.cssSelector(".error-block"));
        String errorText = errorBlock.getText();
        assertTrue(errorText.contains("Неправильный логин. Может быть не менее 3 и не более 20 символов"));
    }
    @Test
    void AuthInvalidLoginMaxLength() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement btn = driver.findElement(By.cssSelector("button"));

        //        ввод в поле username строки больше 20-и символов
        username.clear();
        username.sendKeys(ConfProp.getProperty("123TestTestTestTestTest"));
        btn.click();
        WebElement errorBlock = driver.findElement(By.cssSelector(".error-block"));
        String errorText = errorBlock.getText();
        assertTrue(errorText.contains("Неправильный логин. Может быть не менее 3 и не более 20 символов"));
    }
    @Test
    void AuthInvalidLoginField() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement btn = driver.findElement(By.cssSelector("button"));

//        ввод в поле username строки со спец символами
        username.clear();
        username.sendKeys("teSt!5*%)S123");
        btn.click();
        WebElement errorBlock = driver.findElement(By.cssSelector(".error-block"));
        String errorText = errorBlock.getText();
        assertTrue(errorText.contains("Неправильный логин. Может состоять только из латинских букв и цифр, без спецсимволов"));
    }
    @Test
    void AuthLoginEmpty() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement password = driver.findElement(By.cssSelector("#login .field [type='password']"));
        WebElement btn = driver.findElement(By.cssSelector("button"));

        username.clear();
        password.clear();
        username.sendKeys("");
        password.sendKeys("123");
        btn.click();
        WebElement errorBlock = driver.findElement(By.cssSelector(".error-block"));
        String errorText = errorBlock.getText();
        assertTrue(errorText.contains("Поле не может быть пустым"));
    }
    @Test
    void AuthPassEmpty() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement password = driver.findElement(By.cssSelector("#login .field [type='password']"));
        WebElement btn = driver.findElement(By.cssSelector("button"));

        username.clear();
        password.clear();
//        ввод в поле username 20 символов, поле password пустое
        username.sendKeys("1234TestTestTestTest");
        btn.click();
        WebElement errorBlock = driver.findElement(By.cssSelector(".error-block"));
        String errorText = errorBlock.getText();
        assertTrue(errorText.contains("Поле не может быть пустым"));
    }

    @Test
    void AuthValid() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement password = driver.findElement(By.cssSelector("#login .field [type='password']"));
        WebElement btn = driver.findElement(By.cssSelector("button"));

        username.clear();
        password.clear();
//        ввод в поле username 20 символов, поле password пустое
        username.sendKeys(ConfProp.getProperty("login"));
        password.sendKeys(ConfProp.getProperty("pass"));
        btn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(username));
        String url = driver.getCurrentUrl();
        assertEquals(url, ConfProp.getProperty("url"));
    }
}

