package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		// TODO: connect using the underlying messaging layer connection
		this.connection = msgclient.connect();
	}
	
	public void disconnect() {
		
		// TODO: disconnect/close the underlying messaging connection
		connection.close();
				
	}
	
	public byte[] call(byte[] rpcrequest) {
	/* TODO: 
		
		Make a remote call on the RPC server by sending the RPC request message
		and receive an RPC reply message
		
		rpcrequest is the marshalled rpcrequest from the client-stub
		rpctreply is the rpcreply to be unmarshalled by the client-stub
		
		*/
		
		byte[] rpcreply;
		
		Message send = new Message(rpcrequest);
		connection.send(send);
		
		
		Message reply = connection.receive();
		rpcreply = reply.getData(); //henter ut dataene, pakker ut. 
		
		return rpcreply;
	
	}

}
