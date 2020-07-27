package gomoku.alfabeta;

/**
 * Esta clase desempeña una búsqueda con poda Alfa-Beta para juegos que 
 * implementan {@link alfabeta.Posicion Posicion}. Poda Alfa-Beta es un
 * algoritmo de búsqueda de adversarios usada comúnmente para el juego
 * de máquinas de dos jugadores (Tres en Raya, Ajedrez, Go, etc.). Este para
 * de evaluar un movimiento completamente cuando al menos se encontró una 
 * posibilidad que prueba que el movimiento será peor que el movimiento 
 * previamente examinado. Tales movimientos ya no necesitan ser evaluados más.
 * Cuando se aplica un árbol estándar minimax, este devuelve el mismo 
 * movimiento como lo hace minimax, pero poda las ramas que no influencien en
 *  la decisión final.
 */
public class AlfaBeta {
	private Posicion juego;

	/**
	 * Inicializa un nueva busqueda {@link alfabeta.AlfaBeta AlfaBeta}.
	 * 
	 * @param juego
	 *            La posición del juego desde la que debería buscar.
	 */
	public AlfaBeta(Posicion juego) {
		this.juego = juego;
	}

	/**
	 * Calcula el mejor movimiento en la posición actual para la profundidad
         * dada.
	 * 
	 * @param profundidad
	 *            el número de plies que serán calculados.
	 * @return el mejor movimiento calculado.
	 */
	public Movimiento analizarProfundidad(int profundidad) {
		AgenteBuscador agente = new AgenteBuscador(juego, profundidad);
		agente.start();
		try {
			agente.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return agente.obtenerMejorMovimiento();
	}

	/**
	 * Calcula el mejor movimiento en la posición actual para la duración
         * dada (profundización iterativa).
	 * 
	 * @param miliSegundos
	 *            después de este tiempo el hilo de búsqueda sera interrumpido.
	 * @return el mejor movimiento calculado.
	 */
	public Movimiento analizar(int miliSegundos) {
		AgenteBuscador agente = new AgenteBuscador(juego);
		agente.start();
		try {
			Thread.sleep(miliSegundos);
			agente.interrupt();
			agente.join();
		} catch (InterruptedException e) {
		}
		return agente.obtenerMejorMovimiento();
	}
}
