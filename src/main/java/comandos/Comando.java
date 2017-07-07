package comandos;

import com.google.gson.Gson;
//REVISADO
public class Comando {

	public static final int CONEXION = 0;//
	public static final int CREACIONPJ = 1;//
	public static final int DESCONECTAR = 2;
	public static final int INICIOSESION = 3;//
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;//
	public static final int REGISTRO = 6;//
	public static final int SALIR = 7;//
	public static final int BATALLA = 8;//
	public static final int ATACAR = 9;//
	public static final int FINALIZARBATALLA = 10;//
	public static final int ACTUALIZARPERSONAJE = 11;//
	public static final int COMERCIO = 12;//
	public static final int FINALIZARCOMERCIO = 13;//
	public static final int TRUEQUE = 14;//
	public static final int ENVIARCHAT = 15;

	protected final Gson gson = new Gson();
	protected static  String cadenaLeida;
	protected static String objetoLeido;

	public void setCadena(String cadenaLeida) {
		Comando.cadenaLeida = cadenaLeida;
	}
	
	public void setobjetoLeido(String objetoLeido) {
		Comando.objetoLeido = objetoLeido;
	}
}
