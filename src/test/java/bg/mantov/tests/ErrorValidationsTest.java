package bg.mantov.tests;

import bg.mantov.pageobjects.CartPage;
import bg.mantov.pageobjects.ProductCatalog;
import bg.mantov.testcomponents.BaseTest;

import bg.mantov.testcomponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {
        landingPage.login("mantov@abc.com", "dsaafsfsa");
        Assert.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase("incorrect email or password."));
    }

    @Test
    public void productErrorValidation() {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.login("mantov@abc.com", "Pa$$1234");
        productCatalog.addToCart(productName);
        CartPage cartPage = productCatalog.goToCart();
        Assert.assertFalse(cartPage.isInCart(productName + "3"));
    }
}
