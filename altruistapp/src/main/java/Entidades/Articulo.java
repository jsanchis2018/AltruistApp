package Entidades;

public class Articulo {
    private int idArticulo;
    private String nombre;
    private String estado;
    private int idPuntoDeRecogida;

    // Getters y Setters
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPuntoDeRecogida() {
        return idPuntoDeRecogida;
    }

    public void setIdPuntoDeRecogida(int idPuntoDeRecogida) {
        this.idPuntoDeRecogida = idPuntoDeRecogida;
    }
}
