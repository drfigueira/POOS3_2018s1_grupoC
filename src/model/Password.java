package model;
;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe que encapsula as fun&ccedil;&otilde;es de encripta&ccedil;&atilde;o e valida&ccedil;&atilde;o de senhas.
 */
public class Password {
    private byte[] senha;

    /**
     * Construtor base da classe, recebe a palavra que ser&aacute; utilizada como senha.
     * @param senha Uma String contendo a nova senha.
     */
    public Password(String senha) {
        try {
            if(senha == null || senha.isEmpty()) {
                senha = "senha";
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            this.senha = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Verifica se a senha passada como par&acirc;metro &eacute; compat&iacute;vel com a senha armazenada nesse objeto.
     * @param senha Uma String contendo a senha que deseja-se verificar.
     * @return Um boolean informando a validade da senha informada (true caso seja a senha correta, false caso contr&aacute;rio).
     */
    public boolean verificarSenha(String senha) {
        boolean retorno = false;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            
	        byte[] palpite = md.digest();

	        retorno = this.senha.length == palpite.length;
            for(int i = 0; i < this.senha.length && retorno; i++) {
                retorno = this.senha[i] == palpite[i];
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return retorno;
    }
}
