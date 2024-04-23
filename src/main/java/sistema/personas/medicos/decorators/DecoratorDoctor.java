package sistema.personas.medicos.decorators;

import sistema.personas.medicos.IMedico;

/**
 * Patron Decorator.<br>
 */
public class DecoratorDoctor extends DecoratorMedico {
    private static double aumentoDoctor = 0.1;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public DecoratorDoctor() {

    }

    public DecoratorDoctor(IMedico encapsulado) {
        super(encapsulado);
    }

    public static double getAumentoDoctor() {
        return aumentoDoctor;
    }

    /**
     * Seta aumento para todos los medicos con titulo de posgrado de doctor.<br>
     *
     * @param aumentoDoctor numero entre 0 y 1.<br>
     */
    public static void setAumentoDoctor(double aumentoDoctor) {
        DecoratorDoctor.aumentoDoctor = aumentoDoctor;
    }

    /**
     * Decorator de sueldo para medicos con titulo de doctor.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad. </b><br>
     */
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * (1 + DecoratorDoctor.aumentoDoctor);
    }

    /**
     * Decorator de descripcion para medicos con titulo de doctor.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad</b><br>
     */
    @Override
    public String getDescripcion() {
        return super.getDescripcion() + ", Doctor";
    }

    @Override
    public String toString() {
        return super.toString() + "[posgrado=Doctor]";
    }
}