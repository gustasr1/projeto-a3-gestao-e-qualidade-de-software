package main;

// Classe que representa um produto do cardápio

public class Produto {

    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public double calcularSubtotal(double quantidade) {
        return quantidade * preco;
    }
}
    

