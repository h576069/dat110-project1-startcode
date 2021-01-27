package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;

public class MessagingClient {

	private String server;
	
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
		System.out.println("Server="+ server);
		System.out.println("port="+ port);
	}

	// connect to messaging server
	public Connection connect() {

		Socket clientSocket=null;
		Connection connection=null;
		try {
			clientSocket = new Socket(server, port);
			connection = new Connection(clientSocket);
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

		
	

		return connection;
	}
}
