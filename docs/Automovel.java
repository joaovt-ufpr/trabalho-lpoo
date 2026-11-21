package trabalhofinaljava;

public class Automovel extends Veiculo {

    private ModeloAutomovel modelo;

    public Automovel(Marca marca, Estado estado, Locacao locacao, Categoria categoria,
            double valorDeCompra, String placa, int ano, ModeloAutomovel modelo) {
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    public ModeloAutomovel getModelo() {
        return modelo;
    }

    @Override
    public double getValorDiariaLocacao() {
        switch (getCategoria()) {
            case POPULAR:
                return 100.0;
            case INTERMEDIARIO:
                return 300.0;
            case LUXO:
                return 450.0;
            default:
                return 0.0;
        }
    }
}
