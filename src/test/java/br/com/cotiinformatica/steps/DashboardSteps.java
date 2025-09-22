package br.com.cotiinformatica.steps;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cotiinformatica.helpers.ScreenshotHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class DashboardSteps {

	// Atributo
	private ChromeDriver driver;
	private WebDriverWait wait;

	@Dado("eu estou na página de login do sistema")
	public void eu_estou_na_página_de_login_do_sistema() {
		
		//Configuração para execução dos testes em modo headless
				ChromeOptions options = new ChromeOptions();
		       options.addArguments("--headless"); // necessário em CI
		       options.addArguments("--no-sandbox"); // necessário em GitHub Actions
		       options.addArguments("--disable-dev-shm-usage"); // necessário em CI/Linux
		       options.addArguments("--remote-allow-origins=*");
				
				//abrir o mavegador
				driver = new ChromeDriver(options);

	
		// maximizar o navegador
		driver.manage().window().maximize();

		// acessar a página de login
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");

		// definir a configuração do wait (aguardar)
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Dado("eu informo o nome de usuário {string}")
	public void eu_informo_o_nome_de_usuário(String username) {

		// Capturar o campo para preenchimento do nome do usuário
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")));
		element.clear(); // limpar o campo
		element.sendKeys(username); // preencher o campo
	}

	@Dado("eu informo a senha {string}")
	public void eu_informo_a_senha(String password) {
		// Capturar o campo para preenchimento da senha do usuário
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")));
		element.clear(); // limpar o campo
		element.sendKeys(password); // preencher o campo
	}

	@Quando("eu solicito o acesso ao sistema")
	public void eu_solicito_o_acesso_ao_sistema() {
		// Capturar o botão de login
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")));
		element.click(); // clicar no botão
	}

	@Então("eu sou autenticado com sucesso")
	public void eu_sou_autenticado_com_sucesso() {

		// Capturar a URL da página
		String urlAtual = driver.getCurrentUrl();

		// Verificar se o sistema abriu a página principal da área restrita
		assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", urlAtual);
	}

	@Dado("eu estou na página do dashboard")
	public void eu_estou_na_página_do_dashboard() {

		// Capturar o link da página no menu do sistema
		WebElement element = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[8]/a")));

		// Clicar no link
		element.click();
	}

	@Então("o sistema exibe os indicadores principais da aplicação")
	public void o_sistema_exibe_os_indicadores_principais_da_aplicação() {

		// Capturar os indicadores
		WebElement timeAtWork = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p")));
		WebElement myActions = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div/p")));
		// Verificar o conteudo
		assertEquals("Time at Work", timeAtWork.getText());
		assertEquals("My Actions", myActions.getText());

		// Gerando um screenshot
		ScreenshotHelper.captureScreenshot(driver, "Acesso ao dashboard principal");
	}
}
