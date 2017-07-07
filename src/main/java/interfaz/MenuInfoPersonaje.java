package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import dominio.Inventario;
import dominio.Objeto;
import dominio.Personaje;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;
//REVISADO
public class MenuInfoPersonaje {

	private static final int anchoPersonaje = 128;
	private static final BufferedImage menu = Recursos.menuEnemigo;
	public static final int menuBatallar = 0;
	public static final int menuInformacion = 1;
	public static final int menuSubirNivel = 2;
	public static final int menuGanarBatalla = 3;
	public static final int menuPerderBatalla = 4;
	public static final int menuInventario = 5;
	public static final int menuMercado = 6;
	public static final int mercadoExitoso = 7;
	public static final int mercadoFallido = 8;

	private static final String[] leyendaBoton = { "Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar",
			"Comerciar", "Aceptar", "Aceptar" };

	private int x;
	private int y;
	private PaquetePersonaje personaje;

	Inventario inventario;

	public MenuInfoPersonaje(int x, int y, PaquetePersonaje personaje) {
		this.x = x;
		this.y = y;
		this.personaje = personaje;
	}

	public void graficar(Graphics g, int tipoMenu) {

		// dibujo el menu
		g.drawImage(menu, x, y, null);

		// dibujo el personaje
		g.drawImage(Recursos.personaje.get(personaje.getRaza()).get(6)[0], x + menu.getWidth() / 2 - anchoPersonaje / 2,
				y + 70, 128, 128, null);

		// muestro el nombre
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", 1, 20));
		Pantalla.centerString(g, new Rectangle(x, y + 15, menu.getWidth(), 0), personaje.getNombre());

		// Grafico la leyenda segun el tipo de menu
		switch (tipoMenu) {
		case menuBatallar:
			graficarMenuInformacion(g);
			break;
		case menuInformacion:
			graficarMenuInformacion(g);
			break;
		case menuSubirNivel:
			graficarMenuSubirNivel(g);
			break;
		case menuGanarBatalla:
			graficarMenuGanarBatalla(g);
			break;
		case menuPerderBatalla:
			graficarMenuPerderBatalla(g);
			break;
		case menuInventario:
			graficarMenuInventario(g);
			break;
		case menuMercado:
			graficarMenuMercado(g);
			break;
		case mercadoExitoso:
			graficarMercadoExitoso(g);
			break;
		case mercadoFallido:
			graficarMercadoFallido(g);
			break;

		}

		// muestro los botones
		g.setFont(new Font("Book Antiqua", 1, 20));
		g.drawImage(Recursos.botonMenu, x + 50, y + 380, 200, 25, null);
		g.setColor(Color.WHITE);
		Pantalla.centerString(g, new Rectangle(x + 50, y + 380, 200, 25), leyendaBoton[tipoMenu]);
	}

