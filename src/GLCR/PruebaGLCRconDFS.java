/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GLCR;

import algoritmos.BusquedaDFS;

/**
 *
 * @author Luis
 */
public class PruebaGLCRconDFS {
    public static void main(String[] args) {
        System.out.println("\t( GRANJERO , LOBO , CABRA , REPOLLO )");
        BusquedaDFS.buscar(true, new Estado_GLCR());
    }
}

