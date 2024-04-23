package sistema.persistencia;

/**
 * Lanza Exception y no IOException/ClassNotFound para dejar abieta la puerta a BBDD.
 */
public interface IPersistencia<E> {
	/**
	 * Abre un archivo para utilizarlo como input
	 * @param fileName
	 * @throws Exception si no se puede abrir el archivo
	 */
    void openInput(String fileName) throws Exception;

    /**
     * Cierra el input
     * @throws Exception si no se abrio un input previamente
     */
    void closeInput() throws Exception;

    /**
     * Abre un archivo para utilizarlo como output
     * @param fileName
     * @throws Exception si no se puede abrir el archivo
     */
    void openOutput(String fileName) throws Exception;

    /**
     * Cierra el output
     * @throws Exception
     */
    void closeOutput() throws Exception;

    /**
     * Escribe un objeto
     * @param obj
     * @throws Exception
     */
    void write(E obj) throws Exception;

    /**
     * Lee un objeto. Si ningun input stream fue abierto, devuelve null
     * @return objeto leido o null
     * @throws Exception
     */
    E read() throws Exception;
}
