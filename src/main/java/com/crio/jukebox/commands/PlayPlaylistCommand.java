package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dtos.Songdto;
import com.crio.jukebox.exceptions.NoSongsFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userid=tokens.get(1);
        String playlistid=tokens.get(2);
        try {
            Songdto playPlaylist = playlistService.playPlaylist(userid,playlistid);
            System.out.println(playPlaylist);
        } catch (NoSongsFoundException e) {
            System.out.println(e.getMessage());        
        }
    }
    
}
