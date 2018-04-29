package suporte;

import com.sun.jna.platform.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Screenshot {
    public static void tirar(WebDriver navegador, String arquivo) {
        File screenshot = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
        try {
        org.apache.commons.io.FileUtils.copyFile(screenshot, new File(arquivo));
        }catch (Exception e) {
            System.out.println("HOUVERAM PROBLEMAS AO COPIAR O ARQUIVO PARA A PASTA" + e.getMessage());
        }
    }
}
