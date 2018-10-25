/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalchat;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author darrellpoleon
 */
public class ChatUser {
    
    protected String name;
    protected Socket socket;
    protected ObjectInputStream socketInput;
    protected ObjectOutputStream socketOutput;
        
}
