package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.Playlistdto;
import com.crio.jukebox.dtos.Songdto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.NoSongsFoundException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface IPlaylistService {
    public Playlist create(String userid,String playlistName,List<String> songIds) throws SongNotFoundException;
    public void delete(String userid,String playlistid) throws PlaylistNotFoundException;
    public Songdto playPlaylist(String userid,String playlistid) throws NoSongsFoundException;
    public Playlistdto addSong(String userid,String playlistid,List<String> songIds) throws SongNotFoundException;
    public Playlistdto deleteSong(String userid,String playlistid,List<String> songIds) throws NoSongsFoundException;
    public Songdto playNextSong(String userid);
    public Songdto playPreviousSong(String userid);
    public Songdto playGivenSong(String userid,String songid) throws SongNotFoundException;
}
