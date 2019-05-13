package cliente;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

// Bien

public class ConsultaUbicacion {

	public static final String LICENCE_KEY = "0";
	public static final String CONSULTED_IP = "155.54.1.1";

	public static void main(String[] args) {
		IP2Geo servicio = new IP2Geo();
		IP2GeoSoap puerto = servicio.getIP2GeoSoap();
		
		IPInformation ipInfo = puerto.resolveIP("155.54.1.1", LICENCE_KEY);
		
		String pais = ipInfo.getCountry();
		String ciudad = ipInfo.getCity();
		
		System.out.println("IP consultada: " + CONSULTED_IP);
		System.out.println("Ciudad: " + ciudad);
		System.out.println("País: " + pais);
	}

}
