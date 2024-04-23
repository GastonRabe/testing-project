package sistema.personas.medicos.especialidades;

import sistema.personas.medicos.Medico;

/**
 * Clase que modela a un medico cirujano de la clinica.<br>
 */
public class Cirujano extends Medico {
    private static double aumentoCirujano = 0.1;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public Cirujano() {

    }

    /**
     * Constructor. <br>
     * <b>Pre: </b> nombre, apellido, direccion, ciudad distintos de null; telenofo y dni enteros positivos.<br>
     * <b>Post: </b> se genera una nueva instancia de la clase.<br>
     *
     * @param nombre    Nombre del medico. Debe ser distinto de null.<br>
     * @param apellido  Apellido del medico. Debe ser distinto de null.<br>
     * @param direccion Direccion del medico. Debe ser distinto de null.<br>
     * @param ciudad    Ciudad de residencia del medico. Debe ser distinto de null.<br>
     * @param telefono  Telefono de contacto del medico. Numero entero positivo.<br>
     * @param dni       DNI del medico. Numero entero positivo.<br>
     * @param matricula Numero de matricula del medico.<br>
     */
    public Cirujano(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni, int matricula) {
        super(nombre, apellido, direccion, ciudad, telefono, dni, matricula);
    }

    public static double getAumentoCirujano() {
        return aumentoCirujano;
    }

    /**
     * Setea porcentaje de aumento para todos los cirujano.<br>
     * <b>Pre: </b> aumentoCirujano deber se un numero entre 0 y 1.<br>
     *
     * @param aumentoCirujano numero entre 0 y 1.<br>
     */
    public static void setAumentoCirujano(double aumentoCirujano) {
        Cirujano.aumentoCirujano = aumentoCirujano;
    }

    /**
     * Retorna sueldo de un medico Cirujano.<br>
     */
    @Override
    public double getSueldo() {
        return Medico.sueldoBasico * (1 + Cirujano.aumentoCirujano);
    }

    @Override
    public String getDescripcion() {
        return "Cirujano";
    }

    @Override
    public String toString() {
        return super.toString() + "[especialidad=" + this.getDescripcion() + "]";
    }
}
