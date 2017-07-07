package mensajeria;

import java.io.Serializable;
import comandos.Comando;
//REVISADO
public class PaqueteTrueque extends Paquete implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idEnemigo;
	private int nuevoObjeto;
	private int nuevoObjetoEnemigo;

	public PaqueteTrueque(int id, int idEnemigo, int nuevoObjeto, int nuevoObjetoEnemigo, boolean hayIntercambio) {
		setComando(Comando.TRUEQUE);
		this.id = id;
		this.idEnemigo = idEnemigo;
		this.nuevoObjeto = nuevoObjeto;
		this.nuevoObjetoEnemigo = nuevoObjetoEnemigo;

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

	public int getNuevoObjeto() {
		return nuevoObjeto;
	}

	public void setNuevoObjeto(int nuevoObjeto) {
		this.nuevoObjeto = nuevoObjeto;
	}

	public int getNuevoObjetoEnemigo() {
		return nuevoObjetoEnemigo;
	}

	public void setnuevoObjetoEnemigo(int nuevoObjetoEnemigo) {
		this.nuevoObjetoEnemigo = nuevoObjetoEnemigo;
	}

}
