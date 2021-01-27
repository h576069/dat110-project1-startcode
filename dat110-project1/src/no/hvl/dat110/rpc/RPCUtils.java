package no.hvl.dat110.rpc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;
        Charset charset= StandardCharsets.UTF_8;
		// TODO: marshall RPC identifier and string into byte array
        byte [] strbytes=str.getBytes(charset);
        encoded= new byte[strbytes.length + 1];
        encoded[0]=rpcid;
        
      
       for(int i=0;i<strbytes.length && i < encoded.length;i++) {
    	   encoded[i+1]= strbytes[i];
    	   
       }
       System.out.println(str);
		
		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;
		Charset charset= StandardCharsets.UTF_8;
		byte[] utenRpcid= new byte[data.length -1];
		
		for(int i=0;i<data.length-1 ;i++) {
	    	   utenRpcid[i]=data[i+1];
	    	   
	       }
     decoded= new String(utenRpcid,charset);
		System.out.println(decoded);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// TODO: marshall RPC identifier in case of void type

		if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
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
		return null;
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
