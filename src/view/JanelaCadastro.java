package view;

import model.UserList;
import model.UserSystem;
import util.FileHandler;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class JanelaCadastro extends JInternalFrame implements ActionListener{
	private JPanel painelCamposText;
    private JPanel painelButton;
    
    private JLabel labelEmail;
    private JLabel labelSenha;
    private JLabel labelConfirmarSenha;
    
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmarSenha;
    
    private JButton buttonCadastrar;
    private JButton buttonCancelar;
    
    private GridBagLayout layout;
    private GridBagConstraints constraints;
	
	private JanelaPrincipal janelaPrincipal;
	private UserList listUsuario;
	
	public JanelaCadastro(JanelaPrincipal janelaPrincipal, UserList listUsuario){
        super("Cadastro de Usuários", true, true, true, true);
        this.janelaPrincipal = janelaPrincipal;
        this.listUsuario = listUsuario;
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
		labelConfirmarSenha = new JLabel("Confirme a Senha:");
		
		txtEmail = new JTextField(30);
		txtSenha = new JPasswordField(10);
		txtConfirmarSenha = new JPasswordField(10);
		
		buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.addActionListener(this);
		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);
		
		inserirComponentesNaJanela();
	}
	
	private void inserirComponentesNaJanela(){
		adicionaComponentes(painelCamposText, labelEmail, 0, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, labelSenha, 1, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, labelConfirmarSenha, 2, 0, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, txtEmail, 0, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, txtSenha, 1, 1, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        adicionaComponentes(painelCamposText, txtConfirmarSenha, 2, 1, GridBagConstraints.WEST, 1, 1, GridBagConstraints.BOTH);
        
        
        adicionaComponentes(painelButton, buttonCadastrar, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
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
		setTitle("Janela de Cadastro");
        setVisible(true);
        setSize(550, 300); 
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(buttonCadastrar == e.getSource()){
			buttonCadastrarClicado();
			System.out.println("Bot�o Cadastro Clicado");
		}else{
			buttonCancelarClicado();
		}
		
	}
	
	public void buttonCadastrarClicado(){
		String email, senha, confirmarSenha;
		UserSystem userSystem;
		
		email = txtEmail.getText();
		senha = txtSenha.getText();
		confirmarSenha = txtConfirmarSenha.getText();

		if(email.equals("")){
			JOptionPane.showMessageDialog(null,"Campo Email está vazio. Por Favor Preencher !!!");
		}
		else if(!email.matches("[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}")) {
			JOptionPane.showMessageDialog(null,"Opa! Parece que isso não é um email. Por Favor preencher novamente!!!");

		}
		else if(senha.equals("")){
			JOptionPane.showMessageDialog(null,"Campo Senha está vazio. Por Favor Preencher !!!");
		}
		else if(confirmarSenha.equals("")){
			JOptionPane.showMessageDialog(null,"Campo Confirmar Senha está vazio. Por Favor Preencher !!!");
		}
		else if((senha.equals(confirmarSenha))){
			userSystem = new UserSystem(email, senha);
			if(listUsuario.addUsuario(userSystem)){
				FileHandler.getInstance().storeUsers(listUsuario);
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usuário Cadastrado Com Sucesso !!!");
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Já existe um e-mail cadastrado com esse endereço !!!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Senhas Diferentes !!!");
		}
	}
	
	public void buttonCancelarClicado(){
		limparCampos();
	}


	
	private void limparCampos(){
		txtEmail.setText("");
		txtSenha.setText("");
		txtConfirmarSenha.setText("");
	}
}
