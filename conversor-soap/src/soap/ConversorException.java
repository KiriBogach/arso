package soap;

import javax.xml.ws.WebFault;

@WebFault
public class ConversorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	protected String info;

	public ConversorException(String info) {
		super(info);
		this.info = info;
	}

	public String getFaultInfo() {
		return info;
	}
}