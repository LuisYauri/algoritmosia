package TresEnRayas;
/**
 * Variante de la interfaz Busqueda. Ya que los jugadores solo pueden
 * controlar el próximo movimiento, el método <code>tomarDecision</code>
 * devuelve solamente una acción, no una secuencia de acciones.
 */
public interface BusquedaAdversarios<ESTADO, ACCION> {

	/** Devuelve la acción que parece ser la mejor al estado dado. */
	ACCION tomarDecision(ESTADO estado);

	/**
	 * Devuelve todas  las métricas de la búsqueda
	 * 
	 * @return todas las métricas de la búsqueda.
	 */
	Metricas getMetricas();
}
