package MisionerosCanibales;
import algoritmos.BusquedaBFS;
import utils.Estado;

public class PruebaMisionerosCanibalesBFS {
    public static void main(String[] args) {
                int[] a = { 3,3,1};
                //int[] a = { 4,2,3,1};
                //int[] a = { 2,4,3,1};
                
                //USa BFS para ver cómo se generan los estados
                System.out.println("\tMISIONERO | CANIBAL | BOTE");
 		BusquedaBFS.buscar(true, new EstadoMisioneroCanibal(a));
    }    
}
