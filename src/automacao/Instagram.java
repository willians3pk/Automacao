package automacao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Instagram {

    private WebDriver browser;
    private String loginInstagram;
    private String senhaInstagram;
    private List<String> novaAba;

    public Instagram() {
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public String getLoginInstagram() {
        return loginInstagram;
    }

    public void setLoginInstagram(String loginInstagram) {
        this.loginInstagram = loginInstagram;
    }

    public String getSenhaInstagram() {
        return senhaInstagram;
    }

    public void setSenhaInstagram(String senhaInstagram) {
        this.senhaInstagram = senhaInstagram;
    }

    public List<String> getNovaAba() {
        return novaAba;
    }

    public void setNovaAba(List<String> novaAba) {
        this.novaAba = novaAba;
    }

    public void login() {
        // ----- Tela Login do Instagram ----- //
        try {
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar todos os elementos da pagina;
            System.setProperty("webdriver.Chrome.driver", "/usr/bin/chromedriver");
            browser.get("https://www.instagram.com/accounts/login/?hl=pt-br");
            browser.findElement(By.name("username")).sendKeys(loginInstagram); // campo usuario
            browser.findElement(By.name("password")).sendKeys(senhaInstagram); // campo senha
            browser.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button/div")).click(); // botão entrar
            String titulo;
            titulo = browser.getTitle();
            System.out.println("Pagina: " + titulo);

            // ----- Tela Salvar suas informações de Login ----- //
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/div/section/div/button")).click();// botao salvar informações

            // ----- Tela Turn on Notificação ------ //;
            WebDriverWait wait = new WebDriverWait(browser, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[2]"))); // espera botao not now ficar visivel;
            browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[2]")).click(); // botao not now;

//            // ----- Perfil do usuario Instagram ---- //    
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String nomePerfil = browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/section/div[3]/div[1]/div/div[2]/div[2]")).getText();

            if (nomePerfil.equals("rachin.24")) {
                System.out.println("Elemento encontrado");
            } else {
                System.out.println("não encontramos o elemento");
            }

            String titulo1;
            titulo1 = browser.getTitle();
            System.out.println("Pagina: " + titulo1);

        } catch (Exception e) {
//          botão entrar
            browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/article/div[2]/div[1]/div/form/div[4]/button/div")).click();
        }
    }

    public void seguirUsuario() throws InterruptedException {

        try {
            WebDriverWait wait2 = new WebDriverWait(browser, 10);
            wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("iniciarTarefas")));// ferifica se o botão iniciar está visivel
            browser.findElement(By.id("iniciarTarefas")).click(); // clica no botão iniciar;
        } catch (Exception e) {
            System.out.println("Nao foi possivel clicar no botao iniciar " + e);
            System.out.println("Tendando novamente!!");
            browser.findElement(By.id("iniciarTarefas")).click(); // clica no botão iniciar;
            System.out.println("Botão iniciar encontrado!");
        }

        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar todos os elementos da pagina;
        Thread.sleep(100);

        try {

            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            WebElement verlink = browser.findElement(By.id("conectar_step_4")); // pega o texto do botao Ver link, e verifica se ele está visivel
            System.out.println(verlink.getText() + " Visivel");
            verlink.click();
            Thread.sleep(1000);
            System.out.println("Clicando Perfil a ser seguido!");
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro ao clicar no Botao ver link");
            System.out.println("OU voce está sem Tarefas");
        }

        try {
            novaAba = new ArrayList<>(browser.getWindowHandles());
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            browser.switchTo().window(novaAba.get(2));
            Thread.sleep(2000);

            String titulo = browser.getTitle();
            System.out.println("Pagina: " + titulo);

            browser.findElement(By.className("_2dbep")).sendKeys(Keys.TAB, Keys.ENTER); // clica no botao seguir;
//            WebElement element = browser.findElement(By.xpath("//*[contains(text(), 'Follow')]"));
//            element.click();
            Thread.sleep(2000);

            System.out.println("Você começou a seguir alguem! \n ");

            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            browser.switchTo().window(novaAba.get(2)).close(); // fecha a Aba do instagram;
            browser.switchTo().window(novaAba.get(1)); // volta pra pagina do Dizu;
            browser.findElement(By.xpath("//*[@id=\"conectar_step_5\"]/button")).click(); // clica no botao confirmar;
            System.out.println("Confirmado com Sucesso! \n");

        } catch (Exception e) {
            System.out.println("Erro elemento Follow nao encontrado! " + e);
        }

//        try {
//            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//            WebElement pularTarefa = browser.findElement(By.xpath("//*[@id=\"conectar_form\"]/div[3]/div[1]/button"));
//            System.out.println(pularTarefa.getText());
//            pularTarefa.click();
//            
//        } catch (Exception e) {
//        }
    }

}
