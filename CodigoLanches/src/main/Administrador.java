package main;

import javax.swing.*;
import java.util.Map;

public class Administrador {
    private Map<Integer, Produto> cardapio;

    public Administrador(Map<Integer, Produto> cardapio) {
        this.cardapio = cardapio;
    }

    public void menuAdministrador() {
        int opcao = -1;

        while (opcao != 0) {
            String menu = """
            ---- MODO ADMINISTRADOR ----
            1 - Adicionar item ao cardápio
            2 - Alterar item do cardápio
            3 - Excluir item do cardápio
            4 - Ver cardápio atual
            0 - Voltar
            Escolha uma opção:
            """;

            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> adicionarItemCardapio();
                case 2 -> alterarItemCardapio();
                case 3 -> excluirItemCardapio();
                case 4 -> mostrarCardapio();
                case 0 -> JOptionPane.showMessageDialog(null, "Saindo do modo administrador.");
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    public void adicionarItemCardapio() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do novo produto:");
            double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto:"));

            int novoCodigo = cardapio.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
            cardapio.put(novoCodigo, new Produto(nome, preco));

            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Preço inválido.");
        }
    }

    public void alterarItemCardapio() {
        try {
            StringBuilder lista = new StringBuilder("Itens do cardápio:\n");
            cardapio.forEach((codigo, produto) ->
                lista.append(codigo).append(" - ").append(produto.getNome())
                     .append(" (R$ ").append(produto.getPreco()).append(")\n")
            );

            int codigo = Integer.parseInt(JOptionPane.showInputDialog(lista + "\nDigite o código do item que deseja alterar:"));

            if (cardapio.containsKey(codigo)) {
                Produto antigo = cardapio.get(codigo);

                String novoNome = JOptionPane.showInputDialog("Digite o novo nome (ou deixe em branco para manter):", antigo.getNome());
                String precoStr = JOptionPane.showInputDialog("Digite o novo preço (ou deixe em branco para manter):", antigo.getPreco());

                String nomeFinal = novoNome.isEmpty() ? antigo.getNome() : novoNome;
                double precoFinal = precoStr.isEmpty() ? antigo.getPreco() : Double.parseDouble(precoStr);

                cardapio.put(codigo, new Produto(nomeFinal, precoFinal));
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Código não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    public void excluirItemCardapio() {
        try {
            StringBuilder lista = new StringBuilder("Itens do cardápio:\n");
            cardapio.forEach((codigo, produto) ->
                lista.append(codigo).append(" - ").append(produto.getNome()).append("\n")
            );

            int codigo = Integer.parseInt(JOptionPane.showInputDialog(lista + "\nDigite o código do item a excluir:"));

            if (cardapio.containsKey(codigo)) {
                cardapio.remove(codigo);
                JOptionPane.showMessageDialog(null, "Item excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Código inválido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    public void mostrarCardapio() {
        StringBuilder lista = new StringBuilder("Cardápio Atual:\n");
        cardapio.forEach((codigo, produto) ->
            lista.append(codigo).append(" - ").append(produto.getNome())
                 .append(" (R$ ").append(String.format("%.2f", produto.getPreco())).append(")\n")
        );
        JOptionPane.showMessageDialog(null, lista.toString());
    }
}
