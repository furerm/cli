package commandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class commandLineInterpreter {
	private runCommand rc = null;
	public enum validCommands { pwd, ls, mkdir, cd, touch, quit };
		
	
	public commandLineInterpreter(Inode<String> fs) {
    	this.rc = new runCommand(fs);
    }
	
    /* devuelve lista con comando y argumentos */
	
	private int executeCmd(String cmd, String[] tokens){
        int err = 0;
		tokens = cmd.split("\\s+");
        switch (tokens[0]) {
        case "pwd":
        	//System.out.println("pwd");
        	if (tokens.length > 1) {
        		System.out.println("usage: pwd");
        		err = 1;
           	} else {
        		rc.pwd();
        	}
            break;
        
        case "ls":
        	//System.out.println("ls");
        	if (tokens.length == 2) {
        		if (!tokens[1].equals("-r")) {
        			System.out.println("usage: ls [-r]");
        			err = 1;
        		} else {
        			//run recursivo
        		}
        	}

        	if (tokens.length > 2) {
        		System.out.println("usage: ls [-r]");
       			err = 1;
       		}
        	
        	if (tokens.length == 1) {
            	rc.ls("");
       		} 

        	break;
        
        case "mkdir":
        	//System.out.println("mkdir");
        	if (tokens.length == 2) {
        		if (tokens[1].length()<=100) {
        			rc.mkdir(tokens[1]);
        		} else {
        			System.out.println("usage: mkdir [dirName]");
        			err = 1;
        		}
        	} else {
    			System.out.println("usage: mkdir [dirName]");
    			err = 1;
        	}
        	
        	break;

        case "cd":
        	//System.out.println("cd");
        	if (tokens.length == 2){
        		rc.cd(tokens[1]);
        	} else {
        		System.out.println("usage: cd [dirName]");
        		err = 1;
        	}
        	break;

        case "touch":
        	//System.out.println("touch");
        	if (tokens.length == 2) { 
        		if(tokens[1].length()<=100) {
        			rc.touch(tokens[1]);
        		} else {
        			System.out.println("usage: touch [fileName]");
        			err = 1;
        		}
        	} else {
    			System.out.println("usage: touch [fileName]");
    			err = 1;
        	}
  
        	break;
        case "quit":
        	//System.out.println("quit");
        	err = -1;
        	if (tokens.length > 1) { 
        		System.out.println("usage: quit");
        		err = 1;
        	}
        	break;
        default: 
        	System.out.println("unknow command");
        	err = 1;
            break;
    }
		return err;
}
        
    /* Lee la STDIN y parsea la linea 
    devuelve -1 si el comando es quit()
    */    
    public boolean Run() throws IOException{
        int err = 0;
        // tokens del comando
        String[] tokens = null;
        
        /* Leo linea desde stdin */
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String cmd = reader.readLine();
        
        err=executeCmd(cmd, tokens);
        
        // comando quit
        if(err==-1) {        
        	return false;
        }
        
        return true;
    }
}
