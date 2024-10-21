package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService){
        this.playlistService=playlistService;
    }


    @Override
    public void execute(List<String> tokens) {
        String userid=tokens.get(1);
        String playlistName=tokens.get(2);
        List<String> songids=new ArrayList<>();
        for(int i=3;i<tokens.size();i++){
            songids.add(tokens.get(i));
        }
        Playlist playlist;
        try {
            playlist = playlistService.create(userid, playlistName, songids);
            System.out.println("Playlist ID - "+playlist.getId());
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
}
