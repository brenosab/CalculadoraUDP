package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import interfaces.ICalculadora;
import interfaces.IServer;

public class CalculadoraProxy implements IServer, ICalculadora {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	private InetAddress host;
	private int port;
	private DatagramSocket socket;

	public CalculadoraProxy(String host, int port) {
		try {
			this.host = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		this.port = port;
	}

	public void open() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		socket.close();
	}
	
	int handleServerRequest(String message) throws Exception {
		DatagramPacket sendPacket, receivePacket;
		byte[] buffer = new byte[BUFSIZE];
		sendPacket = new DatagramPacket(message.getBytes(),message.length(),this.host,this.port);
		socket.send(sendPacket);
		receivePacket = new DatagramPacket(buffer,BUFSIZE);
		socket.receive(receivePacket);
		String resultAsString = new String(Arrays.copyOf(buffer, receivePacket.getLength()));
		int result = Integer.parseInt(resultAsString);
		return result;
	}
	
	public Integer somar(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.somar(v1, v2);
		Integer result = handleServerRequest(msg);
		return result;
	}

	public Integer subtrair(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.subtrair(v1, v2);
		Integer result = handleServerRequest(msg);
		return result;
	}

	public Integer multiplicar(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.multiplicar(v1, v2);
		Integer result = handleServerRequest(msg);
		return result;
	}

	public Integer dividir(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.dividir(v1, v2);
		int result = handleServerRequest(msg);
		return result;
	}
}