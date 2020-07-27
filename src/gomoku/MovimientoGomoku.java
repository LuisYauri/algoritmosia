package gomoku;

import gomoku.alfabeta.Movimiento;

public class MovimientoGomoku implements Movimiento {
	private int x;
	private int y;

	public MovimientoGomoku(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object comp) {
		if (comp instanceof MovimientoGomoku) {
			MovimientoGomoku m = (MovimientoGomoku) comp;
			return this.x == m.x && this.y == m.y;
		} else {
			return false;
		}
	}
}
