package main;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

// Classe principal que representa o sistema de lanchonete
public class LanchoneteSystem {

    private Map<Integer, Produto> cardapio;
    private double totalPedido;
    private Administrador admin;

    public LanchoneteSystem() {
        inicializarCardapio();
        totalPedido = 0;
        admin = new Administrador(cardapio); //Inicia o modo admin
    }

    private void inicializarCardapio() {
        cardapio = new HashMap<>();
        cardapio.put(1, new Produto("Cachorro quente", 7.5));
        cardapio.put(2, new Produto("Hamburguer", 20.0));
        cardapio.put(3, new Produto("Xburguer", 25.5));
        cardapio.put(4, new Produto("Egg Burguer", 22.0));
        cardapio.put(5, new Produto("Refrigerante Lata", 5.5));
    }

    public void executar() {
        int codigo = -1;
        
        while (codigo != 0) {
            codigo = exibirMenuEObterOpcao();

            if(codigo == 99) {
                admin.menuAdministrador(); // Ativa o modo administrador
            } else if (codigo != 0 && cardapio.containsKey(codigo)) {
                processarPedido(codigo);
            } else if (codigo != 0) {
                JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }
  }

    private int exibirMenuEObterOpcao() {
        StringBuilder menu = new StringBuilder("----MENU----\n");
        cardapio.forEach((key, produto) -> 
            menu.append(key).append(" - ").append(produto.getNome()).append("\n"));
        menu.append("0 - Sair\n");
        menu.append("Digite sua opção");
        
        String valorDigitado = JOptionPane.showInputDialog(menu.toString());
        
        if (valorDigitado == null || valorDigitado.isEmpty()) {
            return -1;
        }
        
        try {
            return Integer.parseInt(valorDigitado);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processarPedido(int codigoProduto) {
        try {
            String inputQuantidade = JOptionPane.showInputDialog("Informe a quantidade");
            double quantidade = Double.parseDouble(inputQuantidade);
            
            Produto produto = cardapio.get(codigoProduto);
            double subtotal = produto.calcularSubtotal(quantidade);
            totalPedido += subtotal;
            
            exibirResumoPedido(subtotal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida");
        }
    }

    private void exibirResumoPedido(double subtotal) {
        String texto = String.format("Subtotal: %.2f\nTotal do pedido: %.2f", 
                                   subtotal, totalPedido);
        JOptionPane.showMessageDialog(null, texto);
    }

    public static void main(String[] args) {
        new LanchoneteSystem().executar();
    }



}


