package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

public class BaseTest {

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.verboseLogging", "false");

        String driverPath = System.getProperty("webdriver.edge.driver", "E:/msedgedriver.exe");
        System.setProperty("webdriver.edge.driver", driverPath);

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");

        webDriver = new EdgeDriver(options);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        webDriver.manage().window().maximize();
        webDriver.get("https://dev-dash.janitri.in/");

        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
