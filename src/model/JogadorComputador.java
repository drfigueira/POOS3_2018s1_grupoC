package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogadorComputador extends Jogador {
    private int opcaoJogada;

    public int pensarJogada(Mesa m) {
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
}
