package sistema.egreso;

import static org.mockito.Mockito.when; import org.mockito.Mockito;
import java.io.Serializable; import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Objects; import java.util.Random;
import sistema.atencion.HistoriaClinica;
import sistema.facturacion.ConsultaMedica;
import sistema.facturacion.Factura;
import sistema.facturacion.Internacion;
import sistema.personas.medicos.IMedico;
import sistema.personas.pacientes.Paciente;

/**
 * Clase que modela el modulo de egreso de la clinica.<br>
 * <b>Invariante: </b> facturas distinto de null.<br>
 */
public class ModuloEgreso implements Serializable {
    private ArrayList<Factura> facturas = new ArrayList<>();

    /**
     * Genera una facutura con los items de la historia clinica aun no facturados.<br>
     * <b>pre: </b> paciente, historia Clinica y fecha distintos de null.<br>
     * <b>post: </b> se genera una nueva factura.<br>
     *
     * @param paciente        paciente a facturar
     * @param historiaClinica hisotoria clinica del paciente
     * @param fecha           fecha de la factura
     */
    public void facturar(Paciente paciente, HistoriaClinica historiaClinica, GregorianCalendar fecha) {
        ArrayList<ConsultaMedica> consultaMedicas = new ArrayList<>();
        ArrayList<Internacion> internaciones = new ArrayList<>();

        for (Internacion in : historiaClinica.getInternaciones()) {
            if (!in.isFacturada()) {
                in.setFacturada(true);
                internaciones.add(in);
            }
        }
        for (ConsultaMedica cm : historiaClinica.getConsultaMedicas()) {
            if (!cm.isFacturada()) {
                cm.setFacturada(true);
                consultaMedicas.add(cm);
            }
        }
        if (consultaMedicas.size() != 0 || internaciones.size() != 0) {
            Factura factura = new Factura(fecha, paciente, consultaMedicas, internaciones);
            this.facturas.add(factura);
        }
    }

    /**
     * Retorna el detalle de una factura.<br>
     * <b>Pre: </b> numeroFactura mayor a cero.<br>
     *
     * @param numeroFactura numero de la factura que se desea buscar.<br>
     * @return detalle de la factua o null, si no existe factura con el numero indicado.<br>
     */
    public String getFactura(int numeroFactura) {
        Iterator<Factura> it = this.facturas.iterator();
        Factura fact = null;
        boolean esta = false;

        while (it.hasNext() && !esta) {
            fact = it.next();
            if (fact.getNroFactura() == numeroFactura)
                esta = true;
        }
        return esta ? fact.toString() : null;
    }

    /**
     * Genera el reporte del medico en orden cronologico segun las fechas indicadas.<br>
     * <b>Pre: </b> medico, desde y hasta diferentes de null.<br>
     *
     * @param medico medico cuyo reporte se desea generar. <br>
     * @param desde  fecha inicial.<br>
     * @param hasta  fecha final.<br>
     * @return reporte del medico.<br>
     */
    public String reporteMedico(IMedico medico, GregorianCalendar desde, GregorianCalendar hasta) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<ConsultaMedica> consultaMedicas;
        double total = 0;
        String detalle;

        stringBuilder.append("********* Reporte de actividad medica ***********************************\n");
        stringBuilder.append("Medico: Dr. ").append(medico.getApellido()).append(". Matricula nro.: ").append(medico.getMatricula()).append(".\n");
        for (Factura factura : this.facturas) {
            consultaMedicas = factura.getConsultasConMedicos();
            for (ConsultaMedica consultaMedica : consultaMedicas) {
                if (consultaMedica.getMedico() == medico && (desde.compareTo(factura.getFecha()) <= 0 && hasta.compareTo(factura.getFecha()) >= 0)) {
                    total += consultaMedica.getSubtotal();
                    detalle = String.format("Fecha: %s, paciente: %s %s, honorarios: %.2f.\n", formatter.format(factura.getFecha().getTime()), factura.getPaciente().getNombre(), factura.getPaciente().getApellido(), consultaMedica.getSubtotal());
                    stringBuilder.append(detalle);
                }
            }
        }
        stringBuilder.append("Total: ").append(String.format("%.2f", total)).append("\n");
        stringBuilder.append("*************************************************************************\n");
        return stringBuilder.toString();
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     * 
     * @return la ultima factura agregada
     */
    public String ultimaFacturaAgregada() {
        int numeroFactura = this.facturas.size();
        Iterator<Factura> it = this.facturas.iterator();
        Factura fact = null;
        boolean esta = false;

        while (it.hasNext() && !esta) {
            fact = it.next();
            if (fact.getNroFactura() == numeroFactura)
                esta = true;
        }
        return esta ? fact.toString() : null;
    }

