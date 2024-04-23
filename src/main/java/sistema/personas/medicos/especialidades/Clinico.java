package sistema.personas.medicos.especialidades;

import sistema.personas.medicos.Medico;

/**
 * Clase que modela a un medico clinico de la clinica.<br>
 */
public class Clinico extends Medico {
    private static double aumentoClinico = 0.05;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public Clinico() {

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
    public Clinico(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni, int matricula) {
        super(nombre, apellido, direccion, ciudad, telefono, dni, matricula);
    }

    public static double getAumentoClinico() {
        return aumentoClinico;
    }

    /**
     * Setea porcentaje de aumento para todos los clinicos.<br>
     * <b>Pre: </b> aumentoClinico deber se un numero entre 0 y 1.<br>
     *
     * @param aumentoClinico numero entre 0 y 1.<br>
     */
    public static void setAumentoClinico(double aumentoClinico) {
        Clinico.aumentoClinico = aumentoClinico;
    }

    /**
     * Retorna sueldo de un medico clinico.<br>
     */
    @Override
    public double getSueldo() {
        return Medico.sueldoBasico * (1 + Clinico.aumentoClinico);
    }

    @Override
    public String getDescripcion() {
        return "Clinico";
    }

    @Override
    public String toString() {
        return super.toString() + "[especialidad=" + this.getDescripcion() + "]";
    }
}
