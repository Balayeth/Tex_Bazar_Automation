package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Scanner;

public class Registration {
    private WebDriver driver;
    @Before
    public void setup() throws InterruptedException {
        System.setProperty("WebDriver.chrome.driver", System.getProperty("user.dir") + "src/main/java/driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("Go to site")
    public void go_to_site() {
        driver.get("https://tex-bazar-dev.vercel.app/");
    }

    @When("Click on {string} button")
    public void click_on_button(String string) throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='/auth']")).click();
        Thread.sleep(3000);

    }

    @Then("User should be navigated to {string} form page")
    public void user_should_be_navigated_to_form_page(String string) throws InterruptedException {
        WebElement registration_form = driver.findElement(By.xpath("//p[@class='text-sm font-semibold text-black-light dark:text-black-dark sm:text-xl undefined']"));
        String actual_result= registration_form.getText();
        String expected_result= "Create a new account";
        SoftAssert reg_form=new SoftAssert();
        reg_form.assertEquals(actual_result,expected_result,"Not Matched");

    }

    @When("Enter an exiting phone number")
    public void enter_an_exiting_phone_number() throws InterruptedException {

//        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("01581578231");
        Thread.sleep(3000);
    }

    @And("Click on terms and conditions check box")
    public void click_on_terms_and_conditions_check_box() throws InterruptedException {
        driver.findElement(By.xpath("//button[@role='checkbox']")).click();
        Thread.sleep(3000);
    }

    @And("Click on Send OTP button")
    public void click_on_send_otp_button() throws InterruptedException {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
    }

    @Then("User should see an error message that contains {string}")
    public void user_should_see_an_error_message_that_contains(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-title and contains(text(), 'This number already exists, Please login')]\n")));
        Assert.assertTrue(errorPopup.getText().contains(expectedMessage), "This number already exists, Please login");
    }
    @When("Enter a new phone number")
    public void enter_a_new_phone_number() throws InterruptedException {
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("01600000001");
        Thread.sleep(3000);
    }
    @Then("User should be navigated to OTP Verification page")
    public void user_should_be_navigated_to_otp_verification_page() {
        WebElement OTP_Verification= driver.findElement(By.xpath("//p[@title='OTP Verification']"));
        String expected_title="OTP Verification";
        String actual_title=OTP_Verification.getText();
        SoftAssert otp_verification= new SoftAssert();
        otp_verification.assertEquals(expected_title,actual_title,"OTP Page title is not matched");

    }

    @When("Enter correct OTP")
    public void enter_correct_otp() {
        driver.findElement(By.id("otp")).sendKeys("123456");
    }

    @When("Click on Submit button")
    public void click_on_submit_button() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User should be navigated to complete registration page")
    public void user_should_be_navigated_to_complete_registration_page() {
        WebElement complete_reg_page= driver.findElement(By.xpath("//button[@type='submit']"));
        String expected_result="COMPLETE REGISTRATION";
        String actual_result=complete_reg_page.getText();
        SoftAssert registration_complete=new SoftAssert();
        registration_complete.assertEquals(actual_result,expected_result,"Page loading failed");

    }

    @When("Enter Company or user Name")
    public void enter_company_or_user_name() {
        driver.findElement(By.xpath("//input[@placeholder='Enter your name']")).sendKeys("Company ABCD");


    }

    @When("Enter six digits password")
    public void enter_six_digits_password() {
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
    }
    @And("Click {string} button")
    public void click_button(String string) {
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }

    @Then("User should see a success toast message which contains {string}")
    public void user_should_see_a_success_toast_message_which_contains(String string) {

    }


}
