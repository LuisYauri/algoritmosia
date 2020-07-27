package BusquedaPuzle8;

import algoritmos.BusquedaBFS;

public class PruebaPuzle8conBFS {
    public static void main(String[] args) {
        	//int[] a = { 3, 1, 2, 4, 5, 6, 7, 0, 8};
                //int[] a = {1,2,3,4,5,6,0,7,8};
                int[] a = { 1, 0, 3, 4, 2, 5, 7, 8, 6};
                //int[] a = {1, 2, 3, 4, 5, 6, 8, 7,0};
 		BusquedaBFS.buscar(true, new EstadoPuzleOcho(a));
    }    
}
