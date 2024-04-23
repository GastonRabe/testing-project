package sistema.excepciones;

/**
 * Excepcion que indica un error al crear un medico a causa de una contratacion invalida.<br>
 */
public class ContratacionNoValidaException extends ImposibleCrearMedicoException {
    private String contratacionInvalida;

    public ContratacionNoValidaException(String message, String contratacionInvalida) {
        super(message);
        this.contratacionInvalida = contratacionInvalida;
    }

    public String getContratacionInvalida() {
        return contratacionInvalida;
    }
}
