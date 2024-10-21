package com.crio.jukebox.exceptions;

public class SongNotFoundException extends Exception{
    public SongNotFoundException(){
        super();
    }
    public SongNotFoundException(String msg){
        super(msg);
    }
}
