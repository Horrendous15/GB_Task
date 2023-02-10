package org.example.WebTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
public class PageAuthElements {
    public WebDriver driver;
    public PageAuthElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[@id='login']/div[1]/label/input")
    private WebElement usernameField;

    @FindBy(xpath = "#login .field [type='password']")
    private WebElement passField;

    @FindBy(xpath = "button [form='login']")
    private WebElement buttonLogin;

    public void inputLogin(String login) {
        usernameField.sendKeys(login); }
    public void inputPass(String pass) {
        passField.sendKeys(pass); }

    public void btnClick() {
        buttonLogin.click(); }
}
