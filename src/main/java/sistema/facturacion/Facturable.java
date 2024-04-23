package sistema.facturacion;

/**
 * Interfaz que agrega comportamiento de Facturacion a Consulta Medica e Internacion.<br>
 */
public interface Facturable {
    boolean isFacturada();

    void setFacturada(boolean facturada);
}
