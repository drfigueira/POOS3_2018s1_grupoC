package model;

import java.util.regex.Pattern;

/**
 * Classe que abstrai um usuario do sistema. A op&ccedil;&atilde;o por utilizar uma classe abstrata foi a devido &agrave; incerteza quanto
 * &agrave;s necessidades futuras do projeto quando se tratar de usu&aacute;rios do sistema.
 */
public abstract class User {
    private String email = null;
    private Password senha = null;

    public User() { }

    /**
     * Construtor base de User, recebe o email e a senha do usuario.
     * @param email Uma String contendo o email do usuario.
     * @param senha Uma String que ser&aacute; utilizada para construir a senha do usuario.
     */
    public User(String email, String senha) {
        setEmail(email);
        this.senha = new Password(senha);
    }

    /**
     * Permite alterar o email do usu&aacute;rio.
     * @param email Uma String representando o novo email do usu&aacute;rio.
     */
    public void setEmail(String email) {
        String pattern = "[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}";
        if(email.matches(pattern)) {
            this.email = email;
        }
    }

    /**
     * Recupera o email do User.
     * @return Uma String contendo o email do usuario.
     */
    public String getEmail() { return this.email; }

    /**
     * Permite alterar a senha do usu&aacute;rio. A altera&ccedil;&atilde;o poder&aacute; ser feita se, e somente se, o email inserido estiver correto.
     * @param email  Uma String contendo o email do usu&aacute;rio.
     * @param senha Uma String que construir&aacute; a nova senha do usu&aacute;rio.
     */
    public void setSenha(String email, String senha) {
        if (this.email.equals(email)) {
            this.senha = new Password(senha);
        }
    }

    public void setSenha(String email, byte[] senha) {
        if (this.email.equals(email)) {
            this.senha = new Password(senha);
        }
    }

    public Password getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        boolean retorno = false;
        if(o != null) {
            if (o instanceof User) {
                retorno = ((User) o).getEmail().equals(this.email);
            }
        }
        return retorno;
    }
}
