package com.crio.jukebox.exceptions;

public class PlaylistNotFoundException extends Exception{
    public PlaylistNotFoundException(){
        super();
    }
    public PlaylistNotFoundException(String msg){
        super(msg);
    }
}
