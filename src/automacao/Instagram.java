package automacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Instagram {

    private WebDriver browser;
    private List<String> novaAba;
    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

    public Instagram() {
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public List<String> getNovaAba() {
        return novaAba;
    }

    public void setNovaAba(List<String> novaAba) {
        this.novaAba = novaAba;
    }

    public void login(String usuario, String senha) {
        // ----- Tela Login do Instagram ----- //
        try {
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar todos os elementos da pagina;
            System.setProperty("webdriver.Chrome.driver", "/usr/bin/chromedriver");
            browser.get("https://www.instagram.com/accounts/login/?hl=pt-br");
            browser.findElement(By.name("username")).sendKeys(usuario); // campo usuario
            browser.findElement(By.name("password")).sendKeys(senha); // campo senha
            browser.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button/div")).click(); // botão entrar
            String titulo;
            titulo = browser.getTitle();
            System.out.println("Pagina: " + titulo);

            // ----- Tela Salvar suas informações de Login ----- //
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/div/section/div/button")).click();// botao salvar informações

//            // ----- Tela Turn on Notificação ------ //;
            WebDriverWait wait = new WebDriverWait(browser, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[2]"))); // espera botao not now ficar visivel;
            browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[2]")).click(); // botao not now;
            System.out.println("Pronto!");

//            // ----- Perfil do usuario Instagram ---- //    
//            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            String nomePerfil = browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/section/div[3]/div[1]/div/div[2]/div[2]")).getText();
//
//            if (nomePerfil.equals(usuario)) {
//                System.out.println("Elemento encontrado");
//            } else {
//                System.out.println("não encontramos o elemento");
//            }
            System.out.println("usuario logado com SUCESSO!");

        } catch (Exception e) {
//          botão entrar
            System.out.println("erro " + e);
//            browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/article/div[2]/div[1]/div/form/div[4]/button/div")).click();
        }
    }

    public void deslogar(String usuario) {

        try {
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            browser.get("https://www.instagram.com/" + usuario);
            browser.findElement(By.className("wpO6b")).click();
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div/button[9]")).click();
            System.out.println("usuario deslogado com sucesso!\n");
        } catch (Exception e) {
        }

    }

    public void seguirUsuario(String usuario) throws InterruptedException {

        try {

            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);// espera carregar os elementos da pagina;
            WebElement comboBox = browser.findElement(By.id("instagram_id")); // cria o objeto comboBox;
            for (WebElement usuarios : comboBox.findElements(By.tagName("option"))) {
                if (usuarios.getText().contains(usuario)) {
                    usuarios.click();   //seleciona o usuario na comboBox;
                    System.out.println("usuario selecionado!");
                }
            }

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
            System.out.println(verlink.getText() + " Visivel\n");
            verlink.click();
            Thread.sleep(1000);
            System.out.println("Clicando Perfil a ser seguido!");
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\nvoce está sem Tarefas \n");
        }

        try {

            novaAba = new ArrayList<>(browser.getWindowHandles());
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            browser.switchTo().window(novaAba.get(1));
            Thread.sleep(1000);

            String titulo = browser.getTitle();
            System.out.println("Pagina: " + titulo + "\n");

            try {
//                Essa condição é caso você ja esteja seguindo o Perfil, se voce estiver seguindo ele e execultado, caso contrario...
                String botao1 = browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/div/button")).getText();
                if (botao1.contains("Message")) {
                    Thread.sleep(1000);
                    System.out.println("------------------ Você ja está Seguindo esse Perfil! ----------------------\n");
                    browser.switchTo().window(novaAba.get(1)).close(); // fecha a Aba do instagram;
                    browser.switchTo().window(novaAba.get(0)); // volta pra pagina do Dizu;
                    Thread.sleep(2000);
                    browser.findElement(By.xpath("//*[@id=\"conectar_step_5\"]/button")).click(); // clica no botao confirmar;
                    System.out.println("Confirmado com Sucesso! \n");
                    Thread.sleep(3000);
                    browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

                }
            } catch (Exception e) {

                System.out.println("Você ainda não está Seguindo esse Perfil");
                browser.findElement(By.className("_2dbep")).sendKeys(Keys.TAB, Keys.ENTER); // clica no botao seguir;
                Thread.sleep(2000);
                String botao2 = browser.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/div/button")).getText();
                System.out.println("Texto do botao: " + botao2 + "\n");
                System.out.println("Você começou a seguir alguem! \n ");
                browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                Thread.sleep(500);
                browser.switchTo().window(novaAba.get(1)).close(); // fecha a Aba do instagram;
                browser.switchTo().window(novaAba.get(0)); // volta pra pagina do Dizu;
                Thread.sleep(500);
                browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                browser.findElement(By.xpath("//*[@id=\"conectar_step_5\"]/button")).click(); // clica no botao confirmar;
                System.out.println();
                System.out.println("Confirmado com Sucesso! "+formatoHora.format(new Date())+"\n");
                Thread.sleep(3000);

            }

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
