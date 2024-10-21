package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dtos.Songdto;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand{

    private final IPlaylistService playlistService;

    public PlaySongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userid=tokens.get(1);
        String toggle=tokens.get(2);
        if(toggle.equals("NEXT")){
            Songdto playNextSong = playlistService.playNextSong(userid);
            System.out.println(playNextSong);
        }
        else if(toggle.equals("BACK")){
            Songdto playPreviousSong = playlistService.playPreviousSong(userid);
            System.out.println(playPreviousSong);
        }
        else{
            try {
                Songdto playGivenSong = playlistService.playGivenSong(userid, toggle);
                System.out.println(playGivenSong);
            } catch (SongNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    } 
}
