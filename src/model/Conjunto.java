package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Conjunto {
    private static int LIMITE = 7;
    private List<Pedra> conjunto;

    public Conjunto() {
        conjunto = new LinkedList<>();
        criaPedras();
        embaralharPedras();
    }

    private void criaPedras() {
        for(int i = 0; i < LIMITE; i++) {
            for(int j = i; j < LIMITE; j++) {
                conjunto.add(new Pedra(i, j));
            }
        }
    }

    public void embaralharPedras() {
        Collections.shuffle(conjunto);
    }

    public Pedra getPedra() {
        Pedra p = null;
        if(!conjunto.isEmpty()) {
            p = conjunto.get(0);
            conjunto.remove(0);
        }
        return p;
    }

    public int getSize() {
        return conjunto.size();
    }

    public boolean isEmpty() {
        return conjunto.size() == 0;
    }

}