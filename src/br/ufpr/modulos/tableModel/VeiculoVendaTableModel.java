package br.ufpr.modulos.tableModel;

import br.ufpr.modulos.classes.Veiculo;
import br.ufpr.modulos.classes.Van;
import br.ufpr.modulos.classes.Motocicleta;
import br.ufpr.modulos.telas.Automovel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

public class VeiculoVendaTableModel extends AbstractTableModel {

    private List<Veiculo> veiculos = new ArrayList<Veiculo>();
    private String[] colunas = {"Placa", "Marca", "Modelo", "Ano", "Preço para venda"};

    @Override
    public int getRowCount() {
        return veiculos.size();
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
        Veiculo v = veiculos.get(linha);
        switch (coluna) {
            case 0:
                return v.getPlaca();
            case 1:
                return v.getMarca();
            case 2:
                return getModelo(v);
            case 3:
                return String.format("%04d", v.getAno());
            case 4:
                return String.format(Locale.forLanguageTag("pt-BR"), "R$%,.2f", v.getValorParaVenda());
            default:
                return null;
        }
    }

    // getModelo() nao faz parte de VeiculoI, cada subclasse tem o seu
    private Object getModelo(Veiculo v) {
        if (v instanceof Automovel) {
            return ((Automovel) v).getModelo();
        } else if (v instanceof Motocicleta) {
            return ((Motocicleta) v).getModelo();
        } else if (v instanceof Van) {
            return ((Van) v).getModelo();
        }
        return null;
    }

    public Veiculo getVeiculo(int linha) {
        return veiculos.get(linha);
    }

    // Troca a lista exibida (resultado do filtro) e redesenha a tabela
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        fireTableDataChanged();
    }
}
