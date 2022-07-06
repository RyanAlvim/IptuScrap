package PuxarDados;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import ReaderPdf.Pdf;

public class Assertiva {
	
	private static WebDriver driver;
	private static FileWriter file;	
	public Assertiva() throws IOException {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		file = new FileWriter("contato.csv",true);
		file.write("nome;telefone;endereco\n");

	}
	
	public static void AssertivaDados() throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(/*options*/);
		driver.get("https://app.assertivasolucoes.com.br/login");
		Thread.sleep(1000);
		WebElement cliente = driver.findElement(By.id("cliente"));
		cliente.sendKeys("");
		Thread.sleep(500);
		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys("");
		Thread.sleep(500);
		WebElement senha = driver.findElement(By.id("senha"));
		senha.sendKeys("");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[text()='Entrar']")).click();
		Thread.sleep(1000);
		for(Map.Entry<String, String> dadosAssertiva : Pdf.cpf_Endereco.entrySet()) { //Pdf.Cpf_Pessoas
			try {
				driver.get("https://app.assertivasolucoes.com.br/");
				Thread.sleep(1000);
				WebElement selectCpf = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[2]/div/form/div[1]/span/div[1]/select"));
				Select selectCpfValue = new Select(selectCpf);
				selectCpfValue.selectByVisibleText("CPF");
				Thread.sleep(500);
				WebElement campoCpf = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[2]/div/form/div[1]/span/div[2]/input"));
				campoCpf.sendKeys(dadosAssertiva.getKey()); //Pdf.cpfAssertiva
				Thread.sleep(500);
				WebElement dropmenu = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[2]/div/form/div[1]/span/div[3]/div/button"));
				dropmenu.click();
				Thread.sleep(250);
				Actions actionProvider = new Actions(driver);
				actionProvider.sendKeys(Keys.DOWN).perform();
				Thread.sleep(250);
				actionProvider.sendKeys(Keys.ENTER).perform();
				Thread.sleep(250);
				driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[2]/div/form/div[2]/div[2]/span[2]/span/button")).click();
				Thread.sleep(10000);
				List<WebElement> telefonesLista = driver.findElements(By.className("phone-number"));
				System.out.println("Nome: " + driver.findElement(By.className("dados_name_copy")).getText());
				for(WebElement telefoneContato : telefonesLista) {
					if(!telefoneContato.getText().contains("@")) {
						String telSplit[] = telefoneContato.getText().split(" ");
						char caracterEspecial = telSplit[1].charAt(0);
						if(caracterEspecial == '9') {
							System.out.println("Telefone: " + telefoneContato.getText());
							file.write(String.format("%s;%s;%s\n", driver.findElement(By.className("dados_name_copy")).getText(), telefoneContato.getText(),dadosAssertiva.getValue()));
						}
					}
					
				}
			}catch(Exception e) {
				System.out.println("Usuário não encontrado!");
			}
			
		}
		file.close();
		JOptionPane.showMessageDialog(null, "PROGRAMA ENCERRADO!");
	}
}
