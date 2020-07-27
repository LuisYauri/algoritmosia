package TresEnRayas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Un estado del juego Tres en Raya es caracterizado por un tablero
 * conteniendo símbolos X y O, el próximo jugador a mover y una información
 * de utilidad
 */
public class EstadoTresEnRaya implements Cloneable {
	public static final String O = "O";
	public static final String X = "X";
	public static final String VACIO = "-";
	//
	private String[] tablero = new String[] { VACIO, VACIO, VACIO, VACIO,
            VACIO, VACIO, VACIO, VACIO, VACIO };

	private String jugadorAMover = X;
	private double utilidad = -1; // 1: gana X, 0: gana O, 0.5: empate

	public String getJugadorAMover() {
		return jugadorAMover;
	}
	
	public boolean estaVacio(int col, int fil) {
		return tablero[getPosicionAbs(col, fil)] == VACIO;
	}

	public String getValor(int col, int fil) {
		return tablero[getPosicionAbs(col, fil)];
	}

	public double getUtilidad() {
		return utilidad;
	}

	public void marcar(UbicacionXY accion) {
		marcar(accion.getCoordenadaX(), accion.getCoordenadaY());
	}

	public void marcar(int col, int fil) {
		if (utilidad == -1 && getValor(col, fil) == VACIO) {
			tablero[getPosicionAbs(col, fil)] = jugadorAMover;
			analizarUtilidad();
			jugadorAMover = (jugadorAMover == X ? O : X);
		}
	}

	private void analizarUtilidad() {
		if (lineaCompletaTab()) {
			utilidad = (jugadorAMover == X ? 1 : 0);
		} else if (getNumeroPosicionesMarcadas() == 9) {
			utilidad = 0.5;
		}
	}

	public boolean lineaCompletaTab() {
		return (algunaFilaCompleta() || algunaColumnaCompleta() || 
                        algunaDiagonalCompleta());
	}
	
	private boolean algunaFilaCompleta() {
		for (int fil = 0; fil < 3; fil++) {
			String val = getValor(0, fil);
			if (val != VACIO && val == getValor(1, fil) && 
                                val == getValor(2, fil)) {
				return true;
			}
		}
		return false;
	}

	private boolean algunaColumnaCompleta() {
		for (int col = 0; col < 3; col++) {
			String val = getValor(col, 0);
			if (val != VACIO && val == getValor(col, 1) && 
                                val == getValor(col, 2)) {
				return true;
			}
		}
		return false;
	}

	private boolean algunaDiagonalCompleta() {
		boolean retVal = false;
		String val = getValor(0, 0);
		if (val != VACIO && val == getValor(1, 1) && val == getValor(2, 2)) {
			return true;
		}
		val = getValor(0, 2);
		if (val != VACIO && val == getValor(1, 1) && val == getValor(2, 0)) {
			return true;
		}
		return retVal;
	}

	public int getNumeroPosicionesMarcadas() {
		int retVal = 0;
		for (int col = 0; col < 3; col++) {
			for (int fil = 0; fil < 3; fil++) {
				if (!(estaVacio(col, fil))) {
					retVal++;
				}
			}
		}
		return retVal;
	}

	public List<UbicacionXY> getPosicionesNoMarcadas() {
		List<UbicacionXY> resultado = new ArrayList<UbicacionXY>();
		for (int col = 0; col < 3; col++) {
			for (int fil = 0; fil < 3; fil++) {
				if (estaVacio(col, fil)) {
					resultado.add(new UbicacionXY(col, fil));
				}
			}
		}
		return resultado;
	}

	@Override
	public EstadoTresEnRaya clone() {
		EstadoTresEnRaya copia = null;
		try {
			copia = (EstadoTresEnRaya) super.clone();
			copia.tablero = Arrays.copyOf(tablero, tablero.length);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); // nunca debería suceder...
		}
		return copia;
	}

	@Override
	public boolean equals(Object unObj) {
		if (unObj != null && unObj.getClass() == getClass()) {
			EstadoTresEnRaya otroEstado = (EstadoTresEnRaya) unObj;
			for (int i = 0; i < 9; i++) {
				if (tablero[i] != otroEstado.tablero[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		//Necsario para asegurar que objetos iguales tengan códigos
                //hash equivalentes
		return toString().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for (int fil = 0; fil < 3; fil++) {
			for (int col = 0; col < 3; col++) {
				strBuilder.append(getValor(col, fil) + " ");
			}
			strBuilder.append("\n");
		}
		return strBuilder.toString();
	}

	//
	// MÉTODOS PRIVADOS
	//

	private int getPosicionAbs(int col, int fil) {
		return fil * 3 + col;
	}
}
