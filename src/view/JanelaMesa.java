package view;

import model.*;
import util.FileHandler;

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
    private UserList listUser;
    private UserSystem user;


    public JanelaMesa(Jogo jogo, UserList listUser, String email) {
        this.jogo = jogo;
        this.listUser = listUser;
        jogo.init();

        user = listUser.getUsuario(email);
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

        panelJogador = new JogadorHumanoPanel();
        setPanelJogador(jogo.getDonoDoTurno());
        panel.adicionarComponente(panelJogador, 1, 0, GridBagConstraints.CENTER,
                1, 1, GridBagConstraints.BOTH);

        add(panel);
    }

    private void setPanelJogador(Jogador jogador){
        panelJogador.setJogador((JogadorHumano) jogador);

        if (!jogo.getDonoDoTurno().possuiJogada(jogo.getMesa())) {
            btnJogar.setEnabled(false);
        }
        if (jogo.getDomino().isEmpty()) {
            btnComprar.setEnabled(false);
        }
        if(!btnComprar.isEnabled() && !btnJogar.isEnabled()) {
            btnPassar.setEnabled(true);
        }
    }

    private void atualizarMesa() {

        labelMesa.setText(jogo.getMesa().toString());
        pack();
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
        //setSize(700, 1000);
        setLocationRelativeTo(null);
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
        btnJogar.setEnabled(panelJogador.possuiJogada(jogo.getMesa()));
        btnComprar.setEnabled(!panelJogador.possuiJogada(jogo.getMesa()));
    }

    private void efetuarJogada() {
        Pedra p = panelJogador.getSelectedIndex();
        if (!jogo.getMesa().jogarPedra(p)) {
            //panelJogador.compraPedra(p);
            JOptionPane.showMessageDialog(this, "Jogada Inválida",
                    "Não é possível jogar a pedra " + p, JOptionPane.ERROR_MESSAGE);
        }
        else {
            panelJogador.atualizarMao();
            passarTurno();
        }
    }

    private void efetuarCompra() {
        panelJogador.compraPedra(jogo.getDomino(), jogo.getMesa());
        btnJogar.setEnabled(panelJogador.possuiJogada(jogo.getMesa()));
    }

    public void passarTurno() {
        if(jogo.getDonoDoTurno().getHand().isEmpty()) {
            JOptionPane.showMessageDialog(this, "VITÓRIA", "Jogador " +
                    ((JogadorHumano) jogo.getDonoDoTurno()).getEmail(), JOptionPane.INFORMATION_MESSAGE);

            //ADICIONANDO PONTO PARA O VENCEDOR
            jogo.getDonoDoTurno().venceu();
            Ranking ranking = Ranking.getInstance();

            //ADICIONANDO PLAYER NO RANKING
            if (!ranking.contains(jogo.getDonoDoTurno())) {
                ranking.addPlayer(jogo.getDonoDoTurno());
            } else {
                ranking.getJogadorByEmail(((JogadorHumano) jogo.getDonoDoTurno()).getEmail()).venceu();
            }

            //FAZENDO JOGO RECEBER USUARIO PERDEDOR
            jogo.passaTurno();
            //ACRESCENTANDO UMA DERROTA
            jogo.getDonoDoTurno().perdeu();

            //ADICIONANDO PLAYER NO RANKING
            if (!ranking.contains(jogo.getDonoDoTurno())) {
                ranking.addPlayer(jogo.getDonoDoTurno());
            } else {
                ranking.getJogadorByEmail(((JogadorHumano) jogo.getDonoDoTurno()).getEmail()).venceu();
            }

            user.setOnline(false);
            FileHandler.getInstance().storeRanking(ranking);
            dispose();
        }
        jogo.passaTurno();
        setPanelJogador(jogo.getDonoDoTurno());
        atualizarMesa();
    }

}
