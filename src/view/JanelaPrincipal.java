package view;

import model.UserList;
import util.FileHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class JanelaPrincipal extends JFrame implements ActionListener{
	private JMenuBar barra;

    private JMenu menuConfiguracao;
    private JMenu menuArquivo;

    private JMenuItem opcaoCadastro;
    private JMenuItem opcaoLogin;
    private JMenuItem opcaoSair;

    private JMenuItem opcaoRegras;
    
    private JDesktopPane desktop;
    
    private JanelaCadastro janelaCadastro;
    private JanelaLogin janelaLogin;
    private JanelaRegras janelaRegras;

    private UserList listUsuario;

    private FileHandler file;
    
    public JanelaPrincipal(){
    	criaComponentes();
    	ajustaComponentes();
        file = FileHandler.getInstance();
        listUsuario = file.loadUsers(); //Carregando list do arquivo.
    }


    private void criaComponentes(){
    	desktop = new JDesktopPane();
    	barra = new JMenuBar();
    	
    	menuArquivo = new JMenu("Arquivo");
        menuConfiguracao = new JMenu("Configuração");
    	
    	opcaoCadastro = new JMenuItem("Cadastro");
    	opcaoCadastro.addActionListener(this);
    	
    	opcaoLogin = new JMenuItem("Login");
    	opcaoLogin.addActionListener(this);
    	
    	opcaoSair = new JMenuItem("Sair");
    	opcaoSair.addActionListener(this);
    	
    	menuArquivo.add(opcaoCadastro);
    	menuArquivo.add(opcaoLogin);
    	menuArquivo.add(opcaoSair);

        opcaoRegras = new JMenuItem("Regras");
        opcaoRegras.addActionListener(this);

        menuConfiguracao.add(opcaoRegras);
    	
    	barra.add(menuArquivo);
    	barra.add(menuConfiguracao);
    	setJMenuBar(barra);
    	
    	add(desktop);
    }
    private void ajustaComponentes(){
    	setTitle("Jogo Dominó");
        setVisible(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(opcaoCadastro == e.getSource()){
			//VERIFICA��O SE A JANELA DE CADASTRO ESTA ABERTA
            if(!isVerificarJanelaAtiva(janelaCadastro)){
                  System.out.println("Cadastro");
                  janelaCadastro = new JanelaCadastro(this, listUsuario);
                  carregarJanela(janelaCadastro);
            }else{
                JOptionPane.showMessageDialog(null, "Janela de CADASTRO J Aberta !!!");
            } 
		}else if(opcaoLogin == e.getSource()){
			//VERIFICA SE A JANELA DE LOGIN ESTA ABERTA
			if(!isVerificarJanelaAtiva(janelaLogin)){
                System.out.println("Login");
                janelaLogin = new JanelaLogin(this, listUsuario);
                carregarJanela(janelaLogin);
          }else{
              JOptionPane.showMessageDialog(null, "Janela de LOGIN Já Aberta !!!");
          }
		}else if(opcaoRegras == e.getSource()){
            if(!isVerificarJanelaAtiva(janelaRegras)){
                System.out.println("Regras");
                janelaRegras = new JanelaRegras();
                carregarJanela(janelaRegras);
            }else{
                JOptionPane.showMessageDialog(null, "Janela de REGRAS Já Aberta !!!");
            }
        }else{
			System.out.println("Sair");
            System.exit(0);
		}
		
	}

	//METODO PARA CHAMAR AS TELAS
    private void carregarJanela(JInternalFrame janela){
        desktop.add(janela);
        desktop.moveToFront(janela);
    }

    //METODO PARA VERIFICAR SE A JANELA JA ESTA ABERTA
    private boolean isVerificarJanelaAtiva(JInternalFrame janela){
        return janela != null && !janela.isClosed();
    }
	
}
