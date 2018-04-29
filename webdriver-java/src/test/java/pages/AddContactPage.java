package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddContactPage extends BasePage {
    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage escolherTipoDeContato(String tipo) {
       WebElement campoType =  navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new org.openqa.selenium.support.ui.Select(campoType).selectByVisibleText(tipo);
        return this;
    }

    public AddContactPage digitarContato(String contato) {
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
        return this;
    }

    public MePage clicarNoSalvar() {
        navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
        return new MePage(navegador);

    }

    public MePage adicionarContato(String tipo, String contato) {
        escolherTipoDeContato(tipo);
        digitarContato(contato);
        clicarNoSalvar();

        return new MePage(navegador);
    }
}
