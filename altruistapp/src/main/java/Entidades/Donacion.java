package Entidades;

import java.util.Date;

public class Donacion {
    private int idDonacion;
    private Date fechaIngresoArticulo;
    private Date fechaAceptacionDonacion;
    private int idOfreceDonacion;
    private int idAceptaDonacion;
    private String estado;
    private Usuario usuarioSolicitante;
    private Articulo articulo;

    // Getters y Setters
    public int getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(int idDonacion) {
        this.idDonacion = idDonacion;
    }

    public Date getFechaIngresoArticulo() {
        return fechaIngresoArticulo;
    }

    public void setFechaIngresoArticulo(Date fechaIngresoArticulo) {
        this.fechaIngresoArticulo = fechaIngresoArticulo;
    }

    public Date getFechaAceptacionDonacion() {
        return fechaAceptacionDonacion;
    }

    public void setFechaAceptacionDonacion(Date fechaAceptacionDonacion) {
        this.fechaAceptacionDonacion = fechaAceptacionDonacion;
    }

    public int getIdOfreceDonacion() {
        return idOfreceDonacion;
    }

    public void setIdOfreceDonacion(int idOfreceDonacion) {
        this.idOfreceDonacion = idOfreceDonacion;
    }

    public int getIdAceptaDonacion() {
        return idAceptaDonacion;
    }

    public void setIdAceptaDonacion(int idAceptaDonacion) {
        this.idAceptaDonacion = idAceptaDonacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Articulo getArticulo() {
        return articulo;
    }
    
    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }
    
    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }
    
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getNombreArticulo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setNombreArticulo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
