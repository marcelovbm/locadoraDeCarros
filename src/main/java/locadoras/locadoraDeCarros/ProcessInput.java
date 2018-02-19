package locadoras.locadoraDeCarros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import locadoras.locadoraDeCarros.enumeration.TiposDeCarros;

@Component
public class ProcessInput {

	static Logger logger = Logger.getLogger(ProcessInput.class);

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String fileAbsolutePath = (String) exchange.getIn().getHeader("CamelFilePath");

		// LER ARQUIVO
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileAbsolutePath))));
		String header = in.readLine();
		in.close();

		String[] parametros = header.split(":");
		String[] intervalorDeDatas = parametros[2].split(",");

		Long[] faturas = calculaTotalDeGastos(intervalorDeDatas, fileAbsolutePath);

		filtraQuantidadePessoas(faturas, Integer.parseInt(parametros[1]));
	}

	public void filtraQuantidadePessoas(Long[] faturas, int quantidadePessoas) {

		if (quantidadePessoas > TiposDeCarros.SOUTHCAR.getQuantidadeDePessoas()
				& quantidadePessoas <= TiposDeCarros.NORTHCAR.getQuantidadeDePessoas()) {
			logger.info("HYUNDAI CRETA:NORTHCAR");
		} else if (quantidadePessoas > TiposDeCarros.WESTCAR.getQuantidadeDePessoas()
				& quantidadePessoas <= TiposDeCarros.SOUTHCAR.getQuantidadeDePessoas()) {
			if (faturas[1] < faturas[0]) {
				logger.info("FIAT MOBI:SOUTHCAR");
			} else {
				logger.info("HYUNDAI CRETA:NORTHCAR");
			}
		} else {
			if (faturas[0] <= faturas[1] & faturas[0] <= faturas[2]) {
				logger.info("HYUNDAI CRETA:NORTHCAR");
			} else if (faturas[1] <= faturas[0] & faturas[1] <= faturas[2]) {
				logger.info("FIAT MOBI:SOUTHCAR");
			} else {
				logger.info("FERRARI:WESTCARD");
			}
		}
	}

	public Long[] calculaTotalDeGastos(String[] intervalorDeDatas, String fileAbsolutePath) throws ParseException {

		Long[] totais = { 0L, 0L, 0L };

		for (int i = 0; i < intervalorDeDatas.length; i++) {
			Date date = new SimpleDateFormat("ddMMMyyyy", new Locale("pt", "BR")).parse(intervalorDeDatas[i]);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == 1 || dayOfWeek == 6 || dayOfWeek == 7) {
				if (fileAbsolutePath.contains("Regular")) {
					totais[0] += TiposDeCarros.NORTHCAR.getFimDeSemanaClienteRegular();
					totais[1] += TiposDeCarros.SOUTHCAR.getFimDeSemanaClienteRegular();
					totais[2] += TiposDeCarros.WESTCAR.getFimDeSemanaClienteRegular();
				} else {
					totais[0] += TiposDeCarros.NORTHCAR.getFimDeSemanaClienteFidelidade();
					totais[1] += TiposDeCarros.SOUTHCAR.getFimDeSemanaClienteFidelidade();
					totais[2] += TiposDeCarros.WESTCAR.getFimDeSemanaClienteFidelidade();
				}
			} else {
				if (fileAbsolutePath.contains("Regular")) {
					totais[0] += TiposDeCarros.NORTHCAR.getSegundaSextaClienteRegular();
					totais[1] += TiposDeCarros.SOUTHCAR.getSegundaSextaClienteRegular();
					totais[2] += TiposDeCarros.WESTCAR.getSegundaSextaClienteRegular();
				} else {
					totais[0] += TiposDeCarros.NORTHCAR.getFimDeSemanaClienteFidelidade();
					totais[1] += TiposDeCarros.SOUTHCAR.getFimDeSemanaClienteFidelidade();
					totais[2] += TiposDeCarros.WESTCAR.getFimDeSemanaClienteFidelidade();
				}
			}
		}

		logger.info("NORTHCAR - " + totais[0]);
		logger.info("SOUTHCAR - " + totais[1]);
		logger.info("WESTCAR - " + totais[2]);
		return totais;
	}
}
