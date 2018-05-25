package view;

import model.JogadorHumano;
import model.Jogo;
import model.UserSystem;

public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo(new JogadorHumano(new UserSystem("user1@teste.com", "123")),
                new JogadorHumano(new UserSystem("user2@teste.com", "123")));
        new JanelaMesa(jogo);
    }
}
