/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalchat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author darrellpoleon
 */
public class TerminalChat {
    
    public static BotNet botNet;
            
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {        
        
        botNet = new BotNet();
                
        botNet.add(new ChatBot("Jason", "127.0.0.1", 8888));
        botNet.add(new ChatBot("Mary", "127.0.0.1", 8889));
        botNet.add(new ChatBot("Harry", "127.0.0.1", 8890));
        
        // Show Welcome message
        System.out.println("######### Welcome to Terminal chat! #########");
        System.out.println("");
        
        botNet.start();
        
        System.out.println("");
        
        // Create ChatCLI
        new ChatCLI().start();         
    }        
    
}
