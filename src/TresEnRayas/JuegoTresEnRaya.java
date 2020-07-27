package TresEnRayas;
import java.util.List;


/**
 * Provides an implementation of the Tic-tac-toe game which can be used for
 * experiments with the Minimax algorithm.
 * 
 * @author Ruediger Lunde
 * 
 */
public class JuegoTresEnRaya implements Juego<EstadoTresEnRaya, UbicacionXY, String> {

	EstadoTresEnRaya initialState = new EstadoTresEnRaya();

	@Override
	public EstadoTresEnRaya getEstadoInicial() {
		return initialState;
	}

	@Override
	public String[] getJugadores() {
		return new String[] { EstadoTresEnRaya.X, EstadoTresEnRaya.O };
	}

	@Override
	public String getJugador(EstadoTresEnRaya state) {
		return state.getJugadorAMover();
	}

	@Override
	public List<UbicacionXY> getAcciones(EstadoTresEnRaya state) {
		return state.getPosicionesNoMarcadas();
	}

	@Override
	public EstadoTresEnRaya getResultado(EstadoTresEnRaya state, UbicacionXY action) {
		EstadoTresEnRaya result = state.clone();
		result.marcar(action);
		return result;
	}

	@Override
	public boolean esTerminal(EstadoTresEnRaya state) {
		return state.getUtilidad() != -1;
	}

	@Override
	public double getUtilidad(EstadoTresEnRaya state, String player) {
		double result = state.getUtilidad();
		if (result != -1) {
			if (player == EstadoTresEnRaya.O)
				result = 1 - result;
		} else {
			throw new IllegalArgumentException("State is not terminal.");
		}
		return result;
	}
}
