package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class ComandoInicioSesion extends NextCliente {
	private NextCliente nextCliente;

	@Override
	public void setNextServidor(NextCliente probador) {
		nextCliente = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.INICIOSESION == comando) {
			synchronized (this) {
				Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
				if (paquete.getMensaje().equals(Paquete.msjExito)) {

					// El usuario ya inicio sesion
					cliente.getPaqueteUsuario().setInicioSesion(true);

					// Recibo el paquete personaje con los datos
					cliente.setPaquetePersonaje((PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class));

				} else {
					if (paquete.getMensaje().equals(Paquete.msjFracaso))
						JOptionPane.showMessageDialog(null,
								"Error al iniciar sesion. Revise el usuario y la contraseña");

					// El usuario no pudo iniciar sesion
					cliente.getPaqueteUsuario().setInicioSesion(false);
				}
			}

		} else {
			nextCliente.solicitudDelComando(comando);
		}
	}

	@Override
	public NextCliente getNextServidor() {
		return nextCliente;
	}

}
