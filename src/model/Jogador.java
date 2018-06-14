package model;

public abstract class Jogador {
    protected ConjuntoPedra hand;
    private int score = 0;

    public Jogador() {
        hand = new ConjuntoPedra();
    }

    public ConjuntoPedra getHand() { return this.hand; }

    public void setHand(Domino d, int limite) {
        Pedra p = null;
        int i = 0;
        if(d != null) {
            while(i < limite && !d.isEmpty()) {
                p = d.removePedra(0);
                if(p != null) {
                    hand.addPedra(p);
                }
                i++;
            }
        }
    }

    public boolean possuiJogada(Mesa m) {
        boolean retorno = false;
        int i = 0;
        while(!retorno && i < hand.getSize()) {
            retorno = hand.getAt(i).pedraValida(m.getPontaEsquerda());
            if(!retorno) {
                retorno = hand.getAt(i).pedraValida(m.getPontaDireita());
            }
            i++;
        }
        return retorno;
    }

    public boolean comprarPedra(Domino domino, Mesa mesa) {
        boolean retorno = false;
        if(domino != null) {
            while(!retorno && !domino.isEmpty()) {
                hand.addPedra(domino.removePedra(0));
                retorno = possuiJogada(mesa);
            }
        }
        return retorno;
    }

    public Pedra maiorBucha() {
        Pedra bucha = null;
        int soma = 0;
        for(Pedra p: hand.get()) {
            if(p.getLeft() == p.getRight()) {
                if(soma < p.getLeft() + p.getRight()) {
                    bucha = p;
                    soma = p.getLeft() + p.getRight();
                }
            }
        }
        return bucha;
    }

    public Pedra maiorPedra() {
        Pedra pedra = null;
        int soma = 0;
        for(Pedra p: hand.get()) {
            if(soma < p.getLeft() + p.getRight()) {
                pedra = p;
                soma = p.getLeft() + p.getRight();
            }
        }
        return pedra;
    }

    /**
     * Deve retornar o score atual do JogadorComputador. O score será dado pela diferença entre
     * o número de vitórias e derrotas do Jogador.
     * @return Um inteiro contendo o score do Jogador.
     */
    public int getScore() {
        return score;
    }

    public void venceu() { score++; }

    public void perdeu() {
        score--;
    }

    public void setScore(int score) { this.score = score; }

    /*
    public void setContJogoVenceu(int contJogoVenceu) {
        this.contJogoVenceu = contJogoVenceu;
    }

    public void setContJogoPerdeu(int contJogoPerdeu){
        this.contJogoPerdeu = contJogoPerdeu;
    }*/

    /**
     * Método que efetua uma jogada a partir do índice da pedra passado como parâmetro.
     * @param indexPedra O índice da pedra desejada.
     * @return A Pedra desejada.
     * @throws IndexOutOfBoundsException
     */
    public Pedra jogar(int indexPedra) throws  IndexOutOfBoundsException {
        if (indexPedra < 0 || indexPedra >= hand.getSize()) {
            throw new IndexOutOfBoundsException("Pedra número " + indexPedra + " não encontrada");
        }
        return hand.getAt(indexPedra);
    }

}



