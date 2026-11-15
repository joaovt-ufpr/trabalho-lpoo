package trabalhofinaljava;

public class Motocicleta extends Veiculo {

    private ModeloMotocicleta modelo;

    public Motocicleta(Marca marca, Estado estado, Locacao locacao, Categoria categoria,
            double valorDeCompra, String placa, int ano, ModeloMotocicleta modelo) {
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    public ModeloMotocicleta getModelo() {
        return modelo;
    }

    @Override
    public double getValorDiariaLocacao() {
        switch (getCategoria()) {
            case POPULAR:
                return 70.0;
            case INTERMEDIARIO:
                return 200.0;
            case LUXO:
                return 350.0;
            default:
                return 0.0;
        }
    }
}
