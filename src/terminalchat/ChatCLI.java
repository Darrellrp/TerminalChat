/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author darrellpoleon
 */
public class ChatCLI extends ChatUser {        
            
    private static Scanner input;    
    
    public void start() throws IOException, InterruptedException, ClassNotFoundException {
        // Create input Scanner
        input = new Scanner(System.in);
        
        // Enter your name
        System.out.println("What is your name?");                
        name = input.nextLine();
        
        System.out.println("");
        
        while (true) {
            promptMenu();
            
            String menuChoice = input.nextLine();            
            
            doAction(menuChoice);   
        }        
    }
    
    private void promptMenu() {
        System.out.println("[SEND <User> <Message>] Send message to user ");
        System.out.println("[!who] See active Users");
        System.out.println("[logout] Logout");

        System.out.println("");
        
    }
    
    private void doAction(String menuChoice) throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("");
        
        String[] command = menuChoice.split("\\s+");
        String actionCommand = command[0];        
                                
        if (actionCommand.equalsIgnoreCase("send")) {
            
            String userName = command[1];
            String message = command[2];
            
            ChatBot otherUser = BotNet.getBotByName(userName);
            
            socket = new Socket(otherUser.getHost(), otherUser.getPort());
            
            socketInput = new ObjectInputStream((socket.getInputStream()));
            socketOutput = new ObjectOutputStream(socket.getOutputStream());

            sendMessage(name, otherUser.getName(), message);
            
            Thread.sleep(1000);
            
            receiveMessage();
        }                         

        else if (menuChoice.equalsIgnoreCase("!who")) {
            showActiveUsers();
        }

        else if (menuChoice.equalsIgnoreCase("logout")) {
            System.out.println("Bye!");
            System.exit(0);
        }

        else {
            System.out.println("Invalid command");
        }


        Thread.sleep(1500);
        System.out.println("");        
    }
    
    private void sendMessage(String senderName, String receiverName, String message) throws IOException, InterruptedException {        
        Message m = new Message(senderName, receiverName, message);
        socketOutput.writeObject(m);        
    }
    
    private void receiveMessage() throws IOException, InterruptedException, ClassNotFoundException {
        Message m = (Message) socketInput.readObject();
        System.out.println(String.format("@%s: %s", m.getReceiverName(), m.getMessage()));            
        
        Thread.sleep(1000);
    }
    
    private void showActiveUsers() {
        System.out.println("Active users:");
        
        BotNet.all().forEach((bot) -> {
            System.out.println(bot.getName());
        });
    }
    
}
