package ArbolAlfaBetaSinModificar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import ArbolAlfaBetaSinModificar.Nodo.MinMax;

public class AlfaBeta {
    public HashMap<String, Nodo> arbolDeJuego = new HashMap<String, Nodo>();
    public ArbolDeBusqueda arbol;
    Nodo proxNodoAMover;
    PrintWriter archivo;

    public AlfaBeta() throws FileNotFoundException{
            arbolDeJuego = new ArbolDeBusqueda().obtenerArbol();
            archivo = new PrintWriter("src/ArbolAlfaBeta/salida.txt");
            algoritmoAlfaBeta();
            archivo.close();
    }

    public void algoritmoAlfaBeta(){
            int mejorMovida = (int)valor_max(arbolDeJuego.get("A0"));
            archivo.println("Expansiones completadas");
            System.out.println("Expansiones completadas");
            imprimirValoresAlfaBeta();
            archivo.println("\nValor de Nodo Max A = " + mejorMovida);
            archivo.println("\nMovida que el Nodo Max A debería hacer es " +
                    proxNodoAMover.nombre);
            System.out.println("\nValor de Nodo Max A = " + mejorMovida);
            System.out.println("\nMovida que el Nodo Max A debería hacer es " + 
                    proxNodoAMover.nombre);
    }

    public double valor_max(Nodo nodoActual){	
        if (nodoActual.hijos.size() == 0) // si es nodo hoja
                return nodoActual.valor;
        double max = Double.NEGATIVE_INFINITY;
        archivo.println("Expandiendo Nodo Max " + nodoActual.nombre + 
                ": Alfa = " + nodoActual.alfa + 
                ", Beta: " + nodoActual.beta);
        System.out.println("Expandiendo Nodo Max " + nodoActual.nombre + 
                ": Alfa = " + nodoActual.alfa + 
                ", Beta: " + nodoActual.beta);
        for (Nodo hijo : nodoActual.hijos){
                if (nodoActual.alfa >= nodoActual.beta){
                    archivo.println("Saltando expansión de Nodo " + 
                            hijo.nombre + 
                            " ya que este no puede hacer alfa más alto");
                    System.out.println("Saltando expansión de Nodo " + 
                            hijo.nombre + 
                            " ya que este no puede hacer alfa más alto");
                    if (nodoActual.hijos.indexOf(hijo) == nodoActual.hijos.size())
                        return max;
                    else
                        continue;
                }
                hijo.alfa = nodoActual.alfa;
                hijo.beta = nodoActual.beta;
                double temp = valor_min(hijo);
                if (temp >= max) {
                    max = temp;
                    proxNodoAMover = hijo;
                    if (temp >= nodoActual.alfa)
                        nodoActual.alfa = temp;
                    System.out.println("Nodo Max " + nodoActual.nombre + " actualizado, el valor es ahora: " + max);
                    System.out.println("Alfa: " + nodoActual.alfa + ", Beta: " + nodoActual.beta);
                }
        }
        return max;
    }

    public double valor_min(Nodo nodoActual){
        if (nodoActual.hijos.size() == 0) // si es nodo hoja
                return nodoActual.valor;
        double min = Double.POSITIVE_INFINITY;
        archivo.println("Expandiendo Nodo Min " + nodoActual.nombre +
            ": Alfa = " + nodoActual.alfa + 
            ", Beta: " + nodoActual.beta);
        System.out.println("Expandiendo Nodo Min " + nodoActual.nombre + 
            ": Alfa = " + nodoActual.alfa + 
            ", Beta: " + nodoActual.beta);
        for (Nodo hijo : nodoActual.hijos){
            if (nodoActual.alfa >= nodoActual.beta){
                archivo.println("Saltando la expansión del Nodo " + hijo.nombre 
                        + " ya que este no puede hacer un beta más bajo.");
                System.out.println("Saltando la expansión del Nodo " + hijo.nombre 
                        + " ya que este no puede hacer un beta más bajo.");
                if (nodoActual.hijos.indexOf(hijo) == nodoActual.hijos.size())
                        return min;
                else 
                        continue;
            }
            hijo.alfa = nodoActual.alfa;
            hijo.beta = nodoActual.beta;
            double temp = valor_max(hijo);
            if (temp <= min) {
                min = temp;
                if (temp <= nodoActual.beta)
                        nodoActual.beta = temp;
                System.out.println("Nodo Min " + nodoActual.nombre + " actualizado, el valor es ahora: " + min);
                System.out.println("Alfa: " + nodoActual.alfa + ", Beta: " + nodoActual.beta);
            }
        }
        return min;
    }

