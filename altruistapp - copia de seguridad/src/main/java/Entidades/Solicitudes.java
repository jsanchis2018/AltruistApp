/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

public class Solicitudes {
    private int idSolicitudes;
    private int idUsuarioRecibe;
    private int idUsuarioRealiza;

    // Constructor
    public Solicitudes(int idSolicitudes, int idUsuarioRecibe, int idUsuarioRealiza) {
        this.idSolicitudes = idSolicitudes;
        this.idUsuarioRecibe = idUsuarioRecibe;
        this.idUsuarioRealiza = idUsuarioRealiza;
    }

    // Getters and Setters
    public int getIdSolicitudes() {
        return idSolicitudes;
    }

    public void setIdSolicitudes(int idSolicitudes) {
        this.idSolicitudes = idSolicitudes;
    }

    public int getIdUsuarioRecibe() {
        return idUsuarioRecibe;
    }

    public void setIdUsuarioRecibe(int idUsuarioRecibe) {
        this.idUsuarioRecibe = idUsuarioRecibe;
    }

    public int getIdUsuarioRealiza() {
        return idUsuarioRealiza;
    }

    public void setIdUsuarioRealiza(int idUsuarioRealiza) {
        this.idUsuarioRealiza = idUsuarioRealiza;
    }
}