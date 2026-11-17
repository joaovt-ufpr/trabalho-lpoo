/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trabalhofinaljava;

/**
 *
 * @author Samsung
 */

import javax.swing.*;
import java.awt.*;

public class TrabalhoFinalJava extends JFrame {

    public TrabalhoFinalJava() {
        setTitle("Sistema de Locação de Veículos");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Título
        JLabel titulo = new JLabel("Sistema de Locação de Veículos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Botões
        JButton btnClientes = new JButton("Cadastrar Cliente");
        JButton btnCadastrar = new JButton("Cadastrar Veículo");
        JButton btnLocacao = new JButton("Realizar Locação");
        JButton btnDevolucao = new JButton("Realizar Devolução");

        // Painel dos botões
        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 10, 10));
        painelBotoes.setBorder(
            BorderFactory.createEmptyBorder(30, 100, 30, 100)
        );

        painelBotoes.add(btnClientes);
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnLocacao);
        painelBotoes.add(btnDevolucao);

        // Layout principal
        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);

        // Ação: cadastrar cliente
        btnClientes.addActionListener(e -> {
            new TelaClientes().setVisible(true);
        });

        // Ação: cadastrar veículo
        btnCadastrar.addActionListener(e -> {
            new TelaIncluirVeiculo().setVisible(true);
        });

        // Ação: realizar locação
        btnLocacao.addActionListener(e -> {
            new TelaLocacao().setVisible(true);
        });

        // Ação: realizar devolução
        btnDevolucao.addActionListener(e -> {
            new TelaDevolucao().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrabalhoFinalJava().setVisible(true);
        });
    }
}
