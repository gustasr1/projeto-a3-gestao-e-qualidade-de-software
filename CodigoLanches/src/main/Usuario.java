package main;

//Classe para representar usuários com login e senha
public class Usuario {

    private String login;
    private String senha;
    private boolean isAdmin;

    public Usuario(String login, String senha, boolean isAdmin){
        this.login = login;
        this.isAdmin = isAdmin;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    
    
}
