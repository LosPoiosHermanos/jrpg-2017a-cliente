package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Inventario;
import dominio.Objeto;
import estados.Estado;

public class PaqueteInventario extends Paquete implements Serializable, Cloneable {
	private int idPersonaje;
	//SABRI usar esta clase o agregar inventario a paquete personaje
	private Inventario inventario;
	
	//falta agregar historia del personaje
	
	public PaqueteInventario() {
//		setComando(Comando.INVENTARIO);
	}

	public ArrayList<Objeto> getInventario() {
		return inventario.clone();
	}

	public void addInventario(Objeto obj) {
		this.inventario.añadir(obj);
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
}
