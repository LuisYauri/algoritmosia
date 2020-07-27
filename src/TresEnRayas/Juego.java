package TresEnRayas;
import java.util.List;

/**
 * Un juego puede ser formalmente definido como uno de la clase de problemas
 * de búsqueda con los siguientes elementos: <br>
 * <ul>
 * <li>S0: El estado inicial, especifica como está configurado el juego al
 * inicio.</li>
 * <li>JUGADORES(s): Define que jugador tiene el movimiento en un estado.</li>
 * <li>ACCIONES(s): Devuelve el conjunto legal de movidas en un estado.</li>
 * <li>RESULTADO(s, a): Modelo de transición, define el resultado de un movimiento.</li>
 * <li>PRUEBA-TERMINAL(s): Prueba de estado terminal, es verdadero cuando se
 * acabo el juego. Los estados donde los juegos terminan se llaman estado terminal.</li>
 * <li>UTILIDAD(s, p): 
 * Una función de utilidad (también llamada función objetivo o función recompensa),
 * define el valor numérico final para un juego que termina en estado terminal s 
 * para un jugador p. En el ajedrez, el resultado es una victoria, una derrota o
 * un empate, con valores +1, 0 o 1/2. Algunos juegos tienen una variedad más
 * amplia de resultados posibles; las recompensas en el backgammon van desde 0
 * a +192. Un juego de suma cero se define (confusamente) como uno donde la 
 * recompensa total para todos los jugadores es la misma para cada instancia
 * del juego. El ajedrez es suma cero porque cada juego tiene una recompensa de
 * 0 + 1, 1 + 0 o 1/2 + 1/2. "Suma constante" hubiera sido un término mejor, 
 * pero la suma cero es tradicional y tiene sentido si imaginas que a cada 
 * jugador se le cobra una tarifa de entrada de 1/2 para entrar al juego.</li>
 * </ul>
 * 
 * @param <ESTADO>
 *            Tipo que es usado por los estados en el juego.
 * @param <ACCION>
 *            Tipo que es usado por las acciones en el juego.
 * @param <JUGADOR>
 *            Tipo que es usado por los jugadores en el juego.
 */
public interface Juego<ESTADO, ACCION, JUGADOR> {

	ESTADO getEstadoInicial();

	JUGADOR[] getJugadores();

	JUGADOR getJugador(ESTADO estado);

	List<ACCION> getAcciones(ESTADO estado);

	ESTADO getResultado(ESTADO estado, ACCION accion);

	boolean esTerminal(ESTADO estado);

	double getUtilidad(ESTADO estado, JUGADOR jugador);
}
