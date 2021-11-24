package commandLine;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.concurrent.TimeUnit;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    	boolean keepRunning = true;
        /* creo el nodo root del FS, este puede ser compartido por varias consolas */
        Inode<String> root = new Inode<>('d', "root");
        
        /* creo una instancia de la linea de comandos y le paso el nodo root */
        commandLineInterpreter CLI =  new commandLineInterpreter(root);
        
        while(keepRunning){
        	keepRunning = CLI.Run();
        }

    }
	
}
