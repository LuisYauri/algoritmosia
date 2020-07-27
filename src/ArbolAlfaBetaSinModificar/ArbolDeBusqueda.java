package ArbolAlfaBetaSinModificar;

import java.util.HashMap;

import ArbolAlfaBetaSinModificar.Nodo.MinMax;

public class ArbolDeBusqueda {
	public HashMap<String, Nodo> arbolDeJuego = new HashMap<String, Nodo>();

	public ArbolDeBusqueda(){
		// Profundidad: 0, MAX
		Nodo A0 = new Nodo("A0", MinMax.MAX);
		// Profundidad: 1, MIN
		Nodo B0 = new Nodo("B0", MinMax.MIN);
		Nodo B1 = new Nodo("B1", MinMax.MIN);
		// Profundidad: 2, MAX (excepto un nodo hoja)
		Nodo C0 = new Nodo("C0", MinMax.MAX);
		Nodo C1 = new Nodo("C1", 5); // hoja
		Nodo C2 = new Nodo("C2", MinMax.MAX);
		Nodo C3 = new Nodo("C3", MinMax.MAX);
		Nodo C4 = new Nodo("C4", MinMax.MAX);
		// Profundidad: 3, MIN (excepto dos nodos hojas)
		Nodo D0 = new Nodo("D0", MinMax.MIN);
		Nodo D1 = new Nodo("D1", MinMax.MIN);
		Nodo D2 = new Nodo("D2", MinMax.MIN);
		Nodo D3 = new Nodo("D3", MinMax.MIN);
		Nodo D4 = new Nodo("D4", MinMax.MIN);
		Nodo D5 = new Nodo("D5", MinMax.MIN);
		Nodo D6 = new Nodo("D6", -3); // hoja
		Nodo D7 = new Nodo("D7", MinMax.MIN);
		Nodo D8 = new Nodo("D8", MinMax.MIN);
		Nodo D9 = new Nodo("D9", 8); // hoja
		// Profundidad: 4, Nodos Hoja
		Nodo E0 = new Nodo("E0", 3);
		Nodo E1 = new Nodo("E1", -2);
		Nodo E2 = new Nodo("E2", 1);
		Nodo E3 = new Nodo("E3", -6);
		Nodo E4 = new Nodo("E4", 1);
		Nodo E5 = new Nodo("E5", -5);
		Nodo E6 = new Nodo("E6", 4);
		Nodo E7 = new Nodo("E7", 2);
		Nodo E8 = new Nodo("E8", 0);
		Nodo E9 = new Nodo("E9", 7);
		Nodo E10 = new Nodo("E10", 2);
		Nodo E11 = new Nodo("E11", 3);
		Nodo E12 = new Nodo("E12", 6);
		Nodo E13 = new Nodo("E13", 1);
		Nodo E14 = new Nodo("E14", 2);

		// Hacer conecciones
		A0.agregarHijo(B0); A0.agregarHijo(B1);
		B0.agregarHijo(C0); B0.agregarHijo(C1); B0.agregarHijo(C2);
		B1.agregarHijo(C3); B1.agregarHijo(C4);
		C0.agregarHijo(D0); C0.agregarHijo(D1); C0.agregarHijo(D2);
		C2.agregarHijo(D3); C2.agregarHijo(D4);
		C3.agregarHijo(D5); C3.agregarHijo(D6);
		C4.agregarHijo(D7); C4.agregarHijo(D8); C4.agregarHijo(D9);
		D0.agregarHijo(E0); D0.agregarHijo(E1); D0.agregarHijo(E2);
		D1.agregarHijo(E3);
		D2.agregarHijo(E4); D2.agregarHijo(E5);
		D3.agregarHijo(E6);
		D4.agregarHijo(E7); D4.agregarHijo(E8);
		D5.agregarHijo(E9); D5.agregarHijo(E10);
		D7.agregarHijo(E11);
		D8.agregarHijo(E12); D8.agregarHijo(E13); D8.agregarHijo(E14);

		arbolDeJuego.put("A0", A0);
		arbolDeJuego.put("B0", B0); arbolDeJuego.put("B1", B1);
		arbolDeJuego.put("C0", C0); arbolDeJuego.put("C1", C1); arbolDeJuego.put("C2", C2);
		arbolDeJuego.put("C3", C3); arbolDeJuego.put("C4", C4);
		arbolDeJuego.put("D0", D0); arbolDeJuego.put("D1", D1); arbolDeJuego.put("D2", D2);
		arbolDeJuego.put("D3", D3); arbolDeJuego.put("D4", D4); arbolDeJuego.put("D5", D5);
		arbolDeJuego.put("D6", D6); arbolDeJuego.put("D7", D7); arbolDeJuego.put("D8", D8);
		arbolDeJuego.put("D9", D9); arbolDeJuego.put("E0", E0); arbolDeJuego.put("E1", E1);
		arbolDeJuego.put("E2", E2); arbolDeJuego.put("E3", E3); arbolDeJuego.put("E4", E4);
		arbolDeJuego.put("E5", E5); arbolDeJuego.put("E6", E6); arbolDeJuego.put("E7", E7);
		arbolDeJuego.put("E8", E8); arbolDeJuego.put("E9", E9); arbolDeJuego.put("E10", E10);
		arbolDeJuego.put("E11", E11); arbolDeJuego.put("E12", E12); arbolDeJuego.put("E13", E13);
		arbolDeJuego.put("E14", E14);
	}

	public HashMap<String, Nodo> obtenerArbol(){
		return arbolDeJuego;
	}
}
