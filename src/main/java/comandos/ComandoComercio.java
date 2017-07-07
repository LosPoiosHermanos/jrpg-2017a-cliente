package comandos;

import java.io.IOException;

public class ComandoComercio extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.COMERCIO == comando) {
			//escuchaMensajes
//			escuchaMensajes.setPaqueteComercio((PaqueteComercio) gson.fromJson(objetoLeido, PaqueteComercio.class));
//			escuchaMensajes.getJuego().getPersonaje().setEstado(Estado.estadoComercio);
//			Estado.setEstado(null);
//			escuchaMensajes.getJuego().setEstadoComercio(
//					new EstadoComercio(escuchaMensajes.getJuego(), escuchaMensajes.getPaqueteComercio()));
//			Estado.setEstado(escuchaMensajes.getJuego().getEstadoComercio());
			
			//juego
//			PaqueteComercio paqueteComercio = (PaqueteComercio) gson.fromJson(objetoLeido, PaqueteComercio.class);
//			juego.getPersonaje().setEstado(Estado.estadoComercio);
//			Estado.setEstado(null);
//			juego.setEstadoComercio(new EstadoComercio(juego, paqueteComercio));
//			Estado.setEstado(juego.getEstadoComercio());
			
		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
