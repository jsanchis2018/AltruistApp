/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
public class EstadoSolicitud {
    private int idEstadoSolicitud;
    private String pendiente;
    private String aceptada;
    private int idEstadoSolicitudRef;

    // Constructor
    public EstadoSolicitud(int idEstadoSolicitud, String pendiente, String aceptada, int idEstadoSolicitudRef) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.pendiente = pendiente;
        this.aceptada = aceptada;
        this.idEstadoSolicitudRef = idEstadoSolicitudRef;
    }

    // Getters and Setters
    public int getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(int idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getAceptada() {
        return aceptada;
    }

    public void setAceptada(String aceptada) {
        this.aceptada = aceptada;
    }

    public int getIdEstadoSolicitudRef() {
        return idEstadoSolicitudRef;
    }

    public void setIdEstadoSolicitudRef(int idEstadoSolicitudRef) {
        this.idEstadoSolicitudRef = idEstadoSolicitudRef;
    }
}