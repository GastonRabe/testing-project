package sistema.habitaciones;

/**
 * Clase que modela una habitacion compartida de la clinica.<br>
 * Aplica Patron Singleton.<br>
 * <b>Pre: </b> al instanciar la clase por primera vez debe indicarse el valor del costo de la habitacion mediante el metodo setCostoHabCompartida;
 * de otro modo el costo sera por defecto 0.<br>
 */
public class HabitacionCompartida extends Habitacion {
    private static HabitacionCompartida instance;
    private double costoHabCompartida;

    /**
     * Constructor privado correspondiente al patron Singleton.<br>
     */
    private HabitacionCompartida() {
        // Contructor privado y vacio
    }

    /**
     * Metodo estatico para obtener la instancia segun patron Singleton.<br>
     *
     * @return unica instancia de la clase HabitacionCompartida.
     */
    public static HabitacionCompartida getInstance() {
        if (HabitacionCompartida.instance == null)
            HabitacionCompartida.instance = new HabitacionCompartida();
        return HabitacionCompartida.instance;
    }

    /**
     * Devuelve los costos adicionales propios de la habitacion compartida.<br>
     * <b>Pre: </b> diasInternado debe ser un numero entero positivo.<br>
     *
     * @param diasInternado cantidad de dias que dura la internacion. Numero entero positivo.<br>
     * @return costos adicionales propios de la habitacion compartida.<br>
     */
    @Override
    public double costosAdicionales(int diasInternado) {
        return this.costoHabCompartida * diasInternado;
    }

    /**
     * Devuelve el costo de la habitacion compartida.<br>
     *
     * @return costo de la habitacion compartida.<br>
     */
    @Override
    public double getCostoHabitacion() {
        return this.costoHabCompartida;
    }

    /**
     * Asigna el costo de la habitacion compartida.<br>
     *
     * @param costoHabCompartida costo de la habitacion compartida. Debe ser mayor o igual a cero.<br>
     */
    public void setCostoHabCompartida(double costoHabCompartida) {
        this.costoHabCompartida = costoHabCompartida;
    }

    @Override
    public String toString() {
        return "Hab. Compartida";
    }
}