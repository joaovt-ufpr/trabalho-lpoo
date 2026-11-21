package br.ufpr.modulos.classes;

public class Van extends Veiculo {

    private ModeloVan modelo;

    public Van(Marca marca, Estado estado, Locacao locacao, Categoria categoria,
            double valorDeCompra, String placa, int ano, ModeloVan modelo) {
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    public ModeloVan getModelo() {
        return modelo;
    }

    @Override
    public double getValorDiariaLocacao() {
        switch (getCategoria()) {
            case POPULAR:
                return 200.0;
            case INTERMEDIARIO:
                return 400.0;
            case LUXO:
                return 600.0;
            default:
                return 0.0;
        }
    }
}
