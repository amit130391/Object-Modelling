package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchCommandException;


public class CommandInvoker {
    private static final Map<String,ICommand> commandMap=new HashMap<>();

    public void register(String commandName,ICommand command){
        commandMap.put(commandName, command);
    }

    private ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName,List<String> tokens) {
        ICommand command=get(commandName);
        if(command==null){
            try {
                throw new NoSuchCommandException("Invalid Command!");
            } catch (NoSuchCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        command.execute(tokens);
    }
}
