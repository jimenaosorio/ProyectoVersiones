package clases;

/**
 * Created by Jimena on 24-06-2016.
 */
public class Cliente {
    //Atributos de cada cliente
    private String idCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private Vendedor vendedor;
    private boolean activo=true;


    public Cliente(String nombre, String direccion, String telefono, Vendedor vendedor){

        this.nombre=nombre;
        this.direccion=direccion;
        this.telefono=telefono;
        this.vendedor=vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
//Getter y Setter de cada atributo (menos id_cliente que no tiene setter


    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
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
