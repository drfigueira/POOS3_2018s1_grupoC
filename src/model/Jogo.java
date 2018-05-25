package model;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private Mesa mesa;
    private Domino domino;
    private int turno;
    private Jogador donoDoTurno;

    public Jogo(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        mesa = new Mesa();
        domino= Domino.getInstance();
        turno = 0;
    }

    public void init() {
        jogador1.setHand(domino, 7);
        jogador2.setHand(domino, 7);

        Pedra p1, p2;

        p1 = jogador1.maiorBucha();
        if (p1 == null) {
            p1 = jogador1.maiorPedra();
        }

        p2 = jogador2.maiorBucha();
        if (p2 == null) {
            p2 = jogador2.maiorPedra();
        }

        donoDoTurno = ((p1.getLeft() + p1.getRight()) > (p2.getLeft() + p2.getRight())) ? jogador1 : jogador2;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Domino getDomino() {
        return domino;
    }

    public int getTurno() {
        return turno;
    }

    public Jogador getDonoDoTurno() {
        return donoDoTurno;
    }

    public void passaTurno() {
        turno++;
        donoDoTurno = (donoDoTurno == jogador1) ? jogador2 : jogador1;
    }
}
