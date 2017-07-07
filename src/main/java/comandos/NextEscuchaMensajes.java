package comandos;

import java.io.IOException;

public abstract class NextEscuchaMensajes extends Comando {
//	protected static Juego juego;
//	protected static EscuchaMensajes escuchaMensajes;

//	 public void setJuego(Juego juego) {
//	 this.juego = juego;
//	 }

//	public void setEscuchaMensajes(EscuchaMensajes escuchaMensajes) {
//		this.escuchaMensajes = escuchaMensajes;
//	}

	public abstract void setNextEscuchaMensajes(NextEscuchaMensajes probador);

	public abstract void solicitudDelComando(int comando) throws IOException;

	public abstract NextEscuchaMensajes getNextEscuchaMensajes();
}
