package domain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactPage extends Page {
    @FindBy(tagName = "td")
    private List<WebElement> tableElements;

    @FindBy(id="contactFirstName")
    private WebElement contactFirstNameField;

    @FindBy(id="contactLastName")
    private WebElement contactLastNameField;

    @FindBy(id="contactDate")
    private WebElement contactDateField;

    @FindBy(id="contactHour")
    private WebElement contactHourField;

    @FindBy(id="contactPhoneNumber")
    private WebElement contactPhoneNumberField;

    @FindBy(id="contactEmail")
    private WebElement contactEmailField;

    @FindBy(id="addContact")
    private WebElement addContactButton;

    @FindBy(id="deleteContact")
    private WebElement deleteContactButton;

    @FindBy(css="div.alert-danger ul li")
    private List<WebElement> errorMessages;

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=Contacts");
    }

    public void setContactFirstName(String firstName){
        contactFirstNameField.clear();
        contactFirstNameField.sendKeys(firstName);
    }

    public void setContactLastName(String lastName){
        contactLastNameField.clear();
        contactLastNameField.sendKeys(lastName);
    }

    public void setContactDate(String contactDate){
        contactDateField.clear();
        contactDateField.sendKeys(contactDate);
    }

    public void setContactHour(String contactHour){
        contactHourField.clear();
        contactHourField.sendKeys(contactHour);
    }

    public void setContactPhoneNumber(String phoneNumber){
        contactPhoneNumberField.clear();
        contactPhoneNumberField.sendKeys(phoneNumber);
    }

    public void setContactEmail(String email){
        contactEmailField.clear();
        contactEmailField.sendKeys(email);
    }

    public ContactPage submitValid(){
        addContactButton.click();
        return PageFactory.initElements(driver, ContactPage.class);
    }

    public void submitInvalid(){addContactButton.click();}

    public ContactPage deleteValid(){
        deleteContactButton.click();
        return PageFactory.initElements(driver, ContactPage.class);
    }

    public int getContactOverviewListLength() {
        int counter = 0;
        for (WebElement listItem:tableElements){
            counter++;
        }
        return counter;
    }

    public boolean hasErrorMessage(String message){
        for (WebElement errorMessage : errorMessages)
            if(message.equals(errorMessage.getText()))
                return true;
        return false;
    }
}
