package soap;

import javax.jws.WebService;

// El servicio no se publica correctamente en Tomcat
// En web.xml ha faltado asociar la URL con el servlet

@WebService
public interface Conversor {
	
	int parseInt(String numero) throws ConversorException;
}
