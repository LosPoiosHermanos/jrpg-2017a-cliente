package comandos;

import java.io.IOException;

public class ComandoBatalla extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.BATALLA == comando) {
			//escuchaMensajes
//			escuchaMensajes.setPaqueteBatalla(gson.fromJson(objetoLeido, PaqueteBatalla.class));
//			escuchaMensajes.getJuego().getPersonaje().setEstado(Estado.estadoBatalla);
//			Estado.setEstado(null);
//			escuchaMensajes.getJuego().setEstadoBatalla(new EstadoBatalla(escuchaMensajes.getJuego(), escuchaMensajes.getPaqueteBatalla()));
//			Estado.setEstado(escuchaMensajes.getJuego().getEstadoBatalla());
			
			//juego
//			PaqueteBatalla paqueteBatalla =(PaqueteBatalla) gson.fromJson(objetoLeido, PaqueteBatalla.class);
//			juego.getPersonaje().setEstado(Estado.estadoBatalla);
//			Estado.setEstado(null);
//			juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
//			Estado.setEstado(juego.getEstadoBatalla());

		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
