package sistema.persistencia.dto;


import sistema.atencion.ModuloAtencion;
import sistema.egreso.ModuloEgreso;
import sistema.ingreso.ModuloIngreso;
import sistema.personas.medicos.IMedico;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class ClinicaDTO implements Serializable {
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private HashMap<Integer, IMedico> medicos = new HashMap<>();
    private ModuloIngreso moduloIngreso = new ModuloIngreso();
    private ModuloAtencion moduloAtencion = new ModuloAtencion();
    private ModuloEgreso moduloEgreso = new ModuloEgreso();

    // Habitacioens
    private double costoAsignacion;
    // Medicos
    private double sueldoBasico;
    private double aumentoCirujano;
    private double aumentoClinico;
    private double aumentoPediatra;
    private int nroHistoriaClinica;
    private double aumentoDoctor;
    private double aumentoMagister;
    private double aumentoPermanente;
    private double aumentoTemporario;
    // modulo Ingreso
    private int nroOrden;
    // Factura
    private int sigNroFactura;
    private double costoHabCompartida;
    private double costoHabPrivada;
    private double costoHabTerapiaIntensiva;

    public double getCostoAsignacion() {
        return costoAsignacion;
    }

    public void setCostoAsignacion(double costoAsignacion) {
        this.costoAsignacion = costoAsignacion;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public double getAumentoCirujano() {
        return aumentoCirujano;
    }

    public void setAumentoCirujano(double aumentoCirujano) {
        this.aumentoCirujano = aumentoCirujano;
    }

    public double getAumentoClinico() {
        return aumentoClinico;
    }

    public void setAumentoClinico(double aumentoClinico) {
        this.aumentoClinico = aumentoClinico;
    }

    public double getAumentoPediatra() {
        return aumentoPediatra;
    }

    public void setAumentoPediatra(double aumentoPediatra) {
        this.aumentoPediatra = aumentoPediatra;
    }

    public int getNroHistoriaClinica() {
        return nroHistoriaClinica;
    }

    public void setNroHistoriaClinica(int nroHistoriaClinica) {
        this.nroHistoriaClinica = nroHistoriaClinica;
    }

    public double getAumentoDoctor() {
        return aumentoDoctor;
    }

    public void setAumentoDoctor(double aumentoDoctor) {
        this.aumentoDoctor = aumentoDoctor;
    }

    public double getAumentoMagister() {
        return aumentoMagister;
    }

    public void setAumentoMagister(double aumentoMagister) {
        this.aumentoMagister = aumentoMagister;
    }

    public double getAumentoPermanente() {
        return aumentoPermanente;
    }

    public void setAumentoPermanente(double aumentoPermanente) {
        this.aumentoPermanente = aumentoPermanente;
    }

    public double getAumentoTemporario() {
        return aumentoTemporario;
    }

    public void setAumentoTemporario(double aumentoTemporario) {
        this.aumentoTemporario = aumentoTemporario;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(int nroOrden) {
        this.nroOrden = nroOrden;
    }

    public int getSigNroFactura() {
        return sigNroFactura;
    }

    public void setSigNroFactura(int sigNroFactura) {
        this.sigNroFactura = sigNroFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public HashMap<Integer, IMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(HashMap<Integer, IMedico> medicos) {
        this.medicos = medicos;
    }

    public ModuloIngreso getModuloIngreso() {
        return moduloIngreso;
    }

    public void setModuloIngreso(ModuloIngreso moduloIngreso) {
        this.moduloIngreso = moduloIngreso;
    }

    public ModuloAtencion getModuloAtencion() {
        return moduloAtencion;
    }

    public void setModuloAtencion(ModuloAtencion moduloAtencion) {
        this.moduloAtencion = moduloAtencion;
    }

    public ModuloEgreso getModuloEgreso() {
        return moduloEgreso;
    }

    public void setModuloEgreso(ModuloEgreso moduloEgreso) {
        this.moduloEgreso = moduloEgreso;
    }

    public double getCostoHabCompartida() {
        return costoHabCompartida;
    }

    public void setCostoHabCompartida(double costoHabCompartida) {
        this.costoHabCompartida = costoHabCompartida;
    }

    public double getCostoHabPrivada() {
        return costoHabPrivada;
    }

    public void setCostoHabPrivada(double costoHabPrivada) {
        this.costoHabPrivada = costoHabPrivada;
    }

    public double getCostoHabTerapiaIntensiva() {
        return costoHabTerapiaIntensiva;
    }

    public void setCostoHabTerapiaIntensiva(double costoHabTerapiaIntensiva) {
        this.costoHabTerapiaIntensiva = costoHabTerapiaIntensiva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicaDTO that = (ClinicaDTO) o;
        return Double.compare(that.costoAsignacion, costoAsignacion) == 0 && Double.compare(that.sueldoBasico, sueldoBasico) == 0 && Double.compare(that.aumentoCirujano, aumentoCirujano) == 0 && Double.compare(that.aumentoClinico, aumentoClinico) == 0 && Double.compare(that.aumentoPediatra, aumentoPediatra) == 0 && nroHistoriaClinica == that.nroHistoriaClinica && Double.compare(that.aumentoDoctor, aumentoDoctor) == 0 && Double.compare(that.aumentoMagister, aumentoMagister) == 0 && Double.compare(that.aumentoPermanente, aumentoPermanente) == 0 && Double.compare(that.aumentoTemporario, aumentoTemporario) == 0 && nroOrden == that.nroOrden && sigNroFactura == that.sigNroFactura && Double.compare(that.costoHabCompartida, costoHabCompartida) == 0 && Double.compare(that.costoHabPrivada, costoHabPrivada) == 0 && Double.compare(that.costoHabTerapiaIntensiva, costoHabTerapiaIntensiva) == 0 && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion) && Objects.equals(ciudad, that.ciudad) && Objects.equals(telefono, that.telefono) && Objects.equals(medicos, that.medicos) && Objects.equals(moduloIngreso, that.moduloIngreso) && Objects.equals(moduloAtencion, that.moduloAtencion) && Objects.equals(moduloEgreso, that.moduloEgreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccion, ciudad, telefono, medicos, moduloIngreso, moduloAtencion, moduloEgreso, costoAsignacion, sueldoBasico, aumentoCirujano, aumentoClinico, aumentoPediatra, nroHistoriaClinica, aumentoDoctor, aumentoMagister, aumentoPermanente, aumentoTemporario, nroOrden, sigNroFactura, costoHabCompartida, costoHabPrivada, costoHabTerapiaIntensiva);
    }
}
