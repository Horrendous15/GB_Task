package org.example.WebTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;


import static io.restassured.RestAssured.given;

public class PageAuthElements {
    public WebDriver driver;
    public PageAuthElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[@href='/']")
    private WebElement Home;

    @FindBy(xpath = "#login .field [type='password']")
    private WebElement passField;

    @FindBy(xpath = "button [form='login']")
    private WebElement buttonLogin;

    public void HomeClick() {
        Home.click(); }
    public void inputPass(String pass) {
        passField.sendKeys(pass); }

    public void btnClick() {
        buttonLogin.click(); }
}
