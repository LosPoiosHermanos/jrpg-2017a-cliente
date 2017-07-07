package comandos;

import java.io.IOException;

public class CoREscuchaMensajes extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {

		ComandoConexion conexion = new ComandoConexion();
		this.setNextEscuchaMensajes(conexion);

		ComandoMovimiento movimiento = new ComandoMovimiento();
		conexion.setNextEscuchaMensajes(movimiento);

		ComandoBatalla batalla = new ComandoBatalla();
		movimiento.setNextEscuchaMensajes(batalla);

		ComandoAtacar atacar = new ComandoAtacar();
		batalla.setNextEscuchaMensajes(atacar);

		ComandoFinalizarBatalla finalizarBatalla = new ComandoFinalizarBatalla();
		atacar.setNextEscuchaMensajes(finalizarBatalla);

		ComandoActualizarPersonaje actualizarPersonaje = new ComandoActualizarPersonaje();
		finalizarBatalla.setNextEscuchaMensajes(actualizarPersonaje);

		ComandoComercio comercio = new ComandoComercio();
		actualizarPersonaje.setNextEscuchaMensajes(comercio);

		ComandoFinalizarComercio finalizarComercio = new ComandoFinalizarComercio();
		comercio.setNextEscuchaMensajes(finalizarComercio);

		ComandoTrueque trueque = new ComandoTrueque();
		finalizarComercio.setNextEscuchaMensajes(trueque);

		try {
			nextEscuchaMensajes.solicitudDelComando(comando);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
