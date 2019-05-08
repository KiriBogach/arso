package cliente;

import soap.Conversor;
import soap.ConversorException;
import soap.ConversorImplService;

public class ConvertidorEnteros {

	public static void main(String[] args) {
		
		ConversorImplService servicio = new ConversorImplService();
		Conversor puerto = servicio.getConversorImplPort();
		
		String[] entradas = {"14", "2", "2,2", "null", "123712", "2.1"};
		
		for (String entrada : entradas) {
			System.out.println("Entrada: " + entrada);
			
			try {
				System.out.println("Salida: " + puerto.parseInt(entrada));
			} catch (ConversorException e) {
				System.out.println("Excepción: " + e);
			} finally {
				// Retorno de carro
				System.out.println();
			}
		}
		
	}
}
