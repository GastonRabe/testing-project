package sistema.personas.medicos.decorators;

import sistema.personas.medicos.IMedico;

/**
 * Patron Decorator.<br>
 */
public class DecoratorMagister extends DecoratorMedico {
    private static double aumentoMagister = 0.05;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public DecoratorMagister() {

    }

    public DecoratorMagister(IMedico encapsulado) {
        super(encapsulado);
    }

    public static double getAumentoMagister() {
        return aumentoMagister;
    }

    /**
     * Setea aumento para todos los medicos con titulo de posgrado de master.<br>
     *
     * @param aumentoMagister numero entre 0 y 1.<br>
     */
    public static void setAumentoMagister(double aumentoMagister) {
        DecoratorMagister.aumentoMagister = aumentoMagister;
    }

    /**
     * Decorator de sueldo para medicos con titulo de Master.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad</b><br>
     */
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * (1 + DecoratorMagister.aumentoMagister);
    }

    /**
     * Decorator de la descripcion para medicos con titulo de Master.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad</b>.<br>
     */
    @Override
    public String getDescripcion() {
        return super.getDescripcion() + ", Magister";
    }

    @Override
    public String toString() {
        return super.toString() + "[posgrado=Magister]";
    }
}