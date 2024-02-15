package bg.mantov.tests;

import bg.mantov.pageobjects.*;
import bg.mantov.testcomponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(Map<String, String> input) throws InterruptedException {
        ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("password"));
        productCatalog.addToCart(input.get("product"));
        CartPage cartPage = productCatalog.goToCart();
        Assert.assertTrue(cartPage.isInCart(input.get("product")));
        CheckoutPage orderPage = cartPage.checkout();
        orderPage.selectCountry(input.get("country"));
        ConfirmationPage confirmationPage = orderPage.placeOrder();
        //Assert.assertTrue(confirmationPage.isConfirmed());
        Assert.assertTrue(confirmationPage.confirmationMsg().equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistory() {
        ProductCatalog productCatalog = landingPage.login("mantov@abc.com", "Pa$$1234");
        OrdersPage ordersPage = productCatalog.goToOrders();
        Assert.assertTrue(ordersPage.isInOrdersHistory(productName));
    }


    @DataProvider
    public Object[] getData() throws IOException {

        List<HashMap<String, String>> dataSets = getDataReader().convertJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/bg/mantov/data/purchaseOrder.json");
        return new Object[]{dataSets.get(0), dataSets.get(1)};

        /*Map<String, String> dataSet = new HashMap<>();
        dataSet.put("email", "mantov@abc.com");
        dataSet.put("password", "Pa$$1234");
        dataSet.put("product", "ZARA COAT 3");
        dataSet.put("country", "Bulgaria");

        Map<String, String> dataSet1 = new HashMap<>();
        dataSet1.put("email", "fantov@afa.com");
        dataSet1.put("password", "Pa$$1234");
        dataSet1.put("product", "ADIDAS ORIGINAL");
        dataSet1.put("country", "el salvador");*/
    }
}
