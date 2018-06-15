package view;

import model.JogadorHumano;
import model.Jogo;
import model.UserList;
import model.UserSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaHome extends JFrame implements ActionListener {
    JDesktopPane desktop;

    private JPanel painelLabel;
    private JPanel painelButtonJogarVSJogador;
    private JPanel painelButtonJogarVSMaquina;
    private JPanel painelButtonRanking;
    private JPanel painelButtonSair;

    private JButton buttonJogadorVsJogador;
    private JButton buttonJogadorVsMaquina;
    private JButton buttonRanking;
    private JButton buttonSair;

    private JLabel labelHome;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    JanelaRanking janelaRanking;
    JanelaLoginDois janelaLoginDois;


    private String email;
    private UserList listUsuario;

    public JanelaHome(UserList listUsuario, String email){
        super("Home");
        this.listUsuario = listUsuario;
        this.email = email;
        criaComponentes();
        ajustaComponentes();
    }

    private void criaComponentes(){
        desktop = new JDesktopPane();

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);

        Border border;
        border = BorderFactory.createEtchedBorder();

        painelLabel = new JPanel(layout);
        painelLabel.setBorder(border);

        painelButtonJogarVSJogador = new JPanel(layout);
        painelButtonJogarVSJogador.setBorder(border);

        painelButtonJogarVSMaquina = new JPanel(layout);
        painelButtonJogarVSMaquina.setBorder(border);

        painelButtonRanking = new JPanel(layout);
        painelButtonRanking.setBorder(border);

        painelButtonSair = new JPanel(layout);
        painelButtonSair.setBorder(border);

        labelHome = new JLabel("#### Menu ####");

        buttonJogadorVsJogador = new JButton("Jogar Vs Jogador");
        buttonJogadorVsJogador.addActionListener(this);

        buttonJogadorVsMaquina = new JButton("Jogar Vs Máquina");
        buttonJogadorVsMaquina.addActionListener(this);

        buttonRanking = new JButton("Ranking");
        buttonRanking.addActionListener(this);

        buttonSair = new JButton("Sair");
        buttonSair.addActionListener(this);

        add(desktop);
        inserirComponentesNaJanela();
    }

    private void inserirComponentesNaJanela(){
        adicionaComponentes(painelLabel, labelHome, 0, 2, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelButtonJogarVSJogador, buttonJogadorVsJogador, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelButtonJogarVSMaquina, buttonJogadorVsMaquina, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelButtonRanking, buttonRanking, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelButtonSair, buttonSair, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);

        adicionaComponentes(this, painelLabel, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(this, painelButtonJogarVSJogador, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(this, painelButtonJogarVSMaquina, 2, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(this, painelButtonRanking, 3, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(this, painelButtonSair, 4, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
    }

    private void adicionaComponentes(Container container, JComponent component, int linha, int coluna, int posicao, int largura, int altura, int preenche){
        constraints.gridy = linha;
        constraints.gridx = coluna;

        constraints.anchor = posicao;

        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.weightx = 1;
        constraints.weighty = 1;

        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = preenche;

        layout.setConstraints(component, constraints);

        Font f = new Font("Tahoma", Font.BOLD, 16);//FONTE || NEGRITO || TAMANHO
        component.setFont(f);

        container.add(component);
    }

    private void ajustaComponentes(){
        setTitle("Home");
        setVisible(true);
        //setSize(550, 300);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonJogadorVsJogador){
            JOptionPane.showMessageDialog(null, "Você Escolheu Jogar Contra Outro Jogador !!!");
            buttonJogadorXJogadorClicado();

        }else if(e.getSource() == buttonJogadorVsMaquina){
            JOptionPane.showMessageDialog(null, "Você Escolheu Jogar Contra Máquina !!!");
        }else if(e.getSource() == buttonRanking){
            JOptionPane.showMessageDialog(null, "Você Escolheu Ranking !!!");
            janelaRanking = new JanelaRanking();
            janelaRanking.setVisible(true);
        }else{
            listUsuario.getUsuario(email).setOnline(false);
            dispose();
        }
    }

    public void buttonJogadorXJogadorClicado(){
        janelaLoginDois = new JanelaLoginDois(listUsuario, email);
        janelaLoginDois.setVisible(true);
        /*
        JogadorHumano jH1 = new JogadorHumano(listUsuario.getUsuario(email));
        //JogadorHumano jH2 = new
        Jogo jogo = new Jogo(jH1,
                new JogadorHumano(new UserSystem("user2@teste.com", "123")));
        new JanelaMesa(jogo);*/
    }

}
