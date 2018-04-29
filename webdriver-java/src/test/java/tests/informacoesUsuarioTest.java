package tests;

import static org.junit.Assert.*;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTestData.csv")
public class informacoesUsuarioTest {
    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();

    @Before
    public void setup(){
        navegador = Web.createChrome();

        //Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        //OUTRA FORMA - Primeiro identificar um elemento e depois interagir com ele.
        //WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
        //linkSignIn.click();

        //Identificando o formulário de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "Longin" que está dentro do formulário de ID "Signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com name "Password" que está dentro do formulário de IS "Signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no  link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato") String contato, @Param(name="mensagem") String mensagemEsperada) {
        //Clicar no botão através do seu xpath: //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde está o formulario de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new org.openqa.selenium.support.ui.Select(campoType).selectByVisibleText(tipo);

        //No campo de name "contact" digitar "+5514998004564"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE", que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals (mensagemEsperada, mensagem);


        /*Validar que dentro do elemento com class "me" está o texto "Hi, Julio".
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio", textoNoElementoMe);*/

    }
    @Test
    public void removerUmContatoDeUmUsuario(){
        //Clicar no elemento pelo seu xpath //span[text()"+5514998004564"/following-sibling=a
        navegador.findElement(By.xpath("//span[text()=\"+5514998004564\"]/following-sibling::a")).click();

        //Confirmar a janela Java Script
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi Reast in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "C:\\Users\\luana.linares\\OneDrive - Centro Paula Souza - FATEC\\Backup\\Documentos\\Cursos\\Automação de Testes com Java\\Screenshots\\" + Generator.dataHoraParaArquivo() + test.getMethodName()   + ".png";
        Screenshot.tirar(navegador,screenshotArquivo);

        //Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Clicar no link com o texto Logout
        navegador.findElement(By.linkText("Logout")).click();
    }


    @After
    public void tearDown(){
        //Fechar o navegador
        //navegador.quit(); - Fecha todas as abas abertas;
        //navegador.close(); - Fecha a aba em uso.
        //navegador.close();
    }
}
