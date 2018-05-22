package model;

public class Pedra {
    private int left;
    private int right;

    /**
     * Construtor de uma pedra do jogo dominó, que recebe dois inteiros para left e right.
     * @param left
     * @param right
     */
    public Pedra(int left, int right) {
        setLeft(left);
        setRight(right);
    }

    /**
     * Define valor inteiro para o lado esquerdo da pedra.
     * @param left
     */
    private void setLeft(int left) {
        this.left = left;
    }

    /**
     * Define valor inteiro para o lado direito da pedra.
     * @param right
     */
    private void setRight(int right) {
        this.right = right;
    }

    /**
     * Retorna valor inteiro do lado esquerdo da pedra.
     * @return int left
     */
    public int getLeft() {
        return left;
    }

    /**
     * Retorna valor inteiro do lado direito da pedra.
     * @return int right
     */
    public int getRight() {
        return right;
    }

    /**
     * Verifica encaixe da pedra, do lado esquerdo.
     * @param n
     * @return
     */
    public boolean encaixeLeft(int n) {
        boolean retorno = false;
        if(left == n) {
            retorno = true;
        }
        return retorno;
    }

    /**
     * Verifica encaixe da pedra, do lado direito.
     * @param n
     * @return
     */
    public boolean encaixeRight(int n) {
        boolean retorno = false;
        if(right == n) {
            retorno = true;
        }
        return retorno;
    }

    /**
     * Vira a pedra, substituindo os valores entre left e right.
     */
    public void virarPedra() {
        int aux = left;
        left = right;
        right = aux;
    }

    @Override
    public String toString() {
        return "|" + left + ":" + right + "|";
    }

}