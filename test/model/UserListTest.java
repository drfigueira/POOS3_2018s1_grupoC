package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserListTest {
    private UserList list;

    @BeforeAll
    void setUp() {
        list = new UserList();
        list.addUsuario(new UserSystem("primeiro@usuario.com", "senha1"));
        list.addUsuario(new UserSystem("segundo@usuario.com", "senha2"));
        list.addUsuario(new UserSystem("terceiro@usuario.com", "senha3"));
    }

    @Test
    void addUsuarioValido() {
        UserSystem u = new UserSystem("novo@usuario.com", "senha");
        Assertions.assertTrue(list.addUsuario(u));
    }

    @Test
    void addUsuarioNulo() {
        Assertions.assertFalse(list.addUsuario(null));
    }

    @Test
    void getUsuarioComEmailValido() {
        UserSystem u = new UserSystem("primeiro@usuario.com", "");
        Assertions.assertEquals(u, list.getUsuario("primeiro@usuario.com"));
    }

    @Test
    void getUsuarioComEmailInvalido() {
        Assertions.assertNull(list.getUsuario(""));
    }

    @Test
    void getAtComIndexValido() {
        UserSystem u = new UserSystem("terceiro@usuario.com", "");
        Assertions.assertEquals(u, list.getAt(2));
    }

    @Test
    void getAtComIndexInvalido() {
        Assertions.assertNull(list.getAt(10));
    }

    @Test
    void loginUsuarioValidoCorretamente() {
        //Assertions.assertTrue(list.logonUsuario("segundo@usuario.com", "senha2"));
    }

    @Test
    void loginUsuarioValidoIncorretamente3VezesEDepoisLogarCorretamente() {
        //Assertions.assertFalse(list.logonUsuario("segundo@usuario.com", "senha"));
        //Assertions.assertFalse(list.logonUsuario("segundo@usuario.com", "senha"));
        //Assertions.assertFalse(list.logonUsuario("segundo@usuario.com", "senha"));
        //Assertions.assertFalse(list.logonUsuario("segundo@usuario.com", "senha2"));
    }

}