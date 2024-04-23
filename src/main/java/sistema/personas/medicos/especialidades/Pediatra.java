package sistema.personas.medicos.especialidades;

import sistema.personas.medicos.Medico;

/**
 * Clase que modela a un medico pediatra de la clinica.<br>
 */
public class Pediatra extends Medico {
    private static double aumentoPediatra = 0.07;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public Pediatra() {

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
    public Pediatra(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni, int matricula) {
        super(nombre, apellido, direccion, ciudad, telefono, dni, matricula);
    }

    public static double getAumentoPediatra() {
        return aumentoPediatra;
    }

    /**
     * Setea porcentaje de aumento para todos los pediatras.<br>
     * <b>Pre: </b> aumentoPediatra deber se un numero entre 0 y 1.<br>
     *
     * @param aumentoPediatra numero entre 0 y 1.<br>
     */
    public static void setAumentoPediatra(double aumentoPediatra) {
        Pediatra.aumentoPediatra = aumentoPediatra;
    }

    /**
     * Retorna sueldo de un medico pediatra
     */
    @Override
    public double getSueldo() {
        return Medico.sueldoBasico * (1 + Pediatra.aumentoPediatra);
    }

    @Override
    public String getDescripcion() {
        return "Pediatra";
    }

    @Override
    public String toString() {
        return super.toString() + "[especialidad=" + this.getDescripcion() + "]";
    }
}
