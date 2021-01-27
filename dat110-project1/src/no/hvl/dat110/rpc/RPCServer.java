package no.hvl.dat110.rpc;

import java.io.IOException;
import java.util.HashMap;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;
	
	// hashmap to register RPC methods which are required to implement RPCImpl
	
	private HashMap<Integer,RPCImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Integer,RPCImpl>();
		
		// the stop RPC methods is built into the server
		services.put((int)RPCCommon.RPIDSTOP,new RPCServerStopImpl());
	}
	
	public void run() {
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
		
		try {
			connection = msgserver.accept();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {
	    
		   int rpcid;
		   
		   // A: mottar melding over connection 
		   Message m = connection.receive();

		   // A: henter ut rpcid og lagrer payload data alene
		   byte[] allData = m.getData();
		   rpcid = allData[0];
		   byte[] data = new byte[allData.length-1];
		   for (int i = 1; i < allData.length; i++) {
			   data[i-1] = allData[i];
		   }

		   // A: finner metoden i hashmappet
		   RPCImpl metode = this.services.get(rpcid);
		   
		   // A: utfører metoden
		   byte[] resultat = metode.invoke(data);
		   
		   // A: sender svaret tilbake over connection
		   connection.send(new Message(resultat));
		   
		   if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
		   }
		}
	
	}
	
	public void register(int rpcid, RPCImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {
		connection.close();
		msgserver.stop();
		
	}
}
