package no.hvl.dat110.rpc;

import java.util.Arrays;

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
		byte b = ((Integer) x).byteValue();

		byte[] result = new byte[4];

		result[0] = (byte) (x >> 24);
		result[1] = (byte) (x >> 16);
		result[2] = (byte) (x >> 8);
		result[3] = (byte) (x /* >> 0 */);

		System.out.println("int = " + x);
		System.out.println("int som byte = " + ((Integer) x).byteValue());
		System.out.println("int igjen = " + ((Byte) b).intValue());
		System.out.println("evt: " + result[0] + ", " + result[1] + ", " + result[2] + ", " + result[3] + ", ");
		
		System.out.println("tilbake: :(");
		// TODO: marshall RPC identifier and string into byte array

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// TODO: unmarshall integer contained in data

		if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}

		return decoded;

	}
}
