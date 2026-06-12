/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tes;

/**
 *
 * @author camper
 */
public class ClienteTes {
    private int id;
    private String nombre;
    private String identificacion;
    private String correo;
    private String telefono;
    private double saldoPendiente;

    // Constructor vacío (buena práctica para frameworks y mapeos)
    public ClienteTes() {
    }

    // Constructor completo
    public ClienteTes(int id, String nombre, String identificacion, String correo, String telefono,
            double saldoPendiente) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.saldoPendiente = saldoPendiente;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    // Método toString opcional para facilitar pruebas/depuración
    @Override
    public String toString() {
        return "Cliente [" + identificacion + "] " + nombre + " - Email: " + correo + "saldoPendiente="
                + saldoPendiente;
    }

}
