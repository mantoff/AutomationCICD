package bg.mantov.abstractcomponents;

import bg.mantov.pageobjects.CartPage;
import bg.mantov.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink='/dashboard/cart']")
    WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersButton;

    public void waitForElementToAppear(By findBy) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebElement element) {
        this.wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCart() {
        cartButton.click();
        return new CartPage(this.driver);
    }

    public OrdersPage goToOrders() {
        ordersButton.click();
        return new OrdersPage(this.driver);
    }
}
