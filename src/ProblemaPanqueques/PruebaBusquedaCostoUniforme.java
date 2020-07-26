package ProblemaPanqueques;

import algoritmos.BusquedaCostoUniforme;

public class PruebaBusquedaCostoUniforme {
    public static void main(String[] args) {
        int[] a = { 4,1,3,2};
        //int[] a = { 4,2,3,1};
        //int[] a = { 2,4,3,1};
        BusquedaCostoUniforme.buscar(true, new EstadoPanqueques(a));
    }        
}
