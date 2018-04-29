package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.rmi.runtime.Log;

public class LoginFormPage extends BasePage {


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin (String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("Login")).sendKeys(login);
        return this;
    }

    public LoginFormPage typePassword(String password) {
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public SecretaPage clicarSignIn() {
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new SecretaPage(navegador);
    }

    public SecretaPage fazerLogin(String login, String senha) {
        typeLogin(login);
        typePassword(senha);
        clicarSignIn();

        return new SecretaPage(navegador);
    }

}
