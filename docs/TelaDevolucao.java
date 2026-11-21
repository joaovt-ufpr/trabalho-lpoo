/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trabalhofinaljava;

/**
 *
 * @author renan
 */

public class TelaDevolucao extends javax.swing.JFrame {

    private LocacaoTableModel modeloTabela;

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(TelaDevolucao.class.getName());

    public TelaDevolucao() {

        initComponents();

        configurarBotaoVoltar();

        // Recalcula o tamanho após adicionar o rodapé
        pack();

        // Centraliza a janela
        setLocationRelativeTo(null);

        // Permite redimensionar
        setResizable(true);

        setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE
        );

        // Configura o modelo da tabela
        modeloTabela = new LocacaoTableModel(
                new java.util.ArrayList<VeiculoI>()
        );

        tabelaLocacoes.setModel(modeloTabela);

        // Seleção por linha
        tabelaLocacoes.setRowSelectionAllowed(true);
        tabelaLocacoes.setColumnSelectionAllowed(false);

        atualizarListaLocacoes();
    }

    // Configuração do botão voltar e do rodapé
    private void configurarBotaoVoltar() {

        javax.swing.JButton btnVoltar =
                new javax.swing.JButton("← Voltar");

        btnVoltar.setPreferredSize(
                new java.awt.Dimension(120, 38)
        );

        btnVoltar.addActionListener(e -> dispose());

        java.awt.Container conteudoOriginal = getContentPane();

        javax.swing.JPanel novoConteudo =
                new javax.swing.JPanel(
                        new java.awt.BorderLayout(0, 10)
                );

        novoConteudo.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(
                        5, 5, 5, 5
                )
        );

        setContentPane(novoConteudo);

        // Área principal
        novoConteudo.add(
                conteudoOriginal,
                java.awt.BorderLayout.CENTER
        );

        // Rodapé
        javax.swing.JPanel rodape =
                new javax.swing.JPanel(
                        new java.awt.FlowLayout(
                                java.awt.FlowLayout.LEFT
                        )
                );

        rodape.add(btnVoltar);

        novoConteudo.add(
                rodape,
                java.awt.BorderLayout.SOUTH
        );
    }

    // Atualiza as locações ativas
    private void atualizarListaLocacoes() {

        java.util.List<VeiculoI> veiculosLocados =
                new java.util.ArrayList<>();

        for (Veiculo veiculo : Repositorio.getVeiculos()) {

            if (veiculo.getEstado() == Estado.LOCADO) {

                veiculosLocados.add(veiculo);
            }
        }

        modeloTabela.atualizarLista(veiculosLocados);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        painelCabecalho = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        painelLocacoes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaLocacoes = new javax.swing.JTable();
        btnDevolver = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE
        );

        // Título
        jLabel1.setFont(
                new java.awt.Font("Arial", 1, 24)
        );

        jLabel1.setText("Devolução de Veículos");

        javax.swing.GroupLayout painelCabecalhoLayout =
                new javax.swing.GroupLayout(painelCabecalho);

        painelCabecalho.setLayout(painelCabecalhoLayout);

        painelCabecalhoLayout.setHorizontalGroup(
                painelCabecalhoLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(
                        painelCabecalhoLayout.createSequentialGroup()
                                .addGap(329, 329, 329)
                                .addComponent(
                                        jLabel1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        312,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )
                                .addContainerGap(305, Short.MAX_VALUE)
                )
        );

        painelCabecalhoLayout.setVerticalGroup(
                painelCabecalhoLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(
                        painelCabecalhoLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1)
                                .addContainerGap(33, Short.MAX_VALUE)
                )
        );

        // Painel das locações
        painelLocacoes.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        "Locações Ativas"
                )
        );

        // Tabela
        tabelaLocacoes.setModel(
                new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                                "Cliente",
                                "Placa",
                                "Marca",
                                "Modelo",
                                "Ano",
                                "Data Locação",
                                "Dias",
                                "Valor Total"
                        }
                ) {
                    @Override
                    public boolean isCellEditable(
                            int rowIndex,
                            int columnIndex
                    ) {
                        return false;
                    }
                }
        );

        jScrollPane1.setViewportView(tabelaLocacoes);

        // Botão devolver
        btnDevolver.setText("Devolver Veículo");

        btnDevolver.addActionListener(
                this::btnDevolverActionPerformed
        );

        // Botão atualizar
        btnAtualizar.setText("Atualizar Lista");

        btnAtualizar.addActionListener(
                e -> atualizarListaLocacoes()
        );

        // Layout horizontal do painel
        javax.swing.GroupLayout painelLocacoesLayout =
                new javax.swing.GroupLayout(painelLocacoes);

        painelLocacoes.setLayout(painelLocacoesLayout);

        painelLocacoesLayout.setHorizontalGroup(
                painelLocacoesLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(
                        painelLocacoesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        painelLocacoesLayout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING
                                        )
                                        .addComponent(jScrollPane1)
                                        .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                painelLocacoesLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(btnDevolver)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnAtualizar)
                                        )
                                )
                                .addContainerGap()
                )
        );

        painelLocacoesLayout.setVerticalGroup(
                painelLocacoesLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING
                )
                .addGroup(
                        painelLocacoesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(
                                        jScrollPane1,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        379,
                                        Short.MAX_VALUE
                                )
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                                )
                                .addGroup(
                                        painelLocacoesLayout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE
                                        )
                                        .addComponent(btnDevolver)
                                        .addComponent(btnAtualizar)
                                )
                                .addGap(19, 19, 19)
                )
        );

        // Layout principal
        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING
                                        )
                                        .addComponent(
                                                painelCabecalho,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE
                                        )
                                        .addComponent(
                                                painelLocacoes,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE
                                        )
                                )
                                .addContainerGap()
                )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(
                                painelCabecalho,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED
                        )
                        .addComponent(
                                painelLocacoes,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                        )
                        .addContainerGap()
        );

        pack();
    }

    // Ação do botão devolver
    private void btnDevolverActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        int linhaSelecionada = tabelaLocacoes.getSelectedRow();

        if (linhaSelecionada == -1) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma locação para devolver."
            );

            return;
        }

        VeiculoI veiculo =
                modeloTabela.getVeiculo(
                        tabelaLocacoes.convertRowIndexToModel(
                                linhaSelecionada
                        )
                );

        int confirmacao =
                javax.swing.JOptionPane.showConfirmDialog(
                        this,
                        "Confirma a devolução do veículo "
                                + veiculo.getPlaca() + "?",
                        "Confirmar devolução",
                        javax.swing.JOptionPane.YES_NO_OPTION
                );

        if (confirmacao == javax.swing.JOptionPane.YES_OPTION) {

            veiculo.devolver();

            atualizarListaLocacoes();

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Veículo devolvido com sucesso!"
            );
        }
    }

    // Inicialização
    public static void main(String args[]) {

        try {

            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (
                ReflectiveOperationException
                | javax.swing.UnsupportedLookAndFeelException ex
        ) {

            logger.log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex
            );
        }

        java.awt.EventQueue.invokeLater(() -> {

            new TelaDevolucao().setVisible(true);

        });
    }

    // Componentes
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelCabecalho;
    private javax.swing.JPanel painelLocacoes;
    private javax.swing.JTable tabelaLocacoes;
}
