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
public class LocacaoTableModel extends AbstractTableModel{
    private String[] colunas = {"Cliente", "Placa", "Marca", "Modelo", "Ano", 
        "Data Locacao", "Preco Diaria", "Dias", "Valor Locacao"};
    
    private List<VeiculoI> veiculos;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
