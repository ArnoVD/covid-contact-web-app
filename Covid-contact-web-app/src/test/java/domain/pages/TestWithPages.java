package domain.pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class TestWithPages {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsLeftEmpty_ShowsFormWithErrorMessages() {
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("");
        registerPage.setFirstName("");
        registerPage.setLastName("");
        registerPage.setEmail("robertpalmer@music.com");
        registerPage.setPassword("");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check sticky values
        assertTrue(registerPage.hasEmptyFirstName());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("No firstname given"));
        // Check that person is not registered
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertFalse(overviewPage.containsUserWithEmail("robertpalmer@music.com"));
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitValid();

        // Then
        // Check correct page
        assertEquals("Home", registerPage.getTitle());
        // Check that person is registered
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertTrue(overviewPage.containsUserWithEmail("jimcarrey@hotmail.com"));
    }

    @Test
    public void test_Register_UseridNotFilledIn_ErrorMessageGivenForUseridAndOtherFieldsValueKept(){
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check sticky values
        assertTrue(registerPage.hasEmptyUserid());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("No userid given"));
        // Check sticky first name, last name, email and password
        assertTrue(registerPage.hasStickyFirstName("Jim"));
        assertTrue(registerPage.hasStickyLastName("Carrey"));
        assertTrue(registerPage.hasStickyEmail("jimcarrey@hotmail.com"));
        assertTrue(registerPage.hasStickyPassword("t"));
    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check sticky values
        assertTrue(registerPage.hasEmptyFirstName());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("No firstname given"));
        // Check sticky userid, last name, email and password
        assertTrue(registerPage.hasStickyUserid("jimmy"));
        assertTrue(registerPage.hasStickyLastName("Carrey"));
        assertTrue(registerPage.hasStickyEmail("jimcarrey@hotmail.com"));
        assertTrue(registerPage.hasStickyPassword("t"));
        // Check that person is not registered
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertFalse(overviewPage.containsUserWithEmail("jimcarrey@hotmail.com"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check sticky values
        assertTrue(registerPage.hasEmptyLastName());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("No last name given"));
        // Check sticky userid, first name, email and password
        assertTrue(registerPage.hasStickyUserid("jimmy"));
        assertTrue(registerPage.hasStickyFirstName("Jim"));
        assertTrue(registerPage.hasStickyEmail("jimcarrey@hotmail.com"));
        assertTrue(registerPage.hasStickyPassword("t"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("");
        registerPage.setPassword("t");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check sticky values
        assertTrue(registerPage.hasEmptyEmail());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("No email given"));
        // Check sticky userid, first name, last name and password
        assertTrue(registerPage.hasStickyUserid("jimmy"));
        assertTrue(registerPage.hasStickyFirstName("Jim"));
        assertTrue(registerPage.hasStickyLastName("Carrey"));
        assertTrue(registerPage.hasStickyPassword("t"));
        // Check that person is not registered
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertFalse(overviewPage.containsUserWithEmail("jimcarrey@hotmail.com"));
    }

    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        // Given
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        // When
        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check sticky values
        assertTrue(registerPage.hasEmptyPassword());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("No password given"));
        // Check sticky userid, first name, last name and password
        assertTrue(registerPage.hasStickyUserid("jimmy"));
        assertTrue(registerPage.hasStickyFirstName("Jim"));
        assertTrue(registerPage.hasStickyLastName("Carrey"));
        assertTrue(registerPage.hasStickyEmail("jimcarrey@hotmail.com"));
        // Check that person is not registered
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertFalse(overviewPage.containsUserWithEmail("jimcarrey@hotmail.com"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        // Add first user
        RegisterPage registerPage2 = PageFactory.initElements(driver, RegisterPage.class);

        registerPage2.setUseridField("jimmy");
        registerPage2.setFirstName("Jim");
        registerPage2.setLastName("Carrey");
        registerPage2.setEmail("jimcarrey@hotmail.com");
        registerPage2.setPassword("t");
        registerPage2.submitValid();

        // Register second user with same userid
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitInvalid();

        // Then
        // Check correct page
        assertEquals("Sign Up", registerPage.getTitle());
        // Check error message
        assertTrue(registerPage.hasErrorMessage("User already exists"));
        // Check sticky userid, first name, last name and password
        assertTrue(registerPage.hasStickyUserid("jimmy"));
        assertTrue(registerPage.hasStickyFirstName("Jim"));
        assertTrue(registerPage.hasStickyLastName("Carrey"));
        assertTrue(registerPage.hasStickyEmail("jimcarrey@hotmail.com"));
        assertTrue(registerPage.hasStickyPassword("t"));
    }

    @Test
    public void test_userDeleted_NotFoundInOverview(){
        // Delete default three users
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        overviewPage.deleteDefaultPersons();

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitValid();

        OverviewPage overviewPage2 = PageFactory.initElements(driver, OverviewPage.class);
        // Delete Jim Carrey
        overviewPage2.deleteValid();

        // Then
        // Check correct page
        assertEquals("Overview", registerPage.getTitle());
        // Check if user still in list
        assertFalse(overviewPage2.containsUserWithEmail("jimcarrey@hotmail.com"));
        // Check if empty list H2 is on page
        assertEquals("There are currently no persons registered.", overviewPage2.getEmptyListH2Value());
    }

    /******** TESTS CONTACT PAGE ********/

    @Test
    public void test_ContactDeleted_Succesfully(){
        ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);

        // Register a contact
        contactPage.setContactFirstName("Arno");
        contactPage.setContactLastName("Vandijck");
        contactPage.setContactDate("14/02/2021");
        contactPage.setContactHour("13");
        contactPage.setContactPhoneNumber("0479341128");
        contactPage.setContactEmail("vandijckarno@hotmail.com");
        contactPage.submitValid();
        
        // Count list length before delete
        int counter = contactPage.getContactOverviewListLength();

        // Delete a contact
        contactPage.deleteValid();

        // Then
        // Check correct page title
        assertEquals("Contacts", contactPage.getTitle());
        // Check the count of the list after the delete (It should be one less now)
        assertNotEquals(counter, contactPage.getContactOverviewListLength());
    }

    @Test
    public void test_SuccesfullLogin(){
        // Add a user
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitValid();

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUseridField("jimmy");
        homePage.setPasswordField("t");
        homePage.logInValid();

        // Then
        // Check correct page
        assertEquals("Home", homePage.getTitle());
        // Check welcome text
        assertEquals("Welcome, Jim", homePage.getWelcomeParagraphText());
    }

    @Test
    public void test_FailedLogin(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        // Credentials not registered
        homePage.setUseridField("Kool &");
        homePage.setPasswordField("The Gang");
        homePage.logInInvalid();

        // Check correct page
        assertEquals("Home", homePage.getTitle());
        // Check error message
        assertEquals("User id or password is incorrect.", homePage.getErrorParagraph());
    }

    @Test
    public void test_EmptyLogin(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        // Empty credentials
        homePage.setUseridField("");
        homePage.setPasswordField("");
        homePage.logInInvalid();

        // Check correct page
        assertEquals("Home", homePage.getTitle());
        // Check error message
        assertEquals("User id or password is incorrect.", homePage.getErrorParagraph());
    }

    @Test
    public void test_Existing_Userid_With_EmptyPassword_Login(){
        // Add a user
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitValid();

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        // Empty password with existing userid
        homePage.setUseridField("jimmmy");
        homePage.setPasswordField("");
        homePage.logInInvalid();

        // Check correct page
        assertEquals("Home", homePage.getTitle());
        // Check error message
        assertEquals("User id or password is incorrect.", homePage.getErrorParagraph());
    }

    @Test
    public void test_LogOutButton(){
        // Add a user
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitValid();

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        // Login with registered user
        homePage.setUseridField("jimmy");
        homePage.setPasswordField("t");
        homePage.logInValid();

        // Click on log out button
        homePage.logOutValid();

        // Check correct page
        assertEquals("Home", homePage.getTitle());
        // Check if login paragraph is there
        assertEquals("Please log in", homePage.getLoginParagraph());
    }

    public void registerUser(){
        // Add a user
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);

        registerPage.setUseridField("jimmy");
        registerPage.setFirstName("Jim");
        registerPage.setLastName("Carrey");
        registerPage.setEmail("jimcarrey@hotmail.com");
        registerPage.setPassword("t");
        registerPage.submitValid();
    }
}
