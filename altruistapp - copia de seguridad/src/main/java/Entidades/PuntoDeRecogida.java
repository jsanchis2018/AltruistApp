/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

public class PuntoDeRecogida {
    private int idPuntoRecogida;
    private String nombrePunto;

    // Constructor
    public PuntoDeRecogida(int idPuntoRecogida, String nombrePunto) {
        this.idPuntoRecogida = idPuntoRecogida;
        this.nombrePunto = nombrePunto;
    }

    // Getters and Setters
    public int getIdPuntoRecogida() {
        return idPuntoRecogida;
    }

    public void setIdPuntoRecogida(int idPuntoRecogida) {
        this.idPuntoRecogida = idPuntoRecogida;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }
}