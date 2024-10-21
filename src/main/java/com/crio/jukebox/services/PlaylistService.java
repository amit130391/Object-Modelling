package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.Playlistdto;
import com.crio.jukebox.dtos.Songdto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.NoSongsFoundException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService{
    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;
    private final IUserService userService;

    
    public PlaylistService(IPlaylistRepository playlistRepository, IUserRepository userRepository,
            ISongRepository songRepository, IUserService userService) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.userService = userService;
    }

    @Override
    public Playlist create(String userid, String playlistName, List<String> songIds) throws SongNotFoundException {
        final User user = userRepository.findById(userid).orElseThrow(()->new UserNotFoundException("User with the given id: "+userid+" not found!"));
        List<Song> songs=new ArrayList<>();
        for(String songid:songIds){
            Song song = songRepository.findById(songid).orElseThrow(()->new SongNotFoundException("Some Requested Songs Not Available. Please try again."));
            songs.add(song);
        }
        Playlist playlist = playlistRepository.save(new Playlist(playlistName, songs, user));
        user.addPlaylist(playlist);
        return playlist;
    }

    @Override
    public void delete(String userid, String playlistid) throws PlaylistNotFoundException {
        final User user = userRepository.findById(userid).orElseThrow(()->new UserNotFoundException("User with the given id: "+userid+" not found!"));
        final Playlist playlist = playlistRepository.findById(playlistid).orElseThrow(()->new PlaylistNotFoundException("Playlist not found"));
        playlistRepository.delete(playlist);
        user.deletePlaylist(playlist);
    }

    @Override
    public Songdto playPlaylist(String userid, String playlistid) throws NoSongsFoundException {
        User user = userRepository.findById(userid).get();
        Playlist playlist = playlistRepository.findById(playlistid).get();
        if(playlist.getSongs().isEmpty()){
            throw new NoSongsFoundException("The Playlist is Empty!");
        }
        user.setCurrentPlaylist(playlist);
        userRepository.save(user);
        Song currentSong = playlist.getCurrentSong();

        return new Songdto(currentSong.getSongName(), currentSong.getAlbumName(), currentSong.getFeaturedArtists());
    }

    @Override
    public Playlistdto addSong(String userid, String playlistid, List<String> songIds) throws SongNotFoundException {
        final User user = userRepository.findById(userid).get();
        Playlist playlist = playlistRepository.findById(playlistid).get();
        List<Song> songs=new ArrayList<>();
        for(String songid:songIds){
            Song song = songRepository.findById(songid).orElseThrow(()->new SongNotFoundException("Some Requested Songs Not Available. Please try again."));
            songs.add(song);
        }
        playlist.addSong(songs);
        playlistRepository.save(playlist);
        List<String> finalsongids = playlist.getSongs().stream().map(s->s.getId()).collect(Collectors.toList());

        return new Playlistdto(playlist.getId(), playlist.getPlaylistname(), finalsongids);
    }

    @Override
    public Playlistdto deleteSong(String userid, String playlistid, List<String> songIds) throws NoSongsFoundException {
        final User user = userRepository.findById(userid).get();
        Playlist playlist = playlistRepository.findById(playlistid).get();
        List<Song> songs=new ArrayList<>();
        for(String songid:songIds){
            Song song = songRepository.findById(songid).get();
            songs.add(song);
        }
        playlist.deleteSong(songs);
        playlistRepository.save(playlist);
        List<String> finalsongids = playlist.getSongs().stream().map(s->s.getId()).collect(Collectors.toList());

        return new Playlistdto(playlist.getId(), playlist.getPlaylistname(), finalsongids);
    }

    @Override
    public Songdto playNextSong(String userid) {
        User user = userRepository.findById(userid).get();
        Playlist playlist = user.getCurrentPlaylist();
        Song nextSong = playlist.nextSong();
        return new Songdto(nextSong.getSongName(), nextSong.getAlbumName(), nextSong.getFeaturedArtists());
    }

    @Override
    public Songdto playPreviousSong(String userid) {
        User user = userRepository.findById(userid).get();
        Playlist playlist = user.getCurrentPlaylist();
        Song previousSong = playlist.previousSong();
        return new Songdto(previousSong.getSongName(), previousSong.getAlbumName(), previousSong.getFeaturedArtists());
    }

    @Override
    public Songdto playGivenSong(String userid, String songid) throws SongNotFoundException  {
        User user = userRepository.findById(userid).get();
        Playlist playlist = user.getCurrentPlaylist();
        Song song = songRepository.findById(songid).get();
        if(!playlist.getSongs().contains(song))
        throw new SongNotFoundException("Given song id is not a part of the active playlist");

        Song givenSong = playlist.givenSong(songid);
        
        return new Songdto(givenSong.getSongName(), givenSong.getAlbumName(), givenSong.getFeaturedArtists());
    }

    
}
