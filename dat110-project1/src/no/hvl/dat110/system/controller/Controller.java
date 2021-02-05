package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		

		// A: koble til sensor og display RPC servere
		sensorclient.connect();
		displayclient.connect();
		
		// A: Oppretter lokale objekter for display og sensor
		Sensor sensor = new Sensor();
		Display display = new Display();
		
		// A: registrerer objektene i RPC laget
		sensorclient.register(sensor);
		displayclient.register(display);
		
		
		// register stop methods in the RPC layer
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		// A: looper mens vi leser fra sensor og skriver til display
		for (int i = 0; i < N; i++) {
			int temp = sensor.read();
			display.write(Integer.toString(temp));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
