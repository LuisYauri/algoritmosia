package GLCR;
import algoritmosia.Estado;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Estado_GLCR define un estado para el problema clasico del Granjero, Lobo,
 * Cabra, Repollo. Los estados invalidos son almacenados, no generados. Los
 * estados generados se validan con un areglo de estados inválidos, y se
 * remueven del arreglo de sucesores si sin inválidos. Un estado se define
 * con 4 caracteres (encapsulado por el enum Pos) que representa si una entidad
 * particular (en el orden GLCR) esta en el lado oeste o este del rio.
 * 
 */
public class Estado_GLCR implements Estado
{
	// constante para el estado meta
	private final Estado_GLCR.Pos[] META = new Estado_GLCR.Pos[]
	{ Pos.E, Pos.E, Pos.E, Pos.E };


	/*
	 * Todos los estados glcr se definen con un arreglo de 4 elementos del
         * enum Pos, ya sea este u oeste (lado del río), para cada miembro del
         * problema, "Granjero, Lobo, Cabra, Repollo" en ese orden.
	 */
	public enum Pos
	{
		O, E
	};

	// La representación del estado actual
	public Pos[] estadoActual;

	/**
	 * Constructor por defecto
	 */
	public Estado_GLCR()
	{
		estadoActual = new Pos[]
		{ Pos.O, Pos.O, Pos.O, Pos.O };

	}

	/**
	 * Constructor Polifórmico #1
	 * 
	 * @param gPos
	 *            - Posición del Granjero
	 * @param lPos
	 *            - Posición del Lobo
	 * @param cPos
	 *            - Posición de la Cabra
	 * @param rPos
	 *            - Posición del Repollo
	 */
	public Estado_GLCR(Pos gPos, Pos lPos, Pos cPos, Pos rPos)
	{
		estadoActual = new Pos[]
		{ gPos, lPos, cPos, rPos };
	}

	/**
	 * Constructor Polifórmico #2
	 * 
	 * @param ArregloDeEstado
	 *            - Arreglo conteniendo el estado, el cual tiene 4 posiciones
	 */
	public Estado_GLCR(Estado_GLCR.Pos[] ArregloDeEstado)
	{
		estadoActual = new Pos[]
		{ ArregloDeEstado[0], ArregloDeEstado[1], ArregloDeEstado[2], ArregloDeEstado[3] };
	}

	/**
	 * Cuanto cuesta llegar a este estado
	 */
	@Override
	public double determinarCosto()
	{
		return 1;
	}

	/**
	 * Generar todos los posibles sucesores al estado actual.
	 * 
         * Se recortaran los estados que coincidan con una descripción en el
         * arreglo de "estados inválidos".
	 */
	@Override
	public ArrayList<Estado> generarSucesores()
	{
		ArrayList<Estado> sucesores = new ArrayList<Estado>();
		Estado_GLCR.Pos[] estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
		/*
		 * Si el granjero esta en el oeste el no puede ir al oeste
		 */

		// si el granjero esta en el oeste
		if (estadoTemp[0] == Pos.O)
		{
			// debe seleccionar una entidad para llevarlo al este,
                    
                        // si se lleva el repollo al este
			if (estadoTemp[3] == Pos.O)
			{
				estadoTemp[0] = Pos.E;
				estadoTemp[3] = Pos.E;
				sucesores.add(new Estado_GLCR(estadoTemp));
				estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
			}
                        
                        // si se lleva el lobo al este
			if (estadoTemp[1] == Pos.O)
			{
				estadoTemp[0] = Pos.E;
				estadoTemp[1] = Pos.E;
				sucesores.add(new Estado_GLCR(estadoTemp));
				estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
			}
			// si se lleva la cabra al este
			if (estadoTemp[2] == Pos.O)
			{
				estadoTemp[0] = Pos.E;
				estadoTemp[2] = Pos.E;
				sucesores.add(new Estado_GLCR(estadoTemp));
				estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
			}
			
                        // si el granjero pasa solo
			estadoTemp[0] = Pos.E;
			sucesores.add(new Estado_GLCR(estadoTemp));
			estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);

		}
		// si el granjero esta en el este
		else
		{
			// debe seleccionar una entidad para llevarla al oeste
			
                        // lleva el repollo al oeste
			if (estadoTemp[3] == Pos.E)
			{
				estadoTemp[0] = Pos.O;
				estadoTemp[3] = Pos.O;
				sucesores.add(new Estado_GLCR(estadoTemp));
				estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
			}
                        
                        // lleva al lobo al oeste
			if (estadoTemp[1] == Pos.E)
			{
				estadoTemp[0] = Pos.O;
				estadoTemp[1] = Pos.O;
				sucesores.add(new Estado_GLCR(estadoTemp));
				estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
			}
			// lleva la cabra al oeste
			if (estadoTemp[2] == Pos.E)
			{
				estadoTemp[0] = Pos.O;
				estadoTemp[2] = Pos.O;
				sucesores.add(new Estado_GLCR(estadoTemp));
				estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
			}
			
			// si el granjero pasa sólo
			estadoTemp[0] = Pos.O;
			sucesores.add(new Estado_GLCR(estadoTemp));
			estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);

		}
		for (int i = 0; i < sucesores.size(); i++) 
		{
			Estado_GLCR s = (Estado_GLCR) sucesores.get(i);
			estadoTemp = s.estadoActual;
                        // revisa los conflictos, tampoco deja al estado inicial
			if (Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.E, Pos.E, Pos.O, Pos.O })||
                            Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.E, Pos.O, Pos.O, Pos.O })|| 
                            Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.E, Pos.O, Pos.O, Pos.E })|| 
                            Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.O, Pos.E, Pos.E, Pos.O })|| 
                            Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.O, Pos.O, Pos.E, Pos.E })|| 
                            Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.O, Pos.E, Pos.E, Pos.E })||
                            Arrays.equals(estadoTemp, new Estado_GLCR.Pos[]{ Pos.O, Pos.O, Pos.O, Pos.O }))
			{
				sucesores.remove(i);
				i = -1; //reinicia la búsqueda 
			}
		}
		return sucesores;
	}

	/**
	 * Revisa para ver si el estado actual es el estado meta.
	 * 
	 * @return - true o false, dependiendo en si el estado actual corresponde
	 *         con la meta.
	 */
	@Override
	public boolean esMeta()
	{
		if (Arrays.equals(estadoActual, META))
		{
			return true;
		}
		return false;
	}

        @Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		Estado_GLCR otro = (Estado_GLCR) obj;
		if (!estadoActual.equals(otro.estadoActual))
			return false;
		return true;
	}

	/**
	 * Método para imprimir el estado actual. Imprime la posición actual de
         * cada cosa.
	 */
	@Override
	public void mostrarEstado()
	{
		System.out.println(" ( " +estadoActual[0] + " , " + estadoActual[1] + " , "+ estadoActual[2] + " , " + estadoActual[3] + " ) " );
	}

	/**
	 * Método igual sobrecargado para comparar dos estados.
	 * 
	 * @return true o false, dependiendo si los estados son iguales
	 */
	@Override
	public boolean igual(Estado s)
	{
		if (Arrays.equals(estadoActual, ((Estado_GLCR) s).getEstadoActual()))
		{
			return true;
		}
		else
			return false;

	}

	/**
	 * @return el estadoActual
	 */
	public Pos[] getEstadoActual()
	{
		return estadoActual;
	}
}
