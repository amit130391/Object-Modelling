package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{

    private final Map<String,Song> songMap; 

    public SongRepository(){
        songMap=new HashMap<>();
    }

    public SongRepository(Map<String,Song> songMap){
        this.songMap=songMap;
    }

    @Override
    public void saveSong(Song song){
        songMap.put(song.getId(),song);
    }

    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return songMap.containsKey(id);
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
