import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 */
public class ReceitaService {

	// Esta é a implementação interna do "servico" do banco central.
    // Veja o código fonte abaixo para ver os formatos esperados pelo Banco Central neste cenário.

    public boolean atualizarConta(String agencia, String conta, double saldo, String status)
            throws RuntimeException, InterruptedException {
		
			
        // Formato agencia: 0000
        if (agencia == null || agencia.length() != 4) {
            return false;
        }
        
        // Formato conta: 000000
        if (conta == null || conta.length() != 6) {
            return false;
        }
        
        // Tipos de status validos:
        List tipos = new ArrayList();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");                
                
        if (status == null || !tipos.contains(status)) {
            return false;
        }

        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 4000) + 1000;
        Thread.sleep(wait);

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) {
            throw new RuntimeException("Error");
        }

        return true;
    }

    public void importarArquivoSicredi() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("saida-processamento.csv"));

        int contadorDeLinhas = 0;
        while (scanner.hasNextLine()){
            contadorDeLinhas++;

            if (contadorDeLinhas == 1){
                String cabecalhoArquivoDeSaida = scanner.nextLine().concat(";ret-rf");
                bufferedWriter.write(cabecalhoArquivoDeSaida);
                bufferedWriter.newLine();
            }

            String linha = scanner.nextLine();

            Scanner scannerLeitura = new Scanner(linha);
            scannerLeitura.useLocale(Locale.GERMANY);
            scannerLeitura.useDelimiter(";");

            String agencia = scannerLeitura.next();
            String conta = scannerLeitura.next().replace("-", "");
            double saldo = scannerLeitura.nextDouble();
            String status = scannerLeitura.next();
            boolean retorno = atualizarConta(agencia, conta, saldo, status);

            scannerLeitura.close();

            bufferedWriter.write(formatarLinhaParaCsv(formatarConta(conta), agencia, saldo, status, retorno));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        scanner.close();
    }

    private String formatarConta(String contaSemSeparador){
        return contaSemSeparador.substring(0,contaSemSeparador.length()-1)+"-"+contaSemSeparador.substring(contaSemSeparador.length()-1);
    }

    private String formatarLinhaParaCsv(String conta, String agencia, double saldo, String status, boolean retornoProcessamento){
        return String.format(Locale.GERMANY,"%s;%s;%.2f;%s;%s", agencia, conta, saldo, status, retornoProcessamento);
    }

}
