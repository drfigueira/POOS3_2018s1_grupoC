package view;

import model.JogadorHumano;
import model.Jogo;
import model.UserList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaLoginDois  extends JFrame implements ActionListener {
    private JPanel painelCamposText;
    private JPanel painelButton;

    private JLabel labelEmail;
    private JLabel labelSenha;

    private JTextField txtEmail;
    private JPasswordField txtSenha;

    private JButton buttonEntrar;
    private JButton buttonCancelar;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    JanelaPrincipal janelaPrincipal;
    JanelaHome janelaHome;
    private UserList listUsuario;
    private String emailQueChegou;

    public JanelaLoginDois(UserList listUser, String email){
        super("Janela de Login");
        this.listUsuario = listUser;
        this.emailQueChegou = email;
        criaComponentes();
        ajustaComponentes();
    }

    private void criaComponentes(){
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);

        Border border;
        border = BorderFactory.createEtchedBorder();

        painelCamposText = new JPanel(layout);
        painelCamposText.setBorder(border);

        painelButton = new JPanel(layout);
        painelButton.setBorder(border);

        labelEmail = new JLabel("Email:");
        labelSenha = new JLabel("Senha:");

        txtEmail = new JTextField(30);
        txtSenha = new JPasswordField(10);

        buttonEntrar = new JButton("Entrar");
        buttonEntrar.addActionListener(this);
        buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(this);

        inserirComponentesNaJanela();
    }

    private void inserirComponentesNaJanela(){
        adicionaComponentes(painelCamposText, labelEmail, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, labelSenha, 1, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, txtEmail, 0, 1, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, txtSenha, 1, 1, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);


        adicionaComponentes(painelButton, buttonEntrar, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelButton, buttonCancelar, 0, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);

        adicionaComponentes(this, painelCamposText, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(this, painelButton, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
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
        setTitle("Janela de Login");
        setVisible(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonEntrar){
            buttonEntrarClicado();
        }else{
            buttonCancelarClicado();
        }
    }

    public void buttonEntrarClicado(){
        String email = "", senha = "";
        JogadorHumano jH1;
        JogadorHumano jH2 = null;

        email = txtEmail.getText();
        senha = txtSenha.getText();

        int retorno  = listUsuario.logonUsuario(email, senha);
        if(retorno == 3){
            jH1 = new JogadorHumano(listUsuario.getUsuario(emailQueChegou));
            jH2 = new JogadorHumano(listUsuario.getUsuario(email));

            JOptionPane.showMessageDialog(null, "Seja bem vindo " + email);
            Jogo jogo = new Jogo(jH1, jH2);

            new JanelaMesa(jogo, listUsuario, email);
            dispose();
        }else if(retorno == 0){
            JOptionPane.showMessageDialog(null, "Jogador já está ONLINE " + email);
        }
        else if(retorno == 1){
            JOptionPane.showMessageDialog(null, "Usuário Bloqueado. Por favor aguarde 2 horas do bloqueio e tente novamente !!!");
        }else if(retorno == 2){
            JOptionPane.showMessageDialog(null, "Senha Inválida !!!");
        }

        System.out.println("Retorno: " + retorno);
    }

    public void buttonCancelarClicado(){
        dispose();
    }

    private void limparCampos(){
        txtEmail.setText("");
        txtSenha.setText("");
    }

}
