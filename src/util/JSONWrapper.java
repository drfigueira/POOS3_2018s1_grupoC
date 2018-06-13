package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ranking;
import model.UserList;

import java.io.*;

/**
 * Classe responsável por mapear e converter as classes UserList e Ranking para formato
 * JSON e persistir os dados em texto plano. Da mesma forma, essa classe tem a responsabilidade
 * de recuperar esses dados posteriormente e subí-los para a memória como objetos Java.
 */
public class JSONWrapper  {
    private final String USERS_PATH = "resources/users.json";
    private final String RANKING_PATH = "resources/ranking.json";
    private ObjectMapper mapper;


    /**
     * Construtor vazio.
     */
    public JSONWrapper() {
        mapper = new ObjectMapper();
    }


    /**
     * Recupera as informações referentes aos usuários do sistema e cria uma UserList a partir desses dados.
     * @return Uma UserList contendo todos os usuários do sistema.
     */
    public UserList loadUsers() {
        UserList retorno = null;
        File f = new File(USERS_PATH);

        if (f.exists()) {
            if (f.length() > 0) {
                try {
                    retorno = mapper.readValue(new File(USERS_PATH), UserList.class);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            } else {
                retorno = new UserList();
            }
        }
        return retorno;
    }

    /**
     * Persiste os dados de uma UserList em um arquivo texto em formato JSON.
     * @param userList A UserList a ser persistida.
     */
    public void storeUsers(UserList userList) {
        try {
            BufferedWriter brf = new BufferedWriter(new FileWriter(new File(USERS_PATH)));
            String jsonString = mapper.writeValueAsString(userList);
            brf.write(jsonString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recupera as informações referentes ao ranking dos jogadores e cria um Ranking a partir desses dados.
     * @return Um Ranking contendo todos os jogadores do sistema.
     */
    public Ranking loadRanking() {
        Ranking retorno = null;
        File f = new File(RANKING_PATH);
        if (f.exists()) {
            if (f.length() > 0) {
                try {
                    retorno = mapper.readValue(new File(RANKING_PATH), Ranking.class);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            } else {
                //retorno = new Ranking();,
                retorno = Ranking.getInstance();
            }
        }
        return retorno;
    }

    /**
     * Persiste os dados de um Ranking em um arquivo texto em formato JSON.
     * @param ranking O Ranking a ser persistido.
     */
    public void storeRanking(Ranking ranking) {
        try {
            BufferedWriter brf = new BufferedWriter(new FileWriter(new File(RANKING_PATH)));
            String jsonString = mapper.writeValueAsString(ranking);
            brf.write(jsonString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
