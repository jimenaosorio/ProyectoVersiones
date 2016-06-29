package clases;

import java.util.ArrayList;

/**
 * Created by Jimena on 27-06-2016.
 */
public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private ArrayList<DetallePedido> detalles;
    private String fechaEntrega;
    private int precio;
    private boolean entregado=false;
    private static int cuenta=0; //para generar los ID

    public Pedido(){
        cuenta++;
        idPedido=cuenta;
    }
    //Getters y setters

    public int getIdPedido() {
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

    @Override
    public String toString() {
        return "["+idPedido+"]: "+cliente.getNombre()+", "+fechaEntrega+", valor: $"+precio;
    }


}
