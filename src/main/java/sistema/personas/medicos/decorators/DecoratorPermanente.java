package sistema.personas.medicos.decorators;

import sistema.personas.medicos.IMedico;

/**
 * Patron Decorator.<br>
 */
public class DecoratorPermanente extends DecoratorMedico {
    private static double aumentoPermanente = 0.1;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public DecoratorPermanente() {

    }

    public DecoratorPermanente(IMedico encapsulado) {
        super(encapsulado);
    }

    public static double getAumentoPermanente() {
        return aumentoPermanente;
    }

    /**
     * Seta aumento para medicos en planta permanente.<br>
     *
     * @param aumentoPermanente numero entre 0 y 1.<br>
     */
    public static void setAumentoPermanente(double aumentoPermanente) {
        DecoratorPermanente.aumentoPermanente = aumentoPermanente;
    }

    /**
     * Decorator de sueldo para medicos en planta permanente.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad y titulo de posgrado</b><br>
     */
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * (1 + DecoratorPermanente.aumentoPermanente);
    }

    /**
     * Decorator de descripcion para medicos en planta permanente.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad y titulo de posgrado</b><br>
     */
    @Override
    public String getDescripcion() {
        return super.getDescripcion() + ", Permanente.";
    }

    @Override
    public String toString() {
        return super.toString() + "[contratacion=Permanente]";
    }
}