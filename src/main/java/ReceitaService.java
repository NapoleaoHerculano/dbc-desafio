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

    /**
     * Esta é a implementação interna do "servico" do banco central.
     * Veja o código fonte abaixo para ver os formatos esperados pelo Banco Central neste cenário.
     * @param agencia variável string que receberá o número da agencia.
     * @param conta variável string que receberá o número da conta.
     * @param saldo variável string que receberá o saldo.
     * @param status variável string que receberá o status.
     * @return true, se os dados passados satisfazerem todas as condições;
     *         false, se alguma das condições não forem satisfeitas.
     * @throws RuntimeException
     * @throws InterruptedException
     */
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

    /**
     * Função responsável pela leitura, processamento e exportação do arquivo .csv
     * com o resultado do processamento realizado.
     * @author Francivaldo Napoleão
     * @since 09/09/2022
     * @throws IOException
     * @throws InterruptedException
     */
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

    /**
     * Função auxiliar utilizada para formatar a conta bancária,
     * adicionando o hífen (-) entre o penúltimo e último número.
     * @author Francivaldo Napoleão
     * @since 09/09/2022
     * @param contaSemSeparador - variável string que receberá o número da conta
     *                            sem o hífen (-).
     * @return string com o número da conta formatado no padrão estipulado.
     */
    private String formatarConta(String contaSemSeparador){
        return contaSemSeparador.substring(0,contaSemSeparador.length()-1)+"-"+contaSemSeparador.substring(contaSemSeparador.length()-1);
    }

    /**
     * Função auxiliar utilizada para formatar os dados para o padrão do arquivo
     * .csv de saída.
     * @author Francivaldo Napoleão
     * @since 09/09/2022
     * @param conta     - variável string com a informação da conta bancária lida no
     *                    arquivo .csv de entrada;
     * @param agencia   - variável string com a informação da agência bancária lida no
     *                    arquivo .csv de entrada;
     * @param saldo     - variável double com a informação do saldo lido no
     *                    arquivo .csv de entrada;
     * @param status    - variável string com a informação do status lido no
     *                    arquivo .csv de entrada;
     * @param retornoProcessamento - variável string com o resultado do processamento realizado pelo
     *                               método atualizaConta().
     *
     * @return string com os dados lidos e formatados no padrão estipulado.
     */
    private String formatarLinhaParaCsv(String conta, String agencia, double saldo, String status, boolean retornoProcessamento){
        return String.format(Locale.GERMANY,"%s;%s;%.2f;%s;%s", agencia, conta, saldo, status, retornoProcessamento);
    }

}
