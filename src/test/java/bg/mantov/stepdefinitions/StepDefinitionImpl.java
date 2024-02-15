package bg.mantov.stepdefinitions;

import bg.mantov.pageobjects.*;
import bg.mantov.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    LandingPage landingPage;
    ProductCatalog productCatalog;

    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce page")
    public void i_landed_on_eccommerce_page() throws IOException {
        this.landingPage = launchApp();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {
        this.productCatalog = this.landingPage.login(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
        this.productCatalog.addToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalog.goToCart();
        Assert.assertTrue(cartPage.isInCart(productName));
        CheckoutPage orderPage = cartPage.checkout();
        orderPage.selectCountry("Bulgaria");
        this.confirmationPage = orderPage.placeOrder();
    }

    @Then("Confirmation message {string} is displayed")
    public void confirmation_message_is_displayed(String confMsg) {
        Assert.assertTrue(this.confirmationPage.confirmationMsg().equalsIgnoreCase(confMsg));
    }

    @Then("{string} is displayed")
    public void error_message_is_displayed(String errorMsg) {
        Assert.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase(errorMsg));
    }
}
