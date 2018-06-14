package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaMesaJXM extends JFrame implements ActionListener {

    private AbstractPanel panel;

//    private JogadorComputadorPanel[] bot;

    private JogadorHumanoPanel panelJogador;
    private AbstractPanel panelBotoes;
    private AbstractPanel mesa;

    private JLabel labelMesa;
    private JButton btnJogar;
    private JButton btnPassar;

    private JogoJXM jogo;

//    private Jogador donoDoTurno;


    public JanelaMesaJXM(JogoJXM jogo) {
        this.jogo = jogo;

        jogo.init();

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

    private void setPanelJogador(Jogador jogador){
        panelJogador.setJogador((JogadorHumano) jogador);
        if (!jogo.getJogador().possuiJogada(jogo.getMesa())) {
            btnJogar.setEnabled(false);
            btnPassar.setEnabled(true);
        }else {
            btnJogar.setEnabled(true);
            btnPassar.setEnabled(false);
        }
    }

    private void atualizarMesa() {
        labelMesa.setText(jogo.getMesa().toString());
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
            try {
                efetuarJogada();
            } catch (NaoTemJogadaException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnPassar) {
            passarTurno();
            try {
                jogo.jogarMaquina();
            } catch (NaoTemJogadaException e1) {
                e1.printStackTrace();
            }
        } else {
            dispose();
        }
        btnJogar.setEnabled(panelJogador.possuiJogada(jogo.getMesa()));
        btnPassar.setEnabled(!panelJogador.possuiJogada(jogo.getMesa()) && jogo.getDomino().isEmpty());
    }

    private void efetuarJogada() throws NaoTemJogadaException {

        Pedra p = panelJogador.getSelectedIndex();
        if (!jogo.getMesa().jogarPedra(p)) {
            JOptionPane.showMessageDialog(this, "Jogada Inválida",
                    "Não é possível jogar a pedra " + p, JOptionPane.ERROR_MESSAGE);
        }
        else {
            panelJogador.atualizarMao();
            passarTurno();
            jogo.jogarMaquina();
        }
    }

    public void passarTurno() {
        if(jogo.getDonoDoTurno().getHand().isEmpty()) {
            JOptionPane.showMessageDialog(this, "VITÓRIA", "Jogador " +
                    ((JogadorHumano) jogo.getJogador()).getEmail(), JOptionPane.INFORMATION_MESSAGE);

            //ADICIONANDO PONTO PARA O VENCEDOR
            jogo.getDonoDoTurno().venceu();
            Ranking ranking = Ranking.getInstance();

            //ADICIONANDO PLAYER NO RANKING
            ranking.addPlayer(jogo.getDonoDoTurno());

            //FAZENDO JOGO RECEBER USUARIO PERDEDOR
            jogo.passaTurno();
            //ACRESCENTANDO UMA DERROTA
            jogo.getDonoDoTurno().perdeu();

            //ADICIONANDO PLAYER NO RANKING
            ranking.addPlayer(jogo.getDonoDoTurno());

            dispose();
        }
        jogo.passaTurno();
//        setPanelJogador(jogo.getDonoDoTurno());
        atualizarMesa();
    }

}
