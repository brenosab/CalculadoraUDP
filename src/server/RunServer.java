package server;
import interfaces.ICalculadora;

public class RunServer {
	public static void main(String[] args) {
		CalculadoraServerUDP calc = new CalculadoraServerUDP(ICalculadora.DEFAULT_HOST,ICalculadora.DEFAULT_PORT);
		calc.run();
	}
}