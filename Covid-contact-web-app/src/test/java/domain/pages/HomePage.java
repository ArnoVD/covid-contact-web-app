package domain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page{
    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="logInButton")
    private WebElement logInButton;

    @FindBy(id="logOutButton")
    private WebElement logOutButton;

    @FindBy(id="welcomeParagraph")
    private WebElement welcomeParagraph;

    @FindBy(id="loginParagraph")
    private WebElement loginParagraph;

    @FindBy(id="loginError")
    private WebElement errorParagraph;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=Home");
    }

    public void setUseridField(String userid){
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPasswordField(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage logInValid(){
        logInButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public HomePage logOutValid(){
        logOutButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void logInInvalid(){logInButton.click();}

    public String getWelcomeParagraphText(){return welcomeParagraph.getText();}

    public String getLoginParagraph(){return loginParagraph.getText();}

    public String getErrorParagraph(){return errorParagraph.getText();}
}
