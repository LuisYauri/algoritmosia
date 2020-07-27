package TresEnRayas;
/**
 * <pre>
 * <code>
 * función DECISION-MINIMAX(estado) devuelve una acción
 *   devolver argmax_[a en ACCIONES(s)] VALOR-MIN(RESULTADO(estado, a))
 * 
 * función VALOR-MAX(estado) devuelve un valor utilidad
 *   si PRUEBA-TERMINAL(estado) entonces devolver UTILITY(estado)
 *   v = -infinito
 *   para cada a en ACCIONES(estado) hacer
 *     v = MAX(v, VALOR-MIN(RESULTADO(s, a)))
 *   devolver v
 * 
 * función VALOR-MIN(estado) devuelve un valor utilidad
 *   si PRUEBA-TERMINAL(estado) entonces devolver UTILITY(estado)
 *     v = infinito
 *     para cada a en ACCIONES(estado) hacer
 *       v  = MIN(v, VALOR-MAX(RESULTADO(s, a)))
 *   devolver v
 * </code>
 * </pre>
 * 
 * Algoritmo para calcular las decisiones minimax. Devuelve la acción 
 * correspondiente al mejor posible movimiento, esto es, el movimiento que
 * lleva al resultado con la mejor utilidad, bajo la suposición de que el
 * oponente juega para minimizar la utilidad. Las funciones VALOR-MAX y 
 * VALOR-MIN va a través del árbol de juego completo, hasta las hojas, para
 * determinar el valor de un estado en el nivel superior. La notación
 * argmax_[a en S] f(a) calcula el el elemento de un conjunto S que tiene el
 * valor máximo de f(a).
 * 
 * @param <ESTADO>
 *            Tipo que es usado para los estados en el juego.
 * @param <ACCION>
 *            Tipo que es usado para las acciones en el juego.
 * @param <JUGADOR>
 *            Tipo que es usado para los jugadores en el juego.
 */
public class BusquedaMinimax<ESTADO, ACCION, JUGADOR> implements
		BusquedaAdversarios<ESTADO, ACCION> {

	private Juego<ESTADO, ACCION, JUGADOR> juego;
	private int nodosExpandidos;

        /** Crea un nuevo objeto busqueda para un juego dado */
	public static <ESTADO, ACCION, JUGADOR> BusquedaMinimax<ESTADO, ACCION, JUGADOR> 
            createPara(Juego<ESTADO, ACCION, JUGADOR> juego) {
		return new BusquedaMinimax<ESTADO, ACCION, JUGADOR>(juego);
	}

	public BusquedaMinimax(Juego<ESTADO, ACCION, JUGADOR> juego) {
		this.juego = juego;
	}

	@Override
	public ACCION tomarDecision(ESTADO estado) {
		nodosExpandidos = 0;
		ACCION resultado = null;
		double valorResultado = Double.NEGATIVE_INFINITY;
		JUGADOR jugador = juego.getJugador(estado);
		for (ACCION accion : juego.getAcciones(estado)) {
			double valor = valorMin(juego.getResultado(estado, accion), jugador);
			if (valor > valorResultado) {
				resultado = accion;
				valorResultado = valor;
			}
		}
		return resultado;
	}

	public double valorMax(ESTADO estado, JUGADOR jugador) { // devuelve un valor utilidad
		nodosExpandidos++;
		if (juego.esTerminal(estado))
			return juego.getUtilidad(estado, jugador);
		double valor = Double.NEGATIVE_INFINITY;
		for (ACCION accion : juego.getAcciones(estado))
			valor = Math.max(valor,
					valorMin(juego.getResultado(estado, accion), 
                                                jugador));
		return valor;
	}

	public double valorMin(ESTADO estado, JUGADOR jugador) { // devuelve un valor utilidad
		nodosExpandidos++;
		if (juego.esTerminal(estado))
			return juego.getUtilidad(estado, jugador);
		double valor = Double.POSITIVE_INFINITY;
		for (ACCION accion : juego.getAcciones(estado))
			valor = Math.min(valor,
					valorMax(juego.getResultado(estado, accion), 
                                                jugador));
		return valor;
	}

	@Override
	public Metricas getMetricas() {
		Metricas resultado = new Metricas();
		resultado.set("nodosExpandidos", nodosExpandidos);
		return resultado;
	}
}
