package view;

import model.*;

public class Main {
    public static void main(String[] args) {
        JanelaPrincipal jP = new JanelaPrincipal();

        //JanelaHome jHome = new JanelaHome(new UserList(), "igor@gmail.com");
        //jHome.setVisible(true);
        /*
        //TESTE JOGADOR X JOGADOR
        Jogo jogo = new Jogo(new JogadorHumano(new UserSystem("user1@teste.com", "123")),
                new JogadorHumano(new UserSystem("user2@teste.com", "123")));
        new JanelaMesa(jogo);*/

/*

        //TESTE RANKING
        Jogador j1, j2, j3, j4;

        UserSystem u1, u2, u3, u4;
        u1 = new UserSystem("igor@gmail.com", "123");
        u2 = new UserSystem("Gabriel@gmail.com", "123");
        u3 = new UserSystem("Saturnino@gmail.com", "123");
        u4 = new UserSystem("Antonio@gmail.com", "123");

        j1 = new JogadorHumano(u1);
        j2 = new JogadorHumano(u2);
        j3 = new JogadorHumano(u3);
        j4 = new JogadorHumano(u4);

        j1.setContJogoVenceu(2);
        j1.setContJogoPerdeu(4);

        j2.setContJogoVenceu(8);
        j2.setContJogoPerdeu(3);

        j3.setContJogoVenceu(9);
        j3.setContJogoPerdeu(1);

        j4.setContJogoVenceu(4);
        j4.setContJogoPerdeu(7);


        Ranking ranking = Ranking.getInstance();
        ranking.addPlayer(j1);
        ranking.addPlayer(j2);
        ranking.addPlayer(j3);
        ranking.addPlayer(j4);


        JanelaRanking janelaRanking = new JanelaRanking();
        janelaRanking.setVisible(true);*/

    }
}
