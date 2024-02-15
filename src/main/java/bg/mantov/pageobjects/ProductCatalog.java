package bg.mantov.pageobjects;

import bg.mantov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {
    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(className = "ng-animating")
    WebElement spinner;

    public List<WebElement> getProducts() {
        waitForElementToAppear(By.cssSelector(".mb-3"));
        return products;
    }

    public WebElement getProductByName(String name) {
        return getProducts().stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(name))
                .findFirst().orElse(null);
    }

    public void addToCart(String name) {
        getProductByName(name).findElement(By.cssSelector(".btn.w-10.rounded")).click();
        waitForElementToAppear(By.id("toast-container"));
        waitForElementToDisappear(spinner);
    }
}
