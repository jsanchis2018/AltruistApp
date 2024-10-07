/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
public class EstadoDonacion {
    private int idEstadoDonacion;
    private String disponible;
    private String reservado;
    private String donado;
    private int idEstadoDonacionRef;

    // Constructor
    public EstadoDonacion(int idEstadoDonacion, String disponible, String reservado, String donado, int idEstadoDonacionRef) {
        this.idEstadoDonacion = idEstadoDonacion;
        this.disponible = disponible;
        this.reservado = reservado;
        this.donado = donado;
        this.idEstadoDonacionRef = idEstadoDonacionRef;
    }

    // Getters and Setters
    public int getIdEstadoDonacion() {
        return idEstadoDonacion;
    }

    public void setIdEstadoDonacion(int idEstadoDonacion) {
        this.idEstadoDonacion = idEstadoDonacion;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public String getDonado() {
        return donado;
    }

    public void setDonado(String donado) {
        this.donado = donado;
    }

    public int getIdEstadoDonacionRef() {
        return idEstadoDonacionRef;
    }

    public void setIdEstadoDonacionRef(int idEstadoDonacionRef) {
        this.idEstadoDonacionRef = idEstadoDonacionRef;
    }
}