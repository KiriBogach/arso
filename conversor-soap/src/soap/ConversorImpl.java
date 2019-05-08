package soap;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.Conversor")
public class ConversorImpl implements Conversor {

	@Override
	public int parseInt(String numero) throws ConversorException {
		try {
			return Integer.parseInt(numero);
		} catch (NumberFormatException ex) {
			throw new ConversorException("Error parseando la entrada. " + ex.getMessage());
		}
		
	}

}