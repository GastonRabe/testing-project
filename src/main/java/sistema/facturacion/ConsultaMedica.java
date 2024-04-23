package sistema.facturacion;

import sistema.personas.medicos.IMedico;

import java.util.Objects;

/**
 * Clase que modela una consulta medica.<br>
 * Tiene informacion del medico, la cantidad de consultas realizadas y el valor de la consulta.<br>
 */
public class ConsultaMedica implements Facturable {
    private static double incremento = 0.2;
    private IMedico medico;
    private int cantidadConsultas;
    private double valorConsulta;
    private boolean facturada;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public ConsultaMedica() {

    }

    /**
     * Constructor de la clase.<br>
     * <b>Pre: </b> medico distinto de null, cantidadConsultas mayor a cero.<br>
     * <b>Post: </b> se genera un nueva instancia de consulta medica.<br>
     *
     * @param medico            Medico que interviene en la consulta. Debe ser distinto de null.<br>
     * @param cantidadConsultas Cantidad de consultas realizadas al medico. Debe ser un valor positivo.<br>
     */
    public ConsultaMedica(IMedico medico, int cantidadConsultas) {
        this.medico = medico;
        this.cantidadConsultas = cantidadConsultas;
        this.valorConsulta = this.medico.getSueldo() * (1.00 + ConsultaMedica.incremento);
    }

    public static double getIncremento() {
        return incremento;
    }

    public static void setIncremento(double incremento) {
        ConsultaMedica.incremento = incremento;
    }

    /**
     * Devuelve el subtotal de la consulta contemplando el incremento por sobre lo que cobra el medico.<br>
     *
     * @return subtotal de la consulta.<br>
     */
    public double getSubtotal() {
        return this.valorConsulta * this.cantidadConsultas;
    }

    /**
     * Devuelve el medico que participa de la consulta.<br>
     *
     * @return medico que interviene en la consulta.<br>
     */
    public IMedico getMedico() {
        return medico;
    }

    public void setMedico(IMedico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return String.format("Dr.%-23s %.2f %9s %-10d %5s %.2f \n", this.medico.getApellido(), this.valorConsulta, " ", this.cantidadConsultas, " ", this.getSubtotal());
    }

    public int getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(int cantidadConsultas) {
        this.cantidadConsultas = cantidadConsultas;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    @Override
    public boolean isFacturada() {
        return facturada;
    }

    @Override
    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaMedica that = (ConsultaMedica) o;
        return cantidadConsultas == that.cantidadConsultas && Double.compare(that.valorConsulta, valorConsulta) == 0 && facturada == that.facturada && Objects.equals(medico, that.medico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medico, cantidadConsultas, valorConsulta, facturada);
    }
}
