// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
//**** Changed for E49
  /**
	 * Hook method called after the connection has been closed. The default
	 * implementation does nothing. The method may be overriden by subclasses to
	 * perform special processing such as cleaning up and terminating, or
	 * attempting to reconnect.
	 */
  public void connectionClosed(){
	    
	  try {
		openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		clientUI.display("Server has shutdownc");
	}
	    
  }
  
  protected void connectionException(Exception exception) {
	  clientUI.display("Server shutdownc");
	}
  
  
  
//**** Changed for E49
  public boolean isCommand(String message) {
	  boolean result = false;
	  if(message.startsWith("#")){
		  message = message.substring(1);
		  String[] messageArguments = message.split(" ");
		  switch (messageArguments[0].strip().toLowerCase()){
		  
		  case "quit":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  clientUI.display("quit command does not take any arguments.c");
			  }
			  break;
			  
		  case "logoff":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  clientUI.display("logoff command does not take any arguments.c");
			  }
			  break;
		  case "sethost":
			  if(messageArguments.length ==2) {
				  if(!messageArguments[0].matches(".*\\d.*")){
					  
				  }else {
					  clientUI.display("Invalid host input. String values only.c");
				  }
				  
			  }else {
				  clientUI.display("sethost command take one argument.c");
			  }
			  break;
		  case "setport":
			  if(messageArguments.length ==2) {
				  try {
				  Integer.parseInt(messageArguments[1]);
				  result = true;
				  }catch(NumberFormatException e) {
					  clientUI.display("Invalid port input. Integer values only.c");
				  }
				  
			  }else {
				  clientUI.display("setport command take one argument.c");
			  }
			  break;
		  case "login":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  clientUI.display("login command does not take any arguments.c");
			  }
			  break;
		  case "gethost":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  clientUI.display("gethost command does not take any arguments.c");
			  }
			  break;
		  case "getport":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  clientUI.display("getport command does not take any arguments.c");
			  }
			  break;
		  }
	  }
	  return result;
  }
  
//**** Changed for E50
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  if(isCommand(msg.toString())) {
		  handleMessageFromClientUI(msg.toString());  
	  }else {
    clientUI.display(msg.toString()+"s");
	  }
  }
//**** Changed for E49
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
	
	if (isCommand(message)) {
		String[] commands = message.substring(1).split(" ");
		switch(commands[0].strip().toLowerCase()) {
		case "quit":
			//try {sendToServer(message);}catch(IOException e) {}
			quit();
			break;
		case "logoff":
			if(isConnected()) {
			try
		    {closeConnection();}catch(IOException e) {}
			}else {
				clientUI.display("Client already disconnected.c");
			}
			break;
		case "sethost":
			if(!isConnected()) {
				setHost(commands[1]);
			}else {
				clientUI.display("Client need to disconnect before changing host. please use #logoff command first.c");
			}
			break;
		case "setport":
			if(!isConnected()) {
				setPort(Integer.parseInt(commands[1]));
			}else {
				clientUI.display("Client need to disconnect before changing port. please use #logoff command first.c");
			}
			break;
		case "login":
			if(!isConnected()) {
				try {openConnection();clientUI.display("Client connected successfully.c");}catch(IOException e) {}
			}else {
				clientUI.display("Client is already connected.c");
			}
			break;
		case "gethost":
			clientUI.display("Host name: ("+getHost()+")c");
			break;
		case "getport":
			clientUI.display("Port number: ("+getPort()+")c");
			break;
		
		}
	}else {
		try{
	    	if(isConnected()) {
	    		sendToServer(message);
	    		clientUI.display(message+"c");
	    	}else {
	    		clientUI.display("Client is disconnected. please use #login command first.c");
	    	}
	    }catch(IOException e)
	    {
	      clientUI.display
	        ("Could not send message to server.  Terminating client.c");
	      quit();
	    }
	}
	
	/*
    
    */
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class