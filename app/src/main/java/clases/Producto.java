package clases;

/**
 * Created by Jimena on 27-06-2016.
 */
public class Producto {
    private int codigo;
    private String nombreProducto;
    private int precio;



    @Override
    public String toString() {
        return "[" + codigo + "]: " + nombreProducto + ", $" +precio;
    }

    //Getter y Setter
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
