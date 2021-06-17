package common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.chrome.ChromeDriver;

public class WebAPI {

    public static ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }
    @AfterEach
    public void afterEachTestMethodBDD(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        DateFormat df = new SimpleDateFormat("(yyMMddHHmmssZ)");
        //DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

       // File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("/Users/abestaieb/SeleniumReview/src/RefreshSelenium/ref.png"));

        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }

    }

    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

    //Browser SetUp
    public static WebDriver driver = null;
    public String browserstack_username = "abes2";
    public String browserstack_accesskey = "dGpR3twU2pLPLgXZxmSa";
    public String saucelabs_username = "";
    public String saucelabs_accesskey = "";

    public void openBrowser() throws IOException {
        setUp(false,"browserstack","OS X","catalina","chrome","88","https://www.Amazon-flight.com/");
    }
//

    @Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("false") String cloudEnvName,
                      @Optional("mac") String os, @Optional("10") String os_version, @Optional("chrome-options") String browserName, @Optional("88")
                              String browserVersion, @Optional("https://www.google.com") String url) throws IOException {

        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
            }
        } else {
            getLocalDriver(os, browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.get(url);
        //driver.manage().window().maximize();
    }

    public WebDriver getLocalDriver(@Optional("mac") String OS, String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", "/Users/abestaieb/InterviewQuestions/Generic/src/main/java/MacDriver/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "/Users/abestaieb/InterviewQuestions/Generic/src/main/java/MacDriver/chromedriver");
            }
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("chrome-options")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", "/Users/abestaieb/InterviewQuestions/Generic/src/main/java/MacDriver/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "/Users/abestaieb/InterviewQuestions/Generic/src/main/java/MacDriver/chromedriver");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/BrowserDriver/mac/geckodriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/BrowserDriver/windows/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../Generic/BrowserDriver/windows/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os, String os_version, String browserName,
                                    String browserVersion) throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);

        if (envName.equalsIgnoreCase("Saucelabs")) {
            //resolution for Saucelabs
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        //driver.close();
        driver.quit();
    }
    // helper methodes
    public void elements(String Xpath1,String keyWord,String GXpath,String element)throws InterruptedException{

        driver.findElement(By.xpath(Xpath1)).click();
        driver.findElement(By.xpath(Xpath1)).sendKeys(keyWord);
        List<WebElement> list=driver.findElements(By.xpath(GXpath));
        for(int i=0;i<list.size();i++){
            if(list.get(i).getText().contains(element)){
                list.get(i).click();
                break;
            }
        }
    }
    public void Alert()throws InterruptedException{
        String alert=driver.switchTo().alert().getText();
        System.out.println("the alert text: "+alert);
        //alert.accept();
        //alert.
        Thread.sleep(2000);
    }

    public void handleNotifications(String url ){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver=new ChromeDriver(options);
        driver.get(url);
    }
    public void scenarioOutLine(String usernameXpath,String usernameEmail,String passwordXpath,String password){
        driver.findElement(By.xpath(usernameXpath)).sendKeys(usernameEmail);
        driver.findElement(By.xpath(passwordXpath)).sendKeys(password);
    }
    public void validate(String Xpath,String ExpectedResult) {
        WebElement element=driver.findElement(By.xpath(Xpath));
        String actualResult = element.getText();
        Assert.assertEquals("Search Item not match", ExpectedResult, actualResult);
    }
    public void selectElements(String Xpath,String GXpath,String element){
        driver.findElement(By.xpath(Xpath)).click();
          List<WebElement>  list=driver.findElements(By.xpath(GXpath));
          for(int i=0;i<list.size();i++){
              if(list.get(i).getText().contains(element)){
                  list.get(i).click();
                  break;
              }
          }
    }
    public void selectAnyElements(String Xpath,String GXpath,String element){
        driver.findElement(By.xpath(Xpath)).click();
        List<WebElement>  list=driver.findElements(By.xpath(GXpath));
        for(int i=0;i<list.size();i++){
            if(list.get(i).getText().contains(element)){
                list.get(i).click();
                break;
            }
        }
    }

}
