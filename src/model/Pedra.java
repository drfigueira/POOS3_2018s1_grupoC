package model;

public class Pedra {
    private int left;
    private int right;

    public Pedra(int left, int right) {
        setLeft(left);
        setRight(right);
    }

    private void setLeft(int left) {
        this.left = left;
    }

    private void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

}