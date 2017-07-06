package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


import dominio.Objeto;
import dominio.Personaje;
import juego.Pantalla;
import recursos.Recursos;

public class MenuComercio {


		private static final int x = 280;
		private static final int y = 100;
		private static final int anchoBoton = 150;
		private static final int altoBoton = 25;
		private static final int[][] botones = {{ 350, 480}};
		private boolean habilitado;
		private Personaje personaje;

		public MenuComercio(boolean habilitado, Personaje personaje) {
			this.habilitado = habilitado;
			this.personaje = personaje;
		}

		public void graficar(Graphics g) {

			if (habilitado){
				g.drawImage(Recursos.menuComercio, x, y, null);

			}else
				g.drawImage(Recursos.menuComercioDeshabilitado, x, y, null);

			//listo inventarios
//			Pantalla.centerString(g, new Rectangle(280, 170 , Recursos.menuComercio.getWidth(), 0), 
//					"Id - Nombre -> Atributo  +  Valor");
			
			int posicion = 0;
			for (Objeto obj : personaje.getInventario().getObjetos()) {
				if(obj.getId() > 0){
					Pantalla.centerString(g, new Rectangle(280, (200 + posicion), Recursos.menuComercio.getWidth(), 0),
							obj.getId()+" - "+ obj.getNombre() + " -> " + obj.getAtributoModificado()+ " + " + obj.getAtributo());
					posicion += 20;
				}
			}

			
			// Dibujo los botones
			g.drawImage(Recursos.botonMenu, botones[0][0], botones[0][1], anchoBoton, altoBoton, null);


			// Dibujo las leyendas
			g.setColor(Color.WHITE);
			g.setFont(new Font("Book Antiqua", 1, 14));
			g.drawString("Haga Click Aquí", botones[0][0]+15, botones[0][1]+15);

			// Dibujo el turno de quien es
			g.setFont(new Font("Book Antiqua", 1, 18));
			
			if (habilitado)
				Pantalla.centerString(g, new Rectangle(x, y + 15, Recursos.menuComercio.getWidth(), 0), "Mi Turno");
			else
				Pantalla.centerString(g, new Rectangle(x, y + 15, Recursos.menuComercioDeshabilitado.getWidth(), 0), "Turno Rival");

		}

		public int getObjetoClickeado(int mouseX, int mouseY) {
			if (!habilitado)
				return 0;
			for (int i = 0; i < botones.length; i++) {
				if (mouseX >= botones[i][0] && mouseX <= botones[i][0] + anchoBoton && mouseY >= botones[i][1]
						&& mouseY <= botones[i][1] + anchoBoton)
					return i + 1;
			}
			return 0;
		}

		public boolean clickEnMenu(int mouseX, int mouseY) {
			if (mouseX >= x && mouseX <= x + Recursos.menuComercio.getWidth() && mouseY >= y
					&& mouseY <= y + Recursos.menuComercio.getHeight())
				return habilitado;
			return false;
		}

		public void setHabilitado(boolean b) {
			habilitado = b;
		}

}
