package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dtos.Playlistdto;
import com.crio.jukebox.exceptions.NoSongsFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String songCommand=tokens.get(1);
        String userid=tokens.get(2);
        String playlistid=tokens.get(3);
        List<String> songids=new ArrayList<>();
        for(int i=4;i<tokens.size();i++){
            songids.add(tokens.get(i));
        }
        if(songCommand.equals("ADD-SONG")){
            try {
                Playlistdto plistdto = playlistService.addSong(userid, playlistid, songids);
                System.out.println(plistdto);
            } catch (SongNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(songCommand.equals("DELETE-SONG")){
            try {
                Playlistdto playlistdto = playlistService.deleteSong(userid, playlistid, songids);
                System.out.println(playlistdto);
            } catch (NoSongsFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        
    }
    
}
