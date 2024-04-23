package sistema.persistencia;

import sistema.clinica.Clinica;
import sistema.excepciones.*;
import sistema.habitaciones.Habitacion;
import sistema.habitaciones.HabitacionCompartida;
import sistema.habitaciones.HabitacionPrivada;
import sistema.habitaciones.HabitacionTerapiaIntensiva;
import sistema.persistencia.dto.ClinicaDTO;
import sistema.personas.pacientes.Paciente;

import java.io.IOException;

/**
 * Clase encargada de la pesistencia de los datos.<br>
 */
public class AccesoDatos {
    private static final String FILENAME = "clinica.xml";

    /**
     * @return nombre del archivo XML.
     */
    public static String getFILENAME() {
        return FILENAME;
    }

    /**
     * Despersistir clinica.<br>
     *
     * @return dto de clinica.
     * @throws IOException si existe algun tipo de error asociado a la lectura del archivo.<br>
     */
    public static ClinicaDTO despersistirClinica() throws IOException {
        PersistenciaXML io = new PersistenciaXML();
        ClinicaDTO dto;

        io.openInput(FILENAME);
        dto = (ClinicaDTO) io.read();
        io.closeInput();
        return dto;
    }

    /**
     * Persistir clinica.<br>
     * <b>pre:</b> clinicaDTO distinto de null.<br>
     *
     * @param clinicaDTO dto de clinica, distinto de null
     * @throws IOException si existe algun tipo de error asociado a la escritura del archivo.<br>
     */
    public static void persistirClinica(ClinicaDTO clinicaDTO) throws IOException {
        PersistenciaXML persistenciaXML = new PersistenciaXML();

        persistenciaXML.openOutput(FILENAME);
        persistenciaXML.write(clinicaDTO);
        persistenciaXML.closeOutput();
    }

