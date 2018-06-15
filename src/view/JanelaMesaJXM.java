package view;

import model.*;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaMesaJXM extends JFrame implements ActionListener {
    private AbstractPanel panel;
    private AbstractPanel panelBotoes;
    private AbstractPanel mesa;

    private JogadorHumanoPanel panelJogador;

    private JLabel labelMesa;

    private JButton btnJogar;
    private JButton btnPassar;

    private JogoJXM jogo;

    public JanelaMesaJXM(JogoJXM jogo) {
        this.jogo = jogo;
        this.jogo.init();

        createVisualComponents();
        setProperties();
    }

    private void createVisualComponents() {
        panel = new AbstractPanel();

        createPanelMesa();
        panel.adicionarComponente(mesa, 0, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);

        createPanelBotoes();
        panel.adicionarComponente(panelBotoes, 2, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);

        panelJogador = new JogadorHumanoPanel();
        setPanelJogador(jogo.getJogador());
        panel.adicionarComponente(panelJogador, 1, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);

        add(panel);
    }

    private void createPanelBotoes() {
        panelBotoes = new AbstractPanel();

        btnJogar =  new JButton("Jogar");
        btnJogar.addActionListener(this);

        btnPassar = new JButton("Passar");
        btnPassar.addActionListener(this);


        panelBotoes.adicionarComponente(btnJogar, 0, 0 , GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);
        panelBotoes.adicionarComponente(btnPassar, 0, 2, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);
        btnPassar.setEnabled(false);
    }

    private void setPanelJogador(Jogador jogador) {
        panelJogador.setJogador((JogadorHumano) jogador);
        atualizarBotoes();
    }

    private void atualizarBotoes() {
        if (!jogo.getJogador().possuiJogada(jogo.getMesa())) {
            btnJogar.setEnabled(false);
            btnPassar.setEnabled(true);
        }
        else {
            btnJogar.setEnabled(true);
            btnPassar.setEnabled(false);
        }
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
        if (e.getSource() == btnJogar) {
            efetuarJogada();
            atualizarMesa();
            passarTurno();
        }
        else if (e.getSource() == btnPassar){
            passarTurno();
        }
    }

    private void efetuarJogada() {
        Pedra p = panelJogador.getSelectedIndex();
        if (!jogo.getMesa().jogarPedra(p)) {
            JOptionPane.showMessageDialog(this, "Jogada Inválida",
                    "Não é possível jogar a pedra " + p, JOptionPane.ERROR_MESSAGE);
        }
        else {
            panelJogador.atualizarMao();
            checarCondicaoDeVitoria(panelJogador.getJogador());
        }
    }

    private void passarTurno() {
        jogo.passaTurno();
        do {
            try {
                Pedra p = jogo.jogarMaquina();
                jogo.getMesa().jogarPedra(p);
                atualizarMesa();
                JOptionPane.showMessageDialog(this, "Joguei a pedra " + p + "\nEstou com " +
                        jogo.getDonoDoTurno().getHand().getSize() + " pedras.",
                        "Bot " + (jogo.getTurno() + 1), JOptionPane.INFORMATION_MESSAGE);
            } catch (NaoTemJogadaException e) {
                JOptionPane.showMessageDialog(this, "Não tenho jogada a fazer :(\nEstou com " +
                        jogo.getDonoDoTurno().getHand().getSize() + " pedras.",
                        "Bot " + (jogo.getTurno()+1) + "diz: ", JOptionPane.INFORMATION_MESSAGE);
            }
            jogo.passaTurno();
            checarCondicaoDeVitoria(jogo.getDonoDoTurno());
        } while(jogo.getTurno() != -1);
        atualizarBotoes();
    }

    private void atualizarMesa() {
        labelMesa.setText(jogo.getMesa().toString());
    }

    private void checarCondicaoDeVitoria(Jogador j) {
        if (j.getHand().getSize() == 0) {
            String message, title;
            if (j instanceof JogadorComputador) {
                message = "Bot " + (jogo.getTurno() + 1) + " venceu!!!";
                title = "GAME OVER";
                panelJogador.getJogador().perdeu();
            }
            else {
                message = "PARABÉNS! VOCÊ VENCEU!!!";
                title = "VITÓRIA";
                panelJogador.getJogador().venceu();
            }
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);

            Ranking ranking = Ranking.getInstance();
            if (!ranking.contains(panelJogador.getJogador())) {
                ranking.addPlayer(panelJogador.getJogador());
            } else {
                ranking.getJogadorByEmail(panelJogador.getJogador().getEmail()).venceu();
            }

            FileHandler.getInstance().storeRanking(ranking);
            dispose();
        }
    }
}
