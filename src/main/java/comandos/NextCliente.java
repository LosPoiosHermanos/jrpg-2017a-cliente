package comandos;

import java.io.IOException;

import cliente.Cliente;

public abstract class NextCliente extends Comando {
	protected static Cliente cliente;

	public abstract void setNextServidor(NextCliente probador);

	public abstract void solicitudDelComando(int comando) throws IOException;

	public abstract NextCliente getNextServidor();

	public void setCliente(Cliente cliente) {
		NextCliente.cliente = cliente;
	}

}
