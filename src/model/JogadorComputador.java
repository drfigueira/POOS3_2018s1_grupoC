package model;

public class JogadorComputador implements  Jogador {
    private int contJogoVenceu;
    private int contJogoPerdeu;

    /**
     * Contrutor sem parametros que apenas inicializa as variaveis que armazena quantidade de vitória e derrota
     */
    public JogadorComputador(){
        this.contJogoVenceu = 0;
        this.contJogoPerdeu = 0;
    }

    /**
     * Deve retornar o score atual do JogadorComputador. O score será dado pela diferença entre
     * o número de vitórias e derrotas do Jogador.
     * @return Um inteiro contendo o score do Jogador.
     */
    @Override
    public int getScore() {
        return contJogoVenceu - contJogoPerdeu;
    }

    @Override
    public void venceu() {

    }

    @Override
    public void perdeu() {

    }
}
