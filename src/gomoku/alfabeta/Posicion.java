package gomoku.alfabeta;

import java.util.List;

/**
 * Los juegos tienen que implementar esta interfaz
 *
 */
public interface Posicion {
	/**
	 * Obtener una lista de movimientos posibles que pueden jugarse para la
         * posición del juego actual. Si el juego se acabo, esta lista tiene que
         * estar vacía.
	 * 
	 * @return la lista de {@link alfabeta.Movimiento movimientos} validos.
	 */
	public List<Movimiento> getMovimientos();

	/**
	 * Ejecuta el movimiento dado y lo pone en una pila, así este puede
         * deshacer más tarde. Este método no tiene que revisar si el movimiento
	 * 
	 * @param movimiento
	 *            un movimiento valido.
	 */
	public void hacerMovimiento(Movimiento movimiento);

	/**
	 * Obtiene el último movimiento ejecutado desde la pila de movimientos
         * y los deshace. 
	 */
	public void deshacerMovimiento();

	/**
	 * Evalua la posición del juego actual relativa a la vista del jugador
         * quién hizo el primer movimiento en el juego.
	 * 
	 * @return un valor entero que es positivo si el primer jugador está en
         *         ventaja y negativo si el segundo jugador está en ventaja. El
         *         valor debe ser más grande que Integer.MIN_VALUE y más pequeño
         *         que Integer.MAX_VALUE.
	 */
	public int evaluar();

	/**
	 * Devuelve true si el jugador quien hará el próximo movimiento es el 
         * jugador quien tiene una ventaja si la función de evaluación es
         * positiva.
	 * 
	 * @return true si el jugador actual es quien quiere maximizar el valor
         *         evaluado.
	 */
	public boolean actualmenteMaximizando();
}
