package model;

import java.util.LinkedList;
import java.util.List;

public class Mesa {
    private List<Pedra> carreira;
    private int pontaEsquerda;
    private int pontaDireita;

    public Mesa() {
        carreira = new LinkedList<Pedra>();
    }

    public boolean addPedra(Pedra p) {
        boolean retorno = false;
        if(p != null) {
            if(carreira.size() != 0) {
                if(p.getLeft() == getPontaEsquerda()) {
                    carreira.add(p);
                    setPontaEsqueda(p.getRight());
                    retorno = true;
                }else if(p.getRight() == getPontaEsquerda()) {
                    carreira.add(p);
                    setPontaEsqueda(p.getLeft());
                    retorno = true;
                }else if(p.getLeft() == getPontaDireita()) {
                    carreira.add(carreira.size(), p);
                    setPontaDireita(p.getRight());
                    retorno = true;
                }else if(p.getRight() == getPontaDireita()) {
                    carreira.add(carreira.size(), p);
                    setPontaDireita(p.getLeft());
                    retorno = true;
                }
            }else {
                carreira.add(p);
                setPontaEsqueda(p.getLeft());
                setPontaDireita(p.getRight());
                retorno = true;
            }
        }
        return retorno;
    }

    private void setPontaEsqueda(int n) {
        this.pontaEsquerda = n;
    }

    private void setPontaDireita(int n) {
        this.pontaDireita = n;
    }

    public int getPontaEsquerda() {
        return pontaEsquerda;
    }

    public int getPontaDireita() {
        return pontaDireita;
    }
}