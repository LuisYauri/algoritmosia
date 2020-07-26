package MisionerosCanibales;

import algoritmos.BusquedaCostoUniforme;

public class PruebaMisionerosCanibalesCostoUniforme {
    public static void main(String[] args) {
        //int[] a = { 4,1,3,2};
        int[] a = { 3,3,1};;
        //int[] a = { 2,4,3,1};
        System.out.println("\tMISIONERO | CANIBAL | BOTE");
        BusquedaCostoUniforme.buscar(true, new EstadoMisioneroCanibal(a));
    }        
}