    /**
     * Metodo auxiliar para inicilizar la clinica con medicos y pacientes por defecto.
     *
     * Estado de la clinica:
     * Pacientes en sala de espera: 4
     *    - en patio: 3
     *    - en sala vip: 1
     * Pacientes en atencion: 3
     */
    public static void initClinica() {
        Habitacion.setCostoAsignacion(100);
        HabitacionCompartida.getInstance().setCostoHabCompartida(10);
        HabitacionPrivada.getInstance().setCostoHabPrivada(20);
        HabitacionTerapiaIntensiva.getInstance().setCostoHabTerapiaIntensiva(30);

        Clinica clinica = Clinica.getInstance();
        clinica.setNombre("HIGA");
        clinica.setCiudad("Mar del Plata");
        clinica.setDireccion("Juan B. Justo 10000");
        clinica.setTelefono("4827593");
//        System.out.println(clinica);
//        System.out.println("*************************************************************************************");

        try {
            clinica.agregarMedico("clinica", "doctor", "permanente", "Rene", "Favaloro", "Alverar 3101", "Buenos Aires", "369258", 8125936, 1);
            clinica.agregarMedico("pediatria", "doctor", "permanente", "Carlos", "Rivas", "Libertador 1401", "Buenos Aires", "4852963", 272589, 2);
            clinica.agregarMedico("clinica", "magister", "temporario", "Marta", "Perez", "Diagonal 3 2589", "La Plata", "155937825", 853259, 3);
            clinica.agregarMedico("cirugia", "magister", "temporario", "Lucia", "Rodriguez", "Mitre 1479", "Mar del Plata", "369258", 15236, 4);
            clinica.agregarMedico("cirugia", "doctor", "temporario", "Lucas", "Gonzales", "Salta 936", "Mar del Plata", "369258", 78932, 5);
            clinica.agregarMedico("pediatria", "doctor", "permanente", "Carolina", "Sanchez", "Falucho 3705", "Buenos Aires", "369258", 93624, 6);
        } catch (InformacionPersonalNoValidaException e) {
            System.out.println(e.getMessage());
        } catch (EspecialidadNoValidaException e) {
            System.out.println(e.getMessage() + " Especialidad invalida = " + e.getEspecialidadInvalida());
        } catch (PosgradoNoValidoException e) {
            System.out.println(e.getMessage() + " Posgrado Invalido = " + e.getPosgradoInvalido());
        } catch (ContratacionNoValidaException e) {
            System.out.println(e.getMessage() + " Contratacion Invalida = " + e.getContratacionInvalida());
        }

//        IMedico medico1 = null;
//        IMedico medico2 = null;
//        IMedico medico3 = null;
//        IMedico medico4 = null;
//        IMedico medico5 = null;
//        IMedico medico6 = null;
//        try {
//            medico1 = clinica.getMedico(1);
//            medico2 = clinica.getMedico(2);
//            medico3 = clinica.getMedico(3);
//            medico4 = clinica.getMedico(4);
//            medico5 = clinica.getMedico(5);
//            medico6 = clinica.getMedico(6);
//        } catch (MatriculaIncorrectaException e) {
//            e.printStackTrace();
//        }

//        System.out.println(medico1);
//        System.out.println(medico2);
//        System.out.println(medico3);
//        System.out.println(medico4);
//        System.out.println(medico5);
//        System.out.println(medico6);
//        System.out.println("*************************************************************************************");

        Paciente paciente1 = null;
        Paciente paciente2 = null;
        Paciente paciente3 = null;
        Paciente paciente4 = null;
        Paciente paciente5 = null;
        Paciente paciente6 = null;
        Paciente paciente7 = null;
        Paciente paciente8 = null;

        try {
            paciente1 = clinica.altaPaciente("Juan Cruz", "Mateos", "Almafuerte 2356", "Mar del Plata", "101", 1, "joven");
            paciente2 = clinica.altaPaciente("Camila", "Ezama", "Formosa 2014", "Mar del Plata", "102", 2, "nino");
            paciente3 = clinica.altaPaciente("Noelia", "Echeverria", "Matheu 3952", "Mar del Plata", "103", 3, "mayor");
            paciente4 = clinica.altaPaciente("Sebastian", "Bengoa", "Quintana 1016", "Mar del Plata", "104", 4, "joven");
            paciente5 = clinica.altaPaciente("Candela", "Ramos", "Primera Junta 1006", "Mar del Plata", "105", 5, "nino");
            paciente6 = clinica.altaPaciente("Marcos", "Jimenez", "Roca 1782", "Mar del Plata", "106", 6, "mayor");
            paciente7 = clinica.altaPaciente("Lucas", "Rodriguez", "Paso 3691", "Mar del Plata", "107", 7, "joven");

            paciente8 = clinica.altaPaciente("Sebastian", "Bengoa", "Quintana 1016", "Mar del Plata", "104", 4, "joven");
        } catch (InformacionPersonalNoValidaException e) {
            e.printStackTrace();
        }
//        System.out.println(paciente1);
//        System.out.println(paciente2);
//        System.out.println(paciente3);
//        System.out.println(paciente4);
//        System.out.println(paciente5);
//        System.out.println(paciente6);
//        System.out.println(paciente7);
//        System.out.println(paciente8);
//        System.out.println("Paciente 4 == Paciente 8: " + (paciente4 == paciente8)); // no permite duplicados
//        System.out.println("*************************************************************************************");

        clinica.ingresarPaciente(paciente1);
        clinica.ingresarPaciente(paciente2);
        clinica.ingresarPaciente(paciente3);
        clinica.ingresarPaciente(paciente4);
        clinica.ingresarPaciente(paciente5);
        clinica.ingresarPaciente(paciente6);
        clinica.ingresarPaciente(paciente7);
        clinica.ingresarPaciente(paciente8); // no se agrega (~ clon)
//        System.out.println(clinica.cantidadDePacientesEnSalaEspera());
//        System.out.println(clinica.cantidadDePacientesEnAtencion());
//        System.out.println("*************************************************************************************");
//        System.out.println(clinica.estadoDeLaClinica());
//        System.out.println(paciente1);
//        System.out.println(paciente2);
//        System.out.println(paciente3);
//        System.out.println(paciente4);
//        System.out.println(paciente5);
//        System.out.println(paciente6);
//        System.out.println(paciente7);
/*
Estado de la clinica:
Pacientes en sala de espera: 7
   - en patio: 6
   - en sala vip: 1
Pacientes en atencion: 0
*/
        try {
            clinica.atenderSiguentePaciente();
            clinica.atenderSiguentePaciente();
            clinica.atenderSiguentePaciente();
            clinica.atenderSiguentePaciente();
        } catch (SalaDeEsperaVaciaException e) {
            e.printStackTrace();
//        }
//        System.out.println("*************************************************************************************");
        System.out.println(clinica.estadoDeLaClinica());
//        System.out.println("*************************************************************************************");
/*
Estado de la clinica:
Pacientes en sala de espera: 4
   - en patio: 3
   - en sala vip: 1
Pacientes en atencion: 3
*/

//        try {
//            paciente1 = clinica.egresoPaciente(1);
//            paciente2 = clinica.egresoPaciente(2);
//            paciente3 = clinica.egresoPaciente(3);
//        } catch (PacienteInexistenteException pacienteInexistenteException) {
//            pacienteInexistenteException.printStackTrace();
//        }
//        System.out.println(clinica.estadoDeLaClinica());
/*
Estado de la clinica:
Pacientes en sala de espera: 4
   - en patio: 3
   - en sala vip: 1
Pacientes en atencion: 0
* */
//        System.out.println(paciente1);
//        System.out.println(paciente2);
//        System.out.println(paciente3);

//        System.out.println(paciente1.getNombre());
//        HistoriaClinica hc = Clinica.getInstance().getModuloAtencion().getHistoriaClinicaPaciente(paciente1);
//        System.out.println(hc);
//
//         intervenciones #1
//        try {
//            clinica.agregarConsultaMedicaPaciente(paciente1, new ConsultaMedica(medico3, 3));
//            clinica.agregarConsultaMedicaPaciente(paciente1, new ConsultaMedica(medico1, 2));
//            clinica.agregarInternacionPaciente(paciente1, new Internacion(HabitacionCompartida.getInstance(), 3));
//            clinica.agregarInternacionPaciente(paciente1, new Internacion(HabitacionPrivada.getInstance(), 1));
//            clinica.agregarInternacionPaciente(paciente1, new Internacion(HabitacionTerapiaIntensiva.getInstance(), 1));
//            clinica.facturar(paciente1, new GregorianCalendar(2018, Calendar.MARCH, 17));
//            System.out.println(clinica.getDetalleUltimaFactura());
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }

//        for (Map.Entry<Paciente, HistoriaClinica> entry : Clinica.getInstance().getHistoriasClinicasIterator()) {
//            System.out.println(entry.getKey().getNombre() + ", dni=" + entry.getKey().getDni());
//            System.out.println(entry.getValue());
//        }
////         intervenciones #2
//        try {
//            clinica.agregarConsultaMedicaPaciente(paciente2, new ConsultaMedica(medico2, 2));
//            clinica.agregarConsultaMedicaPaciente(paciente2, new ConsultaMedica(medico4, 1));
//            clinica.agregarInternacionPaciente(paciente2, new Internacion(HabitacionPrivada.getInstance(), 1));
//            clinica.agregarInternacionPaciente(paciente2, new Internacion(HabitacionTerapiaIntensiva.getInstance(), 1));
//            clinica.facturar(paciente2, new GregorianCalendar(2019, Calendar.APRIL, 15));
//            System.out.println(clinica.getDetalleUltimaFactura());
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }
//
////        intervenciones #3
//        try {
//            clinica.agregarConsultaMedicaPaciente(paciente3, new ConsultaMedica(medico2, 1));
//            clinica.agregarConsultaMedicaPaciente(paciente3, new ConsultaMedica(medico6, 4));
//            clinica.agregarInternacionPaciente(paciente3, new Internacion(HabitacionTerapiaIntensiva.getInstance(), 3));
//            clinica.facturar(paciente3, new GregorianCalendar(2020, Calendar.MAY, 23));
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }

