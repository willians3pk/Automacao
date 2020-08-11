package automacao;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Automacao {

    private static WebDriver browser;
    private static String loginDizu = "Testes3@outlook.com.br";
    private static String senhaDizu = "Ws@123456789123";

    public static void main(String[] args) throws AWTException, InterruptedException {

        List<String> contas = new ArrayList<>();
        List<String> senhas = new ArrayList<>();

        contas.add("rachin.24");
        senhas.add("SenhaFake");
        contas.add("java_020x");
        senhas.add("SenhaFake");

        System.setProperty("webdriver.Chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        browser = new ChromeDriver(chromeOptions);
        browser = new ChromeDriver();

        // ----- Tela Login do Instagram ----- //
        Instagram inst = new Instagram();
        inst.setBrowser(browser);

        // abri uma nova aba no navegador;
//        Robot r = new Robot();
//        r.keyPress(KeyEvent.VK_CONTROL);
//        r.keyPress(KeyEvent.VK_T);
//        r.keyRelease(KeyEvent.VK_CONTROL);
//        r.keyRelease(KeyEvent.VK_T);

        List<String> novaAba = new ArrayList<>(browser.getWindowHandles());
//        browser.switchTo().window(novaAba.get(0)).close(); // fecha a aba

        // ----- Nova Aba ------ //
        Dizu dizu = new Dizu();
        dizu.setLoginDizu(loginDizu);
        dizu.setSenhaDizu(senhaDizu);
        dizu.setNovaAba(novaAba);
        dizu.setBrowser(browser);
        inst.setNovaAba(novaAba);
        
        
        while (true) {
            
            inst.login(contas.get(0), senhas.get(0));
            dizu.login();
            dizu.paginaConectarGanhar();

            for (int j = 0; j < 2; j++) {
                inst.seguirUsuario(contas.get(0));
            }
            inst.deslogar(contas.get(0)); // desloga a conta do instagram;

            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
            browser.get("https://www.instagram.com/accounts/login/?hl=pt-br");

            inst.login(contas.get(1), senhas.get(1));
            System.out.println("logado com sucesso!");
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
   
            dizu.login();
            dizu.paginaConectarGanhar();
            
            for (int i = 0; i < 2; i++) {
                inst.seguirUsuario(contas.get(1));
            }
            inst.deslogar(contas.get(1));
            
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
            browser.get("https://www.instagram.com/accounts/login/?hl=pt-br");
            
        }
    }

}
