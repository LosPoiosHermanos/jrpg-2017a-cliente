package comandos;

import java.io.IOException;

public class ComandoTrueque extends NextEscuchaMensajes {
	private NextEscuchaMensajes nextEscuchaMensajes;

	@Override
	public void setNextEscuchaMensajes(NextEscuchaMensajes probador) {
		nextEscuchaMensajes = probador;
	}

	@Override
	public void solicitudDelComando(int comando) throws IOException {
		if (Comando.TRUEQUE == comando) {
//			paqueteTrueque = (PaqueteTrueque) gson.fromJson(objetoLeido, PaqueteTrueque.class);
//			//envio el que quiere intercambiar
//			if(paqueteTrueque.getNuevoObjeto() == 0  && paqueteTrueque.getNuevoObjetoEnemigo() != 0){	
//				juego.getEstadoComercio().setMiTurno(true);
//				juego.getEstadoComercio().mostrarTrueque(paqueteTrueque.getNuevoObjetoEnemigo());
//				
//			}else{//envia respuesta
//				if(paqueteTrueque.getNuevoObjeto() > 0  && paqueteTrueque.getNuevoObjetoEnemigo() > 0 ){	
//					juego.getEstadoComercio().setMiTurno(true);
//					juego.getEstadoComercio().mostrarTrueque( paqueteTrueque.getNuevoObjeto(), paqueteTrueque.getNuevoObjetoEnemigo());
//				}
//			}
			
		} else {
			nextEscuchaMensajes.solicitudDelComando(comando);
		}

	}

	@Override
	public NextEscuchaMensajes getNextEscuchaMensajes() {
		return nextEscuchaMensajes;
	}

}
