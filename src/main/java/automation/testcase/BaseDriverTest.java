package automation.testcase;

import automation.config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base test class for all UI tests.
 *
 * <p>Manages Selenium WebDriver lifecycle:
 * <ul>
 *   <li>{@code @BeforeClass} — launches browser, exposes {@link #driver} and {@link #wait}</li>
 *   <li>{@code @AfterClass} — quits browser after all tests in the class complete</li>
 * </ul>
 *
 * <p>Subclasses override {@link #initInstance()} to initialize their Page Object instances.
 */
public abstract class BaseDriverTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setup() {
        DriverConfig config = DriverConfig.getInstance();
        config.initialize();
        this.driver = config.getDriver();
        this.wait   = config.getWait();
        initInstance();
    }

    /**
     * Called after WebDriver is ready.
     * Override to instantiate Page Objects and Models.
     */
    public void initInstance() {
        // Override in subclasses
    }

    @AfterClass
    public void tearDown() {
        DriverConfig.getInstance().tearDown();
    }
}
