package comandos;

import java.io.IOException;

public class ComandoFinalizarComercio extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.FINALIZARCOMERCIO == comando) {
			//escuchaMensajes
//			escuchaMensajes.setPaqueteFinalizarComercio(
//					(PaqueteFinalizarComercio) gson.fromJson(objetoLeido, PaqueteFinalizarComercio.class));
//			escuchaMensajes.getJuego().getPersonaje().setEstado(Estado.estadoJuego);
//			Estado.setEstado(escuchaMensajes.getJuego().getEstadoJuego());
			
			//juego
//			PaqueteFinalizarComercio paqueteFinalizarComercio = (PaqueteFinalizarComercio) gson.fromJson(objetoLeido,
//					PaqueteFinalizarComercio.class);
//			juego.getPersonaje().setEstado(Estado.estadoJuego);
//			Estado.setEstado(juego.getEstadoJuego());
			

		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
