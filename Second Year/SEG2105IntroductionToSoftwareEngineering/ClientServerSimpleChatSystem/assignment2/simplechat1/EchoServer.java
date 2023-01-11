// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

import client.ChatClient;
import common.ChatIF;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
//Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the server.
   */
  ChatIF serverUI;
  
  String[][] clientsConnected = new String[10000][2];
  int current = -1;
  //Constructors ****************************************************
  
  
//**** Changed for E50
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  
  public EchoServer(int port) 
  {
    super(port);
    
    
  }
  public EchoServer(int port,ChatIF servertUI) throws IOException
  {
    super(port);
    this.serverUI = servertUI;
    listen();
  }

  //****E49
  public void clientConnected(ConnectionToClient client) {
	  boolean done = false;
	  Thread[] clientThreadList = getClientConnections();
	  for (int i=0; i<clientThreadList.length; i++) {
		  if(((ConnectionToClient)(clientThreadList[i])).equals(client)) {
			  try {
			  if(clientsConnected[i][0].equals(client.toString())) {
				  System.out.println("Welcome back, Client "+client+" is connected");
				  clientsConnected[i][1] = "c";
				  done = true;
			  }
			  }catch(Exception e) {}
		  }
	  }
	  if(!done) {
		  
		  System.out.println("Welcome, Client "+client+" is connected");
		  clientsConnected[++current][0] = client.toString();
		  clientsConnected[current][1] = "c";
		  
	  }
	  
	  
  }
  
  synchronized protected void clientDisconnected(ConnectionToClient client) {
	 

  }
  synchronized protected void clientException(
		    ConnectionToClient client, Throwable exception) {
Thread[] clientThreadList = getClientConnections();
	  
	  for (int i=0; i<clientsConnected.length; i++)
	    {
	      try
	      {
	    	  String c = ((ConnectionToClient)(clientThreadList[i])).toString();
	        //if((ConnectionToClient)(clientThreadList[i])).toString().equals(clientsConnected[i])) !=true);
	        if(c== null) {
	        	if(clientsConnected[i][1] == "c") {
	        	 System.out.println(clientsConnected[i][0] + " is disconnected");
	        	 clientsConnected[i][1] = "d";
	        }
	        	
	      }}
	      catch (Exception ex) {}
	    }
  }
  //Instance methods ************************************************
  
  
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("Message received: " + msg + " from " + client);
    
  }
//**** Changed for E49
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServerUI(String message) 
  {
	  if (isCommand(message)) {
			String[] commands = message.substring(1).split(" ");
			switch(commands[0].strip().toLowerCase()) {
			case "quit":
				//try {sendToServer(message);}catch(IOException e) {}
				try {
					close();
					System.exit(0);
				} catch (IOException e1) {}
				break;
			case "stop":
				if(isListening()) {
				stopListening();
				}else {
					serverUI.display("Server is already not listening for Clients.");
				}
				break;
			case "close":
				if(isListening()) {
				stopListening();
				sendToAllClients("#logoff");
				}else {
					serverUI.display("Server is already not listening for Clients.");
				}
				break;
			
			case "setport":
				if(!isListening()) {
					setPort(Integer.parseInt(commands[1]));
				}else {
					serverUI.display("Server need to closed before changing port. please use #close command first.");
				}
				break;
			case "start":
				if(!isListening()) {
					try {listen();serverUI.display("Server is listening for Clients.");}catch(IOException e) {}
				}else {
					serverUI.display("Server already listening for connections.");
				}
				break;
			
			case "getport":
				serverUI.display("Port number: ("+getPort()+")");
				break;
			
			}
		}else {
			if(isListening()) {
		    	serverUI.display(message);
		    	sendToAllClients(message);
		    }else {
		    	serverUI.display("Server is not active. please use #startcommand first.");
		    }
		    
		}
		
	  //serverUI.display(message);
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  //**** added for E49
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
				  serverUI.display("quit command does not take any arguments.");
			  }
			  break;
			  
		  case "stop":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  serverUI.display("stop command does not take any arguments.");
			  }
			  break;
		  case "close":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  serverUI.display("stop command does not take any arguments.");
			  }
			  break;
		  case "setport":
			  if(messageArguments.length ==2) {
				  try {
				  Integer.parseInt(messageArguments[1]);
				  result = true;
				  }catch(NumberFormatException e) {
					  serverUI.display("Invalid port input. Integer values only");
				  }
				  
			  }else {
				  serverUI.display("setport command take one argument.");
			  }
			  break;
		  case "start":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  serverUI.display("start command does not take any arguments.");
			  }
			  break;
		  
		  case "getport":
			  if(messageArguments.length == 1) {
				  result = true;
			  }else {
				  serverUI.display("getport command does not take any arguments.");
			  }
			  break;
		  }
	  }
	  return result;
  }
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
 * @throws IOException 
   */
 public static void main(String[] args) throws IOException 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
