package MisionerosCanibales;

import algoritmos.BusquedaDFS;


public class PruebaMisionerosCanibalesDFS {
    public static void main(String[] args) {
        //int[] a = {4,2,3,1};
        int[] a = { 3,3,1};
        //int[] a = { 2,4,3,1};
        System.out.println("\tMISIONERO | CANIBAL | BOTE");
        BusquedaDFS.buscar(true, new EstadoMisioneroCanibal(a));
    }
}    

