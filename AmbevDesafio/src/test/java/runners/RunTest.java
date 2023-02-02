package runners;


import configUtils.ConfigFramework;
import configUtils.Drivers;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        tags = "@TesteCompleto",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/RelatorioExecucao.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
                "junit:target/cukes/junit.xml",
        })
public class RunTest {

    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public static void afterClass() {
        Drivers.closeDriver(ConfigFramework.getBrowser());
    }

}