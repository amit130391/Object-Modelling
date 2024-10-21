package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity{
    private String name;
    private List<Playlist> playLists;
    private Playlist currentPlaylist;
    
    public User(String name){
        this.name=name;
        this.playLists=new ArrayList<Playlist>();
    }

    public User(String name,List<Playlist> playlists){
        this(name);
        this.playLists=playlists;
    }

    public User(String id,String name){
        this(name);
        this.id=id;
    }

    public User(String id,String name,List<Playlist> playlists){
        this(id, name);
        this.playLists=playlists;
    }

    public String getName() {
        return name;
    }

    public List<Playlist> getPlayList() {
        return playLists;
    }

    

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public void addPlaylist(Playlist playlist){
        playLists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        playLists.removeIf(p->p.getId()==playlist.getId());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id +" "+ name ;
    }

}
