package TresEnRayas;
/**
 * Artificial Intelligence A Modern Approach (3rd Ed.): Page 173.<br>
 * 
 * <pre>
 * <code>
 * función BUSQUEDA-ALFA-BETA(estado) devuelve una acción
 *   v = VALOR-MAX(estado, -infinito, +infinito)
 *   devuelve la acción en ACCIONES(estado) con valor v
 *   
 * función VALOR-MAX(estado, alfa, beta) devuelve un valor utilidad
 *   si PRUEBA-TERMINAL(estado) entonces devuelve UTILIDAD(estado)
 *   v = -infinito
 *   para cada a en ACCIONES(estado) hacer
 *     v = MAX(v, VALOR-MIN(RESULTADO(s, a), alfa, beta))
 *     si v >= beta entonces devuelve v
 *     alfa = MAX(alfa, v)
 *   devuelve v
 *   
 * función VALOR-MIN(estado, alfa, beta) devuelve un valor utilidad
 *   si PRUEBA-TERMINAL(estado) entonces devuelve UTILIDAD(estado)
 *   v = infinity
 *   para cada a en ACCIONES(estado) hacer
 *     v = MIN(v, VALOR-MAX(RESULTADO(s,a), alfa, beta))
 *     si v <= alfa entonces devuelve v
 *     beta = MIN(beta, v)
 *   devuelve v
 * </code>
 * </pre>
 * 
 * 
 * @param <ESTADO>
 *            Tipo usado para los estados en el juego.
 * @param <ACCION>
 *            Tipo usado para las acciones en el juego.
 * @param <JUGADOR>
 *            Tipo usado para las acciones en el juego.
 */
public class BusquedaAlfaBeta<ESTADO, ACCION, JUGADOR> implements
		BusquedaAdversarios<ESTADO, ACCION> {

	Juego<ESTADO, ACCION, JUGADOR> juego;
	private int nodosExpandidos;

	/** Crea un nuevo objeto búsqueda para un juego dado.*/
	public static <ESTADO, ACCION, JUGADOR> BusquedaAlfaBeta<ESTADO, ACCION, JUGADOR> 
            crearPara(Juego<ESTADO, ACCION, JUGADOR> juego) {
		return new BusquedaAlfaBeta<ESTADO, ACCION, JUGADOR>(juego);
	}

	public BusquedaAlfaBeta(Juego<ESTADO, ACCION, JUGADOR> juego) {
		this.juego = juego;
	}

	@Override
	public ACCION tomarDecision(ESTADO estado) {
		nodosExpandidos = 0;
		ACCION resultado = null;
		double valorResultado = Double.NEGATIVE_INFINITY;
		JUGADOR jugador = juego.getJugador(estado);
		for (ACCION accion : juego.getAcciones(estado)) {
			double valor = valorMin(juego.getResultado(estado, accion), 
                                jugador,Double.NEGATIVE_INFINITY, 
                                Double.POSITIVE_INFINITY);
			if (valor > valorResultado) {
				resultado = accion;
				valorResultado = valor;
			}
		}
		return resultado;
	}

	public double valorMax(ESTADO estado, JUGADOR jugador, double alfa, double beta) {
		nodosExpandidos++;
		if (juego.esTerminal(estado))
			return juego.getUtilidad(estado, jugador);
		double valor = Double.NEGATIVE_INFINITY;
		for (ACCION accion : juego.getAcciones(estado)) {
			valor = Math.max(valor, valorMin(//
                                    juego.getResultado(estado, accion), 
                                    jugador, alfa, beta));
			if (valor >= beta)
				return valor;
			alfa = Math.max(alfa, valor);
		}
		return valor;
	}

	public double valorMin(ESTADO estado, JUGADOR jugador, double alfa, double beta) {
		nodosExpandidos++;
		if (juego.esTerminal(estado))
			return juego.getUtilidad(estado, jugador);
		double valor = Double.POSITIVE_INFINITY;
		for (ACCION accion : juego.getAcciones(estado)) {
			valor = Math.min(valor, valorMax(
                                juego.getResultado(estado, accion), 
                                jugador, alfa, beta));
			if (valor <= alfa)
				return valor;
			beta = Math.min(beta, valor);
		}
		return valor;
	}

	@Override
	public Metricas getMetricas() {
		Metricas resultado = new Metricas();
		resultado.set("nodosExpandidos", nodosExpandidos);
		return resultado;
	}
}
