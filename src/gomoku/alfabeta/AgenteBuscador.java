package gomoku.alfabeta;

import java.util.List;

/**
 * Esta clase se usa por AlfaBeta para ejecutar una búsqueda a través
 * del árbol de juego
 */
public class AgenteBuscador extends Thread {
	private Posicion juego;
	private Movimiento mejorMovimiento;
	private int profundidadMax;

	/**
	 * Initializes a new {@link alfabeta.SearchWorker SearchWorker} thread.
	 * 
	 * @param juego
	 *            The game position that should be searched through.
	 */
	public AgenteBuscador(Posicion juego) {
		this.juego = juego;
		this.profundidadMax = 100;
	}

	/**
	 * Inicializa un nuevo hilo {AgenteBuscador}.
	 * 
	 * @param juego
	 *            La posición del juego desde la que debera ser buscado.
	 * @param profundidadMax
	 *            La profundidad máxima que el AgenteBuscador
	 *            buscará antes de que pare por si mismo.
	 */
	public AgenteBuscador(Posicion juego, int profundidadMax) {
		this.juego = juego;
		this.profundidadMax = profundidadMax;
	}

	/**
	 * Ejecutar la búsqueda con profundización iterativa hasta que el hilo
         * sea interrumpido o la profundidad máxima se alcance (100 por defacto).
	 */
	public void run() {
		mejorMovimiento = null;
		int profundidad = 1;
		do {
			if (juego.actualmenteMaximizando()) {
				inicializarMax(profundidad, Integer.MIN_VALUE, Integer.MAX_VALUE);
			} else {
				inicializarMin(profundidad, Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
			profundidad++;
		} while (!isInterrupted() && profundidad <= profundidadMax);
	}

	/**
	 * Para de calcular si no se ha parado aún y devuelve el movimiento
         * calculado.
	 * 
	 * @return el mejor movimiento calculado.
	 */
	public Movimiento obtenerMejorMovimiento() {
		return mejorMovimiento;
	}

	// Método de maximización para el primer ply. Aquí se puede consumir 
        // más tiempo por cosas innecesarias, ya que este método se llama una
        // sola vez por calculo.
	private int inicializarMax(int profundidad, int alfa, int beta) {

		// Devuelve el puntaje evaluado si es el final.
		if (profundidad == 0) {
			return juego.evaluar();
		}
		List<Movimiento> movimientos = juego.getMovimientos();
		if (movimientos.isEmpty()) {
			return juego.evaluar();
		}
                
                // Coloca el mejor movimiento previamente encontrado al inicio
                // de la lista, para mejorar la velocidad (más podas).
		if (mejorMovimiento != null && movimientos.contains(mejorMovimiento)) {
			movimientos.remove(mejorMovimiento);
			movimientos.add(0, mejorMovimiento);
		}

		// Iterar a través de los movimientos
		for (Movimiento movimiento : movimientos) {
			juego.hacerMovimiento(movimiento);
			int valor = min(profundidad - 1, alfa, beta);
			juego.deshacerMovimiento();
			if (valor > alfa) {
				alfa = valor;
				if (alfa >= beta) {
					break; // poda beta
				}
				mejorMovimiento = movimiento;
			}
			// Interrumpido
			if (isInterrupted()) {
				break;
			}
		}
		return alfa;
	}

	// Método de minimización para el primer ply. Aquí es donde podemos
        // consumir más tiempo para cosas innecesarias, ya que este método
        // se llama una sola vez por calculo.
	private int inicializarMin(int profundidad, int alfa, int beta) {

		// Devuelve el puntaje evaluado si es el final.
		if (profundidad == 0) {
			return juego.evaluar();
		}
		List<Movimiento> movimientos = juego.getMovimientos();
		if (movimientos.isEmpty()) {
			return juego.evaluar();
		}

                // Coloca el mejor movimiento previamente encontrado al inicio
                // de la lista, para mejorar la velocidad (más podas).
		if (mejorMovimiento != null && movimientos.contains(mejorMovimiento)) {
			movimientos.remove(mejorMovimiento);
			movimientos.add(0, mejorMovimiento);
		}

		// Itera a través de los movimientos
		for (Movimiento movimiento : movimientos) {
			juego.hacerMovimiento(movimiento);
			int valor = max(profundidad - 1, alfa, beta);
			juego.deshacerMovimiento();
			if (valor < beta) {
				beta = valor;
				if (beta <= alfa) {
					break; // poda alfa
				}
				mejorMovimiento = movimiento;
			}
			// Interrumpido
			if (isInterrupted()) {
				break;
			}
		}
		return beta;
	}

        // Método de maximización para la mayoria de plys. Este método tiene
        // que trabajar rápido.
	private int max(int profundidad, int alfa, int beta) {

		// Devuelve el puntaje evaluado si es el final.
		if (profundidad == 0) {
			return juego.evaluar();
		}
		List<Movimiento> movimientos = juego.getMovimientos();
		if (movimientos.isEmpty()) {
			return juego.evaluar();
		}

		// Itera a través de los movimientos
		for (Movimiento movimiento : movimientos) {
			juego.hacerMovimiento(movimiento);
			int valor = min(profundidad - 1, alfa, beta);
			juego.deshacerMovimiento();
			if (valor > alfa) {
				alfa = valor;
				if (alfa >= beta) {
					break; // poda beta
				}
			}
		}
		return alfa;
	}

	// Método de minimización para la mayoría de los plies. Este método
        // tiene que trabajar rápido.
	private int min(int profundidad, int alfa, int beta) {

		// Devuelve el puntaje evaluado si es el final.
		if (profundidad == 0) {
			return juego.evaluar();
		}
		List<Movimiento> movimientos = juego.getMovimientos();
		if (movimientos.isEmpty()) {
			return juego.evaluar();
		}

		// Itera a través de los movimientos
		for (Movimiento movimiento : movimientos) {
			juego.hacerMovimiento(movimiento);
			int valor = max(profundidad - 1, alfa, beta);
			juego.deshacerMovimiento();
			if (valor < beta) {
				beta = valor;
				if (beta <= alfa) {
					break; // poda alfa
				}
			}
		}
		return beta;
	}
}
