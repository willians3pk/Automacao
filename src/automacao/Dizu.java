package automacao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dizu {

    private WebDriver browser;
    private String loginDizu;
    private String senhaDizu;
    private List<String> novaAba;

    public Dizu() {
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public String getLoginDizu() {
        return loginDizu;
    }

    public void setLoginDizu(String loginDizu) {
        this.loginDizu = loginDizu;
    }

    public String getSenhaDizu() {
        return senhaDizu;
    }

    public void setSenhaDizu(String senhaDizu) {
        this.senhaDizu = senhaDizu;
    }

    public List<String> getNovaAba() {
        return novaAba;
    }

    public void setNovaAba(List<String> novaAba) {
        this.novaAba = novaAba;
    }

    public void login() {
        novaAba = new ArrayList<>(browser.getWindowHandles());
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar todos os elementos da pagina;
        browser.switchTo().window(novaAba.get(1)); // pega o browser da nova aba aberta!!;

        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar todos os elementos da pagina;
        browser.get("https://dizu.com.br/login");
        browser.findElement(By.name("login")).sendKeys(loginDizu);
        browser.findElement(By.name("senha")).sendKeys(senhaDizu);
        browser.findElement(By.cssSelector("button[type=submit]")).click(); // clica no botao acessa conta

        String titulo1;
        titulo1 = browser.getTitle();
        System.out.println("Pagina: "+titulo1);

    }

    public void paginaConectarGanhar() throws InterruptedException {

        try {
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar todos os elementos da pagina;
            browser.findElement(By.className("dizu-flame")); // procura o elemento na pagina, se ele for encontrado, executara a linha seguinte;
            browser.get("https://dizu.com.br/painel/conectar");
            
//            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//            Select comboBox = new Select(browser.findElement(By.id("instagram_id"))); // cria o objeto comboBox;
//            comboBox.selectByValue("38456053026"); // seleciona o perfil da comboBox;

//            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//            String verlink = browser.findElement(By.id("conectar_step_4")).getText(); // pega o texto do botao Ver link, e verifica se ele está visivel
//            System.out.println(verlink + " Visivel");

        } catch (Exception e) {
            System.out.println("Erro ao selecionar perfil, ou botao Ver link nao está visivel");
//            Select comboBox = new Select(browser.findElement(By.id("conta_id"))); // cria o objeto comboBox;
//            System.out.println("Selecionando novamente o Perfil!!");
//            comboBox.selectByIndex(0); // Seleciona a opção selecionar perfil;
//            Thread.sleep(1000);// delay
//            comboBox.selectByValue("38456053026"); // seleciona o perfil da comboBox;
        }
        
//        try {
//            WebDriverWait wait2 = new WebDriverWait(browser, 10);
//            wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("conectar_step_4")));// ferifica se o botão Ver link está visivel
//            browser.findElement(By.id("conectar_step_4")).click(); // clica no botão ver link;
//        } catch (Exception e) {
//            System.out.println("Nao foi possivel clicar no Ver link " + e);
//            System.out.println("Tendando novamente!!");
//            browser.findElement(By.id("conectar_step_4")).click(); // clica no botão ver link;
//        }
    }
    
    
    
}
