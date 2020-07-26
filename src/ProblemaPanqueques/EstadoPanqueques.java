package ProblemaPanqueques;

import java.util.ArrayList;
import java.util.Arrays;
import utils.Estado;

public class EstadoPanqueques implements Estado
{

	private final int CANT_PANQ = 4;
	private int heuristica = 0;//Cantidad de panqueques de menor tamaño debajo del más grande
        private int costo = 0;
	private final int[] META = new int[]{ 1, 2, 3, 4};
	private int[] posicionActual;

	public EstadoPanqueques(int[] posiciones)
	{
		posicionActual = posiciones;
		setHeuristica();
	}

	@Override
	public double determinarCosto()
	{
		return 1;
	}

	private void setHeuristica()
	{
            int mayor = 0;
            if(posicionActual[0] != META[0] ){
                mayor = posicionActual[0];
            }
            if(posicionActual[1] != META[1] ){
                if(posicionActual[1]>mayor)
                    mayor = posicionActual[1];
            }
            if(posicionActual[2] != META[2] ){
                if(posicionActual[2]>mayor)
                    mayor = posicionActual[2];
            }
            if(posicionActual[3] != META[3] ){
                if(posicionActual[3]>mayor)
                    mayor = posicionActual[3];
            }            
            heuristica = mayor;
	}

	public int getHeuristica()
	{
		return heuristica;
	}

	private int[] copiarPosiciones(int[] estado)
	{
		int[] resultado = new int[CANT_PANQ];
		for (int i = 0; i < CANT_PANQ; i++)
		{
			resultado[i] = estado[i];
		}
		return resultado;
	}

	@Override
	public ArrayList<Estado> generarSucesores()
	{
		ArrayList<Estado> sucesores = new ArrayList<Estado>();
		
                //giro 2
                voltearPanqueques(2,sucesores);                
                //giro 3
                voltearPanqueques(3,sucesores); 
                //giro 4
                voltearPanqueques(4,sucesores);
               



		return sucesores;
	}

	private void voltearPanqueques(int n, ArrayList<Estado> s)
	{
		int[] copia = copiarPosiciones(posicionActual);
		int[] temp = copiarPosiciones(posicionActual);
                int i,j;
                for( i=n-1, j=0; i >= 0; i--,j++ )
                {
                    temp[i]=copia[j];
                }
		s.add((new EstadoPanqueques(temp)));
	}

	@Override
	public boolean esMeta()
	{
		if (Arrays.equals(posicionActual, META))
		{
			return true;
		}
		return false;
	}

	@Override
	public void mostrarEstado()
	{
		System.out.println(posicionActual[0] + " | " + posicionActual[1] 
                         + " | " + posicionActual[2] + " | " + posicionActual[3]);
	}

	@Override
	public boolean igual(Estado s)
	{
		if (Arrays.equals(posicionActual, ((EstadoPanqueques) s).getPosicionActual()))
		{
			return true;
		}
		else
			return false;

	}

	public int[] getPosicionActual()
	{
		return posicionActual;
	}
        
        @Override
        public double costoCambioEstado(Estado e){
            EstadoPanqueques otro = (EstadoPanqueques)e;
            int tam = this.getPosicionActual().length;
            double costoCambio=0.0;
            for(int i = tam-1; i >= 0; i--){
                if(this.getPosicionActual()[i] != otro.getPosicionActual()[i]){
                    costoCambio = i+1;
                    break;
                }
            }

            return costoCambio;
        }

        @Override
        public void formatearEstado() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
}
