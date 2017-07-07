package mensajeria;

import java.io.Serializable;
import java.util.Map;
//REVISADO
public class PaqueteDePersonajes extends Paquete implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, PaquetePersonaje> personajes;

	public PaqueteDePersonajes() {

	}

	public PaqueteDePersonajes(Map<Integer, PaquetePersonaje> personajes) {
		this.personajes = personajes;
	}

	public Map<Integer, PaquetePersonaje> getPersonajes() {
		return personajes;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}