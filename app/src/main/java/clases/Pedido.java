package clases;

import java.util.ArrayList;

/**
 * Created by Jimena on 27-06-2016.
 */
public class Pedido {
    private String idPedido;
    private Cliente cliente;
    private ArrayList<DetallePedido> detalles;
    private String fechaEntrega;
    private int precio;
    private boolean entregado=false;
    private Vendedor vendedor;


    public Pedido(){}

    public Pedido(Cliente cliente, String fechaEntrega, int precio, Vendedor vendedor){
        this.idPedido=idPedido;
        this.cliente=cliente;
        this.fechaEntrega=fechaEntrega;
        this.precio=precio;
        this.vendedor=vendedor;
    }
    public Pedido(String idPedido, Cliente cliente, String fechaEntrega, int precio){
        this.idPedido=idPedido;
        this.cliente=cliente;
        this.fechaEntrega=fechaEntrega;
        this.precio=precio;
    }
    //Getters y setters

    public String getIdPedido() {
        return idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public int getPrecio() {
        return precio;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDetalles(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "["+idPedido+"]: "+cliente.getNombre()+", "+fechaEntrega+", valor: $"+precio;
    }


}
