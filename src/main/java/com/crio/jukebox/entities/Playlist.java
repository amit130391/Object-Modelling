package com.crio.jukebox.entities;

import java.util.List;
import com.crio.jukebox.exceptions.NoSongsFoundException;

public class Playlist extends BaseEntity{
    
    private String playlistname;
    private List<Song> songs;
    private User creator;
    private Song currentSong;

    public Playlist(String playlistname, List<Song> songs, User creator) {
        this.playlistname = playlistname;
        this.songs = songs;
        this.creator = creator;
        this.currentSong=songs.get(0);
    }

    public Playlist(String id,String playlistname,List<Song> songs,User creator){
        this(playlistname, songs, creator);
        this.id=id;
    }

    public void addSong(List<Song> songs){
        this.songs.addAll(songs);
    }

    public void deleteSong(List<Song> song) throws NoSongsFoundException{
        for(Song s:song){
            if(songs.contains(s))
            songs.remove(s);
            else
            throw new NoSongsFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
        }
    }

    public Song nextSong(){
        int index=0;
        for(int i=0;i<songs.size();i++){
            if(currentSong.getId().equals(songs.get(i).getId()))
            index=i;
        }
        if(index==songs.size()-1)
        currentSong=songs.get(0);
        else
        currentSong=songs.get(index+1);
        return currentSong;
    }

    public Song previousSong(){
        int index=0;
        for(int i=0;i<songs.size();i++){
            if(currentSong.getId().equals(songs.get(i).getId()))
            index=i;
        } 
        if(index==0)
        currentSong=songs.get(songs.size()-1);
        else
        currentSong=songs.get(index-1);
        return currentSong;
    }

    public Song givenSong(String songid){
        int index=0;
        for(int i=0;i<songs.size();i++){
            if(songid.equals(songs.get(i).getId()))
            index=i;
        }
        currentSong=songs.get(index);
        return currentSong;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public User getCreator() {
        return creator;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist [User=" + creator + ", currentSong=" + currentSong + ", playlistname="
                + playlistname + ", songs=" + songs + "]";
    }

    
    

}
