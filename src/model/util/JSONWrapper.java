package model.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ranking;
import model.UserList;

import java.io.*;


public class JSONWrapper  {
    private final String USERS_PATH = "resources/users.json";
    private final String RANKING_PATH = "resources/ranking.json";
    private ObjectMapper mapper;


    public JSONWrapper() {
        mapper = new ObjectMapper();
    }


    public UserList loadUsers() {
        UserList retorno = null;
        try {
            retorno = mapper.readValue(new File(USERS_PATH), UserList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return retorno;
    }

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

    public Ranking loadRanking() {
        Ranking retorno = null;
        try {
            retorno = mapper.readValue(new File(RANKING_PATH), Ranking.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return retorno;
    }

    public void storeRanking(Ranking userList) {
        try {
            BufferedWriter brf = new BufferedWriter(new FileWriter(new File(RANKING_PATH)));
            String jsonString = mapper.writeValueAsString(userList);
            brf.write(jsonString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
