package model;

public class NaoTemJogadaException extends Exception {
    public NaoTemJogadaException() {
        super("Não há nenhuma jogada a ser feita. O bot deve comprar pedras ou desistir.");
    }
}
