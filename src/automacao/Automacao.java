package automacao;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Automacao {

    private static WebDriver browser;
    private static String loginDizu = "";
    private static String senhaDizu = "";

    public static void main(String[] args) throws AWTException, InterruptedException {

        List<String> contas = new ArrayList<>();
        List<String> senhas = new ArrayList<>();

        contas.add("");
        senhas.add("");
        contas.add("");
        senhas.add("");

        System.setProperty("webdriver.Chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        browser = new ChromeDriver(chromeOptions);
//        browser = new ChromeDriver(); 

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

            inst.login(contas.get(1), senhas.get(1));
            dizu.login();
            
            for (int j = 0; j < 3; j++) {
                inst.selecionPerfil(contas.get(1));
                inst.seguirUsuario(contas.get(1));
            }
            inst.deslogar(contas.get(1)); // desloga a conta do instagram;

            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
            Thread.sleep(2000);
            
            inst.login(contas.get(0), senhas.get(0));
            System.out.println("logado com sucesso!");
            
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
            Thread.sleep(2000);
            
            dizu.login();
            dizu.paginaConectarGanhar();

            for (int i = 0; i < 3; i++) {
                inst.selecionPerfil(contas.get(0));
                inst.seguirUsuario(contas.get(0));
            }
            inst.deslogar(contas.get(0));

            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
            Thread.sleep(2000);
        }
    }

}
