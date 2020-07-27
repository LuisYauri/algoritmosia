package BusquedaPuzle8;

import java.util.ArrayList;
import java.util.Arrays;
import utils.Estado;

/**
 * 
 * EstadoPuzleOcho define un estado para el problema de puzle 8. El tablero se
 * representa por un arreglo unidimensional simple, se intenta crear la ilusión
 * que la representación del estado es bidimensional. En terminos de las 
 * piezas actuales, "0" representa el hueco en el tablero, y "0" tiene un
 * tratamiento especial cuando se generan sucesores. No representamos "0" como
 * una pieza en si misma, es el "hueco" en el tablero.
 * 
 */
public class EstadoPuzleOcho implements Estado
{

	private final int TAM_PUZLE = 9;
	private int fueraDeLugar = 0;
	private int distManh = 0;
	private final int[] META = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 0 };
	private int[] tableroActual;

	/**
	 * Constructor de estado para el tablero del puzle 8
	 * 
	 * @param tablero
	 *            - la representación para el nuevo estado a construir
	 */
	public EstadoPuzleOcho(int[] tablero)
	{
		tableroActual = tablero;
		setFueraDeLugar();
		setDistanciaManh();
	}

	/**
	 * Cuanto cuesta llegar a este estado
	 */
	@Override
	public double determinarCosto()
	{
		return 1;
	}

	/*
	 * Establece la distancia 'piezas fueras de lugar' para el tablero actual
	 */
	private void setFueraDeLugar()
	{
		for (int i = 0; i < tableroActual.length; i++)
		{
			if (tableroActual[i] != META[i])
			{
				fueraDeLugar++;
			}
		}
	}

	/*
	 * Establece la distancia Manhattan para el tablero actual
	 */
	private void setDistanciaManh()
	{
		int indice = -1;

		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 3; x++)
			{
				indice++;

				int val = (tableroActual[indice] - 1);

				if (val != -1)
				{
					int horiz = val % 3;
					int vert = val / 3;

					distManh += Math.abs(vert - (y)) 
                                                + Math.abs(horiz - (x));
				}
			}
		}
	}

	/*
	 * Intenta ubicar "0" en el tablero actual
	 * 
	 * @return el índice del "hueco" (ubicación del 0)
	 */
	private int getHueco()
	{
		// Si devuelve -1, ocurrió un error. El "hueco" debería siempre
                // existir en el tablero y debería siempre ser encontrado para 
                // el bucle. 
		int indiceDelHueco = -1;

		for (int i = 0; i < TAM_PUZLE; i++)
		{
			if (tableroActual[i] == 0)
				indiceDelHueco = i;
		}
		return indiceDelHueco;
	}

	/**
	 * Obtiene las piezas fuera de lugar
	 * 
	 * @return el valor h(n) de las piezas fuera de lugar
	 */
	public int getPiezasFueraDeLugar()
	{
		return fueraDeLugar;
	}

	/**
	 * Obtiene el valor de la Distancia Manhattan
	 * 
	 * @return el valor h(n) para la distancia Manhattan
	 */
	public int getDistanciaManhattan()
	{
		return distManh;
	}

	/*
	 * Hace una copia del arreglo pasado a este
	 */
	private int[] copiarTablero(int[] estado)
	{
		int[] resultado = new int[TAM_PUZLE];
		for (int i = 0; i < TAM_PUZLE; i++)
		{
			resultado[i] = estado[i];
		}
		return resultado;
	}

	/**
	 * Esta pensado en términos de no más 4 operaciones en el hueco. Puede
         * deslizar el hueco en 4 direcciones si está en medio del tablero, 
         * en dos direcciones si el agujero está en una esquina, en tres 
         * direcciones si el agujero está en medio de una fila
	 * 
	 * @return un ArrayList conteniendo todos los sucesores para el estado
	 */
	@Override
	public ArrayList<Estado> generarSucesores()
	{
		ArrayList<Estado> sucesores = new ArrayList<Estado>();
		int hueco = getHueco();

                // trata de generar un estado deslizando una pieza al lado del
                // agujero si se puede caer en el agujero
		if (hueco != 0 && hueco != 3 && hueco != 6)
		{
			/*
			 * podemos deslizar el hueco a la izquierda para generar
                         * un nuevo estado y llevarlo a los sucesores
			 */
			intercambiarYGuardar(hueco - 1, hueco, sucesores);
		}

		// trata de generar un estado deslizando el hueco hacia abajo
		if (hueco != 6 && hueco != 7 && hueco != 8)
		{
			intercambiarYGuardar(hueco + 3, hueco, sucesores);
		}

		// trata de generar un estado deslizando el hueco hacia arriba
		if (hueco != 0 && hueco != 1 && hueco != 2)
		{
			intercambiarYGuardar(hueco - 3, hueco, sucesores);
		}
		// trata de generar un estado deslizando el hueco hacia la derecha
		if (hueco != 2 && hueco != 5 && hueco != 8)
		{
			intercambiarYGuardar(hueco + 1, hueco, sucesores);
		}

		return sucesores;
	}

	/*
	 * Cambia los datos en los índices d1 y d2, en una copia del tablero 
         * actual y crea un nuevo estado basado en este nuevo tablero y lo
         * agrega a s.
	 */
	private void intercambiarYGuardar(int d1, int d2, ArrayList<Estado> s)
	{
		int[] copia = copiarTablero(tableroActual);
		int temp = copia[d1];
		copia[d1] = tableroActual[d2];
		copia[d2] = temp;
		s.add((new EstadoPuzleOcho(copia)));
	}

	/**
	 * Revisa si el estado actual es un estado meta.
	 * 
	 * @return - true o false, depende de si el estado actual coincide con
         *          el estado meta
	 */
	@Override
	public boolean esMeta()
	{
		if (Arrays.equals(tableroActual, META))
		{
			return true;
		}
		return false;
	}

	/**
	 * Método para imprimir el estado actual. Imprime el tablero del puzle.
	 */
	@Override
	public void mostrarEstado()
	{
		System.out.println(tableroActual[0] + " | " + tableroActual[1] 
                        + " | "	+ tableroActual[2]);
		System.out.println("---------");
		System.out.println(tableroActual[3] + " | " + tableroActual[4] 
                        + " | "	+ tableroActual[5]);
		System.out.println("---------");
		System.out.println(tableroActual[6] + " | " + tableroActual[7]
                        + " | "	+ tableroActual[8]);

	}

	/**
	 * Sobrecarga el método igual para comparar dos estados.
	 * 
	 * @return true o false, dependiendo si los estados son iguales
	 */
	@Override
	public boolean igual(Estado s)
	{
		if (Arrays.equals(tableroActual, ((EstadoPuzleOcho) s).getTableroActual()))
		{
			return true;
		}
		else
			return false;

	}

	/**
	 * Obtener el arreglo del tablero actual
	 * 
	 * @return el estado actual
	 */
	public int[] getTableroActual()
	{
		return tableroActual;
	}

    @Override
    public double costoCambioEstado(Estado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeuristica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void formatearEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
