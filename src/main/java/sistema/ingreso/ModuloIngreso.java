package sistema.ingreso;

import sistema.excepciones.SalaDeEsperaVaciaException;
import sistema.personas.pacientes.Paciente;
import sistema.personas.pacientes.PacienteFactory;

import java.util.*;

/**
 * Clase que modela el modulo de ingreso de la clinica.<br>
 * Se ocupa se asignar numero de orden al ingresar un paciente en la espera ( vip o patio).<br>
 * Si ya esta registrado, alta solo devuelve la ref y al ingresar lo pisa con una nuevo valor.<br>
 * <b>Invariante: </b> registro de pacientes, listaDeEspera y lista de pacientesEnPatio distinto de null.<br>
 */
public class ModuloIngreso {
    private static int nroOrden = 0;
    private HashMap<Integer, Paciente> registroDePacientes = new HashMap<>();
    private Queue<Paciente> listaDeEspera = new LinkedList<>();
    private ArrayList<Paciente> listaPacientesEnPatio = new ArrayList<>();
    private Paciente salaVip;

    public ModuloIngreso() {

    }

    public static int getNroOrden() {
        return nroOrden;
    }

    public static void setNroOrden(int nroOrden) {
        ModuloIngreso.nroOrden = nroOrden;
    }

    /**
     * Busca al paciente en el registro historico.<br>
     * Si lo encuentra devuelve su referencia.<br>
     * Si no, lo crea, lo agrega al registro historico y devuelve su referencia.<br>
     *
     * <b>Pre: </b> nombre, apellido, direccion, ciudad, teñetono, rangoEtario distinto de null y no vacio; dni entero positivo.<br>
     * <b>Post:</b> Devuelve referencia a un paciente o null si el paciente no se encuentra registrado y el rango
     * etario es distinto de "joven", "nino" o "mayor". <br>
     *
     * @param nombre      distinto de null y no vacio
     * @param apellido    distinto de null y no vacio
     * @param direccion   distinto de null y no vacio
     * @param ciudad      distinto de null y no vacio
     * @param telefono    distinto de null y no vacio
     * @param dni         entero positivo
     * @param rangoEtario distinto de null y no vacio
     * @return referencia al paciente o null si el paciente no se encuentra registrado y el rango
     * etario es distinto de "joven", "nino" o "mayor". <br>
     */
    public Paciente altaPaciente(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni, String rangoEtario) {
        Paciente paciente;
        if (this.registroDePacientes.containsKey(dni)) {
            return this.registroDePacientes.get(dni);
        } else {
            paciente = PacienteFactory.getPaciente(nombre, apellido, direccion, ciudad, telefono, dni, rangoEtario);
            this.registroDePacientes.put(dni, paciente);
            return paciente;
        }
    }

    /**
     * Ingresa paciente para atencion, si no se encuentra presente en la lista, otorgadole un numero de orden y
     * ubicandolo en sala vip o patio segun corresponda.<br>
     * <p>
     * Si el paciente ya esta presente en la lista de espera el metodo no tiene efecto.<br>
     *
     * <b>Pre: </b> paciente distinto de null.<br>
     * <b>Post: </b> se asigna al paciente un numero de orden y se lo ubica en la sala de espera o en el patio.<br>
     *
     * @param paciente Paciente a ingresar a antencion. Debe ser distinto de null.<br>
     */
    public void ingresoPaciente(Paciente paciente) {
        if (!this.listaDeEspera.contains(paciente)) {
            ModuloIngreso.nroOrden++;
            paciente.setNroOrden(nroOrden);
            this.listaDeEspera.add(paciente);
            if (this.salaVip == null) {
                this.salaVip = paciente;
            } else {
                if (this.salaVip.quedaEnSalaVipFrenteA(paciente))
                    this.listaPacientesEnPatio.add(paciente);
                else {
                    this.listaPacientesEnPatio.add(this.salaVip);
                    this.salaVip = paciente;
                }
            }
        }
    }

    /**
     * Devuelve el proximo paciente en espera de la cola para atender.<br>
     *
     * @return proximo paciente a atender en el orden que le corresponde.<br>
     * @throws SalaDeEsperaVaciaException si no hay pacientes en sala de espera.<br>
     */
    public Paciente getPacienteParaAtender() throws SalaDeEsperaVaciaException {

        if (this.listaDeEspera.isEmpty())
            throw new SalaDeEsperaVaciaException("Sala de espera vacia");
        else {
            Paciente paciente = this.listaDeEspera.remove();
            if (paciente == this.salaVip)
                this.salaVip = null;
            else
                this.listaPacientesEnPatio.remove(paciente);
            return paciente;
        }
    }

    /**
     * Devuelve la cantidad de personas en espera de ser atendidas.<br>
     *
     * @return cantidad de personas en espera de ser atendidas.<br>
     */
    public int cantidadDePacientesEnEspera() {
        return this.listaDeEspera.size();
    }

    /**
     * Devuelve la cantidad de personas esperando en el patio.<br>
     *
     * @return cantidad de personas esperando en el patio.<br>
     */
    public int cantidadDePacientesEnPatio() {
        return this.listaPacientesEnPatio.size();
    }

    /**
     * @return true or false si salaVip esta ocupada
     */
    public boolean salaVipOcupada() {
        return this.salaVip != null;
    }

    public boolean pacienteRegistrado(Paciente paciente) {
        return this.registroDePacientes.containsKey(paciente.getDni());
    }

    public HashMap<Integer, Paciente> getRegistroDePacientes() {
        return registroDePacientes;
    }

    public void setRegistroDePacientes(HashMap<Integer, Paciente> registroDePacientes) {
        this.registroDePacientes = registroDePacientes;
    }

    public Queue<Paciente> getListaDeEspera() {
        return listaDeEspera;
    }

    public void setListaDeEspera(Queue<Paciente> listaDeEspera) {
        this.listaDeEspera = listaDeEspera;
    }

    public ArrayList<Paciente> getListaPacientesEnPatio() {
        return listaPacientesEnPatio;
    }

    public void setListaPacientesEnPatio(ArrayList<Paciente> listaPacientesEnPatio) {
        this.listaPacientesEnPatio = listaPacientesEnPatio;
    }

    public Paciente getSalaVip() {
        return salaVip;
    }

    public void setSalaVip(Paciente salaVip) {
        this.salaVip = salaVip;
    }

    public Iterator<Paciente> getRegistroDePacientesIterator() {
        return this.registroDePacientes.values().iterator();
    }

    public Iterator<Paciente> getPacientesEnListaDeEsperaIterator() {
        return this.listaDeEspera.iterator();
    }

    public Iterator<Paciente> getPacientesEnPatioIterator() {
        return this.listaPacientesEnPatio.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuloIngreso that = (ModuloIngreso) o;
        return Objects.equals(registroDePacientes, that.registroDePacientes) && Objects.equals(listaDeEspera, that.listaDeEspera) && Objects.equals(listaPacientesEnPatio, that.listaPacientesEnPatio) && Objects.equals(salaVip, that.salaVip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registroDePacientes, listaDeEspera, listaPacientesEnPatio, salaVip);
    }
}