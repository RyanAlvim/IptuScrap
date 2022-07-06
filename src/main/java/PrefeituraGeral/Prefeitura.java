package PrefeituraGeral;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PrefeituraSenha.PrefeituraSenha;

public class Prefeitura {
	
	private static WebDriver driver;
	public static String setor_quadra;
	public static ArrayList<String> setor_quadra_lote = new ArrayList<String>();
	
	public Prefeitura() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
	}
	
	public static void BuscarDado() throws InterruptedException, IOException {
		driver = new ChromeDriver();
		driver.get("http://www3.prefeitura.sp.gov.br/cit/Forms/frmPesquisaGeral.aspx");
		Thread.sleep(500);
		WebElement idCampoPrefeitura = driver.findElement(By.id("ctl00_Principal_txt_CodNome"));
		String campoPrefeitura = JOptionPane.showInputDialog("Digite o endereço");
		idCampoPrefeitura.sendKeys(campoPrefeitura);
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_Principal_btn_Pesquisa_Endereco")).click();
		
		Thread.sleep(500);
		String numeroDoImovel = JOptionPane.showInputDialog("Digite o número do Imóvel ");
		WebElement numeroImovel = driver.findElement(By.id("ctl00_Principal_txt_numero"));
		numeroImovel.sendKeys(numeroDoImovel);
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_Principal_btn_pesquisaNumero")).click();
		Thread.sleep(500);
		List<WebElement> listaTable = driver.findElements(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/center/div/table/tbody/tr/td/div/div/table[2]"));
		for(WebElement lista : listaTable) {
			String[] split1 = lista.getText().split(" ");
			for(String selectOption : split1) {
				if(selectOption.contains(".") && selectOption.contains("-")) {
					setor_quadra_lote.add(selectOption);
					System.out.println(selectOption);
					
				}
			}
			
		}
		driver.close();
		driver.quit();
		PrefeituraSenha prefeituraSenha = new PrefeituraSenha();
		prefeituraSenha.DownloadPDF();
	}
}
