package bg.mantov.pageobjects;

import bg.mantov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".em-spacer-1 .ng-star-inserted")
    WebElement orderId;

    @FindBy(className = "hero-primary")
    WebElement confirmationMsg;

    public boolean isConfirmed() {
        return orderId.isDisplayed();
    }

    public String confirmationMsg() {
        return confirmationMsg.getText();
    }
}


