package trabalhofinaljava;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class TelaIncluirVeiculo extends JFrame {

    private JComboBox<String> comboTipo;
    private JComboBox<Marca> comboMarca;
    private JComboBox<Object> comboModelo;
    private JComboBox<Categoria> comboCategoria;
    private JComboBox<Estado> comboEstado;
    private JSpinner spinnerAno;
    private JFormattedTextField txtPlaca;
    private JFormattedTextField txtValorCompra;
    private JButton btnIncluir;
    private JButton btnLimpar;

    public TelaIncluirVeiculo() {
        super("Incluir Veículos");

        comboTipo = new JComboBox<String>(new String[]{"Automóvel", "Motocicleta", "Van"});
        comboMarca = new JComboBox<Marca>(Marca.values());
        comboModelo = new JComboBox<Object>();
        comboCategoria = new JComboBox<Categoria>(Categoria.values());
        // Veiculo recem comprado: nao faz sentido cadastrar ja LOCADO ou VENDIDO
        comboEstado = new JComboBox<Estado>(new Estado[]{Estado.NOVO, Estado.DISPONIVEL});

        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        spinnerAno = new JSpinner(new SpinnerNumberModel(anoAtual, 1950, anoAtual + 1, 1));
        spinnerAno.setEditor(new JSpinner.NumberEditor(spinnerAno, "#")); // sem separador de milhar

        // Mascara da placa: U = letra (convertida para maiuscula), # = digito
        try {
            MaskFormatter mascaraPlaca = new MaskFormatter("UUU-####");
            mascaraPlaca.setPlaceholderCharacter('_');
            txtPlaca = new JFormattedTextField(mascaraPlaca);
        } catch (ParseException ex) {
            txtPlaca = new JFormattedTextField();
        }

        // Valor monetario no formato brasileiro (ex: 45.000,00)
        DecimalFormat formatoMoeda = new DecimalFormat("#,##0.00",
                new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));
        NumberFormatter formatterMoeda = new NumberFormatter(formatoMoeda);
        formatterMoeda.setValueClass(Double.class);
        txtValorCompra = new JFormattedTextField(formatterMoeda);

        btnIncluir = new JButton("Incluir");
        btnLimpar = new JButton("Limpar");

        atualizarModelos();

        // Campos do formulario
        JPanel painelCampos = new JPanel(new GridLayout(8, 2, 8, 8));
        painelCampos.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createTitledBorder("Dados do Veículo"),
                javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        painelCampos.add(new JLabel("Tipo:"));
        painelCampos.add(comboTipo);
        painelCampos.add(new JLabel("Marca:"));
        painelCampos.add(comboMarca);
        painelCampos.add(new JLabel("Modelo:"));
        painelCampos.add(comboModelo);
        painelCampos.add(new JLabel("Categoria:"));
        painelCampos.add(comboCategoria);
        painelCampos.add(new JLabel("Estado:"));
        painelCampos.add(comboEstado);
        painelCampos.add(new JLabel("Ano:"));
        painelCampos.add(spinnerAno);
        painelCampos.add(new JLabel("Placa (XXX-0000):"));
        painelCampos.add(txtPlaca);
        painelCampos.add(new JLabel("Valor de compra (R$):"));
        painelCampos.add(txtValorCompra);

        JPanel painelAcoes = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        painelAcoes.add(btnIncluir);
        painelAcoes.add(btnLimpar);

        JButton btnVoltar = new JButton("← Voltar");
        btnVoltar.setFont(javax.swing.UIManager.getFont("Button.font"));
        btnVoltar.setPreferredSize(new java.awt.Dimension(100, 32));
        btnVoltar.setMinimumSize(new java.awt.Dimension(100, 32));
        btnVoltar.setMaximumSize(new java.awt.Dimension(100, 32));
        btnVoltar.setMargin(new java.awt.Insets(5, 10, 5, 10));

        btnVoltar.addActionListener(e -> dispose());
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.add(btnVoltar, BorderLayout.WEST);
        painelRodape.add(painelAcoes, BorderLayout.EAST);

        setLayout(new BorderLayout(10, 10));
        add(painelCampos, BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);

        // Eventos
        comboTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarModelos();
            }
        });
        btnIncluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incluir();
            }
        });
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        setSize(520, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Troca os modelos da combo conforme o tipo escolhido
    private void atualizarModelos() {
        String tipo = (String) comboTipo.getSelectedItem();
        if (tipo.equals("Automóvel")) {
            comboModelo.setModel(new DefaultComboBoxModel<Object>(ModeloAutomovel.values()));
        } else if (tipo.equals("Motocicleta")) {
            comboModelo.setModel(new DefaultComboBoxModel<Object>(ModeloMotocicleta.values()));
        } else {
            comboModelo.setModel(new DefaultComboBoxModel<Object>(ModeloVan.values()));
        }
    }

    private void incluir() {
        String placa;
        double valorCompra;

        try {
            txtPlaca.commitEdit();
            placa = (String) txtPlaca.getValue();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Placa inválida. Use o formato XXX-0000.");
            return;
        }
        if (placa == null) {
            JOptionPane.showMessageDialog(this, "Informe a placa.");
            return;
        }

        try {
            txtValorCompra.commitEdit();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Informe um valor de compra válido (ex: 45.000,00).");
            return;
        }
        if (txtValorCompra.getValue() == null) {
            JOptionPane.showMessageDialog(this, "Informe o valor de compra.");
            return;
        }
        valorCompra = ((Number) txtValorCompra.getValue()).doubleValue();
        if (valorCompra <= 0) {
            JOptionPane.showMessageDialog(this, "O valor de compra deve ser maior que zero.");
            return;
        }

        if (placaJaCadastrada(placa)) {
            JOptionPane.showMessageDialog(this, "Já existe um veículo com a placa " + placa + ".");
            return;
        }

        Marca marca = (Marca) comboMarca.getSelectedItem();
        Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
        Estado estado = (Estado) comboEstado.getSelectedItem();
        int ano = (Integer) spinnerAno.getValue();
        String tipo = (String) comboTipo.getSelectedItem();

        // Locacao comeca nula: so e criada quando o veiculo for locado
        Veiculo veiculo;
        if (tipo.equals("Automóvel")) {
            veiculo = new Automovel(marca, estado, null, categoria, valorCompra, placa, ano,
                    (ModeloAutomovel) comboModelo.getSelectedItem());
        } else if (tipo.equals("Motocicleta")) {
            veiculo = new Motocicleta(marca, estado, null, categoria, valorCompra, placa, ano,
                    (ModeloMotocicleta) comboModelo.getSelectedItem());
        } else {
            veiculo = new Van(marca, estado, null, categoria, valorCompra, placa, ano,
                    (ModeloVan) comboModelo.getSelectedItem());
        }

        Repositorio.adicionarVeiculo(veiculo);
        JOptionPane.showMessageDialog(this, tipo + " de placa " + placa + " incluído com sucesso.");
        limparCampos();
    }

   /* private boolean placaJaCadastrada(String placa) {
        for (Veiculo v : Repositorio.getVeiculos()) {
            if (v.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
*/
    
    private boolean placaJaCadastrada(String placa) {
    List<Veiculo> lista = Repositorio.getVeiculos();

    for (Veiculo v : lista) {
        if (v.getPlaca().equals(placa)) {
            return true;
        }
    }

    return false;
}
    private void limparCampos() {
        comboTipo.setSelectedIndex(0);
        comboMarca.setSelectedIndex(0);
        comboCategoria.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        spinnerAno.setValue(Calendar.getInstance().get(Calendar.YEAR));
        txtPlaca.setValue(null);
        txtValorCompra.setValue(null);
    }

    // Main so para testar esta tela sozinha
    public static void main(String[] args) {
        new TelaIncluirVeiculo().setVisible(true);
    }
}
