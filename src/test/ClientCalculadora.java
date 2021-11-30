package test;

import client.CalculadoraUDP;
import interfaces.ICalculadora;
import interfaces.IServer;

public class ClientCalculadora {
	private static ICalculadora server;

	public static void main(String[] args) {
		CalculadoraUDP factory = new CalculadoraUDP();
		factory.setServer("localhost", 1099);
		server = factory.getSession();
		((IServer) server).open();

		somar(1, 9);
		somar(2, 18);
		subtrair(100, 50);
		subtrair(200, 100);
		multiplicar(10, 1);
		multiplicar(10, 2);
		dividir(16, 4);
		dividir(27, 3);

		((IServer) server).close();
		System.exit(0);
	}

	private static void dividir(int a, int b) {
		try {
			int result = server.dividir(a, b);
			System.out.println("A divis�o " + a + " / " + b + " = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void multiplicar(int a, int b) {
		try {
			int result = server.multiplicar(a, b);
			System.out.println("A multiplica��o " + a + " * " + b + " = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void subtrair(int a, int b) {
		try {
			int result = server.subtrair(a, b);
			System.out.println("A subtra��o " + a + " - " + b + " = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void somar(int a, int b) {
		try {
			int result = server.somar(a, b);
			System.out.println("A soma " + a + " + " + b + " = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}