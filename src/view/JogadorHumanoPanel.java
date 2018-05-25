package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogadorHumanoPanel extends AbstractPanel {
    private JComboBox<Pedra> mao;
    private JogadorHumano jogador;
    private JLabel labelNome;

    public JogadorHumanoPanel() {
        super();
        labelNome = new JLabel();
        mao = new JComboBox<>();

        adicionarComponente(labelNome, 0, 0, GridBagConstraints.NORTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(mao, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
    }

    private void addComboBoxItems() {
        ConjuntoPedra hand = jogador.getHand();
        for(int i = 0; i < hand.getSize(); i++) {
            this.mao.addItem(hand.getAt(i));
        }
    }

    public JogadorHumano getJogador() {
        return jogador;
    }

    public void setJogador(JogadorHumano jogador) {
        this.jogador = jogador;
        mao.removeAllItems();
        addComboBoxItems();
        labelNome.setText(jogador.toString());
    }

    public Pedra getSelectedIndex() {
        Pedra p = mao.getItemAt(mao.getSelectedIndex());
        return p;
    }

    public void compraPedra(Pedra p) {
        jogador.getHand().addPedra(p);
        mao.removeAllItems();
        addComboBoxItems();
    }

    public void compraPedra(Domino d, Mesa m) {
        jogador.comprarPedra(d, m);
        mao.removeAllItems();
        addComboBoxItems();
    }

    public void atualizarMao() {
        jogador.getHand().removePedra(mao.getSelectedIndex());
    }

}
