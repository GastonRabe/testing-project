package sistema.gui;

import sistema.clinica.Clinica;
import sistema.excepciones.PacienteInexistenteException;
import sistema.facturacion.ConsultaMedica;
import sistema.facturacion.Internacion;
import sistema.habitaciones.Habitacion;
import sistema.persistencia.AccesoDatos;
import sistema.persistencia.dto.ClinicaDTO;
import sistema.persistencia.dto.DTOConverter;
import sistema.personas.medicos.IMedico;
import sistema.personas.pacientes.Paciente;
import sistema.util.Mensaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Controlador implements ActionListener, WindowListener {
    private static Controlador instance = null;
    private InterfazOptionPane optionPane = new PopUp();
    private Ventana ventana;
    private Clinica clinica;

    public Controlador() {
    	
    }

    public static Controlador getInstance() {
        if (Controlador.instance == null)
            Controlador.instance = new Controlador();
        return Controlador.instance;
    }

    public void setVentana(Ventana ventana) {
        this.ventana = ventana;
    }
    
    public Ventana getVentana()
    {
    	return ventana;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public InterfazOptionPane getOptionPane() {
        return optionPane;
    }

    public void setOptionPane(InterfazOptionPane optionPane) {
        this.optionPane = optionPane;
    }

    public void init() {
        this.ventana.actualizarComboHabitaciones(this.clinica.getHabitacionesIterator());
        this.ventana.actualizarComboMedicos(this.clinica.getMedicosIterator());
        this.ventana.actualizarListaPacientesAtencion(this.clinica.getPacientesEnAtencionIterator());
        this.ventana.resetFileds();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "anadirInternacion" : this.anadirInternacion(this.ventana.getPaciente(), this.ventana.getHabitacion(), this.ventana.getCantidadDiasInternacion());
            case "anadirConsulta" : this.anadirConsultaMedica(this.ventana.getPaciente(), this.ventana.getMedico(), this.ventana.getCantidadConsultas());
        }
    }

    /**
     * Permite agregar una internacion a un paciente
     * @param paciente
     * @param habitacion
     * @param dias
     */
    private void anadirInternacion(Paciente paciente, Habitacion habitacion, int dias) {
        if (paciente == null)
            this.optionPane.showMessageDialog(null, Mensaje.ERROR_PACIENTE_NO_SELECCIONADO.getValor(), "Error", JOptionPane.ERROR_MESSAGE);
        else if (habitacion == null)
            this.optionPane.showMessageDialog(null, Mensaje.ERROR_HABITACION_NO_SELECCIONADO.getValor(), "Error", JOptionPane.ERROR_MESSAGE);
        else {
            try {
                this.clinica.agregarInternacionPaciente(paciente, new Internacion(habitacion, dias));
                this.ventana.resetFileds();
                this.ventana.disableButtons();
            } catch (PacienteInexistenteException e) {
                e.printStackTrace();
            }
            this.optionPane.showMessageDialog(null, Mensaje.ANADIRINTERNACION_OK.getValor(), "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *  Permite agregar una internacion a un paciente
     * @param paciente
     * @param medico
     * @param cantidad
     */
    private void anadirConsultaMedica(Paciente paciente, IMedico medico, int cantidad) {
        if (paciente == null)
            this.optionPane.showMessageDialog(null, Mensaje.ERROR_PACIENTE_NO_SELECCIONADO.getValor(), "Error", JOptionPane.ERROR_MESSAGE);
        else if (medico == null)
            this.optionPane.showMessageDialog(null, Mensaje.ERROR_MEDICO_NO_SELECCIONADO.getValor(), "Error", JOptionPane.ERROR_MESSAGE);
        else {
            try {
                this.clinica.agregarConsultaMedicaPaciente(paciente, new ConsultaMedica(medico, cantidad));
                this.ventana.resetFileds();
                this.ventana.disableButtons();
            } catch (PacienteInexistenteException e) {
                e.printStackTrace();
            }
            this.optionPane.showMessageDialog(null, Mensaje.ANADIRCONSULTA_OK.getValor(), "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        try {
            ClinicaDTO dto = AccesoDatos.despersistirClinica();
            DTOConverter.ClinicaFromClinicaDTO(dto);
        } catch (IOException ex) {
            AccesoDatos.initClinica();
        }
    	//AccesoDatos.initClinica();
        this.ventana.actualizarComboHabitaciones(this.clinica.getHabitacionesIterator());
        this.ventana.actualizarComboMedicos(this.clinica.getMedicosIterator());
        this.ventana.actualizarListaPacientesAtencion(this.clinica.getPacientesEnAtencionIterator());
        this.ventana.resetFileds();
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ClinicaDTO dto = DTOConverter.ClinicaDTOFromClinica();
        try {
            AccesoDatos.persistirClinica(dto);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ClinicaDTO dto = DTOConverter.ClinicaDTOFromClinica();
        try {
            AccesoDatos.persistirClinica(dto);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // empty
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // empty
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
