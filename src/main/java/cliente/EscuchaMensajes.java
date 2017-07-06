package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Cliente;
import estados.Estado;
import estados.EstadoBatalla;
import estados.EstadoComercio;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteComercio;
import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteFinalizarComercio;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteTrueque;

public class EscuchaMensajes extends Thread {

	private Juego juego;
	private Cliente cliente;
	private ObjectInputStream entrada;
	private final Gson gson = new Gson();

	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	private Map<Integer, PaquetePersonaje> personajesConectados;

	public EscuchaMensajes(Juego juego) {
		this.juego = juego;
		cliente = juego.getCliente();
		entrada = cliente.getEntrada();
	}

	public void run() {

		try {

			Paquete paquete;
			PaquetePersonaje paquetePersonaje;
			PaqueteMovimiento personaje;
			PaqueteBatalla paqueteBatalla;
			PaqueteAtacar paqueteAtacar;
			PaqueteFinalizarBatalla paqueteFinalizarBatalla;
			PaqueteFinalizarComercio paqueteFinalizarComercio;
			PaqueteComercio paqueteComercio;
			PaqueteTrueque paqueteTrueque;
			personajesConectados = new HashMap<>();
			ubicacionPersonajes = new HashMap<>();

			while (true) {

				String objetoLeido = (String) entrada.readObject();

				paquete = gson.fromJson(objetoLeido, Paquete.class);

				switch (paquete.getComando()) {

				case Comando.CONEXION:
					personajesConectados = (Map<Integer, PaquetePersonaje>) gson
							.fromJson(objetoLeido, PaqueteDePersonajes.class).getPersonajes();
					break;

				case Comando.MOVIMIENTO:
					ubicacionPersonajes = (Map<Integer, PaqueteMovimiento>) gson
							.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes();
					break;

				case Comando.BATALLA:
					paqueteBatalla = gson.fromJson(objetoLeido, PaqueteBatalla.class);
					juego.getPersonaje().setEstado(Estado.estadoBatalla);
					Estado.setEstado(null);
					juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
					Estado.setEstado(juego.getEstadoBatalla());
					break;

				case Comando.ATACAR:
					paqueteAtacar = (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class);

					HashMap<String, Integer> mapa = paqueteAtacar.getHashMap(paqueteAtacar.getNuevaSaludPersonaje(),
							paqueteAtacar.getNuevaEnergiaPersonaje());
					juego.getEstadoBatalla().getEnemigo().actualizar(mapa);
					/*
					 * juego.getEstadoBatalla().getEnemigo().setSalud(
					 * paqueteAtacar.getNuevaSaludPersonaje());
					 * juego.getEstadoBatalla().getEnemigo().setEnergia(
					 * paqueteAtacar.getNuevaEnergiaPersonaje());
					 */

					mapa = paqueteAtacar.getHashMap(paqueteAtacar.getNuevaSaludEnemigo(),
							paqueteAtacar.getNuevaEnergiaEnemigo());
					/*
					 * juego.getEstadoBatalla().getPersonaje().setSalud(
					 * paqueteAtacar.getNuevaSaludEnemigo());
					 * juego.getEstadoBatalla().getPersonaje().setEnergia(
					 * paqueteAtacar.getNuevaEnergiaEnemigo());
					 */
					juego.getEstadoBatalla().getPersonaje().actualizar(mapa);

					juego.getEstadoBatalla().setMiTurno(true);
					break;

				case Comando.FINALIZARBATALLA:
					paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(objetoLeido,
							PaqueteFinalizarBatalla.class);
					juego.getPersonaje().setEstado(Estado.estadoJuego);
					Estado.setEstado(juego.getEstadoJuego());
					break;

				case Comando.ACTUALIZARPERSONAJE:
					paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);

					personajesConectados.remove(paquetePersonaje.getId());
					personajesConectados.put(paquetePersonaje.getId(), paquetePersonaje);

					if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
						juego.actualizarPersonaje();
						juego.getEstadoJuego().actualizarPersonaje();
					}
					break;
				case Comando.COMERCIO:
					paqueteComercio = gson.fromJson(objetoLeido, PaqueteComercio.class);
					juego.getPersonaje().setEstado(Estado.estadoComercio);
					Estado.setEstado(null);
					juego.setEstadoComercio(new EstadoComercio(juego, paqueteComercio));
					Estado.setEstado(juego.getEstadoComercio());
					break;
					
				case Comando.FINALIZARCOMERCIO:
					paqueteFinalizarComercio = (PaqueteFinalizarComercio) gson.fromJson(objetoLeido,
							PaqueteFinalizarComercio.class);
					juego.getPersonaje().setEstado(Estado.estadoJuego);
					Estado.setEstado(juego.getEstadoJuego());
					break;
				case Comando.TRUEQUE:
					paqueteTrueque = (PaqueteTrueque) gson.fromJson(objetoLeido, PaqueteTrueque.class);
					//envio el que quiere intercambiar
					if(paqueteTrueque.getNuevoObjeto() == 0  && paqueteTrueque.getNuevoObjetoEnemigo() != 0){	
						juego.getEstadoComercio().setMiTurno(true);
						juego.getEstadoComercio().mostrarTrueque(paqueteTrueque.getNuevoObjetoEnemigo());
						
					}else{//envia respuesta
						if(paqueteTrueque.getNuevoObjeto() > 0  && paqueteTrueque.getNuevoObjetoEnemigo() > 0 ){	
							juego.getEstadoComercio().setMiTurno(true);
							juego.getEstadoComercio().mostrarTrueque( paqueteTrueque.getNuevoObjeto(), paqueteTrueque.getNuevoObjetoEnemigo());
						}
					}
					break;

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor.");
			e.printStackTrace();
		}
	}

	public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
		return ubicacionPersonajes;
	}

	public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
		return personajesConectados;
	}
}