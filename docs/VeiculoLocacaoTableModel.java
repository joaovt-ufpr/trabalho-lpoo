/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhofinaljava;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author renan
 */
public class VeiculoLocacaoTableModel extends AbstractTableModel{
    
    private String[] colunas = {"Placa", "Marca", "Modelo", "Ano", "Preço da Diaria"};
 
    private List<VeiculoI> veiculos;
    
    public VeiculoLocacaoTableModel(List<VeiculoI> veiculos) {
        this.veiculos = veiculos;
    }
    
    public VeiculoI getVeiculoAt(int linha) {
        return veiculos.get(linha);
    }
    
    @Override
    public int getRowCount() {
       if(veiculos == null) {
           return 0;
       }
       return veiculos.size();
    }
    
    public void atualizarLista(List<VeiculoI> veiculos) {
        this.veiculos = veiculos;
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        VeiculoI veiculo = veiculos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veiculo.getPlaca();

            case 1:
                return veiculo.getMarca();

    
            case 2:
                if (veiculo instanceof Automovel) {
                    return ((Automovel) veiculo).getModelo();
                } else if (veiculo instanceof Motocicleta) {
                    return ((Motocicleta) veiculo).getModelo();
                } else if (veiculo instanceof Van) {
                    return ((Van) veiculo).getModelo();
                }
                return "-";

            case 3:
                return veiculo.getAno();

            case 4:
                return veiculo.getValorDiariaLocacao();

            default:
                return null;
        }
    }
    
}
