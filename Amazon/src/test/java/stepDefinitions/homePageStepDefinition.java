package stepDefinitions;

import common.WebAPI;
import homePage.searchPage;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static homePage.Locators.*;

public class homePageStepDefinition extends WebAPI {


    static searchPage hom;

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // Take a screenshot...
            File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("/Users/abestaieb/BDDReview/Amazon/screenShot.png"));

        }
    }
    @After
    public void closeBrowser(){
        cleanUp();
    }

    @BeforeStep
    public static void getInit(){

        hom= PageFactory.initElements(driver,searchPage.class);
    }


    @Given("user on amazon home page")
    public void user_on_amazon_home_page() throws IOException {
             System.setProperty("webdriver.chrome.driver","/Users/abestaieb/InterviewQuestions/Generic/src/main/java/MacDriver/chromedriver");
        WebDriver driver=new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.Amazon.com");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @When("user click on all to select books section")
    public void user_click_on_all_to_select_books_section() {
    hom.selectAnyElements(webElementSearchAll,webElementAllOptions,"Books");
    }

    @When("user enter your book name on search box option")
    public void user_enter_your_book_name_on_search_box_option() {
        hom.setSearchBox();
        hom.setSearchBar();
    }

    @Then("user validate your book is displaying properly")
    public void user_validate_your_book_is_displaying_properly() {
    String actual="";
    String expected="";
        Assert.assertEquals(actual,expected);
    }

    @When("user click on add to cart")
    public void user_click_on_add_to_cart() {
          hom.setClickItem();
        hom.setAddToCart();
    }

    @When("user click on cart option")
    public void user_click_on_cart_option() {
           hom.setClickCart();
    }

    @Then("user proceed to check out")
    public void user_proceed_to_check_out() {
         hom.setProceedToCheckOut();
    }


}
