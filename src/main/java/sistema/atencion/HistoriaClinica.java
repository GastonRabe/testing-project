package sistema.atencion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import sistema.facturacion.ConsultaMedica;
import sistema.facturacion.Internacion;

/**
 * Clase que modela la hisotoria clinica de un paciente.<br>
 * <b>Invariante: </b> internaciones y consultasMedicas son distintas de null.<br>
 */
public class HistoriaClinica {
    private int numero;
    private ArrayList<Internacion> internaciones = new ArrayList<>();
    private ArrayList<ConsultaMedica> consultaMedicas = new ArrayList<>();
    private ArrayList<Double> listaDeInsumos = new ArrayList<Double>();
    /**
     * Constructor vacio para persistencia XML.<br>
     */
    public HistoriaClinica() {

    }

    public HistoriaClinica(int numero) {
        this.numero = numero;
    }
        
    
    public ArrayList<Double> getListaDeInsumos() {
		return listaDeInsumos;
	}

	public void setListaDeInsumos(ArrayList<Double> listaDeInsumos) {
		this.listaDeInsumos = listaDeInsumos;
	}

	public void addListaDeInsumos(double valor)
    {
    	this.listaDeInsumos.add(valor);
    }
    
    public void removeListaInsumos(int key)
    {
    	this.listaDeInsumos.remove(key);
    }

    /**
     * Agrega una nueva internacion a la historia clinica.<br>
     * <b>pre: </b> internacion distinto de null.<br>
     * <b>post: </b> se agregar una nueva internacion a la historia clinica.<br>
     *
     * @param internacion internacion a ingresar a la historia clinica. Distinto de null.<br>
     */
    public void agregarInternacion(Internacion internacion) {
        this.internaciones.add(internacion);
    }

    /**
     * Agrega una nueva consulta medica a la historia clinica.<br>
     * <b>pre: </b> consultaMedica distinto de null.<br>
     * <b>post: </b> se agregar una nueva consulta medica a la historia clinica.<br>
     *
     * @param consultaMedica consulta medica a ingresar a la historia clinica. Distinto de null.<br>
     */
    public void agregarConsultaMedica(ConsultaMedica consultaMedica) {
        this.consultaMedicas.add(consultaMedica);
    }

    /**
     * @return total de interaciones en historia clinica.<br>
     */
    public int getCantidadDeInternaciones() {
        return this.internaciones.size();
    }

    /**
     * @return total de consultas medicas en historia clinica.<br>
     */
    public int getCantidadDeConsultasMedicas() {
        return this.consultaMedicas.size();
    }

    /**
     * @return cantidad de internaciones aun no facturadas en la historia clinica.<br>
     */
    public int getCantidadDeInternacionesSinFacturar() {
        int total = 0;

        for (Internacion internacion : this.internaciones) {
            if (!internacion.isFacturada())
                total += 1;
        }
        return total;
    }

    /**
     * @return cantidad de consultas medicas no facturadas en la historia clinica.<br>
     */
    public int getCantidadDeConsultasMedicasSinFacturar() {
        int total = 0;

        for (ConsultaMedica consultaMedica : this.consultaMedicas) {
            if (!consultaMedica.isFacturada())
                total += 1;
        }
        return total;
    }

    /**
     * @return iterator de internaciones en historia clinica.<br>
     */
    public Iterator<Internacion> getInternacionesIterator() {
        return this.internaciones.iterator();
    }

    /**
     * @return iterator de consultas medicas en historia clinica.<br>
     */
    public Iterator<ConsultaMedica> getConsultasMedicasIterator() {
        return this.consultaMedicas.iterator();
    }

    /**
     * Getter
     *
     * @return internaciones
     */
    public ArrayList<Internacion> getInternaciones() {
        return internaciones;
    }

    /**
     * Setter
     *
     * @param internaciones distinto de null.<br>
     */
    public void setInternaciones(ArrayList<Internacion> internaciones) {
        this.internaciones = internaciones;
    }

    /**
     * Gettter
     *
     * @return consultas medicas.
     */
    public ArrayList<ConsultaMedica> getConsultaMedicas() {
        return consultaMedicas;
    }

    /**
     * Setter
     *
     * @param consultaMedicas distinto de null
     */
    public void setConsultaMedicas(ArrayList<ConsultaMedica> consultaMedicas) {
        this.consultaMedicas = consultaMedicas;
    }

    /**
     * Getter
     *
     * @return numero de historia clinica
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Setter
     *
     * @param numero entero positivo
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Criterio de igualdad: numero de historia clinica.
     *
     * @param o Object
     * @return true si los numeros de historia clinica coinciden, false de otra manera
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoriaClinica that = (HistoriaClinica) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "numero=" + numero +
                ", internacions=" + internaciones +
                ", consultaMedicas=" + consultaMedicas +
                '}';
    }

}
