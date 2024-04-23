package sistema.habitaciones;

/**
 * Clase que modela una habitacion privada de la clinica.<br>
 * Aplica Patron Singleton.<br>
 * <b>Pre: </b> al instanciar la clase por primera vez debe indicarse el valor del costo de la habitacion mediante el metodo setCostoHabPrivada;
 * de otro modo el costo sera por defecto 0.<br>
 */
public class HabitacionPrivada extends Habitacion {
    private static HabitacionPrivada instance;
    private double costoHabPrivada;

    /**
     * Constructor privado correspondiente al patron Singleton.<br>
     */
    private HabitacionPrivada() {

    }

    /**
     * Metodo estatico para obtener la instancia segun patron Singleton.<br>
     *
     * @return unica instancia de la clase HabitacionPrivada.
     */
    public static HabitacionPrivada getInstance() {
        if (HabitacionPrivada.instance == null)
            HabitacionPrivada.instance = new HabitacionPrivada();
        return HabitacionPrivada.instance;
    }

    /**
     * Devuelve los costos adicionales propios de la habitacion privada.<br>
     * <b>Pre: </b> diasInternado debe ser un numero entero positivo.<br>
     *
     * @param diasInternado cantidad de dias que dura la internacion. Numero entero positivo.<br>
     * @return costos adicionales propios de la habitacion privada.<br>
     */
    @Override
    public double costosAdicionales(int diasInternado) {
        double adicional = this.costoHabPrivada;

        if (2 <= diasInternado && diasInternado <= 5)
            adicional *= diasInternado * 1.3;
        else if (diasInternado >= 6)
            adicional *= diasInternado * 2;
        return adicional;
    }

    /**
     * Devuelve el costo de la habitacion privada.<br>
     *
     * @return costo de la habitacion privada.<br>
     */
    @Override
    public double getCostoHabitacion() {
        return this.costoHabPrivada;
    }

    /**
     * Asigna el costo de la habitacion privada.<br>
     *
     * @param costoHabPrivada costo de la habitacion privada. Debe ser mayor o igual a cero.<br>
     */
    public void setCostoHabPrivada(double costoHabPrivada) {
        this.costoHabPrivada = costoHabPrivada;
    }

    @Override
    public String toString() {
        return "Hab. Privada";
    }
}