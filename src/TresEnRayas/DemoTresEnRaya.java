package TresEnRayas;
/**
 * Aplica la búsqueda Minimax y la poda alfa-beta para encontrar movimientos
 * para el juego tres en raya.
 * 
 */
public class DemoTresEnRaya {
	public static void main(String[] args) {
		System.out.println("DEMO TRES EN RAYA");
		System.out.println("");
		iniciarDemoMinimax();
		//iniciarDemoAlfaBeta();
	}

	private static void iniciarDemoMinimax() {
		System.out.println("DEMO MINIMAX\n");
		JuegoTresEnRaya juego = new JuegoTresEnRaya();
		EstadoTresEnRaya estadoActual = juego.getEstadoInicial();
		BusquedaAdversarios<EstadoTresEnRaya, UbicacionXY> busqueda = 
                        BusquedaMinimax.createPara(juego);
		while (!(juego.esTerminal(estadoActual))) {
			System.out.println(juego.getJugador(estadoActual) + "  jugando ... ");
			UbicacionXY accion = busqueda.tomarDecision(estadoActual);
			estadoActual = juego.getResultado(estadoActual, accion);
			System.out.println(estadoActual);
		}
		System.out.println("demo MINI MAX ejecutado");
	}

	private static void iniciarDemoAlfaBeta() {
		System.out.println("DEMO ALFA BETA\n");
		JuegoTresEnRaya juego = new JuegoTresEnRaya();
		EstadoTresEnRaya estadoActual = juego.getEstadoInicial();
		BusquedaAdversarios<EstadoTresEnRaya, UbicacionXY> busqueda = 
                        BusquedaAlfaBeta.crearPara(juego);
		while (!(juego.esTerminal(estadoActual))) {
			System.out.println(juego.getJugador(estadoActual) + "  jugando ... ");
			UbicacionXY accion = busqueda.tomarDecision(estadoActual);
			estadoActual = juego.getResultado(estadoActual, accion);
			System.out.println(estadoActual);
		}
		System.out.println("DEMO ALFA BETA ejecutado");
	}
}
