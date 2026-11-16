package trabalhofinaljava;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TelaClientes extends JFrame implements ActionListener {

    private JTextField txtNome = new JTextField();
    private JTextField txtSobrenome = new JTextField();
    private JTextField txtRg = new JTextField();
    private JTextField txtCpf = new JTextField();
    private JTextField txtEndereco = new JTextField();

    private JButton btnIncluir = new JButton("Incluir");
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnExcluir = new JButton("Excluir");
    private JButton btnLimpar = new JButton("Limpar");

    private ClienteTableModel modelo;
    private JTable tabela;

    public TelaClientes() {
        super("Manter Clientes");

        modelo = new ClienteTableModel(Repositorio.getClientes());
        tabela = new JTable(modelo);

        // Painel com os campos do formulario, com borda e espacamento interno
        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 8, 8));
        painelCampos.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createTitledBorder("Dados do Cliente"),
                javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Sobrenome:"));
        painelCampos.add(txtSobrenome);
        painelCampos.add(new JLabel("RG:"));
        painelCampos.add(txtRg);
        painelCampos.add(new JLabel("CPF:"));
        painelCampos.add(txtCpf);
        painelCampos.add(new JLabel("Endereço:"));
        painelCampos.add(txtEndereco);

        // Ações ficam à direita e o botão Voltar à esquerda.
        JPanel painelAcoes = new JPanel();
        painelAcoes.add(btnIncluir);
        painelAcoes.add(btnAtualizar);
        painelAcoes.add(btnExcluir);
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

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(painelCampos, BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(painelTopo, BorderLayout.NORTH);
        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes Cadastrados"));
        add(scrollTabela, BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);

        // Eventos
        btnIncluir.addActionListener(this);
        btnAtualizar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnLimpar.addActionListener(this);

        // Ao clicar numa linha da tabela, preenche os campos
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                preencherCampos();
            }
        });

        setSize(820, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIncluir) {
            incluir();
        } else if (e.getSource() == btnAtualizar) {
            atualizar();
        } else if (e.getSource() == btnExcluir) {
            excluir();
        } else if (e.getSource() == btnLimpar) {
            limparCampos();
        }
    }

    private void incluir() {
        if (!camposValidos()) {
            return;
        }
        String cpf = txtCpf.getText().trim();
        if (Repositorio.cpfJaCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, "Já existe um cliente com esse CPF.");
            return;
        }
        Cliente c = new Cliente(txtNome.getText().trim(), txtSobrenome.getText().trim(),
                txtRg.getText().trim(), cpf, txtEndereco.getText().trim());
        Repositorio.adicionarCliente(c);
        modelo.atualizar();
        limparCampos();
    }

    private void atualizar() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela.");
            return;
        }
        if (!camposValidos()) {
            return;
        }
        Cliente c = modelo.getCliente(linha);
        String cpf = txtCpf.getText().trim();

        // Se mudou o CPF, nao pode ser igual ao de outro cliente
        if (!cpf.equals(c.getCpf()) && Repositorio.cpfJaCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, "Já existe um cliente com esse CPF.");
            return;
        }
        c.setNome(txtNome.getText().trim());
        c.setSobrenome(txtSobrenome.getText().trim());
        c.setRg(txtRg.getText().trim());
        c.setCpf(cpf);
        c.setEndereco(txtEndereco.getText().trim());
        modelo.atualizar();
        limparCampos();
    }

    private void excluir() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela.");
            return;
        }
        Cliente c = modelo.getCliente(linha);

        // Regra do enunciado: nao excluir cliente com veiculo locado
        if (Repositorio.clientePossuiLocacaoAtiva(c)) {
            JOptionPane.showMessageDialog(this,
                    "Este cliente não pode ser excluído, pois possui veículo locado.");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this, "Deseja excluir o cliente " + c.getNome() + "?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            Repositorio.removerCliente(c);
            modelo.atualizar();
            limparCampos();
        }
    }

    private void preencherCampos() {
        int linha = tabela.getSelectedRow();
        if (linha != -1) {
            Cliente c = modelo.getCliente(linha);
            txtNome.setText(c.getNome());
            txtSobrenome.setText(c.getSobrenome());
            txtRg.setText(c.getRg());
            txtCpf.setText(c.getCpf());
            txtEndereco.setText(c.getEndereco());
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtRg.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        tabela.clearSelection();
    }

    private boolean camposValidos() {
        if (txtNome.getText().trim().isEmpty() || txtSobrenome.getText().trim().isEmpty()
                || txtRg.getText().trim().isEmpty() || txtCpf.getText().trim().isEmpty()
                || txtEndereco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return false;
        }
        return true;
    }

    // Main so para testar esta tela sozinha
    public static void main(String[] args) {
        new TelaClientes().setVisible(true);
    }
}