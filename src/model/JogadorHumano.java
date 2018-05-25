package model;

public class JogadorHumano extends Jogador {
    private User user;

    /**
     * Construtor base de User, recebe o email e a senha do usuario.
     * E inicializa as váriaveis que armazena a quantidade de vitórias e derrotas
     *
     * @param user O User referente a este jogador.
     */
    public JogadorHumano(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User: ");
        sb.append(user);
        sb.append(" - Score: ");
        sb.append(super.getScore());
        return sb.toString();
    }
}
