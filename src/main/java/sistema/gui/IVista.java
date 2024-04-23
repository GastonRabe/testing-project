package sistema.gui;

import sistema.habitaciones.Habitacion;
import sistema.personas.medicos.IMedico;
import sistema.personas.pacientes.Paciente;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Iterator;

public interface IVista {
    /**
     * Retorna el paciente seleccionado
     *
     * @return el paciente seleccionado o null en caso de no seleccionar ninguno
     */
    Paciente getPaciente();

    /**
     * Retorna el medico seleccionado para la facturacion
     *
     * @return el medico seleccionado o null en caso de no seleccionar ninguno
     */
    IMedico getMedico();

    /**
     * Retorna la cantidad de consultas que realizo un paciente
     *
     * @return la cantidad de consultas realizadas
     * @throws NumberFormatException si el numero de consultas en invalido
     */
    int getCantidadConsultas() throws NumberFormatException;

    Habitacion getHabitacion();

    /**
     * Retorna la cantidad de dias que estuvo internado un paciente
     *
     * @return la cantidad de dias que estuvo internado un paciente
     * @throws NumberFormatException si el numero de dias en invalido
     */
    int getCantidadDiasInternacion() throws NumberFormatException;

    /**
     * Actualiza la lista de pacientes en atencion
     *
     * @param iterator Iterator con los pacientes a mostrar
     */
    void actualizarListaPacientesAtencion(Iterator<Paciente> iterator);

    /**
     * Actualiza el combobox de Medicos en el panel de facturacion
     *
     * @param iterator Iterator con los medicos a mostrar
     */
    void actualizarComboMedicos(Iterator<IMedico> iterator);

    /**
     * Actualiza el combobox de Habitaciones en el panel de facturacion
     *
     * @param iterator Iterator con las habitaciones a mostrar
     */
    void actualizarComboHabitaciones(Iterator<Habitacion> iterator);

    void addActionListener(ActionListener actionListener);

    void addWindowListener(WindowListener windowListener);
    /**
     * Desactiva todos los botones de la ventana
     */
    void disableButtons();

    /**
     * Resetea todos los textos de todos los campos
     */
    void resetFileds();
}
