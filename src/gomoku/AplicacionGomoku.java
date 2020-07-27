package gomoku;

import java.util.List;
import java.util.Scanner;

import gomoku.alfabeta.Movimiento;
import gomoku.alfabeta.AlfaBeta;

public class AplicacionGomoku {

	private Scanner scanner;
	private int nivel;
	private Gomoku juego;

	public AplicacionGomoku() {
		scanner = new Scanner(System.in);
		boolean comenzarComputadora = Math.random() < 0.5;
		boolean continuarJugando;
		boolean computadoraVsComputadora;

		do {
			comenzarComputadora = !comenzarComputadora;

			// Seleccionar nivel
			System.out
					.print("Bienvenido a Gomoku/TresEnRraya!\nPor favor seleccione un nivel (1-20): ");
			do {
				nivel = scanner.nextInt();
				if (nivel < 1 || nivel > 20) {
					System.out.println("Nivel Inválido!");
				}
			} while (nivel < 1 || nivel > 20);
			System.out.println("Nivel Seleccionado " + nivel + ".");

			// Selecccionar juego
			System.out.print("Quiere jugar Gomoku o TresEnRaya (g/t)? ");
			boolean juegoElegido = false;
			do {
				String str;
				str = scanner.next();
				if (str.toLowerCase().startsWith("g")) {
					juego = new Gomoku();
					juegoElegido = true;
					System.out.println("Eligió Gomoku. El primer jugador que obtenga una fila de cinco (horizontal, diagonal o vertical) gana el juego.");
				} else if (str.toLowerCase().startsWith("t")) {
					juego = new TresEnRaya();
					juegoElegido = true;
					System.out.println("Eligió TresEnRaya. El primer jugador que obtenga una fila de tres (horizontal, diagonal o vertical) gana el juego.");
				} else {
					System.out.println("No valido! Por favor conteste con 'g' o 't'!");
				}
			} while (!juegoElegido);

			// Computadora vs computadora?
			System.out.print("Jugara la computadora consigo misma (si/no)? ");
			String r = scanner.next();
			computadoraVsComputadora = r.toLowerCase().startsWith("s");

			// Anuncia quien comienza.
			if (!comenzarComputadora) {
				System.out.println("Usted comienza!");
			} else {
				System.out.println("La computadora comienza!");
			}

			// bucle de juego
			while (!juego.getMovimientos().isEmpty()) {
				System.out.println(juego.toString());
				if (!computadoraVsComputadora
						&& (comenzarComputadora ^ juego.actualmenteMaximizando())) {
					hacerMovimientoUsuario();
				} else {
					hacerMovimientoComputadora();
				}
			}

			// Final del juego
			System.out.println(juego.toString());
			System.out.println("Juego Terminado!");
			System.out.print("Quiere jugar otra vez (si/no)? ");

			String response = scanner.next();
			continuarJugando = response.toLowerCase().startsWith("s");
		} while (continuarJugando);

		scanner.close();
	}

	private void hacerMovimientoUsuario() {
		System.out.println("Es su turno! Ingrese su movimiento.");

		List<Movimiento> movidasValidas = juego.getMovimientos();
		MovimientoGomoku movimiento;
		do {
			System.out.print("Número de Columna: ");
			int col = scanner.nextInt() - 1;
			System.out.print("Número de Fila:    ");
			int fil = scanner.nextInt() - 1;
			movimiento = new MovimientoGomoku(col, fil);
			if (!movidasValidas.contains(movimiento)) {
				System.out.println("Movida Inválida! Intente nuevamente.");
			}
		} while (!movidasValidas.contains(movimiento));
		System.out.println("Bien, su movimiento será ejecutado!");
		juego.hacerMovimiento(movimiento);

	}

	private void hacerMovimientoComputadora() {
		System.out.println("Ahora la computadora esta pensando...");

		// nivel=número de segundos para pensar
		AlfaBeta busqueda = new AlfaBeta(juego);
		Movimiento mejorMovimiento = busqueda.analizar(nivel * 1000);
		juego.hacerMovimiento(mejorMovimiento);

		System.out.println("La computadora movió:");

		// Relax
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		new AplicacionGomoku();
	}

}
