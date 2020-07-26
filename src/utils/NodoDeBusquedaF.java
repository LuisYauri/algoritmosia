package utils;

/**
 * 
 * Clase para representar un NodoDeBusqueda. Esta será una envoltura para un
 * Estado, y rastreara el costo para encontra ese estado y el nodo padre.
 */
public class NodoDeBusquedaF implements Comparable<NodoDeBusquedaF>
{

    final private Estado estadoActual;
    final private NodoDeBusquedaF padre;
    final private double costo; // costo para llegar a este estado
    final private double costoHeu; // costo heurístico
    final private double costoF; // costo f(n)

    /**
     * Constructor para la raíz NodoDeBusqueda
     * 
     * @param e
     *            el estado pasado
     */
    public NodoDeBusquedaF(Estado e)
    {
            estadoActual = e;
            padre = null;
            costo = 0;
            costoHeu = 0;
            costoF = 0;
    }

    /**
     * Constructor para todos los otros NodoDeBusqueda
     * 
     * @param prev
     *            el nodo padre
     * @param e
     *            el estado
     * @param c
     *            el costo g(n) para llegar a este nodo
     * @param h
     *            el costo h(n) para obtener este nodo
     */
    public NodoDeBusquedaF(NodoDeBusquedaF prev, Estado e, double c, double h)
    {
            padre = prev;
            estadoActual = e;
            costo = c;
            costoHeu = h;
            costoF = costo + costoHeu;
    }

    /**
     * @return el estadoActual
     */
    public Estado getEstadoActual()
    {
            return estadoActual;
    }

    /**
     * @return el padre
     */
    public NodoDeBusquedaF getPadre()
    {
            return padre;
    }

    /**
     * @return el costo
     */
    public double getCosto()
    {
            return costo;
    }

    /**
     * 
     * @return el costo heurístico 
     */
    public double getCostoHeu()
    {
            return costoHeu;
    }

    /**
     * 
     * @return el costo f(n) para A*
     */
    public double getCostoF()
    {
            return costoF;
    }


    @Override
    public int compareTo(NodoDeBusquedaF y) {
//        NodoDeBusqueda y = (NodoDeBusqueda) otro;
        if(this.costoF > y.costoF)        return 1;
        else if( this.costoF < y.costoF)  return -1;
        else                            return 0;
    }
}
