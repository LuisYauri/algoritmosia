package algoritmos;

import utils.ListaOrdenadaSE;
import utils.NodoDeBusqueda;
import utils.Estado;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Define una búsqueda BFS en un puzle validado. 
 */
public class BusquedaCostoUniforme
{
    /**
     * Función de inicialización para búsqueda BFS en un puzle 8.
     * 
     * @param ubicacion
     *            - El estado inicial, representado como un arreglo lineal
     * @param d
     *            true para mostrar nodos examinados  
     */
    public static void buscar(boolean d, Estado e)
    {
        NodoDeBusqueda raiz = new NodoDeBusqueda(e);
        
        ListaOrdenadaSE<NodoDeBusqueda> cola = new ListaOrdenadaSE<NodoDeBusqueda>();

        cola.insertar(raiz);

        realizarBusqueda(cola, d);
    }

    /*
     * Método de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Devuelve true si es así, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusqueda n)
    {
        boolean resultado = false;
        NodoDeBusqueda nodoARevisar = n;

        // Mientras el padre de n no sea null, revisar si este es igual
        // al nodo que estamos buscando.
        while (n.getPadre() != null && !resultado)
        {
                if (n.getPadre().getEstadoActual().igual(nodoARevisar.getEstadoActual()))
                {
                        resultado = true;
                }
                n = n.getPadre();
        }

        return resultado;
    }

    /**
     * Realiza una busqueda BFS usando cola como el espacio a buscar
     * 
     * @param cola
     *            - La cola NodoDeBusqueda a ser buscada.
     * @param d
     *            true para mostrar nodos examinados
     */
    public static void realizarBusqueda( ListaOrdenadaSE<NodoDeBusqueda> cola, boolean d)
    {
        int contBusqueda = 1; // contador para el número de iteraciones

        while (!cola.estaVacia()) // mientras la cola no este vacía
        {
            System.out.print("Sale de Cola:");
            NodoDeBusqueda nodoTemp = (NodoDeBusqueda) cola.eliminarAlInicio();
            System.out.print("( costo = " + nodoTemp.getCosto()+")  " ); 
            nodoTemp.getEstadoActual().mostrarEstado();
            
            if (!nodoTemp.getEstadoActual().esMeta()) // si nodoTemp no es una meta
            {
                // generar sucesores inmediatos a nodoTemp
                ArrayList<Estado> sucesoresTemp = nodoTemp.getEstadoActual().generarSucesores(); 

                /*
                 * Recorrer los sucesores, envolverlos en un NodoDeBusqueda, 
                 * comprobar si ya han sido evaluados, y si no, agregarlos a
                 * la cola
                 */
                for (int i = 0; i < sucesoresTemp.size(); i++)
                {
                    // el segundo parametro aquí agrega el costo del nuevo
                    // nodo al costo actual total en el NodoDeBusqueda
                    NodoDeBusqueda nuevoNodo = new NodoDeBusqueda(nodoTemp,
                        sucesoresTemp.get(i), 
                        nodoTemp.getCosto()+
                           (nodoTemp.getEstadoActual()).
                                   costoCambioEstado(sucesoresTemp.get(i)),
                        (sucesoresTemp.get(i)).getHeuristica());

                    if (!revisarRepetidos(nuevoNodo))
                    {
                            cola.insertar(nuevoNodo);
                    }
                }
                contBusqueda++;
            }
            else
            // El estado meta ha sido encontrado. Mostrar el camino para llegar
            // a este
            {
                // Use una pila para rastrear el camino desde el estado inicial
                // hasta el estado meta.
                Stack<NodoDeBusqueda> caminoSolucion = new Stack<NodoDeBusqueda>();
                caminoSolucion.push(nodoTemp);
                nodoTemp = nodoTemp.getPadre();

                while (nodoTemp.getPadre() != null)
                {
                        caminoSolucion.push(nodoTemp);
                        nodoTemp = nodoTemp.getPadre();
                }
                caminoSolucion.push(nodoTemp);

                // El tamaño de la pila antes de vaciarla.
                int iteraciones = caminoSolucion.size();

                for (int i = 0; i < iteraciones; i++)
                {
                        nodoTemp = caminoSolucion.pop();
                        nodoTemp.getEstadoActual().mostrarEstado();
                        System.out.println();
                        System.out.println();
                }
                System.out.println("El costo fue: " + nodoTemp.getCosto());
                if (d)
                {
                        System.out.println("Número de nodos examinados: "
                                        + contBusqueda);
                }

                System.exit(0);
            }
        }

        // Esto no debería ocurrir.
        System.out.println("!Error! No se encontró una solución!");
    }
    

}
