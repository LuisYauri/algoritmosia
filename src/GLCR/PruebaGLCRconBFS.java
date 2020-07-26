/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GLCR;

import algoritmosia.BusquedaBFS;

/**
 *
 * @author Luis
 */
public class PruebaGLCRconBFS {
    public static void main(String[] args) {
        System.out.println("\t( GRANJERO , LOBO , CABRA , REPOLLO )");
        BusquedaBFS.buscar(true, new Estado_GLCR());
    }
    
}
