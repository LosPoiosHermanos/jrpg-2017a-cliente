package comandos;

import java.io.IOException;

public class ComandoAtacar extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.ATACAR == comando) {
//escuchaMensajes
//			escuchaMensajes.setPaqueteAtacar( (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class));
//
//			HashMap<String, Integer> mapa = escuchaMensajes.getPaqueteAtacar().getHashMap(escuchaMensajes.getPaqueteAtacar().getNuevaSaludPersonaje(),
//					escuchaMensajes.getPaqueteAtacar().getNuevaEnergiaPersonaje());
//			escuchaMensajes.getJuego().getEstadoBatalla().getEnemigo().actualizar(mapa);
//
//			mapa = escuchaMensajes.getPaqueteAtacar().getHashMap(escuchaMensajes.getPaqueteAtacar().getNuevaSaludEnemigo(),
//					escuchaMensajes.getPaqueteAtacar().getNuevaEnergiaEnemigo());
//
//			escuchaMensajes.getJuego().getEstadoBatalla().getPersonaje().actualizar(mapa);
//
//			escuchaMensajes.getJuego().getEstadoBatalla().setMiTurno(true);
			
			//juego
//			PaqueteAtacar paqueteAtacar = (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class);
//
//			HashMap<String, Integer> mapa = paqueteAtacar.getHashMap(paqueteAtacar.getNuevaSaludPersonaje(),
//					paqueteAtacar.getNuevaEnergiaPersonaje());
//			juego.getEstadoBatalla().getEnemigo().actualizar(mapa);
//	
//			mapa = paqueteAtacar.getHashMap(paqueteAtacar.getNuevaSaludEnemigo(),
//					paqueteAtacar.getNuevaEnergiaEnemigo());
//		
//			juego.getEstadoBatalla().getPersonaje().actualizar(mapa);
//
//			juego.getEstadoBatalla().setMiTurno(true);
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
