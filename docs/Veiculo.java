package trabalhofinaljava;

import java.util.Calendar;

public abstract class Veiculo implements VeiculoI {

    private Marca marca;
    private Estado estado;
    private Locacao locacao;
    private Categoria categoria;
    private double valorDeCompra;
    private String placa;
    private int ano;

    public Veiculo(Marca marca, Estado estado, Locacao locacao, Categoria categoria,
            double valorDeCompra, String placa, int ano) {
        this.marca = marca;
        this.estado = estado;
        this.locacao = locacao;
        this.categoria = categoria;
        this.valorDeCompra = valorDeCompra;
        this.placa = placa;
        this.ano = ano;
    }

    @Override
    public void locar(int dias, Calendar data, Cliente cliente) {
        if (estado != Estado.DISPONIVEL) {
            throw new RuntimeException("Veículo não está disponível para locação.");
        }
        double valor = dias * getValorDiariaLocacao();
        locacao = new Locacao(dias, valor, data, cliente);
        estado = Estado.LOCADO;
    }

    @Override
    public void vender() {
        if (estado == Estado.LOCADO || estado == Estado.VENDIDO) {
            throw new RuntimeException("Veículo locado ou já vendido não pode ser vendido.");
        }
        estado = Estado.VENDIDO;
    }

    @Override
    public void devolver() {
        if (estado != Estado.LOCADO) {
            throw new RuntimeException("Somente veículo locado pode ser devolvido.");
        }
        locacao = null;
        estado = Estado.DISPONIVEL;
    }

    @Override
    public Estado getEstado() {
        return estado;
    }

    @Override
    public Marca getMarca() {
        return marca;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public Locacao getLocacao() {
        return locacao;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public int getAno() {
        return ano;
    }

    @Override
    public double getValorParaVenda() {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int idade = anoAtual - ano;
        if (idade < 0) { // ano modelo posterior ao ano atual
            idade = 0;
        }
        double valor = valorDeCompra - idade * 0.15 * valorDeCompra;
        if (valor < valorDeCompra * 0.1) {
            valor = valorDeCompra * 0.1;
        }
        return valor;
    }

    // Cada tipo de veiculo tem sua propria tabela de diarias
    @Override
    public abstract double getValorDiariaLocacao();
}
