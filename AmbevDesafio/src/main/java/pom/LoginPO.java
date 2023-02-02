package pom;

import org.openqa.selenium.By;

public class LoginPO {


    public static By emailInput = By.id("user_email");
    public static By passInput = By.id("user_password");
    public static By passConfirmInput = By.id("user_password_confirmation");

    public static By btnLogin = By.xpath("//*[@value='Submit']");
    public static By btnSignUp = By.xpath("//*[@value='Sign up']");

    public static By linkCadastro = By.xpath("//a[text()='Sign up']");

    public static By textosNaTela(String texto) {
        return By.xpath("//*[contains(text(), '" + texto + "')]");
    }


}
