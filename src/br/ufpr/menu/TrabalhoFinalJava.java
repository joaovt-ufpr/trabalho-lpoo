package br.ufpr.menu;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author Samsung
 */

import br.ufpr.modulos.telas.TelaIncluirVeiculo;
import br.ufpr.modulos.telas.TelaLocacao;
import br.ufpr.modulos.telas.TelaDevolucao;
import br.ufpr.modulos.telas.TelaClientes;
import br.ufpr.modulos.telas.TelaVenderVeiculo;
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
        JButton btnVender = new JButton("Vender Veículo");

        // Painel dos botões
        JPanel painelBotoes = new JPanel(new GridLayout(5, 1, 10, 10));
        painelBotoes.setBorder(
            BorderFactory.createEmptyBorder(30, 100, 30, 100)
        );

        painelBotoes.add(btnClientes);
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnLocacao);
        painelBotoes.add(btnDevolucao);
        painelBotoes.add(btnVender);

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

        // Ação: vender veículo
        btnVender.addActionListener(e -> {
            new TelaVenderVeiculo().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrabalhoFinalJava().setVisible(true);
        });
    }
}