    public void imprimirValoresAlfaBeta(){
            archivo.println("\nValores Alfa Beta finales para cada nodo:");
            archivo.println("Nodo A0 - Alfa: " + arbolDeJuego.get("A0").alfa + ", Beta: " + arbolDeJuego.get("A0").beta);
            archivo.println("Nodo B0 - Alfa: " + arbolDeJuego.get("B0").alfa + ", Beta: " + arbolDeJuego.get("B0").beta);
            archivo.println("Nodo B1 - Alfa: " + arbolDeJuego.get("B1").alfa + ", Beta: " + arbolDeJuego.get("B1").beta);
            archivo.println("Nodo C0 - Alfa: " + arbolDeJuego.get("C0").alfa + ", Beta: " + arbolDeJuego.get("C0").beta);
            archivo.println("Nodo C2 - Alfa: " + arbolDeJuego.get("C2").alfa + ", Beta: " + arbolDeJuego.get("C2").beta);
            archivo.println("Nodo C3 - Alfa: " + arbolDeJuego.get("C3").alfa + ", Beta: " + arbolDeJuego.get("C3").beta);
            archivo.println("Nodo C4 - Alfa: " + arbolDeJuego.get("C4").alfa + ", Beta: " + arbolDeJuego.get("C4").beta);
            archivo.println("Nodo D0 - Alfa: " + arbolDeJuego.get("D0").alfa + ", Beta: " + arbolDeJuego.get("D0").beta);
            archivo.println("Nodo D1 - Alfa: " + arbolDeJuego.get("D1").alfa + ", Beta: " + arbolDeJuego.get("D1").beta);
            archivo.println("Nodo D2 - Alfa: " + arbolDeJuego.get("D2").alfa + ", Beta: " + arbolDeJuego.get("D2").beta);
            archivo.println("Nodo D3 - Alfa: " + arbolDeJuego.get("D3").alfa + ", Beta: " + arbolDeJuego.get("D3").beta);
            archivo.println("Nodo D5 - Alfa: " + arbolDeJuego.get("D5").alfa + ", Beta: " + arbolDeJuego.get("D5").beta);
            archivo.println("Nodo D7 - Alfa: " + arbolDeJuego.get("D7").alfa + ", Beta: " + arbolDeJuego.get("D7").beta);
            System.out.println("\nValores Alfa Beta finales para cada nodo:");
            System.out.println("Nodo A0 - Alfa: " + arbolDeJuego.get("A0").alfa + ", Beta: " + arbolDeJuego.get("A0").beta);
            System.out.println("Nodo B0 - Alfa: " + arbolDeJuego.get("B0").alfa + ", Beta: " + arbolDeJuego.get("B0").beta);
            System.out.println("Nodo B1 - Alfa: " + arbolDeJuego.get("B1").alfa + ", Beta: " + arbolDeJuego.get("B1").beta);
            System.out.println("Nodo C0 - Alfa: " + arbolDeJuego.get("C0").alfa + ", Beta: " + arbolDeJuego.get("C0").beta);
            System.out.println("Nodo C2 - Alfa: " + arbolDeJuego.get("C2").alfa + ", Beta: " + arbolDeJuego.get("C2").beta);
            System.out.println("Nodo C3 - Alfa: " + arbolDeJuego.get("C3").alfa + ", Beta: " + arbolDeJuego.get("C3").beta);
            System.out.println("Nodo C4 - Alfa: " + arbolDeJuego.get("C4").alfa + ", Beta: " + arbolDeJuego.get("C4").beta);
            System.out.println("Nodo D0 - Alfa: " + arbolDeJuego.get("D0").alfa + ", Beta: " + arbolDeJuego.get("D0").beta);
            System.out.println("Nodo D1 - Alfa: " + arbolDeJuego.get("D1").alfa + ", Beta: " + arbolDeJuego.get("D1").beta);
            System.out.println("Nodo D2 - Alfa: " + arbolDeJuego.get("D2").alfa + ", Beta: " + arbolDeJuego.get("D2").beta);
            System.out.println("Nodo D3 - Alfa: " + arbolDeJuego.get("D3").alfa + ", Beta: " + arbolDeJuego.get("D3").beta);
            System.out.println("Nodo D5 - Alfa: " + arbolDeJuego.get("D5").alfa + ", Beta: " + arbolDeJuego.get("D5").beta);
            System.out.println("Nodo D7 - Alfa: " + arbolDeJuego.get("D7").alfa + ", Beta: " + arbolDeJuego.get("D7").beta);

    }

    public static void main(String arg[]) throws IOException{
            new AlfaBeta();
    }
}
