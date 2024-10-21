package com.crio.jukebox.dtos;

import java.util.List;

public class Playlistdto {
    private String playlistId;
    private String playlistName;
    private List<String> songIds;

    public Playlistdto(String playlistId, String playlistName, List<String> songIds) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songIds = songIds;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getSongIds(List<String> st) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<st.size()-1;i++){
            sb.append(st.get(i)).append(' ');
        }
        sb.append(st.get(st.size()-1));

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Playlist ID - "+playlistId+"\nPlaylist Name - "+playlistName+"\nSong IDs - "+getSongIds(songIds);
    }
 
}
