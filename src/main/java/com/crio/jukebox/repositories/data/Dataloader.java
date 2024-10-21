package com.crio.jukebox.repositories.data;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchDataException;

public class Dataloader {
    private static final Map<String,IData> dataMap= new HashMap<>();

    public void register(String dataName,IData data){
        dataMap.put(dataName, data);
    }

    private IData get(String dataName){
        return dataMap.get(dataName);
    }

    public void executeData(String dataName,String dataPath) throws NoSuchDataException{
        IData data=get(dataName);
        if(data==null){
            throw new NoSuchDataException();
        }
        data.loadData(dataPath, ",");
        System.out.println("Songs Loaded successfully");
    }
    

}
