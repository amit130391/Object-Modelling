package com.crio.jukebox.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongData implements IData{

    private final ISongRepository iSongRepository;

    public SongData(ISongRepository iSongRepository){
        this.iSongRepository=iSongRepository;
    }

    @Override
    public void loadData(String dataPath, String delimiter) {
        BufferedReader reader;        
        try {
            reader=new BufferedReader(new FileReader(dataPath));
            String line = reader.readLine();
            while(line!=null){
            List<String> tokens=Arrays.asList(line.split(delimiter));
            if(tokens.size()==6)
            addSong(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4), tokens.get(5));
            line=reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addSong(String id,String songName, String genre, String albumName, String albumArtist,
    String featured_Artists){
        iSongRepository.saveSong(new Song(id, songName, genre, albumName, albumArtist, featured_Artists));
    }
    
}
