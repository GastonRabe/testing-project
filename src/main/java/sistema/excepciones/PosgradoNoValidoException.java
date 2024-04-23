package sistema.excepciones;

/**
 * Excepcion que indica un error al crear un medico a causa de un posgrado invalida.<br>
 */
public class PosgradoNoValidoException extends ImposibleCrearMedicoException {
    private String posgradoInvalido;

    public PosgradoNoValidoException(String message, String posgradoInvalido) {
        super(message);
        this.posgradoInvalido = posgradoInvalido;
    }

    public String getPosgradoInvalido() {
        return posgradoInvalido;
    }
}
