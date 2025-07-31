package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "E:/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notification");
        
        webDriver = new EdgeDriver(options);
        webDriver.get("https://dev-dash.janitri.in/");
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
