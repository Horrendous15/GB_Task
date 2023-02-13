package org.example.WebTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPosts {
    static WebDriver driver;
    public static PageAuthElements elements;
    public String Token(String username, String password) {
        return given()
                .formParam("username", username)
                .formParam("password", password)
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .log().all()
                .extract().sessionId();
//                .cookie();
    };
    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.get(ConfProp.getProperty("url"));
//       Token(String username, String password);

    }

    @BeforeEach
    void getPage() {


        driver.get(ConfProp.getProperty("url") + "?sort=createdAt&order=ASC");
////        Cookie cookie = new Cookie("username", "gngbfgb");
//
////        driver.manage().addCookie(new Cookie("session_id", "gngbfgb"));
////        driver.manage().getCookieNamed("gngbfgb");
//        driver.manage().getCookieNamed("session_id");
    }

    @Test
    void Posts() {
        String token = Token(ConfProp.getProperty("login"), ConfProp.getProperty("pass"));
        System.out.println(token);
//        System.out.println(driver.manage().getCookies());
        elements.HomeClick();
    }


}

