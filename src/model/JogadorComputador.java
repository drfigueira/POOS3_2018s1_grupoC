package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogadorComputador extends Jogador {
    private int opcaoJogada;

    /**
     * Pensa em uma jogada de acordo com o estado atual da mesa.
     * O método avalia todas as pedras na mão do jogador, avaliando qual a melhor jogada a
     * partir dos seguintes critérios:
     *
     *  - Uma jogada de preferência não deve fechar o bot.
     *  - A melhor jogada é a que deixa a ponta exposta com o maior número de possibilidades para que o
     *  bot efetue uma próxima jogada.
     *
     * @param m A Mesa onde a jogada será efetuada.
     * @return O índice da melhor opção. Retorna -1 caso nenhuma pedra seja jogável.
     */
    private int pensarJogada(Mesa m) {
        int size = hand.getSize();
        int maiorValue = 0, retorno = -1;

        if (possuiJogada(m)) {
            for (int i = 0; i < size; i++) {
                Pedra p = hand.getAt(i);
                int value = 0;
                if (p.pedraValida(m.getPontaEsquerda()) || p.pedraValida(m.getPontaEsquerda())) {
                    value++;
                }
                if (p.isBucha()) {
                    value++;
                }
                value += naoFecha(i);

                if (value > maiorValue) {
                    maiorValue = value;
                    retorno = i;
                }
            }
        }
        return retorno;
    }

    /**
     * Avalia se a pedra do índice em questão não fecha a mão do bot.
     *
     * @param index O índice da pedra que se deseja avaliar.
     * @return Uma pontuação que indica quantas opções de jogada o bot terá caso jogue essa pedra
     * e esta não seja encoberta por outra até seu próximo turno.
     */
    private int naoFecha(int index) {
        int retorno = -1;
        Pedra candidata = hand.getAt(index);

        for(int i = 0; i< hand.getSize(); i++) {
            Pedra p = hand.getAt(i);
            if (candidata.encaixeLeft(p.getLeft()) || candidata.encaixeLeft(p.getRight()) ||
                candidata.encaixeRight(p.getLeft()) || candidata.encaixeRight(p.getRight())) {
                retorno++;
            }
        }
        return retorno;
    }

    /**
     * Avalia qual a melhor jogada a ser efetuada e retorna a Pedra selecionada.
     * @param m A mesa onde a jogada será efetuada.
     * @return A Pedra a ser jogada.
     * @throws NaoTemJogadaException caso não haja pedras aptas.
     */
    public Pedra jogar(Mesa m) throws NaoTemJogadaException {
        int index = this.pensarJogada(m);
        if (index == -1) {
            throw new NaoTemJogadaException();
        }
        return this.jogar(index);
    }
}
