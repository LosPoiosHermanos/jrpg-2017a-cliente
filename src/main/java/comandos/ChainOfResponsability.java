package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

public class ChainOfResponsability extends NextCliente {
	private NextCliente nextCliente;

	@Override
	public void setNextServidor(NextCliente probador) {
		nextCliente = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {

		ComandoRegistro registro = new ComandoRegistro();
		this.setNextServidor(registro);

		ComandoInicioSesion inicioSesion = new ComandoInicioSesion();
		registro.setNextServidor(inicioSesion);

		ComandoSalir salir = new ComandoSalir();
		inicioSesion.setNextServidor(salir);

		try {
			nextCliente.solicitudDelComando(comando);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo leer la entrada de datos.");
			e.printStackTrace();
		}

	}

	@Override
	public NextCliente getNextServidor() {
		return nextCliente;
	}

}
