package no.hvl.dat110.rpc;

import java.io.IOException;
import java.util.HashMap;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer implements RPCImpl { // A: implementerer interface for å definere invoke metoden

	private MessagingServer msgserver;
	private Connection connection;

	// hashmap to register RPC methods which are required to implement RPCImpl

	private HashMap<Integer, RPCImpl> services;

	public RPCServer(int port) {

		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Integer, RPCImpl>();

		// the stop RPC methods is built into the server
		services.put((int) RPCCommon.RPIDSTOP, new RPCServerStopImpl());
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

			// A: mottar melding over connection som inneholder RPC request
			Message m = connection.receive();

			// A: utfører metoden
			byte[] resultat = this.invoke(m.getData());
			
			// A: sender svaret tilbake over connection
		   connection.send(new Message(resultat));

			int rpcid = resultat[0];
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

	// A: MÅ implementere invoke metoden fra RPCImpl.java
	@Override
	public byte[] invoke(byte[] request) {
//		System.out.println("I INVOKE RPCServer");
		int rpcid = request[0];
		RPCImpl metode = this.services.get(rpcid);
		System.out.println("Metode : " + metode);
		
//		System.out.println("req lengde: " + request.length);
		byte[] resultat = metode.invoke(request);

		// håndtere unmarshalling av parametre
		// kalle på den ekte underliggende eksterne metoden for implementasjon
		// marshalle retur verdien
		return resultat;
	}
}
