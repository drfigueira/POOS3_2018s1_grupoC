package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void getUsuario() {

    }

    @Test
    void loginUsuario() {
    }

    @Test
    void getAt() {
    }
}