package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import frames.MenuCreacionPj;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class ComandoRegistro extends NextCliente {
	private NextCliente nextCliente;

	@Override
	public void setNextServidor(NextCliente probador) {
		nextCliente = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.REGISTRO == comando) {
			synchronized (this) {
				Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
				if (paquete.getMensaje().equals(Paquete.msjExito)) {

					// Abro el menu para la creaci√≥n del personaje
					MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(cliente, cliente.getPaquetePersonaje());
					menuCreacionPJ.setVisible(true);

					// Espero a que el usuario cree el personaje
					try {
						wait();

						// Le envio los datos al servidor
						cliente.getPaquetePersonaje().setComando(Comando.CREACIONPJ);
						cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
						JOptionPane.showMessageDialog(null, "Registro exitoso.");

						// Recibo el paquete personaje con los datos (la id
						// incluida)
						cliente.setPaquetePersonaje((PaquetePersonaje) gson
								.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class));

					} catch (InterruptedException | JsonSyntaxException | ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "El cliente recibido est· obsoleto.");
						e.printStackTrace();
					}

					// Indico que el usuario ya inicio sesion
					cliente.getPaqueteUsuario().setInicioSesion(true);

				} else {
					if (paquete.getMensaje().equals(Paquete.msjFracaso))
						JOptionPane.showMessageDialog(null, "No se pudo registrar.");

					// El usuario no pudo iniciar sesion
					cliente.getPaqueteUsuario().setInicioSesion(false);
				}
			}

		}else
		{
			nextCliente.solicitudDelComando(comando);
		}
	}

	@Override
	public NextCliente getNextServidor() {
		return nextCliente;
	}

}
