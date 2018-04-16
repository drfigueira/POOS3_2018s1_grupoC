package View;

import model.UserSystem;

public class Main {
    public static void main(String[] args) {
        /**
         * Criando Objeto Usuário para testes
         */
        UserSystem usuario1 = new UserSystem("igor.gabriel.saturnino@gmail.com", "igor123");

        /**
         * Declarando váriavel para armazenar o valor que vai ser retornado quando Usuário tentar acessar
         * SENHA INVALIDA
         * ACESSO COM SUCESSO
         * USUARIO BLOQUEADO
         */
        int retornoVerifica;

        retornoVerifica = usuario1.verificaAcesso("igor123");//1ª TENTATIVA
        retornoVerifica = usuario1.verificaAcesso("igor123");//2ª TENTATIVA
        retornoVerifica = usuario1.verificaAcesso("igor123");//3ª TENTATIVA
        retornoVerifica = usuario1.verificaAcesso("igor123");//4ª TENTATIVA

        if(retornoVerifica == UserSystem.ACESSO_COM_SUCESSO){
            System.out.println("Acesso com sucesso !!! ");
        }else if(retornoVerifica == UserSystem.SENHA_INVALIDA){
            System.out.println("Senha invalida !!!");
        }else{
            System.out.println("Usuário Bloqueado, aguarde até o tempo de desbloqueio !!!");
        }
    }
}
