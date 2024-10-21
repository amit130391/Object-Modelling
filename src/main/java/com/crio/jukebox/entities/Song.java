package com.crio.jukebox.entities;

import java.util.Arrays;
import java.util.List;

public class Song extends BaseEntity{
    private String songName;
    private String genre;
    private String albumName;
    private String albumArtist;
    private List<String> featuredArtists;

    public Song(String songName, String genre, String albumName, String albumArtist,
            String featured_Artists) {
        this.songName = songName;
        this.genre = genre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.featuredArtists = tofeaturedArtists(featured_Artists);
    }

    public Song(String id,String songName, String genre, String albumName, String albumArtist,
    String featured_Artists){
        this(songName, genre, albumName, albumArtist, featured_Artists);
        this.id=id;
    }

    public Song(Song song){
        this(song.id,song.songName,song.genre,song.albumName,song.albumArtist,song.featuredArtists.toString());
    }


    public List<String> tofeaturedArtists(String token){
        String[] arrofStr=token.split("#");
        return Arrays.asList(arrofStr);
    }

    public String getSongName() {
        return songName;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public List<String> getFeaturedArtists() {
        return featuredArtists;
    }

    @Override
    public String toString() {
        return "Song [songid="+id+" songName=" + songName + "genre="+genre+" albumName="+albumName+" albumArtist="+albumArtist+" featuredArtist"+featuredArtists+"]";
    }
    // private String songName;
    // private String genre;
    // private String albumName;
    // private String albumArtist;
    // private List<String> featuredArtists;
    

    
}
