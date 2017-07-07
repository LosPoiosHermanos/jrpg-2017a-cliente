package comandos;

import java.io.IOException;

public class ComandoMovimiento extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.MOVIMIENTO == comando) {
			//escuchaMensajes
//			escuchaMensajes.setUbicacionPersonajes((Map<Integer, PaqueteMovimiento>) gson
//					.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes());
			
			//juego
//			juego.setUbicacionPersonajes((Map<Integer, PaqueteMovimiento>) gson
//					.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes());

		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
