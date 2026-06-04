package automation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Singleton configuration class that manages Selenium WebDriver lifecycle.
 *
 * <p>Supports Chrome and Firefox via WebDriverManager (no manual driver setup required).
 * Browser type is controlled by the {@code BROWSER} environment variable.
 */
public class DriverConfig {

    private static final Logger log = LoggerFactory.getLogger(DriverConfig.class);

    private static DriverConfig instance;
    private WebDriver driver;
    private WebDriverWait wait;

    private static final int DEFAULT_TIMEOUT_SECONDS = Integer.parseInt(
            System.getenv().getOrDefault("DEFAULT_TIMEOUT_SECONDS", "30"));

    private DriverConfig() {}

    public static synchronized DriverConfig getInstance() {
        if (instance == null) {
            instance = new DriverConfig();
        }
        return instance;
    }

    /**
     * Initializes the WebDriver based on the {@code BROWSER} env var.
     * Defaults to Chrome if not set.
     */
    public void initialize() {
        String browser = System.getenv().getOrDefault("BROWSER", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "true"));

        log.info("Initializing {} driver (headless={})", browser, headless);

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments(
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--disable-gpu",
                        "--window-size=1920,1080",
                        "--disable-extensions",
                        "--ignore-certificate-errors"
                );
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        log.info("WebDriver initialized successfully");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("WebDriver closed");
        }
        instance = null;
    }
}
