package model;

public class JogadorHumano extends User implements Jogador {
    private int contJogoVenceu;
    private int contJogoPerdeu;

    /**
     * Construtor base de User, recebe o email e a senha do usuario.
     * E inicializa as váriaveis que armazena a quantidade de vitórias e derrotas
     *
     * @param email Uma String contendo o email do usuario.
     * @param senha Uma String que ser&aacute; utilizada para construir a senha do usuario.
     */
    public JogadorHumano(String email, String senha) {
        super(email, senha);
        this.contJogoVenceu = 0;
        this.contJogoPerdeu = 0;
    }

    /**
     * Deve retornar o score atual do JogadorHumano. O score será dado pela diferença entre
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
