package model;

public class JogoJXM {
    private JogadorHumano jogador;
    private JogadorComputador[] bot;

    private Mesa mesa;
    private Domino domino;
    private int turno;
    private Jogador donoDoTurno;


    public JogoJXM(JogadorHumano jogador) {
        bot = new JogadorComputador[3];
        this.jogador = jogador;
        domino= Domino.getInstance();
        mesa = new Mesa();
        carregarMaquinas();

        turno = -1;
    }

    public void carregarMaquinas() {
        for(int i = 0; i < 3; i++) {
            bot[i] = new JogadorComputador();
            bot[i].setHand(domino, 7);
        }
    }

    public void init() {
        jogador.setHand(domino, 7);
        Pedra bucha;
        Pedra p = null;
        int pos = 0;
        bucha = jogador.maiorBucha();

        for(int i = 0; i < 3; i++) {
            p = bot[i].maiorBucha();
            if(bucha != null) {
                if(p != null) {
                    if((bucha.getLeft() + bucha.getRight()) < (p.getLeft() + p.getRight())) {
                        bucha = p;
                        pos = i;
                    }
                }
            }else {
                bucha = bot[i].maiorBucha();
            }
        }

        donoDoTurno = pos != -1? bot[pos] : jogador;
        turno = pos;
    }

    public Jogador getJogador() {
        return this.jogador;
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

    public Pedra jogarMaquina() throws NaoTemJogadaException {
        Pedra p = null;
        if(!bot[turno].possuiJogada(mesa)) {
            passaTurno();
        }else {
            p = bot[turno].jogar(mesa);
        }
        return p;
    }

    public void passaTurno() {
        turno++;
        if(turno >= 0 && turno < 3) {
            donoDoTurno = bot[turno];
        }else {
            donoDoTurno = jogador;
            turno = -1;
        }
    }
}
