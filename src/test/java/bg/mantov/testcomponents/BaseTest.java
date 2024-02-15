package bg.mantov.testcomponents;

import bg.mantov.data.DataReader;
import bg.mantov.pageobjects.LandingPage;
import bg.mantov.tests.SubmitOrderTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;

    public LandingPage landingPage;

    public DataReader dataReader;

    public WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        //Give the location of the .properties file
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java/bg//mantov//resources//GlobalData.properties");
        //Below method loads the file and extracts all key-value pairs from it
        properties.load(fileInputStream);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            //OPTIONAL: we can add below line to make sure the window is maximized and the elements are visible, despite running in headless
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browserName.equalsIgnoreCase("firefox")) {
            //Firefox
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        return driver;
    }

    public DataReader getDataReader() {
        this.dataReader = new DataReader();
        return this.dataReader;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        this.driver = initDriver();
        this.landingPage = new LandingPage(initDriver());
        this.landingPage.goTo();
        return this.landingPage;
    }

    /*@AfterMethod
    public void tearDown() {
        this.driver.close();
    }*/
}
