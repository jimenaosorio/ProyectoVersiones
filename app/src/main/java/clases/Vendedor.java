package clases;

import java.util.ArrayList;

/**
 * Created by Jimena on 24-06-2016.
 */
public class Vendedor {
    private int idVendedor;
    private String nombre;
    private String login;
    private String password;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public Vendedor(){
        pedidos=new ArrayList<Pedido>();
    }

    //Getter y Setter de los atributos


    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Solo devuelve los clientes que no se han eliminado
    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> activos=new ArrayList<Cliente>();
        int tam=clientes.size();
        for(int i=0;i<tam;i++){
            if(clientes.get(i).isActivo()){
                activos.add(clientes.get(i));
            }
        }
        return activos;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    //Solo devuelve los pedidos que no se han entregado
    public ArrayList<Pedido> getPedidos() {
        ArrayList<Pedido> pendientes=new ArrayList<Pedido>();
        int tam=pedidos.size();
        for(int i=0;i<tam;i++){
            if(!pedidos.get(i).isEntregado()){
                pendientes.add(pedidos.get(i));
            }
        }
        return pendientes;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Vendedor [" + idVendedor + "]: " + nombre + "(" + login + ")";
    }

    //Agregar un cliente a la lista

    public void addCliente(Cliente c){
        clientes.add(c);
    }

    //Eliminar un cliente
    public void dropCliente(int idCliente){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);
            if(c.getIdCliente()==idCliente){
                c.setActivo(false);
            }
        }

    }

    //Modificar un cliente
    public void alterCliente(int id, String nuevoNombre, String nuevaDir, String nuevoFono){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);
            if(c.getIdCliente()==id){
                if(nuevoNombre.compareTo("")!=0)c.setNombre(nuevoNombre);
                if(nuevaDir.compareTo("")!=0)c.setDireccion(nuevaDir);
                if(nuevoFono.compareTo("")!=0)c.setTelefono(nuevoFono);
            }
        }
    }

    //Buscar un cliente de la lista
    public Cliente getCliente(int id){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);
            if(c.getIdCliente()==id){
                return c;
            }
        }
        return null;
    }

    //Agregar un pedido a la lista
    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
    }

    //Devolver un pedido
    public Pedido getPedido(int idPedido){
        Pedido pedido;
        int tam=pedidos.size();
        for(int i=0;i<tam;i++){
            pedido=pedidos.get(i);
            if(pedido.getIdPedido()==idPedido){
                return pedido;
            }
        }
        return null;
    }


}
