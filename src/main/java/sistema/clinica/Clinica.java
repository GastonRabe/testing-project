package sistema.clinica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sistema.atencion.HistoriaClinica;
import sistema.atencion.ModuloAtencion;
import sistema.egreso.ModuloEgreso;
import sistema.excepciones.ContratacionNoValidaException;
import sistema.excepciones.EspecialidadNoValidaException;
import sistema.excepciones.InformacionPersonalNoValidaException;
import sistema.excepciones.MatriculaIncorrectaException;
import sistema.excepciones.PacienteInexistenteException;
import sistema.excepciones.PosgradoNoValidoException;
import sistema.excepciones.SalaDeEsperaVaciaException;
import sistema.facturacion.ConsultaMedica;
import sistema.facturacion.Internacion;
import sistema.habitaciones.Habitacion;
import sistema.habitaciones.HabitacionCompartida;
import sistema.habitaciones.HabitacionPrivada;
import sistema.habitaciones.HabitacionTerapiaIntensiva;
import sistema.ingreso.ModuloIngreso;
import sistema.personas.medicos.IMedico;
import sistema.personas.medicos.factory.MedicoFactory;
import sistema.personas.pacientes.Paciente;

/**
 * @author Grupo 4
 * Clase que representa a la clinica.<br>
 * Aplica patron Singleton.<br>
 * Contiene todos los medicos<br>
 * Contiene todos los pacientes a traves de la clase ModuloIngreso.<br>
 *
 * <b>Invariante: </b> medicos, moduloIngreso, moduloAtencion, moduloEgreso distintos de null.<br>
 */
public class Clinica {
    private static Clinica instance = null;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private HashMap<Integer, IMedico> medicos = new HashMap<>();
    private ModuloIngreso moduloIngreso = new ModuloIngreso();
    private ModuloAtencion moduloAtencion = new ModuloAtencion();
    private ModuloEgreso moduloEgreso = new ModuloEgreso();

    /**
     * Patron singleton.<br>
     */
    private Clinica() {

    }

    /**
     * Patron singleton.<br>
     */
    public static Clinica getInstance() {
        if (Clinica.instance == null)
            Clinica.instance = new Clinica();
        return Clinica.instance;
    }
    
    
    public double calculoImporteAdicional(int numeroDeFactura, GregorianCalendar fechaDeSolicitud, Paciente pax)
    {
    	return this.moduloEgreso.calculoImporteAdicional(numeroDeFactura, fechaDeSolicitud,this.moduloAtencion.getHistoriaClinicaPaciente(pax).getListaDeInsumos());
    }
    
    private static boolean validarDatosPersona(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni) {
        boolean sonValidos = true;

        sonValidos &= nombre != null && !nombre.equals("");
        sonValidos &= apellido != null && !apellido.equals("");
        sonValidos &= direccion != null && !direccion.equals("");
        sonValidos &= ciudad != null && !ciudad.equals("");
        sonValidos &= telefono != null && !telefono.equals("");
        sonValidos &= dni > 0;
        return sonValidos;
    }

    private static boolean validarDatosPaciente(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni, String rangoEtario) {
        boolean sonValidos = Clinica.validarDatosPersona(nombre, apellido, direccion, ciudad, telefono, dni);

        sonValidos &= rangoEtario != null && !rangoEtario.equals("") &&
                (rangoEtario.equalsIgnoreCase("joven") ||
                        rangoEtario.equalsIgnoreCase("nino") ||
                        rangoEtario.equalsIgnoreCase("mayor"));
        return sonValidos;
    }

    private static boolean validarDatosMedico(String nombre, String apellido, String direccion, String ciudad, String telefono, int dni, int matricula) {
        boolean sonValidos = Clinica.validarDatosPersona(nombre, apellido, direccion, ciudad, telefono, dni);

        sonValidos &= matricula > 0;
        return sonValidos;
    }

