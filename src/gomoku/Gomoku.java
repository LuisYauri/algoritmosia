package gomoku;

import java.util.ArrayList;
import java.util.List;

import gomoku.alfabeta.Movimiento;
import gomoku.alfabeta.Posicion;

public class Gomoku implements Posicion {

	private static final int ANCHO_DE_JUEGO_ESTANDAR = 8;
	private static final int ALTURA_DE_JUEGO_ESTANDAR = 8;
	private static final int LONGITUD_DE_FILA_ESTANDAR = 5;

	private static final int PUNTAJE_FILA = 100000;
	private static final int PUNTAJE_GANADOR = 1000;

	private int anchoJuego;
	private int alturaJuego;
	private int longitudFila;

	private int[][] cuadricula; // puede ser 1, -1 o 0 (vacío)
	private int jugadorActual; // puede ser 1 or -1
	private ArrayList<MovimientoGomoku> historialMovimientos;

	public Gomoku() {
		this(ANCHO_DE_JUEGO_ESTANDAR, ALTURA_DE_JUEGO_ESTANDAR, LONGITUD_DE_FILA_ESTANDAR);
	}

	public Gomoku(int anchoJuego, int alturaJuego, int longitudFila) {
		this.anchoJuego = anchoJuego;
		this.alturaJuego = alturaJuego;
		this.longitudFila = longitudFila;
		cuadricula = new int[anchoJuego][alturaJuego];
		jugadorActual = 1;
		historialMovimientos = new ArrayList<MovimientoGomoku>();
	}

	@Override
	public List<Movimiento> getMovimientos() {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();

		int evaluation = evaluar();
		if (evaluation > PUNTAJE_GANADOR || evaluation < -PUNTAJE_GANADOR) {
			return movimientos; // perdio
		}

		for (int x = 0; x < anchoJuego; x++) {
			for (int y = 0; y < alturaJuego; y++) {
				if (cuadricula[x][y] == 0) {
					movimientos.add(new MovimientoGomoku(x, y));
				}
			}
		}
		return movimientos;
	}

	@Override
	public void hacerMovimiento(Movimiento movimiento) {
		MovimientoGomoku m = (MovimientoGomoku) movimiento;
		cuadricula[m.getX()][m.getY()] = jugadorActual;
		jugadorActual = -jugadorActual;
		historialMovimientos.add(m);
	}

	@Override
	public void deshacerMovimiento() {
		MovimientoGomoku m = historialMovimientos.remove(historialMovimientos.size() - 1);
		cuadricula[m.getX()][m.getY()] = 0;
		jugadorActual = -jugadorActual;
	}

	@Override
	public int evaluar() {
		int puntuacion = 0;

		for (int jugador = -1; jugador <= 1; jugador += 2) {
			// Ganar horizontal
			for (int y = 0; y < alturaJuego; y++) {
				for (int x = 0; x <= anchoJuego - longitudFila; x++) {
					int i = 0;
					while (i < longitudFila && cuadricula[x + i][y] == jugador) {
						i++;
					}
					if (i == longitudFila) {
						puntuacion += jugador * PUNTAJE_FILA;
					} else if ((x + i < anchoJuego && cuadricula[x + i][y] == 0)
							|| (x - 1 > 0 && cuadricula[x - 1][y] == 0)) {
						puntuacion += jugador * i; // también da puntos por una posible
									   // fila
					}
				}
			}
			for (int y = 0; y <= alturaJuego - longitudFila; y++) {
				for (int x = 0; x < anchoJuego; x++) {
					// Ganar vertical
					int i = 0;
					while (i < longitudFila && cuadricula[x][y + i] == jugador) {
						i++;
					}
					if (i == longitudFila) {
						puntuacion += jugador * PUNTAJE_FILA;
					} else if ((y + i < alturaJuego && cuadricula[x][y + i] == 0)
							|| (y - 1 > 0 && cuadricula[x][y - 1] == 0)) {
						puntuacion += jugador * i; // también da puntos por una posible
									   // fila
					}
				}
				for (int x = 0; x <= anchoJuego - longitudFila; x++) {
					// Primer diagonal
					int i = 0;
					while (i < longitudFila && cuadricula[x + i][y + i] == jugador) {
						i++;
					}
					if (i == longitudFila) {
						puntuacion += jugador * PUNTAJE_FILA;
					} else if ((x + i < anchoJuego && y + i < alturaJuego && cuadricula[x
							+ i][y + i] == 0)
							|| (x - 1 > 0 && y - 1 > 0 && cuadricula[x - 1][y - 1] == 0)) {
						puntuacion += jugador * i; // también da puntos por una posible
									   // fila
					}
					// Otra diagonal
					i = 0;
					while (i < longitudFila
							&& cuadricula[x + longitudFila - 1 - i][y + i] == jugador) {
						i++;
					}
					if (i == longitudFila) {
						puntuacion += jugador * PUNTAJE_FILA;
					} else if ((x + longitudFila - 1 - i > 0 && y + 1 < alturaJuego && cuadricula[x
							+ longitudFila - 1 - i][y + i] == 0)
							|| (x + 1 < anchoJuego && y - 1 > 0 && cuadricula[x + 1][y - 1] == 0)) {
						puntuacion += jugador * i; // también da puntos por una posible
									   // fila
					}
				}
			}
		}
		return puntuacion;
	}

	@Override
	public boolean actualmenteMaximizando() {
		return jugadorActual == 1;
	}

	@Override
	public String toString() {
		String ret = "\nTablero:\n    ";
		for (int x = 0; x < anchoJuego; x++) {
			ret += "--";
		}
		ret += "-  FIL:\n";
		for (int y = 0; y < alturaJuego; y++) {
			ret += "   |";
			for (int x = 0; x < anchoJuego; x++) {
				ret += " ";
				if (cuadricula[x][y] == 1) {
					ret += "x";
				} else if (cuadricula[x][y] == -1) {
					ret += "o";
				} else {
					ret += "-";
				}
			}
			ret += " | " + (y + 1) + "\n";
		}
		ret += "    ";
		for (int x = 0; x < anchoJuego; x++) {
			ret += "--";
		}
		ret += "-\nCOL:";
		for (int x = 1; x <= anchoJuego; x++) {
			String colNum = x + "";
			if (colNum.length() < 2) {
				ret += " ";
			}
			ret += colNum;
		}
		ret += "\n";
		return ret;
	}

}