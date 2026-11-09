package trabalhofinaljava;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {

    private List<Cliente> clientes;
    private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereço"};

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Cliente c = clientes.get(linha);
        switch (coluna) {
            case 0:
                return c.getNome();
            case 1:
                return c.getSobrenome();
            case 2:
                return c.getRg();
            case 3:
                return c.getCpf();
            case 4:
                return c.getEndereco();
            default:
                return null;
        }
    }

    // Retorna o cliente de uma linha da tabela
    public Cliente getCliente(int linha) {
        return clientes.get(linha);
    }

    // Chamar depois de incluir, atualizar ou excluir para redesenhar a tabela
    public void atualizar() {
        fireTableDataChanged();
    }
}