package web;

import configUtils.ConfigFramework;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtils extends ConfigFramework {

    public static void openURL(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static Wait esperaFluente(WebDriver driver, int seconds) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(seconds / 2))
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public static boolean isElementoPresente(WebDriver driver, By by, int tempoEspera) {
        boolean isPresente;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            isPresente = true;
        } catch (Exception e) {
            isPresente = false;
        }
        return isPresente;
    }

    public static boolean isElementoVisivel(WebDriver driver, By by, int tempoEspera) {
        boolean isVisivel;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            WebElement element = driver.findElement(by);
            Assert.assertTrue(element.isDisplayed());
            isVisivel = true;
        } catch (Exception e) {
            isVisivel = false;
        }
        return isVisivel;
    }

    public static void clickjs(WebDriver driver, By locator, int tempoEspera) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
        wait1.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait1.until(ExpectedConditions.elementToBeClickable(locator));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(locator));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    public static void fillInputjs(WebDriver driver, By locator, String text, int tempoEspera) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + text + "'", element);
    }

    public static void scroll(WebDriver driver, int ammount) {
        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("window.scrollBy(0," + ammount + ")", "");
    }

    public static int getExistingElement(WebDriver driver, By by, int tempoEspera) {
        try {
            WebElement aguardaElemento = (new WebDriverWait(driver, Duration.ofSeconds(tempoEspera)))
                    .until(ExpectedConditions.presenceOfElementLocated((by)));
            System.out.println("Elemento " + by + ", detectado");
            return 1;
        } catch (
                Exception e) {
            System.err.println("Elemento " + by + ", NAO detectado");
            System.out.println("O elemento [{ " + by.toString() + "}] foi identificado com sucesso.");
            return 0;
            //##utilizar assert erro com msg##
        }
    }

    public static String getText(WebDriver driver, By by, int tempoEspera) {
        boolean isPresente = isElementoPresente(getBrowser(), by, tempoEspera);
        String valorObtido = "";
        if (isPresente) {
            WebElement element = driver.findElement(by);
            valorObtido = element.getText();
            return valorObtido;
        } else {
            Assert.fail("Erro ao procurar o elemento " + by + ", se certifique que o layout dos objetos se manteve!");
            return valorObtido;
        }
    }

    public static String getAtributeTitle(WebDriver driver, By by, int tempoEspera) {
        boolean isPresente = isElementoPresente(getBrowser(), by, tempoEspera);
        String valorObtido = "";
        if (isPresente) {
            WebElement element = driver.findElement(by);
            valorObtido = element.getAttribute("title");
            return valorObtido;
        } else {
            Assert.fail("Erro ao procurar o elemento " + by + ", se certifique que o layout dos objetos se manteve!");
            return valorObtido;
        }
    }

    public static void implicitoWait(WebDriver driver, By by, int tempoEspera) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
        wait1.until(ExpectedConditions.presenceOfElementLocated(by));
        wait1.until(ExpectedConditions.elementToBeClickable(by));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
    }

    public static WebElement fillInput(WebDriver driver, By by, String valor, int tempoEspera) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element = driver.findElement(by);
            element = driver.findElement(by);
            element.click();
            element.clear();
            element.sendKeys(valor.trim());
        } catch (Exception e) {
            System.err
                    .println("Ação input, elemento - " + by + " não encontrado, " + "tempo de espera = " + tempoEspera + "s: " + e.toString());
            Assert.fail("Ação input, elemento - " + by + " não encontrado, " + "tempo de espera = " + tempoEspera + "s: " + e.toString());
        }
        return element;
    }

    public static void scrollToElement(WebDriver driver, By by, int tempoEspera) {
        boolean isPresente = isElementoPresente(driver, by, tempoEspera);
        if (isPresente) {
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } else {
            Assert.fail("Erro ao obter elemento " + by + " para realizar scroll");
        }
    }

    public static void click(WebDriver driver, By by, int tempoEspera) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.err
                    .println("Ação click, elemento - " + by + " não encontrado, " + "tempo de espera = " + tempoEspera + "s");
            Assert.fail("Ação click, elemento - " + by + " não encontrado, " + "tempo de espera = " + tempoEspera + "s");
        }
        try {
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            System.err.println("Ação click, elemento - " + by + " interceptado, " + e);
            Assert.fail("Ação click, elemento - " + by + " interceptado, " + e);
        }
    }

}
