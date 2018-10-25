/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalchat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author darrellpoleon
 */
public class BotNet {
    
    private static List<ChatBot> bots;
    
    public BotNet() {
        BotNet.bots = new ArrayList<>();
    }
    
    public void start() {
        bots.forEach(bot -> {
            new Thread(bot).start();
        });        
    }
    
    public void add(ChatBot newBot) {
        bots.add(newBot);
    }
    
    public static List<ChatBot> all() {
        return bots;
    }
    
    public static ChatBot getBotByName(String userName) {
        for (ChatBot bot : bots) {
            if (bot.getName().equalsIgnoreCase(userName)) {
                return bot;
            }
        }
        
        System.out.println("User unknown");
        return null;
    }
}
