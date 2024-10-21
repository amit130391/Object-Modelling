package com.crio.jukebox.dtos;

import java.util.List;

public class Songdto {
    private String songName;
    private String albumName;
    private List<String> artists;

    public Songdto(String songName, String albumName, List<String> artists) {
        this.songName = songName;
        this.albumName = albumName;
        this.artists = artists;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    // public List<String> getArtists() {
    //     return artists;
    // }

    public String getArtists(List<String> st){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<st.size()-1;i++){
            sb.append(st.get(i)).append(',');
        }
        sb.append(st.get(st.size()-1));

        return sb.toString();
    }

    @Override
    public String toString() {
        return  "Current Song Playing\nSong - " + songName + "\nAlbum - " + albumName + "\nArtists - " + getArtists(artists);
    }
    
}
