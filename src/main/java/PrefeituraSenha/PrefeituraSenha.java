package PrefeituraSenha;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PrefeituraGeral.Prefeitura;
import PuxarDados.Assertiva;
import ReaderPdf.Pdf;

public class PrefeituraSenha {

	private static WebDriver driver;
	private static HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	private static String downloadFilepath = System.getProperty("user.dir");
	public static String Rua_Avenida_etc;
	
	public PrefeituraSenha() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
	}
	
	public static void DownloadPDF() throws InterruptedException, IOException {

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(options);
		driver.get("http://notcertiptu.prefeitura.sp.gov.br/PaginasRestritas/frm001_Gerar_Notif_Lanc.aspx");
		Thread.sleep(500);
		WebElement cpf = driver.findElement(By.id("formBody_txtUser"));
		cpf.sendKeys("");
		Thread.sleep(500);
		WebElement senha = driver.findElement(By.id("formBody_txtPassword"));
		senha.sendKeys("");
		String captcha = JOptionPane.showInputDialog("Digite o Captcha");
		WebElement captchaVerificar = driver.findElement(By.id("txtValidacao"));
		captchaVerificar.sendKeys(captcha);
		Thread.sleep(500);
		driver.findElement(By.id("formBody_Button1")).click();
		Thread.sleep(1500);
		Rua_Avenida_etc = JOptionPane.showInputDialog("Qual o tipo de Pesquisa? (R,AL,AV)");
		for(int i =0; i < Prefeitura.setor_quadra_lote.size(); i++) {
			try {
				driver.get("http://notcertiptu.prefeitura.sp.gov.br/PaginasRestritas/frm001_Gerar_Notif_Lanc.aspx");
				Thread.sleep(1000);
				WebElement cadastro_imovel = driver.findElement(By.id("txt_SQL"));
				String setor_quadra_split[] = Prefeitura.setor_quadra_lote.get(i).split("-");
				cadastro_imovel.sendKeys(setor_quadra_split[0]+setor_quadra_split[1]);
				Thread.sleep(1500);
				WebElement data = driver.findElement(By.id("txt_Exercicio"));
				data.sendKeys("2022");
				Thread.sleep(500);
				driver.findElement(By.id("btnConsultar")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("btnGerar")).click();
				Pdf pdf = new Pdf();
				pdf.LerPdf();
				
			}catch(Exception e) {
				System.out.println("Cadastro não existente");
			}
		}
		driver.close();
		driver.quit();
		Assertiva assertiva = new Assertiva();
		assertiva.AssertivaDados();
	}
}
