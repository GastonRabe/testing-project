package sistema.excepciones;

public class PacienteInexistenteException extends Exception {
    public PacienteInexistenteException(String message) {
        super(message);
    }
}
