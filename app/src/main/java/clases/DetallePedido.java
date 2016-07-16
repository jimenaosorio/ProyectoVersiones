package clases;

/**
 * Created by Jimena on 27-06-2016.
 */
public class DetallePedido {
    private String idDetalle;
    private int secuencia;
    private Producto producto;
    private int cantidad;
    private int precio;
    private Pedido pedido;

    public DetallePedido(String idDetalle, int secuencia,Producto producto, int cantidad, int precio){
        this.idDetalle=idDetalle;
        this.secuencia=secuencia;
        this.producto=producto;
        this.cantidad=cantidad;
        this.precio=precio;
    }

    public DetallePedido(){}

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

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "["+producto.getCodigo()+"]: "+producto.getNombreProducto()+", $"+producto.getPrecio()+", cant:  "+cantidad;
    }



}