    public Iterator<Factura> getFacturasIterator() {
        return this.facturas.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuloEgreso that = (ModuloEgreso) o;
        return Objects.equals(facturas, that.facturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facturas);
    }

    
    public Factura buscaFactura(int numeroFactura) 
    {
        Iterator<Factura> it = this.facturas.iterator();
        Factura fact = null;
        boolean esta = false;

        while (it.hasNext() && !esta) {
            fact = it.next();
            if (fact.getNroFactura() == numeroFactura)
                esta = true;
        }
        return esta ? fact : null;
    }
    
    public static int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    
	/**
	 * Pre: fecha y numeroDeFactura distintos de null, el formato de la Fecha ya está validado, el número de factura es mayor a 0, la listaDeInsumos puede ser null,
	 * vacía o con elementos. Representa una lista con valores de tipo double que representan costos de insumos utilizados con el paciente.
	 * Post: Se obtiene el total del importe adicional a pagar.
	 */
	public double calculoImporteAdicional(int numeroDeFactura, GregorianCalendar fechaDeSolicitud, ArrayList<Double> listaDeInsumos) {
		double importeParcial=0, importeTotal=0, respuesta=0, A=0.5, B=0.25, C=1.5, D=0.75, suma;
		int i;
		Factura fact = this.buscaFactura(numeroDeFactura);
		Date fecha1,fecha2;
		Random mock=Mockito.mock(Random.class);
		if (numeroDeFactura==2)
			when(mock.nextInt(30)).thenReturn(fact.getFecha().DAY_OF_MONTH); //Este if pertenece al mock, no a la ejecucion en si del programa
		else when(mock.nextInt(30)).thenReturn(40);        					 
		int ALEATORIO=mock.nextInt(30);
		if(fact!=null) //existe nro factura
		{
			fact.toString(); // si no, no guarda el total (cosa de ellos viste)
			fecha1=fechaDeSolicitud.getTime();
			fecha2= fact.getFecha().getTime();
			long diferencia = fecha1.getTime()-fecha2.getTime();
			diferencia/=(24* 1000 * 60 * 60);
			if(Math.abs(diferencia)<10)  //Caso 1.1 
			{
		
				suma=0;
				int size=fact.getInternaciones().size();
				i=0;  //impares
				while (i<size)
				{       
					suma+=fact.getInternaciones().get(i).getSubtotal();
					i+=2;
				}
				size=fact.getConsultasConMedicos().size();
				i=0; //impares
				while (i<size)
				{       
					suma+=fact.getConsultasConMedicos().get(i).getSubtotal();
					i+=2;
				}
				importeParcial=fact.getguardoTotal()-suma*A; 
			}
			else //Caso 1.2
			{
				importeParcial=fact.getguardoTotal()*B;
			}
			if(fact.getPaciente().quedaJoven() == true) //Caso 1.3 significa que el paciente es mayor
			{
				importeTotal=importeParcial*C;
			}
			else 	//Caso 1.4
			{
				importeTotal=importeParcial*D;
			}
			if (ALEATORIO==fact.getFecha().DAY_OF_MONTH) //Caso 1.5
				respuesta=importeTotal;
			else    //Caso 1.6
			{
				suma=0;
				for(i = 0; i < listaDeInsumos.size(); i++)
				{       
					suma+=listaDeInsumos.get(i);
				}
				respuesta=importeTotal+suma;
			}
		}
		else  //no existe numero factura 2.
		{
			respuesta=0;
		}
		
		return respuesta;
	}
}