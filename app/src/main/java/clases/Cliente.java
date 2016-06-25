package clases;

/**
 * Created by Jimena on 24-06-2016.
 */
public class Cliente {
    //Atributos de cada cliente
    private int idCliente;
    private String nombre;
    private String direccion;
    private String telefono;

    //Getter y Setter de cada atributo

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(this.idCliente) +"]: " + this.nombre + ", " + this.direccion + ", " + this.telefono;
    }

}
