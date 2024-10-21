package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userid=tokens.get(1);
        String playlistid=tokens.get(2);

        try {
            playlistService.delete(userid, playlistid);
        } catch (PlaylistNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Delete Successful");
    }
    
}