//        System.out.println(clinica.getDetalleFactura(1));
//        System.out.println(clinica.getDetalleFactura(2));
//        System.out.println(clinica.getDetalleFactura(3));
//
//        System.out.println(clinica.getReporteMedico(medico1, new GregorianCalendar(2015, Calendar.FEBRUARY, 1), new GregorianCalendar()));
//        System.out.println(clinica.getReporteMedico(medico2, new GregorianCalendar(2020, Calendar.FEBRUARY, 1), new GregorianCalendar()));
//        System.out.println(clinica.getReporteMedico(medico3, new GregorianCalendar(2020, Calendar.FEBRUARY, 1), new GregorianCalendar()));
//        System.out.println(clinica.getReporteMedico(medico4, new GregorianCalendar(2020, Calendar.FEBRUARY, 1), new GregorianCalendar()));
//        System.out.println(clinica.getReporteMedico(medico5, new GregorianCalendar(2020, Calendar.FEBRUARY, 1), new GregorianCalendar()));
//        System.out.println(clinica.getReporteMedico(medico6, new GregorianCalendar(2020, Calendar.FEBRUARY, 1), new GregorianCalendar()));
//
//        System.out.println(clinica.estadoDeLaClinica());
//        try {
//            clinica.atenderSiguentePaciente();
//            clinica.atenderSiguentePaciente();
//            clinica.atenderSiguentePaciente();
//            clinica.atenderSiguentePaciente();
//        } catch (SalaDeEsperaVaciaException salaDeEsperaVaciaException) {
//            salaDeEsperaVaciaException.printStackTrace();
//        }
////        clinica.atenderPaciente(); // no tiene efecto
//
//        Paciente pepe = null;
//        try {
//            pepe = clinica.altaPaciente("Pepe", "Argento", "Flores 1234", "Buenos Aires", "123654", 325863, "Mayor");
//        } catch (InformacionPersonalNoValidaException e) {
//            e.printStackTrace();
//        }
//        clinica.ingresarPaciente(pepe);
//        System.out.println(clinica.estadoDeLaClinica());
//
//        try {
//            paciente4 = clinica.egresoPaciente(4);
//        } catch (PacienteInexistenteException ex) {
//            ex.printStackTrace();
//        }
//        try {
//            paciente5 = clinica.egresoPaciente(5);
//        } catch (PacienteInexistenteException ee) {
//            e.printStackTrace();
//        }
//        try {
//            paciente6 = clinica.egresoPaciente(6);
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(clinica.estadoDeLaClinica());
//
////        // intervenciones #4
//        try {
//            clinica.agregarConsultaMedicaPaciente(paciente4, new ConsultaMedica(medico5, 2));
//            clinica.agregarConsultaMedicaPaciente(paciente4, new ConsultaMedica(medico3, 3));
//            clinica.agregarInternacionPaciente(paciente4, new Internacion(HabitacionPrivada.getInstance(), 5));
//            clinica.agregarInternacionPaciente(paciente4, new Internacion(HabitacionTerapiaIntensiva.getInstance(), 1));
//            clinica.facturar(paciente4, new GregorianCalendar(2021, Calendar.JULY, 12));
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }
//
////        // intervenciones #5
//
//        try {
//            clinica.agregarConsultaMedicaPaciente(paciente5, new ConsultaMedica(medico2, 2));
//            clinica.agregarConsultaMedicaPaciente(paciente5, new ConsultaMedica(medico6, 1));
//            clinica.agregarInternacionPaciente(paciente5, new Internacion(HabitacionPrivada.getInstance(), 10));
//            clinica.facturar(paciente5, new GregorianCalendar(2021, Calendar.AUGUST, 26));
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }
//
////        // intervenciones #6
//        try {
//            clinica.agregarConsultaMedicaPaciente(paciente6, new ConsultaMedica(medico2, 1));
//            clinica.agregarConsultaMedicaPaciente(paciente6, new ConsultaMedica(medico6, 4));
//            clinica.agregarInternacionPaciente(paciente6, new Internacion(HabitacionTerapiaIntensiva.getInstance(), 2));
//            clinica.facturar(paciente6, new GregorianCalendar(2021, Calendar.OCTOBER, 27));
//        } catch (PacienteInexistenteException pacienteInexistenteException) {
//            pacienteInexistenteException.printStackTrace();
//        }
//
//        System.out.println(clinica.getDetalleFactura(4));
//        System.out.println(clinica.getDetalleFactura(5));
//        System.out.println(clinica.getDetalleFactura(6));
//
//        System.out.println(clinica.getReporteMedico(medico1, new GregorianCalendar(2017, Calendar.FEBRUARY, 1), new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
//        System.out.println(clinica.getReporteMedico(medico2, new GregorianCalendar(2017, Calendar.FEBRUARY, 1), new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
//        System.out.println(clinica.getReporteMedico(medico3, new GregorianCalendar(2017, Calendar.FEBRUARY, 1), new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
//        System.out.println(clinica.getReporteMedico(medico4, new GregorianCalendar(2017, Calendar.FEBRUARY, 1), new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
//        System.out.println(clinica.getReporteMedico(medico5, new GregorianCalendar(2017, Calendar.FEBRUARY, 1), new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
//        System.out.println(clinica.getReporteMedico(medico6, new GregorianCalendar(2017, Calendar.FEBRUARY, 1), new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
//
//        System.out.println(clinica.estadoDeLaClinica());
//        Paciente paola = null;
//        try {
//            paola = clinica.altaPaciente("Paola", "Argento", "Flores 1234", "Buenos Aires", "123654", 159327258, "Nino");
//        } catch (InformacionPersonalNoValidaException informacionPersonalNoValidaException) {
//            informacionPersonalNoValidaException.printStackTrace();
//        }
//        clinica.ingresarPaciente(paola);
//        try {
//            clinica.atenderSiguentePaciente();
//        } catch (SalaDeEsperaVaciaException e) {
//            e.printStackTrace();
//        }
//        Paciente paciente9 = null;
//        try {
//            paciente9 = clinica.egresoPaciente(7);
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            clinica.agregarInternacionPaciente(paciente9, new Internacion(HabitacionPrivada.getInstance(), 15));
//        } catch (PacienteInexistenteException e) {
//            e.printStackTrace();
//        }
//        System.out.println(clinica.estadoDeLaClinica());
//        try {
//            clinica.atenderSiguentePaciente();
//        } catch (SalaDeEsperaVaciaException e) {
//            e.printStackTrace();
//        }
//        System.out.println(clinica.estadoDeLaClinica());
        }
    }
}
