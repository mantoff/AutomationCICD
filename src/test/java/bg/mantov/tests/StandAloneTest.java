package bg.mantov.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//new comment added for demo purposes
        String productName = "ZARA COAT 3";

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("mantov@abc.com");
        driver.findElement(By.id("userPassword")).sendKeys("Pa$$1234");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement desiredItem = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
        desiredItem.findElement(By.cssSelector(".btn.w-10.rounded")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

        List<WebElement> productsInCart = driver.findElements(By.cssSelector("li div h3"));
        Assert.assertTrue(productsInCart.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName)));
        driver.findElement(By.cssSelector(".totalRow button")).click();

        driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("bu");
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted span"));
        countries.stream().filter(c-> c.getText().equalsIgnoreCase("Bulgaria")).findFirst().get().click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollTo(0, 400)");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".action__submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".em-spacer-1 .ng-star-inserted")).isDisplayed());
        //driver.close();
    }
}
