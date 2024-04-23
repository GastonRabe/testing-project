package sistema.excepciones;

/**
 * Excepcion que indica un error al crear un medico a causa de informacion personal invalida.<br>
 */
public class InformacionPersonalNoValidaException extends ImposibleCrearMedicoException {
    public InformacionPersonalNoValidaException(String message) {
        super(message);
    }
}
