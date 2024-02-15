package bg.mantov.pageobjects;

import bg.mantov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class='ng-star-inserted'] td:nth-child(3)")
    List<WebElement> productsOrdered;

    public boolean isInOrdersHistory(String productName) {
        return productsOrdered.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }
}
