/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Amanda
 */
public class EstadoArticulo {
    private int idEstadoArticulo;
    private int idEstadoArticuloRef;
    private String estado;

    // Constructor
    public EstadoArticulo(int idEstadoArticulo, int idEstadoArticuloRef, String estado) {
        this.idEstadoArticulo = idEstadoArticulo;
        this.idEstadoArticuloRef = idEstadoArticuloRef;
        this.estado = estado;
    }

    // Getters and Setters
    public int getIdEstadoArticulo() {
        return idEstadoArticulo;
    }

    public void setIdEstadoArticulo(int idEstadoArticulo) {
        this.idEstadoArticulo = idEstadoArticulo;
    }

    public int getIdEstadoArticuloRef() {
        return idEstadoArticuloRef;
    }

    public void setIdEstadoArticuloRef(int idEstadoArticuloRef) {
        this.idEstadoArticuloRef = idEstadoArticuloRef;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}