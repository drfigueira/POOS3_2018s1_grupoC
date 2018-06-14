package model;

public class UserList {
    private static int LIMITE = 10;
    public static final int LOGIN_INVALIDO = 0;
    public static final int USUARIO_BLOQUEADO = 1;
    public static final int SENHA_INVALIDA = 2;
    public static final int ACESSO_COM_SUCESSO = 3;

    private UserSystem[] usuarios;
    private int qtdUsuarios;

    /**
     * Contrutor de uma lista de usuários do sistema, com limite de usuários estipulado em 10
     */
    public UserList() {
        usuarios = new UserSystem[LIMITE];
        this.qtdUsuarios = 0;
    }

    /**
     * Adiciona um novo usuário do sistema na lista de usuários
     * @param user
     * @return false se usuário já cadastrado, limite excedido. True se usuário do sistema foi adicionado com sucesso.
     */
    public boolean addUsuario(UserSystem user) {
        boolean deuCerto = false;
        if(user != null) {
            if (this.qtdUsuarios < LIMITE && !containsEmail(user.toString())) {
                usuarios[this.qtdUsuarios] = user;
                this.qtdUsuarios++;
                deuCerto = true;
            }
        }
        return deuCerto;
    }

    /**
     * Verifica se existe email informado já está cadastrado na lista
     * @param email String
     * @return true se email já estiver cadastrado, caso não, retorna false.
     */
    private boolean containsEmail(String email) {
        boolean existe = false;
        int i = 0;
        while(i < this.qtdUsuarios && !existe) {
            if(usuarios[i].toString().equals(email)) {
                existe = true;
            }
            i++;
        }
        return existe;
    }

    /**
     * Retorna usuário do sistema pelo email cadastrado.
     * @param email String
     * @return UserSystem se existir na lista, caso não, retorna null;
     */
    public UserSystem getUsuario(String email) {
        UserSystem user = null;
        int i = 0;
        while(i < this.qtdUsuarios && user == null) {
            if(usuarios[i].toString().equals(email)) {
                user = usuarios[i];
            }
            i++;
        }
        return user;
    }

    /**
     * Efetua o login de um usuário a partir do email e senha passados.
     * @param email Uma String contendo o email
     * @param senha Uma string contendo a senha
     * @return
     */
    public int logonUsuario(String email, String senha) {
        int retorno = LOGIN_INVALIDO;
        int i = 0;
        while(i < this.qtdUsuarios && retorno == LOGIN_INVALIDO) {
            if(usuarios[i].toString().equals(email) && !usuarios[i].isOnline()) {
                retorno = usuarios[i].verificaAcesso(senha);
            }
            i++;
        }
        return retorno;
    }

    /**
     * Retorna usuário do sistema de uma posição valida
     * @param pos inteiro
     * @return UserSystem se posição for valida, caso não, retorna null;
     */
    public UserSystem getAt(int pos) {
        return pos < this.qtdUsuarios? usuarios[pos] : null;
    }

    /**
     * Retorna o tamanho da lista.
     * @return Um inteiro indicando o tamanho da lista.
     */
    public int getSize() { return qtdUsuarios; }

}