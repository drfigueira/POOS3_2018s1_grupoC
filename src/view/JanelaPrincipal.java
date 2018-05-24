package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JanelaPrincipal extends JFrame implements ActionListener{
	private JMenuBar barra;
    private JMenu menuArquivo;
    private JMenuItem opcaoCadastro;
    private JMenuItem opcaoLogin;
    private JMenuItem opcaoSair;
    
    private JDesktopPane desktop;
    
    private JanelaCadastro janelaCadastro;
    private JanelaLogin janelaLogin;
    
    public JanelaPrincipal(){
    	criaComponentes();
    	ajustaComponentes();
    }
    
    private void criaComponentes(){
    	desktop = new JDesktopPane();
    	barra = new JMenuBar();
    	
    	menuArquivo = new JMenu("Arquivo");
    	
    	opcaoCadastro = new JMenuItem("Cadastro");
    	opcaoCadastro.addActionListener(this);
    	
    	opcaoLogin = new JMenuItem("Login");
    	opcaoLogin.addActionListener(this);
    	
    	opcaoSair = new JMenuItem("Sair");
    	opcaoSair.addActionListener(this);
    	
    	menuArquivo.add(opcaoCadastro);
    	menuArquivo.add(opcaoLogin);
    	menuArquivo.add(opcaoSair);
    	
    	barra.add(menuArquivo);
    	
    	setJMenuBar(barra);
    	
    	add(desktop);
    }
    private void ajustaComponentes(){
    	setTitle("Jogo Domin�");
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
                  janelaCadastro = new JanelaCadastro(this);
                  carregarJanela(janelaCadastro);
            }else{
                JOptionPane.showMessageDialog(null, "Janela de CADASTRO J� Aberta !!!");
            } 
		}else if(opcaoLogin == e.getSource()){
			//VERIFICA��O SE A JANELA DE LOGIN ESTA ABERTA
			if(!isVerificarJanelaAtiva(janelaLogin)){
                System.out.println("Login");
                janelaLogin = new JanelaLogin(this);
                carregarJanela(janelaLogin);
          }else{
              JOptionPane.showMessageDialog(null, "Janela de LOGIN J� Aberta !!!");
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
