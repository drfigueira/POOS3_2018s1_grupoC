package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordTest {

    @Test
    void verificarInstanciacaoComParametroVazio() {
        System.out.println("====TESTE PARA SENHA INSTANCIADA COM STRING VAZIA====");
        Assertions.assertTrue(new Password("").verificarSenha("senha"));
    }

//    @Test
//    void verificarInstanciacaoComParametroNulo() {
//        System.out.println("====TESTE PARA SENHA INSTANCIADA COM NULL====");
//        Assertions.assertTrue(new Password(null).verificarSenha("senha"));
//    }

    @Test
    void verificarSenhaComSenhaCorreta() {
        System.out.println("=====TESTE PARA SENHA CORRETA====");
        Assertions.assertTrue(new Password("admin").verificarSenha("admin"));
    }

//    @Test
//    void verificarSenhaComSenhaErrada() {
//        System.out.println("====TESTE PARA SENHA ERRADA====");
//        Assertions.assertFalse(new Password("admin").verificarSenha("aaaaa"));
//    }

    @Test
    void verificarSenhaComSenhaVazia() {
        System.out.println("====TESTE PARA SENHA VAZIA====");
        Assertions.assertFalse(new Password("admin").verificarSenha(""));
    }

    @Test
    void verificarSenhaComSenhaNula() {
        System.out.println("====TESTE PARA SENHA NULA====");
        Assertions.assertFalse(new Password("admin").verificarSenha(null));
    }
}