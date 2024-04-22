package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    WebDriver driver;

    @Given("I am using browser {string}")
    public void iAmUsingBrowser(String browser) {

        if (browser.equals("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() throws InterruptedException {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        Thread.sleep(2000);
    }

    @And("I enter date of birth {string}")
    public void iEnterDateOfBirth(String date) {
        WebElement dateOfBirth = driver.findElement(By.id("dp"));
        dateOfBirth.sendKeys(date);
    }

    @And("I enter first name {string}")
    public void iEnterFirstName(String first) {
        WebElement firstName = driver.findElement(By.id("member_firstname"));
        firstName.sendKeys(first);
    }

    @And("I enter last name {string}")
    public void iEnterLastName(String last) throws InterruptedException {
        WebElement lastName = driver.findElement(By.id("member_lastname"));
        lastName.sendKeys(last);
        Thread.sleep(1000);
    }

    @And("I enter email and confirms email")
    public void iEnterEmailAndConfirmsEmail() {

        String randomEmail = generateRandomEmail();

        WebElement emailInput = driver.findElement(By.id("member_emailaddress"));
        emailInput.sendKeys(randomEmail);

        WebElement emailConfirmInput = driver.findElement(By.id("member_confirmemailaddress"));
        emailConfirmInput.sendKeys(randomEmail);
    }

    public String generateRandomEmail() {
        Random random = new Random();
        int randInt = random.nextInt(10000);
        return "rebecka" + randInt + "@mail.com";
    }

    @And("I enter password {string} and confirms password {string}")
    public void iEnterPasswordAndConfirmsPassword(String password, String passwordConfirm) throws InterruptedException {

        WebElement passwordInput = driver.findElement(By.id("signupunlicenced_password"));
        passwordInput.sendKeys(password);

        WebElement passwordConfirmInput = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        passwordConfirmInput.sendKeys(passwordConfirm);

        WebElement clickOnSomething = driver.findElement(By.cssSelector("body > div > div.page-wrapper"));
        clickOnSomething.click();
        Thread.sleep(2000);
    }

    @And("I {} accept Terms and conditions")
    public void iAcceptTermsAndConditions(String acceptTerms) throws InterruptedException {
        if (acceptTerms.equals("true")) {
            WebElement termsAndConditions = driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > label > span.box"));
            termsAndConditions.click();
            Thread.sleep(2000);
        }
    }

    @And("I {} confirm that I am over 18")
    public void iConfirmThatIAmOver(String confirmAge) {
        if (confirmAge.equals("true")) {
            WebElement overEighteen = driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div.md-checkbox.margin-top-10 > label > span.box"));
            overEighteen.click();
        }
    }

    @And("I {} accept the Code of ethics and conduct")
    public void iAcceptTheCodeOfEthicsAndConduct(String acceptEthics) {
        if (acceptEthics.equals("true")) {
            WebElement codeOfEthics = driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(7) > label > span.box"));
            codeOfEthics.click();
        }
    }

    @When("I click on Confirm and join")
    public void iClickOnConfirmAndJoin() throws InterruptedException {
        WebElement join = driver.findElement(By.name("join"));
        join.click();
        Thread.sleep(2000);
    }

    @Then("I see the result {string}")
    public void iSeeTheResult(String result) throws InterruptedException {
        switch (result) {
            case "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND":
                resultMessage(result);
                break;
            case "Last Name is required":
                errorMessage(result, "#signup_form > div:nth-child(6) > div:nth-child(2) > div > span");
                break;
            case "Password did not match":
                errorMessage(result, "#signup_form > div:nth-child(9) > div > div.row > div:nth-child(2) > div > span > span");
                break;
            case "You must confirm that you have read and accepted our Terms and Conditions":
                errorMessage(result, "#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > span");
                break;
            default:
                throw new IllegalStateException("Unexpected result: " + result);
        }
        Thread.sleep(2000);
    }

    private void resultMessage(String expected) {
        WebElement title = driver.findElement(By.cssSelector("body > div > div.page-content-wrapper > div > h2"));
        String actual = title.getText();
        assertEquals(expected, actual);
    }

    private void errorMessage(String expected, String cssSelector) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(5))).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        String actual = element.getText();
        assertEquals(expected, actual);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
