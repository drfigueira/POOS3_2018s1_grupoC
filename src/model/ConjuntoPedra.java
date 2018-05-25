package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConjuntoPedra {
    private List<Pedra> conjunto;

    public ConjuntoPedra() {
        conjunto = new LinkedList<>();
    }

    public boolean addPedra(Pedra p) {
        return conjunto.add(p);
    }

    public boolean posValida(int pos) {
        return pos >= 0 && pos <= getSize();
    }

    public Pedra removePedra(int pos) {
        return posValida(pos)? conjunto.remove(pos) : null;
    }

    public Pedra getAt(int pos) {
        return posValida(pos) ? conjunto.get(pos) : null;
    }

    public void embaralharPedras() {
        Collections.shuffle(conjunto);
    }

    public int getSize() {
        return conjunto.size();
    }

    public boolean isEmpty() {
        return conjunto.size() == 0;
    }

    public List<Pedra> get() {
        return conjunto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Pedra p: conjunto) {
            sb.append(p.toString());
            sb.append("; ");
        }
        return sb.toString();
    }
}