package ReaderPdf;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import PrefeituraSenha.PrefeituraSenha;
import PuxarDados.Assertiva;

public class Pdf {
	
	public static String cpfAssertiva;
	public static HashMap<String, String> cpf_Endereco = new HashMap<String, String>();
	
	public static void LerPdf() {
		try {
			Thread.sleep(5000);
			File file = new File("NotificacaoLancamento.pdf");
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);

			String cpfSplit[] = text.split("CPF");
			String cpfSemtratar = cpfSplit[1];
			String cpfSplit2[] = cpfSemtratar.split("\n");
			cpfAssertiva = cpfSplit2[0];
			
			String Endereco[] = text.split("\n");
			
			
			for(String EnderecoFilter : Endereco) {
				if(EnderecoFilter.contains(String.format("%s ", PrefeituraSenha.Rua_Avenida_etc.toUpperCase()))) {
					char UltimoCaracter = cpfAssertiva.charAt(cpfAssertiva.length()-1);
					if(UltimoCaracter == ')') {
						cpfAssertiva = cpfAssertiva.substring(0, cpfAssertiva.length()-1);
						System.out.println("CPF: " + cpfAssertiva + " Endereço: " + EnderecoFilter);
						cpf_Endereco.put(cpfAssertiva, EnderecoFilter);
						break;
					}else {
						System.out.println("CPF: " + cpfAssertiva + " Endereço: " + EnderecoFilter);
						cpf_Endereco.put(cpfAssertiva, EnderecoFilter);
						break;
					}
				}
			}

			document.close();
			file.delete();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
