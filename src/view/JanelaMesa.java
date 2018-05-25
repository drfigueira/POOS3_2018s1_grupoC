package view;

import model.Jogador;
import model.JogadorHumano;
import model.Jogo;
import model.Pedra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaMesa extends JFrame implements ActionListener {

    private AbstractPanel panel;

//    private JogadorHumanoPanel jogador1;
//    private JogadorHumanoPanel jogador2;
    private JogadorHumanoPanel panelJogador;
    private AbstractPanel panelBotoes;
    private AbstractPanel mesa;

    private JLabel labelMesa;
    private JButton btnJogar;
    private JButton btnComprar;
    private JButton btnPassar;

    private Jogo jogo;


    public JanelaMesa(Jogo jogo) {
        this.jogo = jogo;
        jogo.init();

        createVisualComponents();
        setProperties();
    }

    private void createVisualComponents() {
        panel = new AbstractPanel();
//        this.jogador1 = new JogadorHumanoPanel((JogadorHumano) jogo.getJogador1());
//        this.jogador2 = new JogadorHumanoPanel((JogadorHumano) jogo.getJogador2());

        createPanelMesa();
        panel.adicionarComponente(mesa, 0, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);

        createPanelBotoes();
        panel.adicionarComponente(panelBotoes, 2, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);

        setPanelJogador(jogo.getDonoDoTurno());
//        panel.adicionarComponente(panelJogador, 1, 0, GridBagConstraints.CENTER,
//                1, 1, GridBagConstraints.BOTH);

        add(panel);
    }

    private void setPanelJogador(Jogador jogador){
        panelJogador = new JogadorHumanoPanel((JogadorHumano) ((jogador == jogo.getJogador1()) ?
                         jogo.getJogador1() : jogo.getJogador2()));
        if (!jogo.getDonoDoTurno().possuiJogada(jogo.getMesa())) {
            btnComprar.setEnabled(false);
        }
        if (jogo.getDomino().isEmpty()) {
            btnComprar.setEnabled(false);
        }
        if(!btnComprar.isEnabled() && !btnJogar.isEnabled()) {
            btnPassar.setEnabled(true);
        }
        panel.adicionarComponente(panelJogador, 1, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);
    }

    private void atualizarMesa() {
        labelMesa.setText(jogo.getMesa().toString());
    }
    private void createPanelBotoes() {
        panelBotoes = new AbstractPanel();
        btnComprar = new JButton("Comprar");
        btnComprar.addActionListener(this);

        btnJogar =  new JButton("Jogar");
        btnJogar.addActionListener(this);

        btnPassar = new JButton("Passar");
        btnPassar.addActionListener(this);


        panelBotoes.adicionarComponente(btnJogar, 0, 0 , GridBagConstraints.CENTER,
                                                1, 1, GridBagConstraints.BOTH);
        panelBotoes.adicionarComponente(btnComprar, 0, 1, GridBagConstraints.CENTER,
                                                1, 1, GridBagConstraints.BOTH);
        panelBotoes.adicionarComponente(btnPassar, 0, 2, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);
        btnPassar.setEnabled(false);
    }

    private void createPanelMesa() {
        mesa = new AbstractPanel();
        labelMesa = new JLabel("Mesa");
        mesa.adicionarComponente(labelMesa, 0, 0, GridBagConstraints.CENTER, 2, 2, GridBagConstraints.BOTH);
        mesa.setBorder(BorderFactory.createEtchedBorder());
    }

    private void setProperties() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnJogar) {
            efetuarJogada();
        } else if (e.getSource() == btnComprar) {
            efetuarCompra();
        } else if (e.getSource() == btnPassar) {
            passarTurno();
        } else {
            dispose();
        }
    }

    private void efetuarJogada() {
        Pedra p = panelJogador.getSelectedIndex();
        if (!jogo.getMesa().jogarPedra(p)) {
            panelJogador.compraPedra(p);
            JOptionPane.showMessageDialog(this, "Jogada Inválida",
                    "Não é possível jogar a pedra " + p, JOptionPane.ERROR_MESSAGE);
        }
        else {
            passarTurno();
        }
    }

    private void efetuarCompra() {
        panelJogador.compraPedra(jogo.getDomino(), jogo.getMesa());
    }

    public void passarTurno() {
        jogo.passaTurno();
        setPanelJogador(jogo.getDonoDoTurno());
        atualizarMesa();
    }
}
