package MisionerosCanibales;

import algoritmos.BusquedaDFSLimitada;

public class PruebaMisionerosCanibalesDFSLimitado {
    public static void main(String[] args) {
        //int[] a = {4,2,3,1};
        int[] a = { 3,3,1}; 
        //int[] a = { 2,4,3,1};
        System.out.println("\tMISIONERO | CANIBAL | BOTE");
        BusquedaDFSLimitada.buscar(true, new EstadoMisioneroCanibal(a), 11);

    }
}   
