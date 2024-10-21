package com.crio.jukebox.config;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.repositories.data.Dataloader;
import com.crio.jukebox.repositories.data.SongData;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {
    private final ISongRepository songRepository=new SongRepository();
    private final IUserRepository userRepository=new UserRepository();
    private final IPlaylistRepository playlistRepository=new PlaylistRepository();

    private final IUserService userService=new UserService(userRepository);
    private final IPlaylistService playlistService=new PlaylistService(playlistRepository, userRepository, songRepository, userService);
    
    private final CreateUserCommand createUsercommand=new CreateUserCommand(userService);
    private final CreatePlaylistCommand createPlaylistCommand=new CreatePlaylistCommand(playlistService);
    private final DeletePlaylistCommand deletePlaylistCommand=new DeletePlaylistCommand(playlistService);
    private final PlayPlaylistCommand playPlaylistCommand=new PlayPlaylistCommand(playlistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand=new ModifyPlaylistCommand(playlistService);
    private final PlaySongCommand playSongCommand=new PlaySongCommand(playlistService);

    private final CommandInvoker commandInvoker = new CommandInvoker();
    private final Dataloader dataloader=new Dataloader();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER", createUsercommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }

    public Dataloader getDataloader(){
        dataloader.register("LOAD-DATA", new SongData(songRepository));
        return dataloader;
    }

}
