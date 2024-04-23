package sistema.excepciones;

/**
 * Excepcion que indica un error a la hora de crear un medico.<br>
 */
public class ImposibleCrearMedicoException extends Exception {
    public ImposibleCrearMedicoException(String message) {
        super(message);
    }
}
