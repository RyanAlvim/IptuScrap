package Main;

import java.io.IOException;

import PrefeituraGeral.Prefeitura;
import PuxarDados.Assertiva;
import ReaderPdf.Pdf;

public class Main {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Prefeitura prefeitura = new Prefeitura();
		prefeitura.BuscarDado();
	}

}
