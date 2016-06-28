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
    private boolean activo=true;
    private static int cuenta=300; //para generar un ID automático

    public Cliente(){
        cuenta++;
        idCliente=cuenta;
    }

    //Getter y Setter de cada atributo (menos id_cliente que no tiene setter


    public int getIdCliente() {
        return idCliente;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(this.idCliente) +"]: " + this.nombre + ", " + this.direccion + ", " + this.telefono;
    }

}
