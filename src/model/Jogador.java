package model;

/**
 * Interface que dita o comportamento de um Jogador de dominó do sistema.
 */
public interface Jogador {

    /**
     * Deve retornar o score atual do Jogador. O score será dado pela diferença entre
     * o número de vitórias e derrotas do Jogador.
     * @return Um inteiro contendo o score do Jogador.
     */
    public int getScore();

    /**
     * Dá uma vitória ao Jogador.
     */
    public void venceu();

    /**
     * Registra uma derrota do Jogador.
     */
    public void perdeu();
}

