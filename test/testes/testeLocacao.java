package testes;

import static org.junit.Assert.*;
import br.ufpr.modulos.classes.Categoria;
import br.ufpr.modulos.classes.Cliente;
import br.ufpr.modulos.classes.Estado;
import br.ufpr.modulos.classes.Locacao;
import br.ufpr.modulos.classes.Marca;
import br.ufpr.modulos.classes.ModeloAutomovel;
import br.ufpr.modulos.telas.Automovel;
import java.util.Calendar;
import org.junit.Test;

/**
 * Testes da regra de locação de veículos.
 * Projeto acadêmico - LPOOI.
 */
public class testeLocacao {

    private Cliente criarCliente() {
        return new Cliente("Renan", "Teste", "1234567",
                "12345678900", "Rua de Teste, 100");
    }

    private Calendar data(int dia, int mes, int ano) {
        Calendar data = Calendar.getInstance();
        data.clear();
        data.set(ano, mes - 1, dia);
        return data;
    }

    private Automovel criarAutomovel() {
        return new Automovel(Marca.VW, Estado.DISPONIVEL, null,
                Categoria.POPULAR, 60000.00, "ABC-1234", 2022,
                ModeloAutomovel.GOL);
    }

    @Test
    public void deveRealizarLocacaoECalcularValor() {
        Automovel carro = criarAutomovel();

        carro.locar(3, data(20, 11, 2026), criarCliente());

        assertEquals(Estado.LOCADO, carro.getEstado());
        assertNotNull(carro.getLocacao());
        assertEquals(300.00, carro.getLocacao().getValor(), 0.001);
        assertEquals(3, carro.getLocacao().getDias());
    }

    @Test
    public void deveCalcularDataDevolucaoDeAcordoComQuantidadeDeDias() {
        Locacao locacao = new Locacao(3, 300.00,
                data(20, 11, 2026), criarCliente());

        Calendar devolucao = locacao.calcularDataDevolucao();

        assertEquals(23, devolucao.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.NOVEMBER, devolucao.get(Calendar.MONTH));
        assertEquals(2026, devolucao.get(Calendar.YEAR));
    }

    @Test
    public void naoDevePermitirNovaLocacaoEnquantoVeiculoEstiverComEstadoLocado() {
        Automovel carro = criarAutomovel();

        carro.locar(3, data(20, 11, 2026), criarCliente());

        // A validação considera o estado atual do veículo, sem implementar
        // regras de agenda ou comparação entre períodos de datas.
        try {
            carro.locar(2, data(25, 11, 2026), criarCliente());
            fail("Não deveria permitir outra locação enquanto o veículo estiver LOCADO.");
        } catch (RuntimeException e) {
            assertEquals("Veículo não está disponível para locação.", e.getMessage());
        }
    }

    @Test
    public void deveLiberarVeiculoAposDevolucao() {
        Automovel carro = criarAutomovel();
        carro.locar(3, data(20, 11, 2026), criarCliente());

        carro.devolver();

        assertEquals(Estado.DISPONIVEL, carro.getEstado());
        assertNull(carro.getLocacao());
    }

    @Test
    public void devePermitirNovaLocacaoDepoisDaDevolucao() {
        Automovel carro = criarAutomovel();
        carro.locar(3, data(20, 11, 2026), criarCliente());
        carro.devolver();

        carro.locar(2, data(10, 11, 2026), criarCliente());

        assertEquals(Estado.LOCADO, carro.getEstado());
        assertEquals(2, carro.getLocacao().getDias());
    }

    @Test
    public void naoDeveAceitarQuantidadeDeDiasMenorOuIgualAZero() {
        Automovel carro = criarAutomovel();

        try {
            carro.locar(0, data(20, 11, 2026), criarCliente());
            fail("A locação não deve aceitar zero dias.");
        } catch (IllegalArgumentException e) {
            assertEquals("A quantidade de dias deve ser maior que zero.", e.getMessage());
        }

        assertEquals(Estado.DISPONIVEL, carro.getEstado());
        assertNull(carro.getLocacao());
    }
}
