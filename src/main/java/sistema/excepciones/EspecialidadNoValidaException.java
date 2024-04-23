package sistema.excepciones;

/**
 * Excepcion que indica un error en la instanciacion de un medico a causa de una contratacion invalida.<br>
 */
public class EspecialidadNoValidaException extends ImposibleCrearMedicoException {
    private String especialidadInvalida;

    public EspecialidadNoValidaException(String message, String especialidadInvalida) {
        super(message);
        this.especialidadInvalida = especialidadInvalida;
    }

    public String getEspecialidadInvalida() {
        return especialidadInvalida;
    }
}
