/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhofinaljava;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author renan
 */
public class LocacaoTableModel extends AbstractTableModel{
    private String[] colunas = {"Cliente", "Placa", "Marca", "Modelo", "Ano", 
        "Data Locacao", "Preco Diaria", "Dias", "Valor Locacao"};
    
    private List<VeiculoI> veiculos;
    
    public LocacaoTableModel(List<VeiculoI> veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public int getRowCount() {
        if (veiculos == null) {
            return 0;
        }

        return veiculos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        VeiculoI veiculo = veiculos.get(rowIndex);
        Locacao locacao = veiculo.getLocacao();

        switch (columnIndex) {

            case 0:
                return locacao.getCliente().getNome();

            case 1:
                return veiculo.getPlaca();

            case 2:
                return veiculo.getMarca();

            case 3:
                if (veiculo instanceof Automovel) {
                    return ((Automovel) veiculo).getModelo();
                } else if (veiculo instanceof Motocicleta) {
                    return ((Motocicleta) veiculo).getModelo();
                } else if (veiculo instanceof Van) {
                    return ((Van) veiculo).getModelo();
                }
                return "-";

            case 4:
                return veiculo.getAno();

            case 5:
                return new SimpleDateFormat("dd/MM/yyyy")
                        .format(locacao.getData().getTime());

            case 6:
                return veiculo.getValorDiariaLocacao();

            case 7:
                return locacao.getDias();

            case 8:
                return locacao.getValor();

            default:
                return null;
        }
    }

    public VeiculoI getVeiculoAt(int linha) {
        return veiculos.get(linha);
    }

    public void atualizarLista(List<VeiculoI> veiculos) {
        this.veiculos = veiculos;
        fireTableDataChanged();
    }
    public VeiculoI getVeiculo(int linha) {
        return veiculos.get(linha);
    }
    
}
