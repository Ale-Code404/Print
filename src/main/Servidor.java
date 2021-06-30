package main;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

import controllers.*;

public class Servidor {
	
	public static void main(String[] args){
		
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 1);
			server.createContext("/", new ImpresionController());
			server.start();
			
			System.out.println("Servidor " + server.getAddress() + " iniciado");
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
