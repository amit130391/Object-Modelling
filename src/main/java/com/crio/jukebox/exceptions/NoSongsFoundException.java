package com.crio.jukebox.exceptions;

public class NoSongsFoundException extends Exception{
    public NoSongsFoundException(){
        super();
    }
    public NoSongsFoundException(String msg){
        super(msg);
    }
}
