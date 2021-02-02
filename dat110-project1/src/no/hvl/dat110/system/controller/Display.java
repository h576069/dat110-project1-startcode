package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;
import no.hvl.dat110.system.display.DisplayImpl;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {
		DisplayImpl dis= new DisplayImpl();
		byte[] svar= dis.invoke(RPCUtils.marshallString(this.RPCID, message));
		RPCUtils.unmarshallVoid(svar);
		
	}
}
