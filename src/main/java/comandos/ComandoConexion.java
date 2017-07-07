package comandos;

import java.io.IOException;

public class ComandoConexion extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.CONEXION == comando) {
			
			//escuchaMensajes
//			PaqueteDePersonajes paqueteDePersonajes = (PaqueteDePersonajes) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
//			escuchaMensajes.setPersonajesConectados(paqueteDePersonajes.getPersonajes());
			
			//juego
//			PaqueteDePersonajes paqueteDePersonajes = (PaqueteDePersonajes) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
//			juego.setPersonajesConectados(paqueteDePersonajes.getPersonajes());
			
		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
