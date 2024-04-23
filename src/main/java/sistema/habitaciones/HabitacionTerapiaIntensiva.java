package sistema.habitaciones;

/**
 * Clase que modela una habitacion de terapia intensiva de la clinica.<br>
 * Aplica Patron Singleton.<br>
 * <b>Pre: </b> al instanciar la clase por primera vez debe indicarse el valor del costo de la habitacion mediante el metodo setCostoHabTerapiaIntensiva;
 * de otro modo el costo sera por defecto 0.<br>
 */
public class HabitacionTerapiaIntensiva extends Habitacion {
    private static HabitacionTerapiaIntensiva instance;
    private double costoHabTerapiaIntensiva;

    /**
     * Constructor privado correspondiente al patron Singleton.<br>
     */
    private HabitacionTerapiaIntensiva() {

    }

    /**
     * Metodo estatico para obtener la instancia segun patron Singleton.<br>
     *
     * @return unica instancia de la clase HabitacionTerapiaIntensiva.
     */
    public static HabitacionTerapiaIntensiva getInstance() {
        if (HabitacionTerapiaIntensiva.instance == null)
            HabitacionTerapiaIntensiva.instance = new HabitacionTerapiaIntensiva();
        return HabitacionTerapiaIntensiva.instance;
    }

    /**
     * Devuelve los costos adicionales propios de la habitacion de terapia intensiva.<br>
     * <b>Pre: </b> diasInternado debe ser un numero entero positivo.<br>
     *
     * @param diasInternado cantidad de dias que dura la internacion. Numero entero positivo.<br>
     * @return costos adicionales propios de la habitacion de terapia intensiva.<br>
     */
    @Override
    public double costosAdicionales(int diasInternado) {
        return Math.pow(this.costoHabTerapiaIntensiva, diasInternado);
    }

    /**
     * Devuelve el costo de la habitacion de terapia intensiva.<br>
     *
     * @return costo de la habitacion de terapia intensiva.<br>
     */
    @Override
    public double getCostoHabitacion() {
        return this.costoHabTerapiaIntensiva;
    }

    /**
     * Asigna el costo de la habitacion de terapia intensiva.<br>
     *
     * @param costoHabTerapiaIntensiva costo de la habitacion de terapia intensiva. Debe ser mayor o igual a cero.<br>
     */
    public void setCostoHabTerapiaIntensiva(double costoHabTerapiaIntensiva) {
        this.costoHabTerapiaIntensiva = costoHabTerapiaIntensiva;
    }

    @Override
    public String toString() {
        return "Hab. T. Intensiva";
    }
}