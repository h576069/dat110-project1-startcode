package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		// Payload har maks lengde på 127 byte
		if (payload.length <= 127)
			this.payload = payload; 
		// A: hva hvis den er større?
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
//		byte[] encoded = null;
		// A: setter lengden på payload til første byte
		byte [] encoded = new byte[128];
		encoded[0] = ((Integer) this.payload.length).byteValue();
		
		// A: setter inn resten av byte-elementene fra payload til encoded
		for (int i = 0; i < this.payload.length; i++) {
			encoded[i+1] = this.payload[i];
		}
		
		// TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
//		if (true)
//		   throw new UnsupportedOperationException(TODO.method());

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		throw new UnsupportedOperationException(TODO.method());
		
	}
}
