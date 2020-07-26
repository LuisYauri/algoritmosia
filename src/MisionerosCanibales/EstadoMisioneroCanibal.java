package MisionerosCanibales;
import utils.Estado;
import java.util.Arrays;
import java.util.ArrayList;

public class EstadoMisioneroCanibal implements Estado {
	private final int CANT_MISIO = 3;
        private final int CANT_CANIB = 3;
	private int heuristica = 0;
        private int costo = 0;
        // Posición META
	private final int[] META = new int[]{ 0, 0, 0};//0 misioneros a la Izq
                                                       //0 canibales a la Izq
                                                       //1 Bote a la Izquierda y 0 Bote a la derecha
                                                       //**Todo a la Izquierda**/
	private int[] posicionActual;

	public EstadoMisioneroCanibal(int[] posiciones) // Posición inicial
	{ //posición 0: cantidad misiciones 
          //posición 1: cantidad de canívales
          //posición 2: posición del bote
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
            heuristica=1;
	}

	public int getHeuristica()
	{
		return heuristica;
	}

	private int[] copiarPosiciones(int[] estado)
	{
		int[] resultado = new int[META.length];
		for (int i = 0; i < META.length; i++)
		{
			resultado[i] = estado[i];
		}
		return resultado;
	}
    
	@Override
	public ArrayList<Estado> generarSucesores()
	{
		ArrayList<Estado> sucesores = new ArrayList<Estado>();
		
                //transportar 1 misionero
                transportar(1,0,sucesores);                
                
                //transportar 1 canibal
                transportar(0,1,sucesores); 
                
                //transportar 2 misioneros
                transportar(2,0,sucesores);                
                
                //transportar 2 canibales
                transportar(0,2,sucesores); 
                
                //transportar 1 misionero y 1 canibal
                transportar(1,1,sucesores);

		return sucesores;
	}
        
        private void transportar(int cantMisio, int cantCanib, ArrayList<Estado> s){
                boolean factible = false;
		int[] temp = copiarPosiciones(posicionActual);
                                
                if(temp[2] == 1){ //Bote está a la izquierda
                    // verificar si hay la cantidad de personajes para realizar el viaje
                    if (cantMisio<=temp[0] && cantCanib<=temp[1]){
                        //disminuyo los valores de la cantidades de personaje 
                        //que se quedan en el lado izquierdo
                        temp[0] = temp[0] - cantMisio;
                        temp[1] = temp[1] - cantCanib;
                        //cambio de lado del bote a la derecha
                        temp[2] = 0;
                        
                        //Verifica que cuando la cantidad de canívales no sea cero
                        if ((temp[1] != 0))
                            //entonces verificar que la cantidad de misioneros 
                            //sea mayor o igual a la cantida dde canívales
                            if(temp[0] >= temp[1])
                                factible = true;
                            else //SI la cantidad de canívales es mayor a 
                            //entonces s factible si la cantidad de 
                            //misioneros es cero entonces solo hay canívales
                                if(temp[0]==0)
                                    factible = true;
                                else
                                    return;
                        else
                            factible = true;

                        //verificación del lado derecho
                        //SI hay caníbales 
                        if (CANT_CANIB-temp[1] != 0)
                            //entonces verificar si cantidad de misioneros es 
                            //mayor o igual a la cantidad de caníbales
                            if(CANT_MISIO-temp[0] >= CANT_CANIB-temp[1])
                                factible = true;
                            else
                            //Solo es factible si la cantidad de misioneros es cero
                                if(CANT_MISIO-temp[0] == 0)
                                    factible = true;
                                else
                                    return;
                        else
                            factible = true;
                        
                        if(factible)
                            s.add(new EstadoMisioneroCanibal(temp));
                    }
                }
                else{ //Bote está a la derecha
                    //Verficiar que si hay la cantidad de personajes para moverlos 
                    if (cantMisio <= CANT_MISIO-temp[0] && cantCanib <= CANT_CANIB-temp[1]){
                        //al transfportar a los de la derecha a la izquierda se suman
                        temp[0] = temp[0] + cantMisio;
                        temp[1] = temp[1] + cantCanib;
                        temp[2] = 1;//cambio de bote a la izquierda         
                        
                        if ((temp[1] != 0)) //si hay caníbales
                            if(temp[0] >= temp[1]) //misioneros mayor o igual a los caníbales
                                factible = true;
                            else
                                if(temp[0]==0) //no hay misioneros, solo caníbales, o nadie
                                    factible = true;
                                else
                                    return;
                        else //si no hay caníbales
                            factible = true;

                        //Verificación del lado izquierdo del río
                        if (CANT_CANIB-temp[1] != 0)
                            // misioneros mayor o igual a acaníbales
                            if(CANT_MISIO-temp[0] >= CANT_CANIB-temp[1])
                                factible = true;
                            else // canibales > a misioneros
                                // solo se permite si no hay misioneros
                                if(CANT_MISIO-temp[0] == 0)
                                    factible = true;
                                else
                                    return;
                        else
                            factible = true;
                        
                        if(factible)
                            s.add(new EstadoMisioneroCanibal(temp));
                    }
                 }
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
                         + " | " + posicionActual[2]);
	}

	@Override
	public boolean igual(Estado s)
	{
		if (Arrays.equals(posicionActual, ((EstadoMisioneroCanibal) s).getPosicionActual()))
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
        
        public double costoCambioEstado(EstadoMisioneroCanibal otro){
            int tam = this.getPosicionActual().length;
            int dif,movidos=0;
            double costoCambio=0.0;
            for(int i = 0; i < tam-1; i++){
                dif = this.getPosicionActual()[i] - otro.getPosicionActual()[i];
                if(dif<0)
                    movidos = movidos + (-1)*dif;
                else
                    movidos = movidos + dif;
            }
            if (movidos == 2)
                costoCambio = 1.0;
            else if (movidos == 1)
                costoCambio = 2.0;
            else
                costoCambio = 0.0;
            
            return costoCambio;
        }    

        @Override
        public double costoCambioEstado(Estado e) {
            EstadoMisioneroCanibal otro = (EstadoMisioneroCanibal)e;
            int tam = this.getPosicionActual().length;
            int dif,movidos=0;
            double costoCambio=0.0;
            for(int i = 0; i < tam-1; i++){
                dif = this.getPosicionActual()[i] - otro.getPosicionActual()[i];
                if(dif<0)
                    movidos = movidos + (-1)*dif;
                else
                    movidos = movidos + dif;
            }
            if (movidos == 2)
                costoCambio = 1.0;
            else if (movidos == 1)
                costoCambio = 2.0;
            else
                costoCambio = 0.0;
            
            return costoCambio;
        }

    @Override
    public void formatearEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
