package bg.mantov.testcomponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtility extends BaseTest {

    public String getScreenshotPath (String testCaseName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png"));
        return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
    }
}
