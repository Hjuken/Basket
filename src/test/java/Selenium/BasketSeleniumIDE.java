package Selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class BasketSeleniumIDE {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void basket2() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().setSize(new Dimension(1263, 717));
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys("11/04/2024");
        {
            WebElement element = driver.findElement(By.cssSelector(".row:nth-child(6) > .col-sm-6:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".row:nth-child(3) > .col-sm-6:nth-child(1) > .form-md-floating-label"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.id("signup_form")).click();
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys("11/04/1986");
        driver.findElement(By.cssSelector("#yourDetailsHeading > span:nth-child(2)")).click();
        driver.findElement(By.id("member_firstname")).click();
        driver.findElement(By.id("member_firstname")).sendKeys("gunnel");
        driver.findElement(By.id("member_lastname")).click();
        driver.findElement(By.id("member_lastname")).sendKeys("andersson");
        driver.findElement(By.id("member_emailaddress")).click();
        driver.findElement(By.id("member_emailaddress")).sendKeys("cimoj28431@kravify.com");
        driver.findElement(By.id("member_confirmemailaddress")).click();
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("cimoj28431@kravify.com");
        driver.findElement(By.id("signupunlicenced_password")).click();
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("basket");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).click();
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("basket");
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(12) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
        driver.findElement(By.name("join")).click();
    }
}