    /**
     * Ingresa un nuevo medico a la clinica.<br>
     * <b>Post: </b> Se agrega un nuevo medico a la clinica.<br>
     *
     * @param especialidad Especialidad del medico: Clinica, Cirugia o Pediatria..<br>
     * @param posgrado     Posgrado: Doctor, Magister.<br>
     * @param contratacion Tipo de contratacion: Permanente, Temporario.<br>
     * @param nombre       Nombre del medico.<br>
     * @param apellido     Apellido del medico.<br>
     * @param direccion    Direccion del medico.<br>
     * @param ciudad       Ciudad de residencia del medico.<br>
     * @param telefono     Telefono de contacto del medico.<br>
     * @param dni          Dni del medico.<br>
     * @param matricula    Matricula del medico.<br>
     * @throws InformacionPersonalNoValidaException si alguno de los parametros String es null o vacio, o si dni o matricula no son enteros positivos.<br>
     * @throws EspecialidadNoValidaException        si la especialidad es distinta de Clinica, Cirugia o Pediatria.
     * @throws PosgradoNoValidoException            si el posgrado es distinto de Doctor, Magister.
     * @throws ContratacionNoValidaException        si la contratacion es distinta de Permanente, Temporario
     */
    public void agregarMedico(String especialidad, String posgrado, String contratacion, String nombre, String apellido,
                              String direccion, String ciudad, String telefono, int dni, int matricula)
            throws InformacionPersonalNoValidaException, EspecialidadNoValidaException,
            PosgradoNoValidoException, ContratacionNoValidaException {

        if (!Clinica.validarDatosMedico(nombre, apellido, direccion, ciudad, telefono, dni, matricula))
            throw new InformacionPersonalNoValidaException("Informacion personal no valida.");
        IMedico medico = MedicoFactory.getMedico(especialidad, posgrado, contratacion, nombre, apellido, direccion, ciudad, telefono, dni, matricula);
        this.medicos.put(matricula, medico);
    }

    /**
     * Devuelve el medico cuya matricula es la solicitada.<br>
     * <b>Pre: </b> matricula debe ser un entero positivo.<br>
     *
     * @param matricula Matricula del medico a buscar.<br>
     * @return Medico cuya matricula se pasa por parametro.<br>
     * @throws MatriculaIncorrectaException si no existe medico asociado a esa matricula.<br>
     */
    public IMedico getMedico(int matricula) throws MatriculaIncorrectaException {

        IMedico medico = this.medicos.getOrDefault(matricula, null);
        if (medico == null)
            throw new MatriculaIncorrectaException("No existe medico asociado a esa matricula");
        else
            return medico;
    }

