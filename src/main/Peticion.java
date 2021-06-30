package main;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.InputStreamReader;
import java.net.URL;

public class Peticion {

	public static String get(String urlFate, String method) throws Exception {

		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlFate);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod(method);

		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;

		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		rd.close();
		return resultado.toString();
	}
}