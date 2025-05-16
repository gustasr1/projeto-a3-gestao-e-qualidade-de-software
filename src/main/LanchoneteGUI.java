package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class LanchoneteGUI {

    private JFrame frame;
    private JTextArea resumoArea;
    private Map<String, Produto> cardapio;
    private double totalPedido = 0;

    public LanchoneteGUI() {
        inicializarCardapio();
        criarInterface();
    }

    private void inicializarCardapio() {
        cardapio = new LinkedHashMap<>();
        cardapio.put("Cachorro Quente", new Produto("Cachorro Quente", 7.5));
        cardapio.put("Hamburguer", new Produto("Hamburguer", 20.0));
        cardapio.put("Xburguer", new Produto("Xburguer", 25.5));
        cardapio.put("Egg Burguer", new Produto("Egg Burguer", 22.0));
        cardapio.put("Refrigerante Lata", new Produto("Refrigerante Lata", 5.5));
    }

    private void criarInterface() {
        frame = new JFrame("Sistema da Lanchonete");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel botoesPanel = new JPanel(new GridLayout(0, 1));
        for (String nome : cardapio.keySet()) {
            JButton botao = new JButton(nome + " - R$ " + cardapio.get(nome).getPreco());
            botao.addActionListener(e -> adicionarProduto(nome));
            botoesPanel.add(botao);
        }

        resumoArea = new JTextArea();
        resumoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resumoArea);

        JButton finalizarButton = new JButton("Finalizar Pedido");
        finalizarButton.addActionListener(e -> finalizarPedido());

        frame.add(botoesPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(finalizarButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void adicionarProduto(String nome) {
        Produto produto = cardapio.get(nome);
        String input = JOptionPane.showInputDialog(frame, "Quantidade de " + nome + ":");
        try {
            int quantidade = Integer.parseInt(input);
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(frame, "Quantidade inválida.");
                return;
            }
            double subtotal = produto.calcularSubtotal(quantidade);
            totalPedido += subtotal;
            resumoArea.append(String.format("%dx %s - R$ %.2f\n", quantidade, nome, subtotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Entrada inválida.");
        }
    }

    private void finalizarPedido() {
        JOptionPane.showMessageDialog(frame, String.format("Total do pedido: R$ %.2f", totalPedido));
        totalPedido = 0;
        resumoArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LanchoneteGUI::new);
    }
}