    /**
     * Da de alta a un paciente.<br>
     * Si el paciente ha sido atendido previamente en la clinica, se lo busca y retorna su referencia.<br>
     * Si se trata de un paciente nuevo, lo crea, se guarda en el registro historico, crea una nueva historia clinica
     * y se devuelve su referencia.<br>
     *
     * @param nombre      Nombre del paciente.
     * @param apellido    Apellido del paciente.
     * @param direccion   Direccion del paciente.
     * @param ciudad      Ciudad del paciente.
     * @param telefono    Telefono del paciente.
     * @param dni         DNI del paciente.
     * @param rangoEtario Rango etario de paciente.
     * @return referencia al paciente.
     * @throws InformacionPersonalNoValidaException si alguno de los parametros String es null o vacio, si dni no es un etero positivo o si
     *                                              rangoEtario no se corresponde con "joven", "mayor", o "nino"
     */
    
    
    public Paciente altaPaciente(String nombre, String apellido, String direccion,
                                 String ciudad, String telefono, int dni, String rangoEtario)
            throws InformacionPersonalNoValidaException {

        if (!Clinica.validarDatosPaciente(nombre, apellido, direccion, ciudad, telefono, dni, rangoEtario))
            throw new InformacionPersonalNoValidaException("Informacion personal no valida.");
        Paciente paciente = this.moduloIngreso.altaPaciente(nombre, apellido, direccion, ciudad, telefono, dni, rangoEtario);
        if (!this.moduloAtencion.existeHistoriaClinicaDePaciente(paciente))
            this.moduloAtencion.nuevaHistoriaClinica(paciente);
        return paciente;
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
     * @param paciente Paciente a ingresar a sala de espera.<br>
     * @throws IllegalArgumentException si paciente es igual a null.<br>
     */
    public void ingresarPaciente(Paciente paciente) throws IllegalArgumentException {
        if (paciente == null)
            throw new IllegalArgumentException("No se puede ingresar un paciente nulo");
        this.moduloIngreso.ingresoPaciente(paciente);
    }

    /**
     * Devuelve la cantidad total de personas en sala de espera (patio + vip).<br>
     *
     * @return cantidad de personas en espera de ser atendidas.<br>
     */
    public int cantidadDePacientesEnSalaEspera() {
        return this.moduloIngreso.cantidadDePacientesEnEspera();
    }

    /**
     * Retira al primer paciente de la lista de espera del modulo de ingreso para ser atendido,
     * ingresandolo en la lista de atencion en el modulo de atencion.<br>
     *
     * <b>Post: </b> Se retira el proximo paciente a ser atendido ingreandolo a la lista de pacientes en atencion.<br>
     *
     * @throws SalaDeEsperaVaciaException si no hay pacientes esperando ser atendidos.<br>
     */
    public void atenderSiguentePaciente() throws SalaDeEsperaVaciaException {
        Paciente pacienteParaAtender = this.moduloIngreso.getPacienteParaAtender();
        this.moduloAtencion.atenderPaciente(pacienteParaAtender);
    }

    /**
     * Devuelve la cantidad total de personas en atencion.<br>
     *
     * @return cantidad de personas en atencion.<br>
     */
    public int cantidadDePacientesEnAtencion() {
        return this.moduloAtencion.cantidadDePacientesEnAtencion();
    }

    /**
     * Agregar Internacion a historia clinica de paciente.<br>
     * <b>post: la historia clinica del paciente tiene una nueva internacion.</b>
     *
     * @param paciente    paciente a agregar internacion<br>
     * @param internacion internacion.<br>
     * @throws IllegalArgumentException     si paciente o intercacion son null.<br>
     * @throws PacienteInexistenteException si el paciente no esta registrado en la clinica.<br>
     */
    public void agregarInternacionPaciente(Paciente paciente, Internacion internacion) throws IllegalArgumentException, PacienteInexistenteException {
        if (paciente == null || internacion == null)
            throw new IllegalArgumentException("No se puede registrar parametros nulos");
        if (!this.moduloIngreso.pacienteRegistrado(paciente))
            throw new PacienteInexistenteException("El paciente no esta registrado en la clinica");
        this.moduloAtencion.agregarInternacionPaciente(paciente, internacion);
    }

    /**
     * Agregar consulta medica a historia clinica de paciente.<br>
     * <b>post: la historia clinica del paciente tiene una nueva consulta medica.</b>
     *
     * @param paciente       paciente a agregar consulta medica.<br>
     * @param consultaMedica consultaMedica.<br>
     * @throws IllegalArgumentException     si paciente o consultaMedica son null.<br>
     * @throws PacienteInexistenteException si el paciente no esta registrado en la clinica.<br>
     */
    public void agregarConsultaMedicaPaciente(Paciente paciente, ConsultaMedica consultaMedica) throws IllegalArgumentException, PacienteInexistenteException {
        if (paciente == null || consultaMedica == null)
            throw new IllegalArgumentException("No se puede registrar parametros nulos");
        if (!this.moduloIngreso.pacienteRegistrado(paciente))
            throw new PacienteInexistenteException("El paciente no esta registrado en la clinica");
        this.moduloAtencion.agregarConsultaMedicaPaciente(paciente, consultaMedica);
    }

    /**
     * <b>pre: </b> paciente distinto de null.<br>
     *
     * @param paciente distinto de null.<br>
     * @return historia clinica del paciente o null en caso de no encontrarse.<br>
     * @throws PacienteInexistenteException si el paciente no esta ingresado a la clinica.<br>
     */
    public HistoriaClinica getHistoriaClinicaPaciente(Paciente paciente) throws PacienteInexistenteException {
        if (!this.moduloIngreso.pacienteRegistrado(paciente))
            throw new PacienteInexistenteException("El paciente no pertenece a la clinica");
        return this.moduloAtencion.getHistoriaClinicaPaciente(paciente);
    }

    /**
     * Selecciona un paciente dado su dni de aquellos en atencion, devolviendolo y removiendolo de la lista de
     * pacientes en atencion.<br>
     * <b>Pre: </b> El dni debe ser mayor a cero.<br>
     * <b>Post: </b> Se devuelve el paciente cuyo documento es dni.<br>
     *
     * @param dni DNI del paciente a buscar. Debe ser un entero positivo.<br>
     * @return Paciente cuyo dni es el pasado por parametro.<br>
     * @throws PacienteInexistenteException en caso de no encontrarse un paciente con ese dni en atencion.<br>
     */
    public Paciente egresoPaciente(int dni) throws PacienteInexistenteException {
        Paciente paciente = this.moduloAtencion.egresoPaciente(dni);
        if (paciente == null)
            throw new PacienteInexistenteException("No existe paciente en atencion con el dni indicado");
        else
            return paciente;
    }

    /**
     * Genera una factura con todas las intervenciones no facturadas de la historia clinica del paciente.<br>
     * <b>post: </b> se genera una nueva factura.<br>
     *
     * @param paciente paciente a facturar
     * @param fecha    fecha de la factura
     * @throws IllegalArgumentException     si paciente o fecha son null.<br>
     * @throws PacienteInexistenteException si el paciente no esta registrado en la clinica.<br>
     */
    public void facturar(Paciente paciente, GregorianCalendar fecha) throws IllegalArgumentException, PacienteInexistenteException {
        if (paciente == null || fecha == null)
            throw new IllegalArgumentException("Debe indicarse un paciente y una fecha para poder facturar.");
        if (!this.moduloIngreso.pacienteRegistrado(paciente))
            throw new PacienteInexistenteException("El paciente no pertenece a la clinica");
        this.moduloEgreso.facturar(paciente, this.moduloAtencion.getHistoriaClinicaPaciente(paciente), fecha);
    }

    /**
     * Busca una determinada factura por numero y devuelve su detalle o null si no la encuentra.
     *
     * @param numeroFactura mayor a 0, numero de la factura a buscar.<br>
     * @return detalle de la factura o null.<br>
     */
    public String getDetalleFactura(int numeroFactura) {
        return this.moduloEgreso.getFactura(numeroFactura);
    }

    /**
     * Genera reporte medico para un medico determinado, entre dos fechas estipuladas.<br>
     * <b>Pre:</b> medico distinto de null, desde menor o igual a hasta.<br>
     * <b>Post:</b> se genera el reporte medico.<br>
     *
     * @param medico Medico al cual se le hace el reporte.<br>
     * @param desde  Fecha Inicial.<br>
     * @param hasta  Fecha Final.<br>
     * @return reporte del medico.<br>
     */
    public String getReporteMedico(IMedico medico, GregorianCalendar desde, GregorianCalendar hasta) {
        return this.moduloEgreso.reporteMedico(medico, desde, hasta);
    }

    /**
     * Devuelve un iterardor con las Habitaciones (Singletons) presentes en la clinica.<br>
     *
     * @return iterador de habitaciones.<br>
     */
    public Iterator<Habitacion> getHabitacionesIterator() {
        List<Habitacion> list = new ArrayList<>();
        list.add(HabitacionPrivada.getInstance());
        list.add(HabitacionCompartida.getInstance());
        list.add(HabitacionTerapiaIntensiva.getInstance());
        return list.iterator();
    }

    public Iterator<IMedico> getMedicosIterator() {
        return this.medicos.values().iterator();
    }

    public Iterator<Paciente> getPacientesEnSalaEsperaIterator() {
        return this.moduloIngreso.getPacientesEnListaDeEsperaIterator();
    }

    public Iterator<Paciente> getPacientesEnAtencionIterator() {
        return this.moduloAtencion.getPacientesEnAtencionIterator();
    }

    /**
     * Metodo ToString que brinda una breve descripcion de la clinica.<br>
     */
    @Override
    public String toString() {
        return "Clinica [" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", telefono=" + telefono +
                ']';
    }

    /**
     * Genera un reporte de la situacion actual de la clinica, indicando cuantos pacientes hay en espera de ser atendidos
     * Indica la cantidad de pacientes en el patio y si hay alguien en la sala VIP
     *
     * @return String     Contiene toda la descripcion mencionada
     */
    public String estadoDeLaClinica() {
        return "Estado de la clinica:\nPacientes en sala de espera: " + this.moduloIngreso.cantidadDePacientesEnEspera() + "\n" +
                "   - en patio: " + this.moduloIngreso.cantidadDePacientesEnPatio() + "\n" +
                "   - en sala vip: " + ((this.moduloIngreso.salaVipOcupada()) ? 1 : 0) + "\n" +
                "Pacientes en atencion: " + this.moduloAtencion.cantidadDePacientesEnAtencion() + "\n";
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Setea el nombre de la Clinica.<br>
     *
     * @param nombre String que contiene nombre de la clinica.<br>
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    /**
     * Setea la direccion de la clinica.<br>
     *
     * @param direccion String que contiene la direccion de la clinica.<br>
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    /**
     * Setea la ciudad de la clinica.<br>
     *
     * @param ciudad String que contiene la ciudad en donde funciona la clinica.<br>
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    /**
     * Setea el telefono de la clinica.<br>
     *
     * @param telefono que almacena el telefono de la clinica.<br>
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Getter
     *
     * @return medicos
     */
    public HashMap<Integer, IMedico> getMedicos() {
        return medicos;
    }

    /**
     * Setter
     *
     * @param medicos medicos
     */
    public void setMedicos(HashMap<Integer, IMedico> medicos) {
        this.medicos = medicos;
    }

    /**
     * Getter
     *
     * @return moduloIngreso
     */
    public ModuloIngreso getModuloIngreso() {
        return moduloIngreso;
    }

    /**
     * Setter
     *
     * @param moduloIngreso moduloIngreso
     */
    public void setModuloIngreso(ModuloIngreso moduloIngreso) {
        this.moduloIngreso = moduloIngreso;
    }

    /**
     * Getter
     *
     * @return moduloAtencion
     */
    public ModuloAtencion getModuloAtencion() {
        return moduloAtencion;
    }

    /**
     * Setter
     *
     * @param moduloAtencion moduloAtencion
     */
    public void setModuloAtencion(ModuloAtencion moduloAtencion) {
        this.moduloAtencion = moduloAtencion;
    }

    /**
     * Getter
     *
     * @return moduloEgreso
     */
    public ModuloEgreso getModuloEgreso() {
        return moduloEgreso;
    }

    /**
     * Setter
     *
     * @param moduloEgreso moduloEgreso
     */
    public void setModuloEgreso(ModuloEgreso moduloEgreso) {
        this.moduloEgreso = moduloEgreso;
    }

    /**
     * Devuelve una lista con todos los pacientes en atencion de la clinica.<br>
     *
     * @return pacientes en atencion
     */
    public ArrayList<Paciente> getPacientesEnAtencion() {
        return this.moduloAtencion.getPacientesEnAtencion();
    }

    /**
     * Devuelve una lista con todos los medicos de la clinica.<br>
     *
     * @return medicos en la clinica.
     */
    public ArrayList<IMedico> getListaMedicos() {
        return new ArrayList<>(this.medicos.values());
    }

    /**
     * Devuelve detalle ultima factura.
     *
     * @return detalle ultima factura.
     */
    public String getDetalleUltimaFactura() {
        return this.moduloEgreso.ultimaFacturaAgregada();
    }

    /**
     * Devuelve un iterator con los datos de paciente e historia clinica.<br>
     *
     * @return Iterador de historias clinicas de pacientes de la clinica.
     */
    public Set<Map.Entry<Paciente, HistoriaClinica>> getHistoriasClinicasIterator() {
        return this.moduloAtencion.getHistoriasClinicasIterator();
    }
}