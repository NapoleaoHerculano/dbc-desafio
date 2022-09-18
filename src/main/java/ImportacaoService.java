import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Francivaldo Napoleão Herculano
 * @since 17/09/2022
 */
public class ImportacaoService {

    private ReceitaService receitaService = new ReceitaService();

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
            boolean retorno;

            try {
                retorno = receitaService.atualizarConta(agencia, conta, saldo, status);
            }catch (RuntimeException exception){
                retorno = false;
            }

            scannerLeitura.close();

            bufferedWriter.write(formatarLinhaParaCsv(formatarConta(conta), agencia, saldo, status, formatarProcessamento(retorno)));
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
    private String formatarLinhaParaCsv(String conta, String agencia, double saldo, String status, String retornoProcessamento){
        return String.format(Locale.GERMANY,"%s;%s;%.2f;%s;%s", agencia, conta, saldo, status, retornoProcessamento);
    }

    /**
     * Função para tratar o retorno do processamento.
     * @param status booleano com o retorno do processamento.
     * @return string referente ao status do processamento.
     */
    private String formatarProcessamento(boolean status){

        if (status){
            return "processado";
        }

        return "erro";
    }
}
