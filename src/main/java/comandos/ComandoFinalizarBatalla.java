package comandos;

import java.io.IOException;

public class ComandoFinalizarBatalla extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.FINALIZARBATALLA == comando) {
			//escuchaMensajes
//			escuchaMensajes.setPaqueteFinalizarBatalla(
//					(PaqueteFinalizarBatalla) gson.fromJson(objetoLeido, PaqueteFinalizarBatalla.class));
//			escuchaMensajes.getJuego().getPersonaje().setEstado(Estado.estadoJuego);
//			Estado.setEstado(escuchaMensajes.getJuego().getEstadoJuego());
			
			//juego
//			PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(objetoLeido,
//					PaqueteFinalizarBatalla.class);
//			juego.getPersonaje().setEstado(Estado.estadoJuego);
//			Estado.setEstado(juego.getEstadoJuego());
//			
		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}
}
