package util;

import model.*;

import java.io.*;

public class FileHandler {
    private final String USERS_PATH = "resources/users.txt";
    private final String RANKING_PATH = "resources/ranking.txt";
    private static FileHandler instance;

    private FileHandler() {
    }

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    /**
     * Recupera as informações referentes aos usuários do sistema e cria uma UserList a partir desses dados.
     * @return Uma UserList contendo todos os usuários do sistema.
     */
    public UserList loadUsers() {
        UserList retorno = new UserList();
        File f = new File(USERS_PATH);

        if (f.exists()) {
            if (f.length() > 0) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(USERS_PATH));
                    String line;
                    while ((line = br.readLine()) != null) {
                        retorno.addUsuario(stringToUser(line));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }

    /**
     * Persiste os dados de uma UserList em um arquivo texto em formato JSON.
     * @param list A UserList a ser persistida.
     */
    public void storeUsers(UserList list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_PATH));
            bw.write(userListToString(list));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ranking loadRanking() {
        Ranking retorno = new Ranking();
        File f = new File(RANKING_PATH);

        if (f.exists()) {
            if (f.length() > 0) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String line;
                    while ((line = br.readLine()) != null) {
                        retorno.addPlayer(stringToJogador(line));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }

    public void storeRanking(Ranking ranking) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(RANKING_PATH));
            bw.write(rankingToString(ranking));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String userToString(UserSystem user) {
        return user.getEmail() + "/" + user.getSenha();
    }

    private UserSystem stringToUser(String string) {
        String[] fields = string.split("/");
        UserSystem retorno = new UserSystem(fields[0]);

        String[] bytes = fields[1].split(",");
        byte[] senha = new byte[bytes.length];
        for (int i = 0; i < senha.length; i++) {
            senha[i] = Byte.parseByte(bytes[i]);
        }
        retorno.setSenha(fields[0], senha);
        return retorno;
    }

    private String userListToString(UserList list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.getSize(); i++) {
            sb.append(userToString(list.getAt(i)));
            sb.append("\n");
        }
        return sb.toString();
    }

    private JogadorHumano stringToJogador(String string) {
        String[] fields = string.split(":");
        JogadorHumano j = new JogadorHumano(new UserSystem(fields[0]));
        j.setScore(Integer.parseInt(fields[1]));
        return j;
    }


    private String rankingToString(Ranking ranking) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ranking.getTamanhoArray(); i++) {
            JogadorHumano j = (JogadorHumano) ranking.getJogadorAtId(i);
            String[] fields = j.toString().trim().split("-");
            sb.append(fields[0].trim().split(":")[1]);
            sb.append(":");
            sb.append(fields[1].trim().split(":")[1]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
