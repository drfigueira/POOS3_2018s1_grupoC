package model;

public class Ranking {
    public static int LIMITE = 10;

    private Jogador[] ranking;
    private int qtdJogadores;
    private static Ranking instance;

    /**
     * Contrutor de um array de Jogador, com limite de Jogador estipulado em 10
     */
    private Ranking() {
        ranking = new Jogador[LIMITE];
        this.qtdJogadores = 0;
    }

    public static Ranking getInstance(){
        if(instance == null){
            instance = new Ranking();
        }
        return instance;
    }

    /**
     * Adiciona um Jogador no Ranking, desde que o array não esteja cheio e o Jogador seja diferente de null.
     * @param player
     * @return false, caso esteja cheio ou Jogador passado por parametro seja null. True se Jogador foi adicionado com sucesso.
     */
    public boolean addPlayer(Jogador player) {
        boolean deuCerto = false;
        if(!isFull() && player != null) {
            ranking[this.qtdJogadores] = player;
            this.qtdJogadores++;
            this.sort();
            deuCerto = true;
        }
        return deuCerto;
    }

    /**
     * Retorna o top5 de Jogador do ranking, caso tenha menos de cinco Jogadores, retorna a quantidade de Jogadores.
     * @return Jogador[]
     */
    public Jogador[] getTop5() {
        Jogador[] player = null;
        int top = this.qtdJogadores > 5? 5 : this.qtdJogadores;
        if(!isEmpty()) {
            player = new Jogador[top];
            for(int i = 0; i < top; i++) {
                player[i] = ranking[i];
            }
        }
        return player;
    }

    /**
     * Retorna o ranking de Jogador caso não esteja vazio.
     * @return Jogador[]
     */
    public Jogador[] getRanking() {
        Jogador[] p = null;
        if(!isEmpty()) {
            p = new Jogador[this.qtdJogadores];
            for(int i = 0; i < this.qtdJogadores; i++) {
                p[i] = this.ranking[i];
            }
        }
        return p;
    }

    /**
     * Ordena o ranking de Jogador em forma decrescente se não estiver vazio.
     */
    private void sort() {
        if(!isEmpty()) {
            for (int i = 0; i < this.qtdJogadores; i++) {
                int max = i;
                for (int j = (i + 1); j < this.qtdJogadores; j++) {
                    if (this.ranking[j].getScore() > this.ranking[max].getScore()) {
                        max = j;
                    }
                }
                if (this.ranking[i].getScore() != this.ranking[max].getScore()) {
                    Jogador p = this.ranking[i];
                    this.ranking[i] = this.ranking[max];
                    this.ranking[max] = p;
                }
            }
        }
    }

    /**
     * Informa se o ranking está vazio.
     * @return false vazio, true não vazio.
     */
    public boolean isEmpty() {
        return this.qtdJogadores == 0;
    }

    /**
     * Informa se o ranking está cheio.
     * @return false não cheio. true cheio.
     */
    public boolean isFull() {
        return this.qtdJogadores == LIMITE;
    }

    public int getTamanhoArray(){
        int i = 0;
        while(ranking[i] != null){
            i++;
        }
        return i;
    }

    public Jogador getJogadorAtId(int pos){
        return ranking[pos];
    }

}
