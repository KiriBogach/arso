package soap;

import javax.jws.WebService;

@WebService
public interface Conversor {
	
	int parseInt(String numero) throws ConversorException;
}