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
    
    @Override
    public int getRowCount() {
       if(veiculos == null) {
           return 0;
       }
       return veiculos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pendente joao fazer a parte dele para implementar esse metodo
        return veiculo;
    }
    
}
