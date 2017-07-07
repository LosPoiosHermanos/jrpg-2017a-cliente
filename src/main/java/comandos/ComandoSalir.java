package comandos;

import java.io.IOException;

import mensajeria.Paquete;

public class ComandoSalir extends NextCliente {
	private NextCliente nextCliente;

	@Override
	public void setNextServidor(NextCliente probador) {
		nextCliente = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.SALIR == comando) {
			// El usuario no pudo iniciar sesion
			cliente.getPaqueteUsuario().setInicioSesion(false);
			cliente.getSalida().writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
			cliente.getSocket().close();
		} else {
			nextCliente.solicitudDelComando(comando);
		}

	}

	@Override
	public NextCliente getNextServidor() {
		return nextCliente;
	}

}
