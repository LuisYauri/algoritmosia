package ArbolAlfaBetaSinModificar;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	public int profundidad;
	public Nodo padre;
	public List<Nodo> hijos = new ArrayList<Nodo>();
	
	public MinMax minmax;
	public double alfa;
	public double beta;
	public double valor;
	public String nombre;
	
	public enum MinMax {MIN, MAX};
	
	// Nodos No-Hoja
	public Nodo(String nombre, MinMax mm){
		profundidad = 0;
		this.nombre = nombre;
		alfa = Double.NEGATIVE_INFINITY;
		beta = Double.POSITIVE_INFINITY;
		minmax = mm;
	}
	
	// Nodos Hoja
	public Nodo(String nombre, int valor){
		this.nombre = nombre;
		this.valor = valor;
	}
		
	public void agregarHijo(Nodo hijo){
		hijos.add(hijo);
		hijo.padre = this;
		hijo.profundidad = this.profundidad + 1;
	}
	
	public String alfaBetaToString(){
		return "Alfa= " + alfa + "Beta= " + beta;
	}
	
}
