/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darrellpoleon
 */
public class ChatBot extends ChatUser implements Runnable, Serializable {
    
    private final String host;
    private final int port;
    private final ServerSocket serverSocket;     
    
    public List<ChatBot> bots;
    
    public static final int IP = 8888;

    public ChatBot(String name, String host, int port) throws IOException {        
        this.name = name;
        this.host = host;
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }        

    @Override
    public void run() {
        try {
            System.out.println("Bot running...");

            while (true) {
                socket = serverSocket.accept();
                
                // Create Object Streams
                socketOutput = new ObjectOutputStream(socket.getOutputStream());                
                socketInput = new ObjectInputStream(socket.getInputStream());
                
                // Read the send message
                Message sendMessage = (Message) socketInput.readObject();
                
                System.out.println(String.format("@%s %s", sendMessage.receiverName, sendMessage.message));
                // Create the to send message
                Message message = new Message(name, sendMessage.senderName, sendMessage.message);
                socketOutput.writeObject(message);

                Thread.sleep(1000);                                
                socket.close();   
            }
        
        } catch (IOException | InterruptedException | ClassNotFoundException ex) {
            Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
    
}
