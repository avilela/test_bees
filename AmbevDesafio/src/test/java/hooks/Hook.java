package hooks;

import configUtils.Drivers;
import configUtils.PropertiesManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static configUtils.ConfigFramework.getBrowser;
import static configUtils.Drivers.abrirBrowser;
import static configUtils.Drivers.closeDriver;

public class Hook {

    public static void iniciarWeb() {
        String userProfile = "C:\\Users\\" + System.getenv("USERNAME") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--start-maximized");
        // options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-session-crashed-bubble");
        //options.addArguments("--enable-popup-blocking");
        //////////////////////////////////////////////////////////
        //o uso da aba anonima evita acumulo de cache do navegador
        //////////////////////////////////////////////////////////
        options.addArguments("--incognito");
        options.setAcceptInsecureCerts(true);
//        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        abrirBrowser("chrome", options, "sim");
        /*WebElement html = getBrowser().findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
*/
    }

    public static void iniciarApp() {
        //Hook.iniciarAPP();
        DesiredCapabilities caps = new DesiredCapabilities();
        PropertiesManager userMobileCenter = new PropertiesManager("src/test/resources/properties/uftMobile/mobile.properties");
        caps.setCapability("oauthClientId", userMobileCenter.getProps().getProperty("oauthClientId"));
        caps.setCapability("oauthClientSecret", userMobileCenter.getProps().getProperty("oauthClientSecret"));
        caps.setCapability("tenantId", userMobileCenter.getProps().getProperty("tenantId"));
        caps.setCapability("mcWorkspaceName", userMobileCenter.getProps().getProperty("mcWorkspaceName"));
        caps.setCapability("udid", userMobileCenter.getProps().getProperty("udid"));
        caps.setCapability("appPackage", userMobileCenter.getProps().getProperty("appPackage"));
        caps.setCapability("appActivity", userMobileCenter.getProps().getProperty("appActivity"));
        caps.setCapability("browserName", "");
        caps.setCapability("noReset", false);
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("automationName", "UiAutomator2");
        //startMobile(caps);
    }

    @Before
    public void init() {

    }

    @After
    public void cleanUp(Scenario scenario) {
        closeDriver(getBrowser());
    }
}