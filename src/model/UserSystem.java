package model;

import java.util.Date;

public class UserSystem extends User {
    public static final int USUARIO_BLOQUEADO = 1;
    public static final int SENHA_INVALIDA = 2;
    public static final int ACESSO_COM_SUCESSO = 3;



    private long inicioBloqueio = 0;
    private Date horaBloqueio = null;
    private boolean online;//TRUE = ESTÁ ONLINE || FALSE = NÃO ESTÁ ONLINE;
    private String senha;
    private int contTentativaAcesso;



    /**
     * Construtor base de User, recebe o email e a senha do usuario.
     *
     * @param email Uma String contendo o email do usuario.
     * @param senha Uma String que ser&aacute; utilizada para construir a senha do usuario.
     */
    public UserSystem(String email, String senha) {
        super(email, senha);
        this.senha = senha;
        contTentativaAcesso = 0;
    }

    /**
     * Método block(); que é chamado quando usuário erra a senha
     * O método verifica se a quantidade de tentativa é igual a 3
     * Se for igual ele armazena o horário inicial em um atributo Long milissegundo(hora atual) do bloqueio.
     */
    private void block(){
        if(contTentativaAcesso == 3){
            inicioBloqueio = System.currentTimeMillis();
            horaBloqueio = new Date();
        }
    }

    /**
     * Método onBlock(); que é chamado automaticamente pelo sistema
     * O método verifica se o Usuário está bloqueado
     * Armazenando em uma váriavel local Long a hora(em milissegundo) que está tentando o acesso
     * Caso o horário(milessegundo) subtraindo do horário(milissegundo) do bloqueio seja maior que 2 horas(em milissegundo)
     * Ele zera os valores dos atributos, assim desbloqueando o usuário e deixando que o mesmo acesse
     * @return
     */
    protected boolean onBlock(){
        long horaAtualEmMilisegundos = System.currentTimeMillis();
        if((horaAtualEmMilisegundos - inicioBloqueio) > 7200000){ //7200000 milisegundos == 2 horas
            horaBloqueio = null;
            inicioBloqueio = 0;
            return true;
        }
        return false;
    }

    /**
     * Método verificaAcesso(); que é chamado pelo Usuário quando o mesmo informa sua senha para login.
     * O método chamado o método onBlock para verificar se o mesmo está bloqueado, caso esteja retorne um valor indicando que está bloqueado
     * Também verifica a senha informada pelo Usuário se bate com a senha que foi cadastrada quando o Objeto foi criado.
     * Caso Usuário erre a senha é chamado o método block();
     * @param senha
     * @return
     */
    public int verificaAcesso(String senha){
        if(onBlock()){
            if(!getSenha().verificarSenha(senha)){
                contTentativaAcesso+=1;
                block();
                return SENHA_INVALIDA;
            }
            return ACESSO_COM_SUCESSO;
        }
        return USUARIO_BLOQUEADO;
    }
}
