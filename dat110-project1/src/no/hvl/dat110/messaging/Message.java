package no.hvl.dat110.messaging;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		// Payload har maks lengde på 127 byte
//		if (payload.length <= (MessageConfig.SEGMENTSIZE-1))
//			this.payload = payload; 

		/* PRØVER EKSTRA OPPGAVEN */
		this.payload = payload;
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload;
	}

	public byte[] encapsulate() {
		// A: setter lengden på payload til første byte
//		byte [] encoded = new byte[MessageConfig.SEGMENTSIZE];

		/* PRØVER EKSTRA OPPGAVEN */
		/*
		 * Der er fler måter å gjøre dette på, men tanken er at de enkelte segment
		 * fortsatt har fast lengde på 128 men en må da sende fler segmenter om data
		 * (payload) er på mer en 127 bytes og så ha en mekanisme via den første bytes
		 * til å si om dette er siste segment slik det kan settes sammen til et fullt
		 * payload i andre enden. Tema her er "fragmentation" som vi også kommer til
		 * senere.
		 */
//		if (this.payload.length > MessageConfig.SEGMENTSIZE - 1) {
//			
//		}
		byte[] encoded = new byte[this.payload.length + 1];

		encoded[0] = ((Integer) this.payload.length).byteValue();

		// A: setter inn resten av byte-elementene fra payload til encoded
		for (int i = 1; i <= this.payload.length; i++) {
			encoded[i] = this.payload[i - 1];
		}

		return encoded;

	}

	public void decapsulate(byte[] received) {

		// A: henter først ut lengden til payload data
		int lengde = ((Byte) received[0]).intValue();
		
		/* PRØVER EKSTRA OPPGAVEN */
		if (lengde < 0) 
			lengde += 2*128;

		// A: oppretter payload tabellen
		byte[] payloadNew = new byte[lengde];

		// A: Setter inn verdiene
		for (int i = 1; i <= lengde; i++)
			payloadNew[i - 1] = received[i];

		// A: oppdaterer objektvariabelen payload
		this.payload = payloadNew;
	}
}
