import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReceitaServiceTest {

    @Test
    void deveRetornarVerdadeiroSeQuantidadeDeCaracteresDaAgenciaForIgualAQuatro() throws InterruptedException {
        ReceitaService receitaService = new ReceitaService();
        boolean retorno = receitaService.atualizarConta("1111","123456", 200.0, "A");

        Assertions.assertTrue(retorno);
    }

    @Test
    void deveRetornarFalsoSeQuantidadeDeCaracteresDaAgenciaForDiferenteDeQuatro() throws InterruptedException {
        ReceitaService receitaService = new ReceitaService();
        boolean retorno = receitaService.atualizarConta("111","123456", 200.0, "A");

        Assertions.assertFalse(retorno);
    }

    @Test
    void deveRetornarFalsoSeAtributoAgenciaForNulo() throws InterruptedException {
        ReceitaService receitaService = new ReceitaService();
        boolean retorno = receitaService.atualizarConta(null,"123456", 200.0, "A");

        Assertions.assertFalse(retorno);
    }

}
