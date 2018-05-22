package model.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class JSONWrapper<C, T> implements Observer {
    private ObjectMapper mapper;
    private String filePath;
    private

    public JSONWrapper(String filePath, T observable) {
        if(filePath != null && !filePath.trim().isEmpty()) {
            this.filePath = filePath;
        }
        mapper = new ObjectMapper();
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
