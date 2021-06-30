package controllers;

import java.awt.print.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.Ticket;

public class ImpresionController implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		if(exchange.getRequestMethod().equals("POST")) {
			
			InputStream is = exchange.getRequestBody();
			String requestJson = new String(new String(is.readAllBytes()));
			System.out.println("Request: " + requestJson);
			
			PrinterJob pj = PrinterJob.getPrinterJob();
			Ticket ticket = new Ticket(requestJson);
			pj.setPrintable(ticket, ticket.getPageFormat());
			
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			
			try {
			   if(pj.printDialog())
			       pj.print(aset);
			} catch (PrinterException e) {
			    e.printStackTrace();
			}
//			
			String response = new String("Respuesta Recibida");
			
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
		}else {
			String response = new String("Servidor de Impresion de facturacion POS");
			
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
		}
	}
}
