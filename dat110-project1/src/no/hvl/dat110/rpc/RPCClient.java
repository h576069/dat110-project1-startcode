package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient extends RPCStub{

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		this.connection = msgclient.connect();
	}
	
	public void disconnect() {
		connection.close();
				
	}
	
	public byte[] call(byte[] rpcrequest) {
		byte[] rpcreply;
		
		Message send = new Message(rpcrequest);
		connection.send(send);
		
		
		Message reply = connection.receive();
		rpcreply = reply.getData(); //henter ut dataene, pakker ut. 
		
		return rpcreply;
	
	}

}
