package org.example.WebTests;

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

public class TestAuth {
    static WebDriver driver;
    String login="gngbfgb";
    String pass="ea3c0db283";
    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
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
    void AuthInvalidPassword() {

        WebElement username = driver.findElement(By.xpath("//*[@id='login']/div[1]/label/input"));
        WebElement password = driver.findElement(By.tagName("#login .field [type='password']"));

        username.sendKeys(login);
        password.sendKeys(pass);
    }
}

