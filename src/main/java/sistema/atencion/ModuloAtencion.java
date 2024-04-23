package sistema.atencion;

import sistema.facturacion.ConsultaMedica;
import sistema.facturacion.Internacion;
import sistema.personas.pacientes.Paciente;

import java.util.*;

/**
 * Clase que modela el modulo de atencion de la clinica.<br>
 * <b>Invariante: </b> historiasClinicas y pacientesEnAtencion distintos de null.<br>
 */
public class ModuloAtencion {
    private HashMap<Paciente, HistoriaClinica> historiasClinicas = new HashMap<>();
    private ArrayList<Paciente> pacientesEnAtencion = new ArrayList<>();

    /**
     * Costructor vacio.<br>
     */
    public ModuloAtencion() {

    }

    /**
     * Agrega al paciente a la lista de pacientes en atencion.<br>
     * <b>Pre: </b> paciente distinto de null.<br>
     * <b>Post: </b> Si el paciente no se encontraba previamente en la lista de atencion se lo agrega,
     * sino no tiene efecto.<br>
     *
     * @param paciente Paciente a agregar a la lista de atencion.<br>
     */
    public void atenderPaciente(Paciente paciente) {
        if (!this.pacientesEnAtencion.contains(paciente))
            this.pacientesEnAtencion.add(paciente);
    }

    /**
     * Devuelve el paciente de la lista de atencion cuyo dni es el sumistrado, eliminandolo de la
     * lista de pacientes en atencion.<br>
     * <b>Pre: </b> dni mayor a 0.<br>
     * <b>Post: </b> Devuelve el paciente cuyo dni es el pasado por parametro, eliminandolo de pacientes en atencion, o null si no se encuentra.<br>
     *
     * @param dni DNI del paciente; dni mayor a 0.<br>
     * @return referencia a un pacinte o null en caso de no existir paciente con ese dni.<br>
     */
    public Paciente egresoPaciente(int dni) {
        Iterator<Paciente> it = this.pacientesEnAtencion.iterator();
        boolean esta = false;
        Paciente paciente = null;

        while (it.hasNext() && !esta) {
            paciente = it.next();
            if (paciente.getDni() == dni) {
                esta = true;
                this.pacientesEnAtencion.remove(paciente);
            }
        }
        return paciente;
    }

    /**
     * Devuelve la cantidad de pacientes que hay en atencion. <br>
     *
     * @return pacientesEnAtencion.size()
     */
    public int cantidadDePacientesEnAtencion() {
        return this.pacientesEnAtencion.size();
    }

    public ArrayList<Paciente> getPacientesEnAtencion() {
        return pacientesEnAtencion;
    }

    public void setPacientesEnAtencion(ArrayList<Paciente> pacientesEnAtencion) {
        this.pacientesEnAtencion = pacientesEnAtencion;
    }

    public Iterator<Paciente> getPacientesEnAtencionIterator() {
        return this.pacientesEnAtencion.iterator();
    }

    public HashMap<Paciente, HistoriaClinica> getHistoriasClinicas() {
        return historiasClinicas;
    }

    public void setHistoriasClinicas(HashMap<Paciente, HistoriaClinica> historiasClinicas) {
        this.historiasClinicas = historiasClinicas;
    }

    public Set<Map.Entry<Paciente, HistoriaClinica>> getHistoriasClinicasIterator() {
        return this.historiasClinicas.entrySet();
    }

    /**
     * Comprueba si el paciente tiene asociada una hisotia clinica<br>
     * <b>pre: </b> paciente distinto de null.<br>
     *
     * @return true si el paciente posee una historia clinica, false de lo contrario.<br>
     */
    public boolean existeHistoriaClinicaDePaciente(Paciente paciente) {
        return this.historiasClinicas.containsKey(paciente);
    }

    /**
     * Genera una nueva historia clinica para el paciente.<br>
     * <b>pre: </b> paciente distinto de null.<br>
     * <b>post: </b> se genera una nueva historia clinica vacia para el paciente.<br>
     *
     * @param paciente paciente a generar nueva historia clinica; distinto de null.<br>
     */
    public void nuevaHistoriaClinica(Paciente paciente) {
        this.historiasClinicas.put(paciente, new HistoriaClinica(paciente.getNroHistoriaClinica()));
    }

    /**
     * Agregar Internacion a historia clinica de paciente.<br>
     * <b>pre: </b> paciente e internacion distintos de null.<br>
     * <b>post: la historia clinica del paciente tiene una nueva internacion.</b>
     *
     * @param paciente    paciente a agregar internacion; distinto de null.<br>
     * @param internacion internacion; distinto de null.<br>
     */
    public void agregarInternacionPaciente(Paciente paciente, Internacion internacion) {
        HistoriaClinica historiaClinica = this.historiasClinicas.get(paciente);
        historiaClinica.agregarInternacion(internacion);
    }

    /**
     * Agregar consulta medica a historia clinica de paciente.<br>
     * <b>pre: </b> paciente y consultaMedica distintos de null.<br>
     * <b>post: la historia clinica del paciente tiene una nueva consulta medica.</b>
     *
     * @param paciente       paciente a agregar consulta medica; distinto de null.<br>
     * @param consultaMedica consultaMedica; distinto de null.<br>
     */
    public void agregarConsultaMedicaPaciente(Paciente paciente, ConsultaMedica consultaMedica) {
        HistoriaClinica historiaClinia = this.historiasClinicas.get(paciente);
        historiaClinia.agregarConsultaMedica(consultaMedica);
    }

    /**
     * <b>pre: </b> paciente distinto de null.<br>
     *
     * @param paciente distinto de null.<br>
     * @return historia clinica del paciente o null en caso de no encontrarse.<br>
     */
    public HistoriaClinica getHistoriaClinicaPaciente(Paciente paciente) {
        return this.historiasClinicas.getOrDefault(paciente, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuloAtencion that = (ModuloAtencion) o;
        return Objects.equals(historiasClinicas, that.historiasClinicas) && Objects.equals(pacientesEnAtencion, that.pacientesEnAtencion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historiasClinicas, pacientesEnAtencion);
    }
}