package mensajeria;

import java.io.Serializable;

import comandos.Comando;
//REVISADO
public class PaqueteFinalizarBatalla extends Paquete implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idEnemigo;

	public PaqueteFinalizarBatalla() {
		setComando(Comando.FINALIZARBATALLA);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEnemigo() {
		return idEnemigo;
	}

	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}
}
