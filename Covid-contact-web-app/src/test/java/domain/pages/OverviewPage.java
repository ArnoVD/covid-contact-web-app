package domain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OverviewPage extends Page{
    @FindBy(tagName = "td")
    private List<WebElement> tableElements;

    @FindBy(id="delete")
    private WebElement deleteButton;

    @FindBy(id="emptyListH2")
    private WebElement emptyListH2;

    public OverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=Overview");
    }

    public OverviewPage deleteValid(){
        deleteButton.click();
        return PageFactory.initElements(driver, OverviewPage.class);
    }

    public OverviewPage deleteDefaultPersons(){
        for (int i = 0; i <= tableElements.size() -1; i++) {
            deleteButton.click();
        }
        return PageFactory.initElements(driver, OverviewPage.class);
    }

    public boolean containsUserWithEmail(String email) {
        boolean found = false;
        for (WebElement listItem:tableElements){
            if (listItem.getText().contains(email)){
                found = true;
            }
        }
        return found;
    }

    public String getEmptyListH2Value(){return emptyListH2.getText();}
}
