package client;

import interfaces.ICalculadora;

public class CalculadoraUDP {
	private String host = ICalculadora.DEFAULT_HOST;
	private int port = ICalculadora.DEFAULT_PORT;
	
	public ICalculadora getSession() {
		return new CalculadoraProxy(host,port);
	}
	public void setServer(String server, int port) {
		this.host = server;
		this.port = port;
	}
}