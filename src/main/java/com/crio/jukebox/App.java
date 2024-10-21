package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.config.ApplicationConfig;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.exceptions.NoSuchDataException;
import com.crio.jukebox.repositories.data.Dataloader;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.
        ApplicationConfig applicationConfig=new ApplicationConfig();
        Dataloader dataloader=applicationConfig.getDataloader();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker(); 
        String inputFile=commandLineArgs.get(0).split("=")[1];
        BufferedReader reader;
        try {
             reader=new BufferedReader(new FileReader(inputFile));
             String line=reader.readLine();
             while(line!=null){
                List<String> tokens=Arrays.asList(line.split(" "));
                String command=tokens.get(0);
                if(command.equals("LOAD-DATA")){
                    String dataFile=tokens.get(1);
                    try {
                        dataloader.executeData(command, dataFile);
                    } catch (NoSuchDataException e) {
                        e.printStackTrace();
                    }
                }else{
                        commandInvoker.executeCommand(command, tokens);
                }
                line=reader.readLine();
             }
             reader.close();

        } catch (IOException | NullPointerException e) {
            
        }

    }
}
