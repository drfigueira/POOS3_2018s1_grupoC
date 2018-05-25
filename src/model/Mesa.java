package model;

import java.util.LinkedList;
import java.util.List;

public class Mesa {
    private List<Pedra> carreira;
    private int pontaEsquerda;
    private int pontaDireita;

    /**
     * Construtor da classe Mesa, onde as pedras serão jogadas conforme regra do jogo
     */
    public Mesa() {
        carreira = new LinkedList<Pedra>();
    }

    /**
     * Adiciona pedra na ponta esquerda da carreira
     * @param p Pedra
     * @return boolean
     */
    public boolean addPedraLeft(Pedra p) {
        boolean retorno = false;
        if(p != null) {
            if(!carreira.isEmpty()) {
                if(p.getLeft() == getPontaEsquerda()) {
                    p.virarPedra();
                    carreira.add(p);
                    setPontaEsqueda();
                    retorno = true;
                }else if(p.getRight() == getPontaEsquerda()) {
                    carreira.add(p);
                    setPontaEsqueda();
                    retorno = true;
                }
            }else {
                carreira.add(p);
                setPontaEsqueda();
                setPontaDireita();
                retorno = true;
            }
        }
        return retorno;
    }

    /**
     * Adiciona pedra na ponta direita da carreira
     * @param p Pedra
     * @return boolean
     */
    public boolean addPedraRight(Pedra p) {
        boolean retorno = false;
        if(p != null) {
            if(!carreira.isEmpty()) {
                if(p.getLeft() == getPontaDireita()) {
                    carreira.add(p);
                    setPontaDireita();
                    retorno = true;
                }else if(p.getRight() == getPontaDireita()) {
                    p.virarPedra();
                    carreira.add(p);
                    retorno = true;
                }else {
                    carreira.add(p);
                    setPontaEsqueda();
                    setPontaDireita();
                    retorno = true;
                }
            }
        }
        return retorno;
    }

    /**
     * Define valor inteiro para ponta esquerda com relação a pedra da ponta esquerda
     */
    private void setPontaEsqueda() {
        pontaEsquerda = carreira.get(0).getLeft();
    }

    /**
     * Define valor inteiro para ponta direita com relação a pedra da ponta direita
     */
    private void setPontaDireita() {
        this.pontaDireita = carreira.get(carreira.size()-1).getRight();
    }

    /**
     * Retorna valor inteiro da ponta esquerda da carreira
     * @return inteiro
     */
    public int getPontaEsquerda() {
        return pontaEsquerda;
    }

    /**
     * Retorna valor inteiro da ponta direita da carreira
     * @return inteiro
     */
    public int getPontaDireita() {
        return pontaDireita;
    }

    public boolean jogarPedra(Pedra p) {
        boolean retorno = false;
        retorno = addPedraLeft(p);
        if (!retorno) {
            retorno = addPedraRight(p);
        }
        return retorno;
    }
}