package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;
import no.hvl.dat110.system.sensor.SensorImpl;

public class Sensor extends RPCStub {

	private byte RPCID = 1;

	public int read() {

		int temp;

		// TODO
		// implement marshalling, call and unmarshalling for read RPC method

		byte[] request = RPCUtils.marshallVoid(RPCID);

		SensorImpl sensor = new SensorImpl();
		
		byte[] reply = sensor.invoke(request);

		temp = RPCUtils.unmarshallInteger(reply);

		return temp; 
		
		
	}

}
