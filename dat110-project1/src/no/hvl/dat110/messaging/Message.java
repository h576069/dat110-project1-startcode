package no.hvl.dat110.messaging;

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
		
		// A: setter lengden på payload til første byte
		byte [] encoded = new byte[128];
		encoded[0] = ((Integer) this.payload.length).byteValue();
		
		// A: setter inn resten av byte-elementene fra payload til encoded
		for (int i = 1; i <= this.payload.length; i++) {
			encoded[i] = this.payload[i-1];
		}

		return encoded;
		
	}

	public void decapsulate(byte[] received) {
		
		// A: henter først ut lengden til payload data
		int lengde = ((Byte) received[0]).intValue();
		
		// A: oppretter payload tabellen
		byte[] payloadNew = new byte[lengde];
		
		// A: Setter inn verdiene
		for (int i = 1; i <= lengde; i++)
			payloadNew[i-1] = received[i];
		
		// A: oppdaterer objektvariabelen payload
		this.payload= payloadNew;
	}
}
