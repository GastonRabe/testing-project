package sistema.personas.medicos.decorators;

import sistema.personas.medicos.IMedico;

/**
 * Patron Decorator.<br>
 */
public class DecoratorTemporario extends DecoratorMedico {
    private static double aumentoTemporario = 0.05;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public DecoratorTemporario() {

    }

    public DecoratorTemporario(IMedico encapsulado) {
        super(encapsulado);
    }

    public static double getAumentoTemporario() {
        return aumentoTemporario;
    }

    /**
     * Setea aumento para medicos temporarios.<br>
     *
     * @param aumentoTemporario numero entre 0 y 1.<br>
     */
    public static void setAumentoTemporario(double aumentoTemporario) {
        DecoratorTemporario.aumentoTemporario = aumentoTemporario;
    }

    /**
     * Decorator de sueldo para medicos temporarios.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad y titulo de posgrado</b><br>
     */
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * (1 + DecoratorTemporario.aumentoTemporario);
    }

    /**
     * Decorator de descripcion para medicos temporarios.<br>
     * <b>Pre: Este decorator se aplica sobre un medico con especialidad y titulo de posgrado</b>.<br>
     */
    @Override
    public String getDescripcion() {
        return super.getDescripcion() + ", Temporario.";
    }

    @Override
    public String toString() {
        return super.toString() + "[contratacion=Temporario]";
    }
}