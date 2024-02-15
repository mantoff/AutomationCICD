package bg.mantov.pageobjects;

import bg.mantov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;

    @FindBy(css = "li div h3")
    List<WebElement> productsInCart;

    public boolean isInCart(String productName) {
        return productsInCart.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage checkout() {
        checkoutBtn.click();
        return new CheckoutPage(this.driver);
    }
}
