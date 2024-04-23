package sistema.facturacion;

import sistema.personas.pacientes.Paciente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Clase que modela una factura de la clinica.<br>
 * Cada factura contiene informacion sobre la fecha de facturacion, el paciente a quien se destina la factura, un numero de factura,
 * y listas de consultas medicas e internaciones.<br>
 */
public class Factura implements Comparable<Factura> {
    private static int sigNroFactura = 0;
    private int nroFactura;
    private GregorianCalendar fecha;
    private Paciente paciente;
    private ArrayList<ConsultaMedica> consultasConMedicos;
    private ArrayList<Internacion> internaciones;
	private double guardoTotal;

    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public Factura() {

    }

    
    public double getguardoTotal()
    {
    	return this.guardoTotal;
    }
    /**
     * Constructor.<br>
     * <b>Pre: </b> todos los parametros deben ser diferentes de null.<br>
     * <b>Post: </b> se genera una nueva factura.<br>
     *
     * @param fecha               Fecha de la factura. Debe ser distinto de null.<br>
     * @param paciente            Paciente a facturar. Debe ser distinto de null.<br>
     * @param consultasConMedicos Lista de consultas medicas. Debe ser distinto de null.<br>
     * @param internaciones       Lista de internaciones. Debe ser distinto de null.<br>
     */
    public Factura(GregorianCalendar fecha, Paciente paciente, ArrayList<ConsultaMedica> consultasConMedicos, ArrayList<Internacion> internaciones) {
        this.nroFactura = ++Factura.sigNroFactura;
        this.fecha = fecha;
        this.paciente = paciente;
        this.consultasConMedicos = consultasConMedicos;
        this.internaciones = internaciones;
    }

    public static int getSigNroFactura() {
        return sigNroFactura;
    }

    public static void setSigNroFactura(int sigNroFactura) {
        Factura.sigNroFactura = sigNroFactura;
    }

    /**
     * Retorna la fecha de facturacion.<br>
     *
     * @return fecha de facturacion.<br>
     */
    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el Paciente al que se le esta facturando.<br>
     *
     * @return paciente facturado.<br>
     */
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Devuelve el numero de factura.<br>
     *
     * @return numero de la factura.<br>
     */
    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    /**
     * Devuelve el subtotal de la factura (consultas medicas + internaciones).<br>
     *
     * @return Subtotal de la factura.<br>
     */
    public double getSubtotal() {
        double subtotal = 0;

        for (ConsultaMedica consulta : this.consultasConMedicos)
            subtotal += consulta.getSubtotal();
        for (Internacion internacion : this.internaciones)
            subtotal += internacion.getSubtotal();
        return subtotal;
    }

    /**
     * Representacion en tipo String de la informacion contenida en la factura.<br>
     *
     * @return Factura.<br>
     */
    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        double total = 0;

        stringBuilder.append("***** Factura ********************************************************\n");
        stringBuilder.append(" - Numero de Factura: ").append(this.nroFactura).append(".\n");
        stringBuilder.append(" - Fecha: ").append(formatter.format(this.fecha.getTime())).append(".\n");
        stringBuilder.append(" - Paciente : ").append(this.paciente.getNombre()).append(" ").append(this.paciente.getApellido()).append(".\n");
        stringBuilder.append("Prestacion                 Valor        Cantidad            Subtotal\n");
        for (ConsultaMedica consulta : this.consultasConMedicos) {
            stringBuilder.append(consulta.toString());
            total += consulta.getSubtotal();
        }
        for (Internacion internacion : this.internaciones) {
            stringBuilder.append(internacion.toString());
            total += internacion.getSubtotal();
        }
        this.guardoTotal=total;
        stringBuilder.append("Total: ").append(String.format("%60.2f\n", total));
        stringBuilder.append("**********************************************************************\n");
        return stringBuilder.toString();
    }

    /**
     * Retorna todas las consultas medicas de la factura actual.<br>
     *
     * @return lista de consultas medicas consultasConMedicos.<br>
     */
    public ArrayList<ConsultaMedica> getConsultasConMedicos() {
        return this.consultasConMedicos;
    }

    public void setConsultasConMedicos(ArrayList<ConsultaMedica> consultasConMedicos) {
        this.consultasConMedicos = consultasConMedicos;
    }

    /**
     * Override del metodo CompareTo, se ordena por fecha.<br>
     */
    @Override
    public int compareTo(Factura o) {
        return this.fecha.compareTo(o.fecha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return nroFactura == factura.nroFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroFactura);
    }

    public ArrayList<Internacion> getInternaciones() {
        return internaciones;
    }

    public void setInternaciones(ArrayList<Internacion> internaciones) {
        this.internaciones = internaciones;
    }
}