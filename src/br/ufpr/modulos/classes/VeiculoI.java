package br.ufpr.modulos.classes;

import br.ufpr.modulos.classes.Marca;
import br.ufpr.modulos.classes.Locacao;
import br.ufpr.modulos.classes.Cliente;
import br.ufpr.modulos.classes.Categoria;
import br.ufpr.modulos.classes.Estado;
import java.util.Calendar;

public interface VeiculoI {

    // Muda estado para LOCADO. Cria uma instancia de Locacao e armazena no atributo locacao.
    // Chama o metodo getValorDiariaLocacao para calcular o valor da locacao.
    public void locar(int dias, Calendar data, Cliente cliente);

    // Muda estado para VENDIDO e nao pode mais ser alugado
    public void vender();

    // Muda estado para DISPONIVEL
    public void devolver();

    public Estado getEstado();

    public Marca getMarca();

    public Categoria getCategoria();

    public Locacao getLocacao();

    public String getPlaca();

    public int getAno();

    // valorParaVenda = valorDeCompra - idadeVeiculoEmAnos * 0,15 * valorDeCompra
    // Se o resultado for menor que 10% do valorDeCompra ou negativo,
    // valorParaVenda = valorDeCompra * 0,1
    public double getValorParaVenda();

    // Abstrato na classe Veiculo
    public double getValorDiariaLocacao();
}
