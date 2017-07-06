package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Objeto;
import dominio.Orco;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuComercio;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteComercio;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteFinalizarComercio;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteTrueque;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoComercio extends Estado {
	private Mundo mundo;
	private Personaje personaje;
	private Personaje enemigo;
	private Objeto objeto1;
	private Objeto objeto2;
	private int[] posMouse;
	private PaquetePersonaje paquetePersonaje;
	private PaquetePersonaje paqueteEnemigo;
	private PaqueteTrueque paqueteTrueque;
	private PaqueteFinalizarComercio paqueteFinalizarComercio;
	private boolean miTurno;

	private boolean seRealizoAccion;
	private boolean haySeleccionado;
	private boolean hayConfimacion;

	private Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;
	private BufferedImage miniaturaEnemigo;

	private MenuComercio menuComercio;

	public EstadoComercio(Juego juego, PaqueteComercio paqueteComercio) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteComercio.isMiTurno();

		paquetePersonaje = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteComercio.getId());
		paqueteEnemigo = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteComercio.getIdEnemigo());

		crearPersonajes();

		menuComercio = new MenuComercio(miTurno, personaje);

		miniaturaEnemigo = Recursos.personaje.get(enemigo.getNombreRaza()).get(5)[0];
		miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];

		paqueteFinalizarComercio = new PaqueteFinalizarComercio();
		paqueteFinalizarComercio.setId(personaje.getIdPersonaje());
		paqueteFinalizarComercio.setIdEnemigo(enemigo.getIdPersonaje());
		
		//menu salida por defecto
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.mercadoExitoso);
		// limpio la accion del mouse
		juego.getHandlerMouse().setNuevoClick(false);

	}

	@Override
	public void actualizar() {

		juego.getCamara().setxOffset(-350);
		juego.getCamara().setyOffset(150);

		seRealizoAccion = false;
		haySeleccionado = false;
		
		int idObjeto;
		String entrada;
		
		if (miTurno) {
			idObjeto = 0;
			if (juego.getHandlerMouse().getNuevoClick()) {
				posMouse = juego.getHandlerMouse().getPosMouse();

				if (menuComercio.clickEnMenu(posMouse[0], posMouse[1])) {

					if (menuComercio.getObjetoClickeado(posMouse[0], posMouse[1]) == 1 && objeto1 == null) {//SELECCIONAR ID DE OBJETO A CANJEAR
						if (personaje.getCantidadObjetos() > 0) {
								do{//volver a preguntar si no esta en su lista
									entrada = JOptionPane.showInputDialog("Escriba el número indice del elemento que quiere canjear.");
									if(entrada != null){
										idObjeto = Integer.parseInt(entrada);
										seRealizoAccion = true;
									}		
								}while( !personaje.getInventario().estaEnInventario(idObjeto));

							if(idObjeto > 0){
								paqueteTrueque = new PaqueteTrueque(paquetePersonaje.getId(),paqueteEnemigo.getId(), 0, idObjeto, false);
								enviarObjeto(paqueteTrueque);
								miTurno = false;
								menuComercio.setHabilitado(false);
								haySeleccionado = true;
							}
							
						}else{
							finalizarComercio();
							juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),MenuInfoPersonaje.mercadoFallido);
							Estado.setEstado(juego.getEstadoJuego());
						}
						
					}

					
					
				}
				if (enemigo.getCantidadObjetos() == 0) {//si no tiene objetos
					juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),MenuInfoPersonaje.mercadoFallido);
					finalizarComercio();
					Estado.setEstado(juego.getEstadoJuego());
				} 
				if(objeto1 != null){
					if(objeto2 != null ){
						if(!hayConfimacion){
							juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.mercadoFallido);
							//si seleccionamos si retornará un cero, si es no un 1 y si es cancelar un 2 supuestamente
							int resp = JOptionPane.showConfirmDialog(null, "¿Desea intercambiar "+
											" "+ objeto1.getNombre()+ "->  "+ objeto1.getAtributoModificado()+" +"+objeto1.getAtributo()+
											" \n por"+ objeto2.getNombre()+ "->  "+ objeto2.getAtributoModificado()+" +"+objeto2.getAtributo()+"  ?");
							
							if(resp == 0){// si respuesta es si
								personaje.getInventario().agregar(objeto2);
								enemigo.getInventario().quitarObjeto(objeto2);
								
								enemigo.getInventario().agregar(objeto1);
								personaje.getInventario().quitarObjeto(objeto1);
								
								juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.mercadoExitoso);
								
								if (personaje.ganarExperiencia(enemigo.getNivel() * 20)) {
									juego.getPersonaje().setNivel(personaje.getNivel());
									juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
											MenuInfoPersonaje.menuSubirNivel);
								}
								
							}
						}
						finalizarComercio();
						
						miTurno = false;
						menuComercio.setHabilitado(false);	
						Estado.setEstado(juego.getEstadoJuego());
						objeto1 = null;
						objeto2 = null;
					}else{	
						//si me enviaron el primer elemento
						//confirma el objeto recibido y contesta con otro
						idObjeto = 0;
						
						do{
							entrada = JOptionPane.showInputDialog("Escriba el indice del elemento a canjear a cambio de: \n"+
												" "+ objeto1.getNombre()+ "->  "+ objeto1.getAtributoModificado()+" +"+objeto1.getAtributo() );
							if(entrada != null)
								idObjeto = Integer.parseInt(entrada);
						}while(!personaje.getInventario().estaEnInventario(idObjeto));
						
						paqueteTrueque = new PaqueteTrueque(paquetePersonaje.getId(), paqueteEnemigo.getId(), objeto1.getId(), idObjeto,false);
						enviarObjeto(paqueteTrueque);
						miTurno = false;
						menuComercio.setHabilitado(false);	
					}
				}
				juego.getHandlerMouse().setNuevoClick(false);
			}
		}

	}

	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
		mundo.graficar(g);

		g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
		g.drawImage(Recursos.personaje.get(paqueteEnemigo.getRaza()).get(7)[0], 550, 75, 256, 256, null);

		mundo.graficarObstaculos(g);
		menuComercio.graficar(g);

		g.setColor(Color.GREEN);

		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);

	}

	private void crearPersonajes() {
		String nombre = paquetePersonaje.getNombre();
		int salud = paquetePersonaje.getSaludTope();
		int energia = paquetePersonaje.getEnergiaTope();
		int fuerza = paquetePersonaje.getFuerza();
		int destreza = paquetePersonaje.getDestreza();
		int inteligencia = paquetePersonaje.getInteligencia();
		int experiencia = paquetePersonaje.getExperiencia();
		int nivel = paquetePersonaje.getNivel();
		int id = paquetePersonaje.getId();

		Casta casta = null;
		if (paquetePersonaje.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paquetePersonaje.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paquetePersonaje.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (paquetePersonaje.getRaza().equals("Humano")) {
			personaje = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		} else if (paquetePersonaje.getRaza().equals("Orco")) {
			personaje = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		} else if (paquetePersonaje.getRaza().equals("Elfo")) {
			personaje = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		}
		personaje.reestablecerInventario(paquetePersonaje.getInventario());
		nombre = paqueteEnemigo.getNombre();
		salud = paqueteEnemigo.getSaludTope();
		energia = paqueteEnemigo.getEnergiaTope();
		fuerza = paqueteEnemigo.getFuerza();
		destreza = paqueteEnemigo.getDestreza();
		inteligencia = paqueteEnemigo.getInteligencia();
		experiencia = paqueteEnemigo.getExperiencia();
		nivel = paqueteEnemigo.getNivel();
		id = paqueteEnemigo.getId();

		casta = null;
		if (paqueteEnemigo.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paqueteEnemigo.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paqueteEnemigo.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (paqueteEnemigo.getRaza().equals("Humano")) {
			enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		} else if (paqueteEnemigo.getRaza().equals("Orco")) {
			enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		} else if (paqueteEnemigo.getRaza().equals("Elfo")) {
			enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		}
		
		//recupero los datos del inventario

		enemigo.reestablecerInventario(paqueteEnemigo.getInventario());
	}


	public void enviarObjeto(PaqueteTrueque paqueteTrueque) { //enviar objeto
	try {
		juego.getCliente().getSalida().writeObject(gson.toJson(paqueteTrueque));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
		e.printStackTrace();
	}
}

	private void finalizarComercio() { 
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarComercio));

			paquetePersonaje.setSaludTope(personaje.getSaludTope());
			paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
			paquetePersonaje.setNivel(personaje.getNivel());
			paquetePersonaje.setExperiencia(personaje.getExperiencia());
			paquetePersonaje.setDestreza(personaje.getDestreza());
			paquetePersonaje.setFuerza(personaje.getFuerza());
			paquetePersonaje.setInteligencia(personaje.getInteligencia());
			
			paquetePersonaje.setInventario(personaje.getIdDeObjetos());

			paqueteEnemigo.setSaludTope(enemigo.getSaludTope());
			paqueteEnemigo.setEnergiaTope(enemigo.getEnergiaTope());
			paqueteEnemigo.setNivel(enemigo.getNivel());
			paqueteEnemigo.setExperiencia(enemigo.getExperiencia());
			paqueteEnemigo.setDestreza(enemigo.getDestreza());
			paqueteEnemigo.setFuerza(enemigo.getFuerza());
			paqueteEnemigo.setInteligencia(enemigo.getInteligencia());
			
			paqueteEnemigo.setInventario(enemigo.getIdDeObjetos());

			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);

			juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigo));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor.");
			e.printStackTrace();
		}
	}

	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	public PaquetePersonaje getPaqueteEnemigo() {
		return paqueteEnemigo;
	}

	public void setMiTurno(boolean b) {
		miTurno = b;
		menuComercio.setHabilitado(b);
		juego.getHandlerMouse().setNuevoClick(false);
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public Personaje getEnemigo() {
		return enemigo;
	}
	
	//recibo el primer elemento del trueque 
	public void mostrarTrueque(int id) {
		objeto1 = enemigo.getInventario().getObjeto(id);
		
	}
	//recibe respuesta a l elemento enviado
	public void mostrarTrueque(int idrta, int idenviado) {
		
		objeto1 = personaje.getInventario().getObjeto(idrta);
		objeto2 = enemigo.getInventario().getObjeto(idenviado);
		
	}
	
}
