package configUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

public class Drivers extends ConfigFramework {


    // Metodo para abrir browser. Ex: 'Firefox' = Abrir com Firefox | 'Chrome' = Abrir com Chrome
    public static void abrirSite(String browser, String url) {
        WebDriver driver;

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
            driver = new FirefoxDriver();

        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.get(url);
        driver.manage().window().maximize();

        setBrowser(driver);
    }


    public static void abrirBrowser(String browser, ChromeOptions options, String visivel) {

        if (browser.equalsIgnoreCase("chrome")) {

            Map<String, String> prefs = new HashMap<String, String>();
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
            prefs.put("binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

            if (visivel == "nao") {
                options.addArguments("headless");
            }

            options.setExperimentalOption("prefs", prefs);
            //  options.addArguments("user-data-dir=C:\\Users\\automacao\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

            // setup the chromedriver using WebDriverManager
            //io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

            // Create driver object for Chrome
            setBrowser(new ChromeDriver(options));
            getBrowser().manage().window().maximize();

        }

    }

    public static void abrirBrowser(String escala) {

        Map<String, String> prefs = new HashMap<String, String>();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ricardo.vaz.d.junior\\IdeaProjects\\FrameSF\\src\\test\\resources\\drivers\\chromedriver.exe");
        prefs.put("binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-session-crashed-bubble");
        if (escala.contains("sim")) {
            options.addArguments("-force-device-scale-factor=0.8");
        }
        //options.addExtensions(new File(ConstantsPath.SALESFORCE_CRX));

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("user-data-dir=C:\\Users\\automacao\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

        // setup the chromedriver using WebDriverManager
        //io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        // Create driver object for Chrome
        setBrowser(new ChromeDriver(options));
        getBrowser().manage().window().maximize();
    }


    public static void closeDriver(WebDriver driver) {
        if (driver == null) {
            System.out.println("Nenhum driver aberto!");
        } else {
            driver.quit();
        }
    }
}