package view;

import model.Jogador;
import model.JogadorHumano;
import model.Ranking;
import model.UserSystem;
import util.FileHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaRanking extends JFrame implements ActionListener {
    private JPanel painelLabelMensagem;
    private JPanel painelTable;
    private JPanel painelButton;

    private JLabel labelMensagem;

    private JTable tableRanking;
    private JScrollPane scrollTable;

    private JButton buttonVoltar;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    public JanelaRanking(){
        super("Ranking");
        criaComponentes();
        ajustaComponentes();
        preencherJtable();
    }

    private void criaComponentes(){
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);

        Border border;
        border = BorderFactory.createEtchedBorder();

        painelLabelMensagem = new JPanel(layout);
        painelLabelMensagem.setBorder(border);

        painelTable = new JPanel(layout);
        painelTable.setBorder(border);

        painelButton = new JPanel(layout);
        painelButton.setBorder(border);

        labelMensagem = new JLabel("#### Ranking ####");



        tableRanking = new JTable();
        /*
        tableRanking.setModel(new DefaultTableModel(
                new Object[][]
                        {
                                {"1º","Joao@gmail.com", "500"},
                                {"2º","Maria@gmail.com", "467"},
                                {"3º","Pedro@gmail.com", "450"},
                                {"4º","Joao_Vitor@gmail.com", "380"},
                                {"5º","Caio@gmail.com", "200"},
                        },
                new String[] {"Posicao", "E-mail", "Pontos"}
        ));*/

        scrollTable = new JScrollPane(tableRanking);


        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(this);

        inserirComponentesNaJanela();
    }

    private void inserirComponentesNaJanela(){
        adicionaComponentes(painelLabelMensagem, labelMensagem, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.NONE);
        adicionaComponentes(painelTable, scrollTable, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelButton, buttonVoltar, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.NONE);


        adicionaComponentes(this, painelLabelMensagem, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.NONE);
        adicionaComponentes(this, painelTable, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(this, painelButton, 2, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.NONE);
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
        setSize(400, 600);
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonVoltar){
//            FileHandler.getInstance().storeRanking(Ranking.getInstance());
            dispose();
        }
    }

    private void preencherJtable() {
        JogadorHumano jH = null;
        String posicao = "", score = "", email = "";
        Ranking ranking = Ranking.getInstance();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Posicao");
        modelo.addColumn("E-mail");
        modelo.addColumn("Score");

            for(int i=0; i<ranking.getTamanhoArray(); i++){
                posicao = String.valueOf(i+1);
                score = String.valueOf(ranking.getJogadorAtId(i).getScore());
                jH = (JogadorHumano) ranking.getJogadorAtId(i);
                email = jH.getEmail();

                modelo.addRow(new String[]{String.valueOf(posicao), email,String.valueOf(score)});
            }

            tableRanking.setModel(modelo);
    }
}
