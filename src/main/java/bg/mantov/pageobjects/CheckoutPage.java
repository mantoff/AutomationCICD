package bg.mantov.pageobjects;

import bg.mantov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder*='Country']")
    WebElement countryField;

    @FindBy(css = ".ta-item.list-group-item.ng-star-inserted span")
    List<WebElement> countries;

    @FindBy(css = ".action__submit")
    WebElement submitBtn;

    public void selectCountry(String countryName) {
        countryField.sendKeys(countryName);
        countries.stream().filter(c-> c.getText().equalsIgnoreCase(countryName)).findFirst().get().click();
    }

    public ConfirmationPage placeOrder() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollTo(0, 400)");
        Thread.sleep(1000);
        submitBtn.click();
        return new ConfirmationPage(this.driver);
    }
}
