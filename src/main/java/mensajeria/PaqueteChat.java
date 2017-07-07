package mensajeria;

import java.io.Serializable;

import comandos.Comando;
//REVISADO
public class PaqueteChat extends Paquete implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emisor;
	private String receptor;
	
	public PaqueteChat(){
		setComando(Comando.ENVIARCHAT);
	}
	
	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor){
		this.receptor = receptor;
	}
	
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}