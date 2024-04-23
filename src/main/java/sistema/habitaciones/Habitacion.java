package sistema.habitaciones;

/**
 * Clase abtracta que modela una habitacion generica de la clinica.<br>
 * Contiene informacion sobre el costo de asignacion basico de una habitacion.<br>
 * <b>Pre: </b> Dicho costo debe inicializarse se manera estatica
 * para poder trabajar con la clase ya que no posee ningun valor por defecto asignado.<br>
 */
public abstract class Habitacion {
    protected static double costoAsignacion;

    public static double getCostoAsignacion() {
        return costoAsignacion;
    }

    /**
     * Especificar costo de asignacion base de una habitacion.<br>
     * Debe utilizarse solo desde la clase Habitacion y no desde las clases heredadas.<br>
     * <b>Pre: </b> CostoAsignacion debe ser un numero positivo mayor o igual a cero.<br>
     * <b>Post: </b> El costo de la asignacion base de una habitacion se setea a costoAsignacion.<br>
     *
     * @param costoAsignacion Costo de asignacion de la habitacion. Debe ser mayor o igual a cero.<br>
     */
    public static void setCostoAsignacion(double costoAsignacion) {
        Habitacion.costoAsignacion = costoAsignacion;
    }

    /**
     * Devuelve el costo de internacion en la habitacion.<br>
     * Implementa Patron Template.<br>
     * <b>Pre: </b> diasInternado debe ser un entero positivo.<br>
     * <b>Post </b> devuelve el costo de internacion en la habitacion en funcion de la cantidad de dias que dure la internacion.<br>
     *
     * @param diasInternado cantidad de dias que dura la internacion. Numero entero positivo.<br>
     * @return costo de internacion en funcion de la cantidad de dias indicada.<br>
     */
    public double getCostoInternacion(int diasInternado) {
        return this.costoAsignacion() + this.costosAdicionales(diasInternado);
    }

    /**
     * Metodo que devuelve los costos adicionales propios de cada habitacion concreta.<br>
     * Debe implementarse en clases especializadad.<br>
     * <b>Pre: </b> diasInternado debe ser un entero positivo.<br>
     *
     * @param diasInternado cantidad de dias que dura la internacion. Numero entero positivo.<br>
     * @return costos adicionales propio de cada habitacion concreta.<br>
     */
    public abstract double costosAdicionales(int diasInternado);

    /**
     * Devuelve el costo basico de asignacion de una habitacion.<br>
     *
     * @return costo de asignacion de una habitacion.<br>
     */
    public double costoAsignacion() {
        return Habitacion.costoAsignacion;
    }

    /**
     * Metodo abtracto a implementarse en clases extendidas que devuelve el costo de cada habitacion concreta.<br>
     *
     * @return costo de la habitacion.<br>
     */
    public abstract double getCostoHabitacion();
}