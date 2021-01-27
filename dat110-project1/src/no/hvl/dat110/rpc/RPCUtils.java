package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return
	// values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the
	// RPC message syntax [rpcid,parameter/return value]

	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array

		if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// TODO: unmarshall String contained in data into decoded

		if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		// A: siden det er void sender vi kun rpcid-en
		byte[] encoded = new byte[1];
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {
		// A: mottar kun en void, så pakker bare ut id-en
		// A: Som vi ikke trenger å pakke ut?
		byte rpcid = data[0];
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {
		// A: integer representeres ved 4 bytes
		byte[] encoded = new byte[1 + 4];

		// A: setter inn rpcid:
		encoded[0] = rpcid;
		
		// A: Bruker ByteBuffer:
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(x);
		// A: henter ut byte array
		byte[] byteInt = bb.array();
		
		// A: limer inn byte array verdiene til encoded
		for (int i = 1; i < encoded.length; i++) {
			encoded[i] = byteInt[i-1];
		}
		
		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {
		// A: henter først ut rpcid
		byte rpcid = data[0];
		
		// A: henter ut dataene:
		byte[] intData = new byte[data.length - 1];
		for (int i = 1; i < data.length; i++) {
			intData[i-1] = data[i];
		}
		
		// A: Bruker ByteBuffer
		ByteBuffer bb = ByteBuffer.wrap(intData);
		
		// A: Henter ut heltallet.
		int decoded = bb.getInt();
		
		return decoded;

	}
}