	private void graficarMenuPerderBatalla(Graphics g) {

		// Informo que perdio la batalla
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), "!Has sido derrotado!");

		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 250, menu.getWidth(), 0), "!No te rindas! Sigue luchando");
		Pantalla.centerString(g, new Rectangle(x, y + 270, menu.getWidth(), 0), "contra los demás personajes");
		Pantalla.centerString(g, new Rectangle(x, y + 290, menu.getWidth(), 0), "para aumentar tu nivel y");
		Pantalla.centerString(g, new Rectangle(x, y + 310, menu.getWidth(), 0), "mejorar tus atributos.");
	}

	private void graficarMenuGanarBatalla(Graphics g) {

		// Informo que gano la batalla
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), "!Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + 230, menu.getWidth(), 0), "a tu enemigo!");

		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 270, menu.getWidth(), 0), "!Felicitaciones! Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + 290, menu.getWidth(), 0), "a tu oponente, sigue así");
		Pantalla.centerString(g, new Rectangle(x, y + 310, menu.getWidth(), 0), "para lograr subir de nivel");
		Pantalla.centerString(g, new Rectangle(x, y + 330, menu.getWidth(), 0), "y mejorar tus atributos.");

	}

	private void graficarMenuSubirNivel(Graphics g) {

		// Informo que subio de nivel
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), "!Has subido de nivel!");
		g.setFont(new Font("Book Antiqua", 0, 18));
		Pantalla.centerString(g, new Rectangle(x, y + 240, menu.getWidth(), 0), "!Felicitaciones!");
		Pantalla.centerString(g, new Rectangle(x, y + 270, menu.getWidth(), 0), "Nuevo Nivel");
		g.setFont(new Font("Book Antiqua", 1, 62));
		Pantalla.centerString(g, new Rectangle(x, y + 325, menu.getWidth(), 0), String.valueOf(personaje.getNivel()));

	}

	public void graficarMenuInformacion(Graphics g) {

		// muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), personaje.getRaza());
		g.drawString("Casta: ", x + 30, y + 260);
		g.drawString("Nivel: ", x + 30, y + 290);
		g.drawString("Experiencia: ", x + 30, y + 320);

		// muestro los atributos
		g.setFont(new Font("Book Antiqua", 0, 20));
		g.drawString(personaje.getCasta(), x + 100, y + 260);
		g.drawString(personaje.getNivel() + " ", x + 100, y + 290);
		g.drawString(personaje.getExperiencia() + " / " + Personaje.tablaDeNiveles[personaje.getNivel() + 1], x + 150,
				y + 320);

	}

	public boolean clickEnBoton(int mouseX, int mouseY) {
		if (mouseX >= x + 50 && mouseX <= x + 250 && mouseY >= y + 380 && mouseY <= y + 405)
			return true;
		return false;
	}

	public boolean clickEnCerrar(int mouseX, int mouseY) {
		if (mouseX >= x + menu.getWidth() - 24 && mouseX <= x + menu.getWidth() + 4 && mouseY >= y + 12
				&& mouseY <= y + 36)
			return true;
		return false;
	}

	public boolean clickEnMenu(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX <= x + menu.getWidth() && mouseY >= y && mouseY <= y + menu.getHeight())
			return true;
		return false;
	}

	// Muestro el inventario
	private void graficarMenuInventario(Graphics g) {
		g.setFont(new Font("Book Antiqua", 0, 12));
		g.setColor(Color.BLACK);

		Pantalla.centerString(g, new Rectangle(x, y + 185, menu.getWidth(), 0), "!INVENTARIO!");
		Inventario inventario = new Inventario(personaje.getInventario());
		int posicion = 30;

		for (Objeto obj : inventario.getObjetos()) {
			if (obj.getId() > 0) {
				// falta scroll o cambiar ventana
				Pantalla.centerString(g, new Rectangle(x, y + (190 + posicion), menu.getWidth(), 0),
						obj.getNombre() + " - " + obj.getAtributoModificado() + " + " + obj.getValor());
				posicion += 30;

			}
		}
		if (posicion == 30) {
			Pantalla.centerString(g, new Rectangle(x, y + (200 + posicion), menu.getWidth(), 0),
					"No tiene elementos por el momento");
		}
	}

	private void graficarMenuMercado(Graphics g) {
		// muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), personaje.getRaza());
		// muestro los atributos
		g.setFont(new Font("Book Antiqua", 0, 18));

		Inventario inventario = new Inventario(personaje.getInventario());
		if (inventario.getCantidadObjetos() >= 0) {
			Pantalla.centerString(g, new Rectangle(x, y + 250, menu.getWidth(), 0),
					"¿Quiere comerciar con " + personaje.getNombre() + " ?");
			Pantalla.centerString(g, new Rectangle(x, y + 290, menu.getWidth(), 0),
					"Tiene " + inventario.getCantidadObjetos() + " objetos.");
		}

	}

	private void graficarMercadoExitoso(Graphics g) {
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), "!Has estado ");
		Pantalla.centerString(g, new Rectangle(x, y + 230, menu.getWidth(), 0), "comerciando!");

		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 270, menu.getWidth(), 0), "Podras ver en tu mochila");
		Pantalla.centerString(g, new Rectangle(x, y + 290, menu.getWidth(), 0), "los elementos conseguidos");
		Pantalla.centerString(g, new Rectangle(x, y + 310, menu.getWidth(), 0), "sigue asi, para subir de nivel");
		Pantalla.centerString(g, new Rectangle(x, y + 330, menu.getWidth(), 0), "y mejorar tus atributos.");

	}

	private void graficarMercadoFallido(Graphics g) {
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), "No has podido ");
		Pantalla.centerString(g, new Rectangle(x, y + 230, menu.getWidth(), 0), "intercambiar");
		Pantalla.centerString(g, new Rectangle(x, y + 260, menu.getWidth(), 0), "un elemento");
		

	}
}
