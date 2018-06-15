package model;

public class Domino extends ConjuntoPedra {
    private static int LIMITE = 7;
    private static Domino instance;

    public Domino() {
        criaPedras();
        embaralharPedras();
    }

    public static Domino getInstance() {
        if(instance == null) {
            instance = new Domino();
        }
        return instance;
    }

    private void criaPedras() {
        for(int i = 0; i < LIMITE; i++) {
            for(int j = i; j < LIMITE; j++) {
                addPedra(new Pedra(i, j));
            }
        }
    }

}