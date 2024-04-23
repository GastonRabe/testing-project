package sistema.gui;

import sistema.habitaciones.Habitacion;
import sistema.personas.medicos.IMedico;
import sistema.personas.pacientes.Paciente;
import sistema.util.Mensaje;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

/**
 * Ventana del modelo MVC.<br>
 */
public class Ventana extends JFrame implements ListSelectionListener, IVista, KeyListener {
    private JPanel contentPane;
    private JPanel panelLista;
    private JPanel panelHistoriasClinicas;
    private JPanel panelDosColumnas;

    private JTextField numeroConsultas;
    private JTextField diasInternacion;
    private JComboBox<IMedico> medico;
    private JComboBox<Habitacion> tipoHabitacion;
    private JList<Paciente> listaPacientesAntencion;
    private DefaultListModel<Paciente> modeloListaPacientesAntencion;
    private JButton botonAnadirConsulta;
    private JButton botonAnadirInternacion;

    public Ventana() {
        this.setName("Sistema de Gestion de Clinica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1034, 591);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        panelHistoriasClinicas = new JPanel();
        tabbedPane.addTab(Mensaje.TITULO.getValor(), null, panelHistoriasClinicas, null);
        panelHistoriasClinicas.setLayout(new GridLayout(1, 0, 0, 0));

        panelDosColumnas = new JPanel();
        panelHistoriasClinicas.add(panelDosColumnas);
        panelDosColumnas.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel PanelListaPacientes = new JPanel();
        panelDosColumnas.add(PanelListaPacientes);
        PanelListaPacientes.setLayout(new BorderLayout(0, 0));

        panelLista = new JPanel();
        PanelListaPacientes.add(panelLista, BorderLayout.CENTER);
        panelLista.setLayout(new BorderLayout(0, 0));

        this.listaPacientesAntencion = new JList<>();
        listaPacientesAntencion.setBorder(new TitledBorder(null, Mensaje.PACIENTESATENCION.getValor(),
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLista.add(listaPacientesAntencion);
        listaPacientesAntencion.addListSelectionListener(this);
        this.modeloListaPacientesAntencion = new DefaultListModel<>(); // agrego el modelo
        this.listaPacientesAntencion.setModel(modeloListaPacientesAntencion);

        JPanel PanelIngreso = new JPanel();
        panelDosColumnas.add(PanelIngreso);
        PanelIngreso.setLayout(new BorderLayout(0, 0));

        JPanel PanelAgregarSubFactura = new JPanel();
        PanelIngreso.add(PanelAgregarSubFactura, BorderLayout.CENTER);
        PanelAgregarSubFactura.setLayout(new GridLayout(2, 0, 0, 0));

        JPanel PanelConsultas = new JPanel();
        PanelAgregarSubFactura.add(PanelConsultas);
        PanelConsultas.setLayout(null);

        numeroConsultas = new JTextField();
        numeroConsultas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        numeroConsultas.setBounds(155, 101, 135, 23);
        PanelConsultas.add(numeroConsultas);
        numeroConsultas.setColumns(10);

        JLabel LabelMedico = new JLabel(Mensaje.MEDICO.getValor());
        LabelMedico.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelMedico.setBounds(28, 46, 69, 28);
        PanelConsultas.add(LabelMedico);

        medico = new JComboBox<>();
        medico.setFont(new Font("Tahoma", Font.PLAIN, 14));
        medico.setBounds(93, 49, 217, 23);
        PanelConsultas.add(medico);

        JLabel LabelNumeroConsultas = new JLabel(Mensaje.NUMEROCONSULTAS.getValor());
        LabelNumeroConsultas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelNumeroConsultas.setBounds(28, 96, 122, 28);
        PanelConsultas.add(LabelNumeroConsultas);

        botonAnadirConsulta = new JButton(Mensaje.ANADIRCONSULTA.getValor());
        botonAnadirConsulta.setActionCommand("anadirConsulta"); // cambiar action command desde avan
        botonAnadirConsulta.setFont(new Font("Tahoma", Font.PLAIN, 12));
        botonAnadirConsulta.setBounds(174, 163, 130, 33);
        botonAnadirConsulta.addActionListener(Controlador.getInstance()); // agregar al boton
        botonAnadirConsulta.setEnabled(false);
        PanelConsultas.add(botonAnadirConsulta);

        JPanel PanelInternaciones = new JPanel();
        PanelInternaciones.setLayout(null);
        PanelAgregarSubFactura.add(PanelInternaciones);

        diasInternacion = new JTextField();
        diasInternacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        diasInternacion.setColumns(10);
        diasInternacion.setBounds(155, 101, 135, 23);
        PanelInternaciones.add(diasInternacion);

        JLabel LabelHabitacion = new JLabel(Mensaje.HABITACION.getValor());
        LabelHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelHabitacion.setBounds(28, 46, 69, 28);
        PanelInternaciones.add(LabelHabitacion);

        tipoHabitacion = new JComboBox<>();
        tipoHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tipoHabitacion.setBounds(155, 49, 135, 23);
        PanelInternaciones.add(tipoHabitacion);

        JLabel LabelDiasInternacion = new JLabel(Mensaje.DIASINTERNACION.getValor());
        LabelDiasInternacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelDiasInternacion.setBounds(28, 96, 122, 28);
        PanelInternaciones.add(LabelDiasInternacion);

        botonAnadirInternacion = new JButton(Mensaje.ANADIRINTERNACION.getValor());
        botonAnadirInternacion.setActionCommand("anadirInternacion"); // cambiar action command desde avan
        botonAnadirInternacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        botonAnadirInternacion.setBounds(174, 163, 130, 33);
        botonAnadirInternacion.addActionListener(Controlador.getInstance()); // agregar al boton
        botonAnadirInternacion.setEnabled(false);
        PanelInternaciones.add(botonAnadirInternacion);

        // Seteo nombres
        numeroConsultas.setName("numeroConsultas");
        diasInternacion.setName("diasInternacion");
        medico.setName("medico");
        tipoHabitacion.setName("habitacion");
        listaPacientesAntencion.setName("listaPacientesAtencion");
        botonAnadirConsulta.setName("botonAnadirConsulta");
        botonAnadirInternacion.setName("botonAnadirInternacion");

        numeroConsultas.addKeyListener(this);
        diasInternacion.addKeyListener(this);

        this.addWindowListener(Controlador.getInstance());
        this.setMinimumSize(new Dimension(900, 600));
        this.setVisible(true);
    }

    @Override
    public Paciente getPaciente() {
        return this.listaPacientesAntencion.getSelectedValue();
    }

    @Override
    public IMedico getMedico() {
        return (IMedico) this.medico.getSelectedItem();
    }

    @Override
    public int getCantidadConsultas() throws NumberFormatException {
        return Integer.parseInt(this.numeroConsultas.getText());
    }

    @Override
    public Habitacion getHabitacion() {
        return (Habitacion) this.tipoHabitacion.getSelectedItem();
    }

    @Override
    public int getCantidadDiasInternacion() throws NumberFormatException {
        return Integer.parseInt(this.diasInternacion.getText());
    }

    @Override
    public void actualizarListaPacientesAtencion(Iterator<Paciente> iterator) {
        this.modeloListaPacientesAntencion.clear();
        while (iterator.hasNext()) {
            this.modeloListaPacientesAntencion.addElement(iterator.next());
        }
        this.repaint();
    }

    @Override
    public void actualizarComboMedicos(Iterator<IMedico> iterator) {
        while (iterator.hasNext()) {
            this.medico.addItem(iterator.next());
        }
    }

    @Override
    public void actualizarComboHabitaciones(Iterator<Habitacion> iterator) {
        while (iterator.hasNext()) {
            this.tipoHabitacion.addItem(iterator.next());
        }
    }


    @Override
    public void addActionListener(ActionListener actionListener) {

    }

    @Override
    public void disableButtons() {
        this.botonAnadirConsulta.setEnabled(false);
        this.botonAnadirInternacion.setEnabled(false);
    }

    @Override
    public void resetFileds() {
        this.diasInternacion.setText(null);
        this.numeroConsultas.setText(null);
        this.listaPacientesAntencion.clearSelection();
        this.medico.setSelectedIndex(-1);
        this.tipoHabitacion.setSelectedIndex(-1);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    	try {
            this.botonAnadirConsulta.setEnabled(this.getCantidadConsultas() != 0);
        } catch (NumberFormatException ex) {
            this.botonAnadirConsulta.setEnabled(false);
        }
        try {
            this.botonAnadirInternacion.setEnabled(this.getCantidadDiasInternacion() != 0);
        } catch (NumberFormatException ex) {
            this.botonAnadirInternacion.setEnabled(false);
        }
    }
}
