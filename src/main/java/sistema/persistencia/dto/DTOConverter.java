package sistema.persistencia.dto;

import sistema.clinica.Clinica;
import sistema.facturacion.Factura;
import sistema.habitaciones.Habitacion;
import sistema.habitaciones.HabitacionCompartida;
import sistema.habitaciones.HabitacionPrivada;
import sistema.habitaciones.HabitacionTerapiaIntensiva;
import sistema.ingreso.ModuloIngreso;
import sistema.personas.medicos.Medico;
import sistema.personas.medicos.decorators.DecoratorDoctor;
import sistema.personas.medicos.decorators.DecoratorMagister;
import sistema.personas.medicos.decorators.DecoratorPermanente;
import sistema.personas.medicos.decorators.DecoratorTemporario;
import sistema.personas.medicos.especialidades.Cirujano;
import sistema.personas.medicos.especialidades.Clinico;
import sistema.personas.medicos.especialidades.Pediatra;
import sistema.personas.pacientes.PacienteFactory;

public class DTOConverter {

    public static ClinicaDTO ClinicaDTOFromClinica() {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setNombre(Clinica.getInstance().getNombre());
        clinicaDTO.setDireccion(Clinica.getInstance().getDireccion());
        clinicaDTO.setCiudad(Clinica.getInstance().getCiudad());
        clinicaDTO.setTelefono(Clinica.getInstance().getTelefono());
        clinicaDTO.setMedicos(Clinica.getInstance().getMedicos());
        clinicaDTO.setModuloIngreso(Clinica.getInstance().getModuloIngreso());
        clinicaDTO.setModuloAtencion(Clinica.getInstance().getModuloAtencion());
        clinicaDTO.setModuloEgreso(Clinica.getInstance().getModuloEgreso());
        clinicaDTO.setCostoAsignacion(Habitacion.getCostoAsignacion());
        clinicaDTO.setSueldoBasico(Medico.getSueldoBasico());
        clinicaDTO.setAumentoCirujano(Cirujano.getAumentoCirujano());
        clinicaDTO.setAumentoClinico(Clinico.getAumentoClinico());
        clinicaDTO.setAumentoPediatra(Pediatra.getAumentoPediatra());
        clinicaDTO.setNroHistoriaClinica(PacienteFactory.getNroHistoriaClinica());
        clinicaDTO.setAumentoDoctor(DecoratorDoctor.getAumentoDoctor());
        clinicaDTO.setAumentoMagister(DecoratorMagister.getAumentoMagister());
        clinicaDTO.setAumentoPermanente(DecoratorPermanente.getAumentoPermanente());
        clinicaDTO.setAumentoTemporario(DecoratorTemporario.getAumentoTemporario());
        clinicaDTO.setNroOrden(ModuloIngreso.getNroOrden());
        clinicaDTO.setSigNroFactura(Factura.getSigNroFactura());
        clinicaDTO.setCostoHabCompartida(HabitacionCompartida.getInstance().getCostoHabitacion());
        clinicaDTO.setCostoHabPrivada(HabitacionPrivada.getInstance().getCostoHabitacion());
        clinicaDTO.setCostoHabTerapiaIntensiva(HabitacionTerapiaIntensiva.getInstance().getCostoHabitacion());
        return clinicaDTO;
    }

    public static void ClinicaFromClinicaDTO(ClinicaDTO clinicaDTO) {
        Clinica.getInstance().setNombre(clinicaDTO.getNombre());
        Clinica.getInstance().setDireccion(clinicaDTO.getDireccion());
        Clinica.getInstance().setCiudad(clinicaDTO.getCiudad());
        Clinica.getInstance().setTelefono(clinicaDTO.getTelefono());
        Clinica.getInstance().setMedicos(clinicaDTO.getMedicos());
        Clinica.getInstance().setModuloIngreso(clinicaDTO.getModuloIngreso());
        Clinica.getInstance().setModuloAtencion(clinicaDTO.getModuloAtencion());
        Clinica.getInstance().setModuloEgreso(clinicaDTO.getModuloEgreso());
        Habitacion.setCostoAsignacion(clinicaDTO.getCostoAsignacion());
        Medico.setSueldoBasico(clinicaDTO.getSueldoBasico());
        Cirujano.setAumentoCirujano(clinicaDTO.getAumentoCirujano());
        Clinico.setAumentoClinico(clinicaDTO.getAumentoClinico());
        Pediatra.setAumentoPediatra(clinicaDTO.getAumentoPediatra());
        PacienteFactory.setNroHistoriaClinica(clinicaDTO.getNroHistoriaClinica());
        DecoratorDoctor.setAumentoDoctor(clinicaDTO.getAumentoDoctor());
        DecoratorMagister.setAumentoMagister(clinicaDTO.getAumentoMagister());
        DecoratorPermanente.setAumentoPermanente(clinicaDTO.getAumentoPermanente());
        DecoratorTemporario.setAumentoTemporario(clinicaDTO.getAumentoTemporario());
        ModuloIngreso.setNroOrden(clinicaDTO.getNroOrden());
        Factura.setSigNroFactura(clinicaDTO.getSigNroFactura());
        HabitacionCompartida.getInstance().setCostoHabCompartida(clinicaDTO.getCostoHabCompartida());
        HabitacionPrivada.getInstance().setCostoHabPrivada(clinicaDTO.getCostoHabPrivada());
        HabitacionTerapiaIntensiva.getInstance().setCostoHabTerapiaIntensiva(clinicaDTO.getCostoHabTerapiaIntensiva());
    }
}