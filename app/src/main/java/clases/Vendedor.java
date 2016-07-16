package clases;

import android.database.Cursor;

import java.util.ArrayList;

import actividad14.unidad3.inacap.cl.proyectoversiones.LoginActivity;
import database.OperacionesBaseDatos;
import database.ProductosDataSource;

/**
 * Created by Jimena on 24-06-2016.
 */
public class Vendedor {
    private String idVendedor;
    private String nombre;
    private String login;
    private String password;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;
    private int montoPedidos=0;
    private int montoCobrado=0;
    private int saldo=0;

    private OperacionesBaseDatos dataSource;
    private Cursor registrosClientes;

    public Vendedor(String idVendedor, String nombre, String login, String password){
        this.idVendedor=idVendedor;
        this.nombre=nombre;
        this.login=login;
        this.password=password;
        pedidos=new ArrayList<Pedido>();
        clientes=new ArrayList<Cliente>();
     //   dataSource= LoginActivity.getDataSource(); //Leer la BD
       // registrosClientes=dataSource.getClientes(idVendedor);
    }
    public Vendedor(){
        pedidos=new ArrayList<Pedido>();
        clientes=new ArrayList<Cliente>();

     //   dataSource= LoginActivity.getDataSource(); //Leer la BD


    }

    //Getter y Setter de los atributos


    public int getMontoPedidos() {
        return montoPedidos;
    }

    public int getMontoCobrado() {
        return montoCobrado;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
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

    public void setMontoPedidos(int montoPedidos) {
        this.montoPedidos = montoPedidos;
    }

    public void setMontoCobrado(int montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
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
    //Devolver la lista de pedidos entregados
    public ArrayList<Pedido> getEntregas(){
        ArrayList<Pedido> entregados=new ArrayList<Pedido>();
        int tam=pedidos.size();
        for(int i=0;i<tam;i++){
            if(pedidos.get(i).isEntregado()){
                entregados.add(pedidos.get(i));
            }
        }
        return entregados;
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


        //Agregarlo a la lista
        clientes.add(c);


    }

    //Eliminar un cliente
    public void dropCliente(String idCliente){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);
            if(c.getIdCliente().compareTo(idCliente)==0){
                c.setActivo(false); //Modificarlo en la lista

            }
        }

    }

    //Modificar un cliente
    public Cliente alterCliente(String id, String nuevoNombre, String nuevaDir, String nuevoFono){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);
            if(c.getIdCliente().compareTo(id)==0){
                if(nuevoNombre.compareTo("")!=0)c.setNombre(nuevoNombre);
                if(nuevaDir.compareTo("")!=0)c.setDireccion(nuevaDir);
                if(nuevoFono.compareTo("")!=0)c.setTelefono(nuevoFono);
                return c;
            }
        }
        return null;
    }

    //Buscar un cliente de la lista
    public Cliente getCliente(String id){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);

            if(c.getIdCliente().compareTo(id)==0){
                return c;
            }
        }
        return null;
    }
    public Cliente getClientePorNombre(String nombre){
        int tam=clientes.size();
        Cliente c;
        for(int i=0;i<tam;i++){
            c=clientes.get(i);
            if(c.getNombre().compareTo(nombre)==0){
                return c;
            }
        }
        return null;
    }

    //Agregar un pedido a la lista
    public void addPedido(Pedido pedido){

        pedidos.add(pedido);
        montoPedidos=montoPedidos+pedido.getPrecio();
        saldo=montoPedidos-montoCobrado;
    }
    //Hacer una entrega
    public void hacerEntrega(String idPedido){
        Pedido pedido=getPedido(idPedido);
        if(pedido!=null) {
            pedido.setEntregado(true);
            montoCobrado = montoCobrado + pedido.getPrecio();
            saldo = montoPedidos - montoCobrado;
        }
    }

    //Buscar un pedido
    public Pedido getPedido(String idPedido){
        Pedido pedido;
        int tam=pedidos.size();
        for(int i=0;i<tam;i++){
            pedido=pedidos.get(i);
            if(pedido.getIdPedido().compareTo(idPedido)==0){
                return pedido;
            }
        }
        return null;
    }


}
