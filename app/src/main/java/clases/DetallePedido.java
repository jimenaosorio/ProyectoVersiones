package clases;

/**
 * Created by Jimena on 27-06-2016.
 */
public class DetallePedido {
    private Producto producto;
    private int cantidad;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "["+producto.getCodigo()+"]: "+producto.getNombreProducto()+", $"+producto.getPrecio()+", cant:  "+cantidad;
    }

}
