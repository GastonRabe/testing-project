package sistema.personas.medicos.decorators;

import sistema.personas.medicos.IMedico;

import java.util.Objects;

/**
 * Clase que implementa Patron Decorator para modificar responsabiliades en forma dinamica a los medicos.<br>
 */
public abstract class DecoratorMedico implements IMedico {
    protected IMedico encapsulado;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public DecoratorMedico() {

    }

    public DecoratorMedico(IMedico encapsulado) {
        this.encapsulado = encapsulado;
    }

    public IMedico getEncapsulado() {
        return encapsulado;
    }

    public void setEncapsulado(IMedico encapsulado) {
        this.encapsulado = encapsulado;
    }

    @Override
    public String getNombre() {
        return encapsulado.getNombre();
    }

    @Override
    public String getApellido() {
        return encapsulado.getApellido();
    }

    @Override
    public String getDescripcion() {
        return this.encapsulado.getDescripcion();
    }

    @Override
    public int getMatricula() {
        return this.encapsulado.getMatricula();
    }

    @Override
    public void setMatricula(int matricula) {
        this.encapsulado.setMatricula(matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecoratorMedico that = (DecoratorMedico) o;
        return Objects.equals(encapsulado, that.encapsulado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encapsulado);
    }

    @Override
    public String toString() {
        return this.encapsulado.toString();
    }
}