
import algoritmosia.Estado;
import algoritmosia.NodoDeBusqueda;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Define una búsqueda BFS.
 */
public class BusquedaBFS {

    /**
     * Función de inicialización para el problema de los canibales.
     *
     * @param ubicaciones - El estado inicial representando el arreglo con la
     * cantidad de misioneros a la izquierda, cantidad de caníbales a la
     * izquierda, 0 si el bote esta a la izquierda o 1 si esta a derecha.
     * @param d true para mostrar nodos examinados
     */
    public static void buscar(int[] ubicaciones, boolean d) {
        NodoDeBusqueda raiz = new NodoDeBusqueda(new EstadoMisioneroCanibal(ubicaciones));
        Queue<NodoDeBusqueda> cola = new LinkedList<NodoDeBusqueda>();

        cola.add(raiz);

        realizarBusqueda(cola, d);
    }

    /*
     * Método de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Devuelve true si es así, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusqueda n) {
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
     * @param cola - La cola NodoDeBusqueda a ser buscada.
     * @param d true para mostrar nodos examinados
     */
    public static void realizarBusqueda(Queue<NodoDeBusqueda> cola, boolean d) {
        int contBusqueda = 1; // contador para el número de iteraciones

        while (!cola.isEmpty()) // mientras la cola no este vacía
        {
             NodoDeBusqueda nodoTemp = (NodoDeBusqueda) cola.poll();
            //#Mostrar Nodo Padre          
            if (nodoTemp.getPadre() != null) {
                System.out.print("Nodo Padre: ");
                nodoTemp.getPadre().getEstadoActual().mostrarEstado();
            } else {
                System.out.println("Es nodo raíz");
            }
            
            System.out.print("Sale de Cola: ");
            nodoTemp.getEstadoActual().mostrarEstado();
            
            System.out.println();
            
            if (!nodoTemp.getEstadoActual().esMeta()) // si nodoTemp no es una meta
            {
                // generar sucesores inmediatos a nodoTemp
                ArrayList<Estado> sucesoresTemp = nodoTemp.getEstadoActual().generarSucesores();

                /*
                 * Recorrer los sucesores, envolverlos en un NodoDeBusqueda, 
                 * comprobar si ya han sido evaluados, y si no, agregarlos a
                 * la cola
                 */
                for (int i = 0; i < sucesoresTemp.size(); i++) {
                    // el segundo parametro aquí agrega el costo del nuevo
                    // nodo al costo actual total en el NodoDeBusqueda
                    NodoDeBusqueda nuevoNodo = new NodoDeBusqueda(nodoTemp,
                            sucesoresTemp.get(i), nodoTemp.getCosto()
                            + sucesoresTemp.get(i).determinarCosto(), 0);
                    
                    
                    if (!revisarRepetidos(nuevoNodo)) {
                        cola.add(nuevoNodo);
                    }
                }
                contBusqueda++;
            } 
            

           
            
            // El estado meta ha sido encontrado. Mostrar el camino para llegar
            // a este

            //#Comentar el bloque else 
//            else 
//            {
//                // Use una pila para rastrear el camino desde el estado inicial
//                // hasta el estado meta.
//                Stack<NodoDeBusqueda> caminoSolucion = new Stack<NodoDeBusqueda>();
//                caminoSolucion.push(nodoTemp);
//                nodoTemp = nodoTemp.getPadre();
//
//                while (nodoTemp.getPadre() != null) {
//                    caminoSolucion.push(nodoTemp);
//                    nodoTemp = nodoTemp.getPadre();
//                }
//                caminoSolucion.push(nodoTemp);
//
//                // El tamaño de la pila antes de vaciarla.
//                int iteraciones = caminoSolucion.size();
//
//                for (int i = 0; i < iteraciones; i++) {
//                    nodoTemp = caminoSolucion.pop();
//                    nodoTemp.getEstadoActual().mostrarEstado();
//                    System.out.println();
//                    System.out.println();
//                }
//                System.out.println("El costo fue: " + nodoTemp.getCosto());
//                if (d) {
//                    System.out.println("Número de nodos examinados: "
//                            + contBusqueda);
//                }
//
//                System.exit(0);
//            }
        }

        // Esto no debería ocurrir.
        System.out.println("!Error! No se encontró una solución!");
    }
}
