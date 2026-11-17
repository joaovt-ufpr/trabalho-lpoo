package trabalhofinaljava;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class TelaVenderVeiculo extends JFrame {

    private static final String TODOS = "Todos";

    private JComboBox<String> comboTipo;
    private JComboBox<Object> comboMarca;
    private JComboBox<Object> comboCategoria;
    private JButton btnFiltrar;
    private JButton btnVender;

    private VeiculoVendaTableModel modelo;
    private JTable tabela;

    public TelaVenderVeiculo() {
        super("Vender Veículos");

        comboTipo = new JComboBox<String>(new String[]{TODOS, "Automóvel", "Motocicleta", "Van"});

        // Primeiro item "Todos" e depois os valores do enum
        comboMarca = new JComboBox<Object>();
        comboMarca.addItem(TODOS);
        for (Marca m : Marca.values()) {
            comboMarca.addItem(m);
        }
        comboCategoria = new JComboBox<Object>();
        comboCategoria.addItem(TODOS);
        for (Categoria c : Categoria.values()) {
            comboCategoria.addItem(c);
        }

        btnFiltrar = new JButton("Filtrar");
        btnVender = new JButton("Vender");

        modelo = new VeiculoVendaTableModel();
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltros.add(new JLabel("Tipo:"));
        painelFiltros.add(comboTipo);
        painelFiltros.add(new JLabel("Marca:"));
        painelFiltros.add(comboMarca);
        painelFiltros.add(new JLabel("Categoria:"));
        painelFiltros.add(comboCategoria);
        painelFiltros.add(btnFiltrar);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnVender);

        setLayout(new BorderLayout(10, 10));
        add(painelFiltros, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vender();
            }
        });

        filtrar(); // ja abre listando todos os disponiveis

        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Monta a lista so com veiculos DISPONIVEL que passam nos filtros escolhidos
    private void filtrar() {
        String tipo = (String) comboTipo.getSelectedItem();
        Object marca = comboMarca.getSelectedItem();
        Object categoria = comboCategoria.getSelectedItem();

        List<Veiculo> resultado = new ArrayList<Veiculo>();
        for (Veiculo v : Repositorio.getVeiculos()) {
            if (v.getEstado() != Estado.DISPONIVEL) {
                continue;
            }
            if (!tipoConfere(v, tipo)) {
                continue;
            }
            if (!marca.equals(TODOS) && v.getMarca() != marca) {
                continue;
            }
            if (!categoria.equals(TODOS) && v.getCategoria() != categoria) {
                continue;
            }
            resultado.add(v);
        }
        modelo.setVeiculos(resultado);
    }

    private boolean tipoConfere(Veiculo v, String tipo) {
        if (tipo.equals("Automóvel")) {
            return v instanceof Automovel;
        } else if (tipo.equals("Motocicleta")) {
            return v instanceof Motocicleta;
        } else if (tipo.equals("Van")) {
            return v instanceof Van;
        }
        return true; // "Todos"
    }

    private void vender() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela.");
            return;
        }
        Veiculo v = modelo.getVeiculo(linha);
        String preco = String.format(Locale.forLanguageTag("pt-BR"), "R$%,.2f", v.getValorParaVenda());

        int resp = JOptionPane.showConfirmDialog(this,
                "Confirma a venda do veículo " + v.getPlaca() + " por " + preco + "?",
                "Confirmar venda", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            v.vender();
            JOptionPane.showMessageDialog(this, "Veículo " + v.getPlaca() + " vendido.");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        filtrar(); // vendido sai da tabela
    }

    // Main so para testar esta tela sozinha (com alguns veiculos de exemplo)
    public static void main(String[] args) {
        Repositorio.adicionarVeiculo(new Automovel(Marca.VW, Estado.DISPONIVEL, null,
                Categoria.POPULAR, 60000, "ABC-1234", 2022, ModeloAutomovel.GOL));
        Repositorio.adicionarVeiculo(new Motocicleta(Marca.HONDA, Estado.DISPONIVEL, null,
                Categoria.INTERMEDIARIO, 25000, "MOT-2020", 2020, ModeloMotocicleta.CBR500));
        Repositorio.adicionarVeiculo(new Van(Marca.MERCEDES, Estado.DISPONIVEL, null,
                Categoria.LUXO, 300000, "VAN-0001", 2015, ModeloVan.SPRINTER));
        Repositorio.adicionarVeiculo(new Automovel(Marca.GM, Estado.NOVO, null,
                Categoria.LUXO, 150000, "NOV-9999", 2026, ModeloAutomovel.ONIX));
        new TelaVenderVeiculo().setVisible(true);
    }
}
