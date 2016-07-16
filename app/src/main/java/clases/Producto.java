package clases;

/**
 * Created by Jimena on 27-06-2016.
 */
public class Producto {
    private String codigo;
    private String nombreProducto;
    private int precio;

    public Producto(String codigo,String nombreProducto, int precio){
        this.codigo=codigo;
        this.nombreProducto=nombreProducto;
        this.precio=precio;

    }
    public Producto(String nombreProducto,int precio){
        this.nombreProducto=nombreProducto;
        this.precio=precio;
    }

    @Override
    public String toString() {
        return "[" + codigo + "]: " + nombreProducto + ", $" +precio;
    }

    //Getter y Setter
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


}
