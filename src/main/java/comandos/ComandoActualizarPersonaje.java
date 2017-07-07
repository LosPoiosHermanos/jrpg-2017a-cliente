package comandos;

import java.io.IOException;

public class ComandoActualizarPersonaje extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.ACTUALIZARPERSONAJE == comando) {
			//escuchaMensajes
//			escuchaMensajes.setPaquetePersonaje((PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class));
//
//			escuchaMensajes.getPersonajesConectados().remove(escuchaMensajes.getPaquetePersonaje().getId());
//			escuchaMensajes.getPersonajesConectados().put(escuchaMensajes.getPaquetePersonaje().getId(), escuchaMensajes.getPaquetePersonaje());
//
//			if (escuchaMensajes.getJuego().getPersonaje().getId() == escuchaMensajes.getPaquetePersonaje().getId()) {
//				escuchaMensajes.getJuego().actualizarPersonaje();
//				escuchaMensajes.getJuego().getEstadoJuego().actualizarPersonaje();
//			}
			
			//juego
//			PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
//
//			juego.getPersonajesConectados().remove(paquetePersonaje.getId());
//			juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
//
//			if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
//				juego.actualizarPersonaje();
//				juego.getEstadoJuego().actualizarPersonaje();
//			}

		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
