package RutaBucarest;

import utils.Estado;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis
 */
public class Estado_Ciudad_Rumania implements Estado{
    
     private final Estado_Ciudad_Rumania.Pos[] META = new Estado_Ciudad_Rumania.Pos[]
	{ Pos.B}; 
    
    
    public String[][] caminos = {{"S","T","Z","0"},{"F","G","P","U"},{"D","P","R","0"},{"C","M","0","0"},{"H","0","0","0"},{"B","S","0","0"},{"B","0","0","0"},{"E","U","0","0"},{"N","V","0","0"},{"M","T","0","0"},{"D","L","0","0"},{"I","0","0","0"},{"S","Z","0","0"},{"B","C","R","0"},{"C","P","S","0"},{"A","F","O","R"},{"A","L","0","0"},{"B","H","V","0"},{"I","U","0","0"},{"A","O","0","0"}};

    @Override
    public double costoCambioEstado(Estado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeuristica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          

    public enum Pos
	{
		A,B,C,D,E,F,G,H,I,L,M,N,O,P,R,S,T,U,V,Z
	};
    
    public Pos[] estadoActual;
    
    public void mostrarCaminos(){
        for(int i=0; i<20;i++){
            for(int j=0;j<4;j++){
                System.out.print(caminos[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public Estado_Ciudad_Rumania()
	{
	estadoActual = new Pos[]
	{ Pos.A};

	}
    
    public Estado_Ciudad_Rumania(Pos ciudad_actual)
        {
	estadoActual = new Pos[]
	{ ciudad_actual};
	
        }
    
    public Estado_Ciudad_Rumania(Estado_Ciudad_Rumania.Pos[] ArregloDeEstado)
	{
	estadoActual = new Pos[]
	{ ArregloDeEstado[0]};
	}
    
    @Override
    public boolean esMeta() {
        if (Arrays.equals(estadoActual, META))
	{
            return true;
	}
            return false;
    }

    @Override
    public ArrayList<Estado> generarSucesores() {
        ArrayList<Estado> sucesores = new ArrayList<Estado>();
        Estado_Ciudad_Rumania.Pos[] estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);
		
        //Para cada ciudad que se obtiene, se agrega al array de sucesores las ciudades a las que se puede llegar.
        if (estadoTemp[0] == Pos.A)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.S;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.T;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.Z;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.B)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.F;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.G;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.P;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.U;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.C)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.D;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.P;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.R;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.D)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.C;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.M;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.E)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.H;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.F)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.B;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.S;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.G)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.B;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.H)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.E;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.U;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.I)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.N;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.V;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.L)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.M;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.T;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.M)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.D;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.L;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.N)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.I;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.O)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.S;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.Z;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.P)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.B;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.C;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.R;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
        }
                
        if (estadoTemp[0] == Pos.R)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.C;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.P;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.S;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.S)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.A;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.F;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.O;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.R;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
        }
                
        if (estadoTemp[0] == Pos.T)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.A;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.L;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.U)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.B;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.H;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.V;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	}
                
        if (estadoTemp[0] == Pos.V)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.I;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.U;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
        } 
                
        if (estadoTemp[0] == Pos.Z)
	{
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.A;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
                    
            estadoTemp[0] = Estado_Ciudad_Rumania.Pos.O;
            sucesores.add(new Estado_Ciudad_Rumania(estadoTemp));
            estadoTemp = Arrays.copyOf(estadoActual, estadoActual.length);// resetear
	} 
                
	return sucesores;
    }

    @Override
    public double determinarCosto() {
        return 1;
    }

    @Override
    public void mostrarEstado() {
        switch(estadoActual[0]){
            case A: System.out.println("Arad");
            break;
            case B: System.out.println("Bucharest");
            break;
            case C: System.out.println("Craiova");
            break;
            case D: System.out.println("Drobeta");
            break;
            case E: System.out.println("Efori");
            break;
            case F: System.out.println("Fagaras");
            break;
            case G: System.out.println("Giurgiu");
            break;
            case H: System.out.println("Hirsova");
            break;
            case I: System.out.println("Iasi");
            break;
            case M: System.out.println("Mehadia");
            break;
            case N: System.out.println("Neamt");
            break;
            case O: System.out.println("Oradea");
            break;
            case P: System.out.println("Pitesti");
            break;
            case R: System.out.println("Rimmicu Vilcea");
            break;
            case S: System.out.println("Sibiu");
            break;
            case T: System.out.println("Timisoara");
            break;
            case U: System.out.println("Urziceni");
            break;
            case V: System.out.println("Vaslui");
            break;
            case Z: System.out.println("Zerind");
            break;
        }
    }

    @Override
    public void formatearEstado() {
        //System.out.println("Ciudad: ("+estadoActual[0]+")");
        switch(estadoActual[0]){
            case A: System.out.println("Arad");
            break;
            case B: System.out.println("Bucharest");
            break;
            case C: System.out.println("Craiova");
            break;
            case D: System.out.println("Drobeta");
            break;
            case E: System.out.println("Efori");
            break;
            case F: System.out.println("Fagaras");
            break;
            case G: System.out.println("Giurgiu");
            break;
            case H: System.out.println("Hirsova");
            break;
            case I: System.out.println("Iasi");
            break;
            case M: System.out.println("Mehadia");
            break;
            case N: System.out.println("Neamt");
            break;
            case O: System.out.println("Oradea");
            break;
            case P: System.out.println("Pitesti");
            break;
            case R: System.out.println("Rimmicu Vilcea");
            break;
            case S: System.out.println("Sibiu");
            break;
            case T: System.out.println("Timisoara");
            break;
            case U: System.out.println("Urziceni");
            break;
            case V: System.out.println("Vaslui");
            break;
            case Z: System.out.println("Zerind");
            break;
        }
    }
    
    @Override
    public boolean igual(Estado s) {
        if (Arrays.equals(estadoActual, ((Estado_Ciudad_Rumania) s).getEstadoActual()))
		{
			return true;
		}
		else
			return false;
    }
    
    public Pos[] getEstadoActual()
    {
	return estadoActual;
    }
    
}
