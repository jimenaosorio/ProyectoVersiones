package clases;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.ArrayList;

import actividad14.unidad3.inacap.cl.proyectoversiones.LoginActivity;
import database.OperacionesBaseDatos;
import database.ProductosDataSource;

/**
 * Created by Jimena on 27-06-2016.
 */
public class ListaVendedores {
    private static ListaVendedores instancia=new ListaVendedores();
    private ArrayList<Vendedor> listaVendedores;
    private OperacionesBaseDatos dataSource;
    private Cursor registrosVendedores, registrosClientes;

    public static ListaVendedores getInstancia(){
        return instancia;
    }

    //Constructor
    private ListaVendedores(){


        //Datos vendedor
        String idVendedor;
        int montoPedido, montoCobrado, saldo;
        String nombreVendedor, login, pass;
        Vendedor vendedor;
        listaVendedores=new ArrayList<Vendedor>();
        //Datos cliente
        String idCliente, idV;
        int activo;
        String nombreCliente, direccion, telefono;
        Cliente cliente;
        ArrayList<Cliente> listaClientes;
        //Leer los vendedores y clientes
        dataSource= LoginActivity.getDataSource(); //Leer la BD


        /*****************************************************/
        /******* Llenar la base con los datos iniciales ******/
        datosInicales();
        /****************************************************/
        registrosVendedores=dataSource.getVendedores(); //Leer tabla vendedores
        if(registrosVendedores!=null) {
            while (registrosVendedores.moveToNext()) { //Recorrer la tabla de vendedores
                idVendedor = registrosVendedores.getString(1);
                nombreVendedor = registrosVendedores.getString(2);
                login = registrosVendedores.getString(3);
                pass = registrosVendedores.getString(4);
                montoPedido = registrosVendedores.getInt(5);
                montoCobrado = registrosVendedores.getInt(6);
                saldo = registrosVendedores.getInt(7);
                //Guardo los datos en el objeto vendedor
                vendedor = new Vendedor();
                vendedor.setIdVendedor(idVendedor);
                vendedor.setNombre(nombreVendedor);
                vendedor.setLogin(login);
                vendedor.setPassword(pass);
                vendedor.setMontoPedidos(montoPedido);
                vendedor.setMontoCobrado(montoCobrado);
                vendedor.setSaldo(saldo);


                registrosClientes = dataSource.getClientes(idVendedor);
                listaClientes = new ArrayList<Cliente>();
                while (registrosClientes.moveToNext()) {

                        idCliente = registrosClientes.getString(0);
                        nombreCliente = registrosClientes.getString(1);
                        direccion = registrosClientes.getString(2);
                        telefono = registrosClientes.getString(3);
                        activo = registrosClientes.getInt(4);
                        //Guardo los datos en el objeto cliente
                        cliente = new Cliente(nombreCliente,direccion,telefono,vendedor);
                        cliente.setIdCliente(idCliente);
                        if (activo == 1) cliente.setActivo(true);
                        else cliente.setActivo(false);
                        listaClientes.add(cliente);
                    }

                    vendedor.setClientes(listaClientes);
                listaVendedores.add(vendedor);

                }

            }
        }





    //Getter y Setter
    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(ArrayList<Vendedor> vendedores) {
        this.listaVendedores = vendedores;
    }

    //Validar login y password
    public String validarLogin(String login, String password){
        Vendedor vendedor;
        int tam=listaVendedores.size();
        for(int i=0;i<tam;i++){     //recorrer la lista de vendedores
            vendedor=listaVendedores.get(i);
            if(vendedor.getLogin().equals(login) && vendedor.getPassword().equals(password)){ //El login y la password coinciden
                return vendedor.getIdVendedor();
            }
        }


        return ""; //Si no lo encuentra
    }
    //Devolver un vendedor
    public Vendedor getVendedor(String id){
        Vendedor vendedor=null;
        int tam=listaVendedores.size();
        for(int i=0;i<tam;i++) {     //recorrer la lista de vendedores
            vendedor = listaVendedores.get(i);
            if(vendedor.getIdVendedor().compareTo(id)==0)
                return vendedor;
        }
        return null;
    }

    public String[] getUsuariosYPasswords(){
        int tam=listaVendedores.size();
        String[] lista=new String[tam];
        String item;
        String usuario, pass;
        for(int i=0;i<tam;i++){
            usuario=listaVendedores.get(i).getLogin();
            pass=listaVendedores.get(i).getPassword();
            item=usuario+":"+pass;
            lista[i]=item;
        }
        return lista;
    }

//Agegar un cliente a la base de datos
    public void addCliente(Cliente cliente, Vendedor vendedor){
        String idCliente;


        //Agregarlo a la base de datos
        try{
            dataSource.getDb().beginTransaction();
            idCliente=dataSource.insertCliente(cliente);
            cliente.setIdCliente(idCliente);

            Log.d("id_cliente","id cliente="+cliente.getIdCliente());
            //Agregarlo a la lista
            vendedor.addCliente(cliente);
            Log.d("lista","En la lista id cliente="+vendedor.getCliente(idCliente));

            dataSource.getDb().setTransactionSuccessful();
        }finally {
            dataSource.getDb().endTransaction();

        }

    }

    //Eliminar (desactivar) un cliente
    public void dropCliente(Cliente cliente){
        try{
            dataSource.getDb().beginTransaction();
            dataSource.desactivarCliente(cliente);
            dataSource.getDb().setTransactionSuccessful();

        }finally {
            dataSource.getDb().endTransaction();
        }
    }

    //Modificar un cliente
    public void alterCliente(Cliente cliente){
        try{
            dataSource.getDb().beginTransaction();
            dataSource.updateCliente(cliente);
            dataSource.getDb().setTransactionSuccessful();
        }finally {
            dataSource.getDb().endTransaction();
        }
    }

    //Agregar un pedido a la base de datos
    public void insertPedido(Pedido pedido){
        String idPedido;
        try{
            dataSource.getDb().beginTransaction();
            idPedido=dataSource.insertPedido(pedido);
            pedido.setIdPedido(idPedido);
            dataSource.getDb().setTransactionSuccessful();

        }finally {
            dataSource.getDb().endTransaction();
        }
    }
    //Agregar un detalle de pedido a la base de datos
    public void insertDetallePedido(DetallePedido detallePedido){
        String idDetalle;
        try{
            dataSource.getDb().beginTransaction();
            idDetalle=dataSource.insertDetallePedido(detallePedido);
            detallePedido.setIdDetalle(idDetalle);
            dataSource.getDb().setTransactionSuccessful();
        }finally {
            dataSource.getDb().endTransaction();
        }
    }
    //Hacer una entrega
    public void hacerEntrga(Vendedor vendedor, Pedido pedido){
        vendedor.hacerEntrega(pedido.getIdPedido());
        Log.d("saldo","saldo="+vendedor.getSaldo());
        try{
            dataSource.getDb().beginTransaction();
            dataSource.modificarCaja(vendedor);
            dataSource.hacerEntrega(pedido.getIdPedido());
            dataSource.getDb().setTransactionSuccessful();
        }finally {
            dataSource.getDb().endTransaction();
        }
    }
    //Lista de produtos
    Producto buscarProducto(String idProducto){
        Cursor productoBase=dataSource.getProducto(idProducto);
        Producto p=null;
        while(productoBase.moveToNext()){
            String codigo=productoBase.getString(0);
            String nombre=productoBase.getString(1);
            int precio=productoBase.getInt(2);
            p=new Producto(codigo,nombre,precio);
        }
        return p;
    }

    //Devolver la lista de pedidos pendientes
    public ArrayList<Pedido> getPedidosPendientes(Vendedor vendedor){
        Cursor pedidosPendientes=dataSource.getPedidosPendientes(vendedor);
        ArrayList<Pedido> pedidosP=new ArrayList<Pedido>();
        Cursor detalles;
        ArrayList<DetallePedido> listaDetalles=new ArrayList<DetallePedido>();
        Pedido pedido;
        Cliente cliente;
        String idPedido,idCliente,fecha;
        int precio;
        DetallePedido detallePedido=null;
        String idDetalle, idProd;
        int secuencia, cantidad, totalLinea;
        Producto producto;

        while(pedidosPendientes.moveToNext()){
            idPedido=pedidosPendientes.getString(0);
            idCliente=pedidosPendientes.getString(1);
            fecha=pedidosPendientes.getString(2);
            precio=pedidosPendientes.getInt(3);
            pedido=new Pedido();
            pedido.setIdPedido(idPedido);
            cliente=vendedor.getCliente(idCliente);
            pedido.setCliente(cliente);
            pedido.setFechaEntrega(fecha);
            pedido.setPrecio(precio);
            pedido.setEntregado(false);
            pedido.setVendedor(vendedor);
            detalles=dataSource.getDetalles(pedido);
            while (detalles.moveToNext()) {
                //Leer desde la BD
                idDetalle = detalles.getString(0);
                secuencia = detalles.getInt(1);
                idProd = detalles.getString(2);
                cantidad = detalles.getInt(3);
                totalLinea = detalles.getInt(4);

                //Crear el detalle
                detallePedido = new DetallePedido();
                detallePedido.setIdDetalle(idDetalle+"#"+secuencia);
                detallePedido.setSecuencia(secuencia);
                producto=buscarProducto(idProd);

                detallePedido.setProducto(producto);
                detallePedido.setCantidad(cantidad);
                detallePedido.setPrecio(totalLinea);
                detallePedido.setPedido(pedido);

                //Agregar el detalle a la lista
                listaDetalles.add(detallePedido);
            }
            pedido.setDetalles(listaDetalles);
            pedidosP.add(pedido);

        }
        return pedidosP;
    }
    //Devolver la lista de pedidos entregados
    public ArrayList<Pedido> getPedidosEntregados(Vendedor vendedor){
        Cursor pedidosEntregados=dataSource.getPedidosEntregados(vendedor);
        ArrayList<Pedido> pedidosE=new ArrayList<Pedido>();
        Cursor detalles;
        ArrayList<DetallePedido> listaDetalles=new ArrayList<DetallePedido>();
        Pedido pedido;
        Cliente cliente;
        String idPedido,idCliente,fecha;
        int precio;
        DetallePedido detallePedido=null;
        String idDetalle, idProd;
        int secuencia, cantidad, totalLinea;
        Producto producto;

        while(pedidosEntregados.moveToNext()){
            idPedido=pedidosEntregados.getString(0);
            idCliente=pedidosEntregados.getString(1);
            fecha=pedidosEntregados.getString(2);
            precio=pedidosEntregados.getInt(3);
            pedido=new Pedido();
            pedido.setIdPedido(idPedido);
            cliente=vendedor.getCliente(idCliente);
            pedido.setCliente(cliente);
            pedido.setFechaEntrega(fecha);
            pedido.setPrecio(precio);
            pedido.setEntregado(false);
            pedido.setVendedor(vendedor);
            detalles=dataSource.getDetalles(pedido);
            while (detalles.moveToNext()) {
                //Leer desde la BD
                idDetalle = detalles.getString(0);
                secuencia = detalles.getInt(1);
                idProd = detalles.getString(2);
                cantidad = detalles.getInt(3);
                totalLinea = detalles.getInt(4);

                //Crear el detalle
                detallePedido = new DetallePedido();
                detallePedido.setIdDetalle(idDetalle+"#"+secuencia);
                detallePedido.setSecuencia(secuencia);
                producto=buscarProducto(idProd);

                detallePedido.setProducto(producto);
                detallePedido.setCantidad(cantidad);
                detallePedido.setPrecio(totalLinea);
                detallePedido.setPedido(pedido);

                //Agregar el detalle a la lista
                listaDetalles.add(detallePedido);
            }
            pedido.setDetalles(listaDetalles);
            pedidosE.add(pedido);

        }
        return pedidosE;
    }
//Devolver un pedido entregado
    public Pedido getPedidoEntregado(Vendedor vendedor, String idPedido){
        Cursor pedidosEntregados=dataSource.getPedidosEntregados(vendedor);
        Pedido pedidoEntregado=null;
        Cursor detalles;
        ArrayList<DetallePedido> listaDetalles=new ArrayList<DetallePedido>();
        Cliente cliente;
        String idPed,idCliente,fecha;
        int precio;
        DetallePedido detallePedido=null;
        String idDetalle, idProd;
        int secuencia, cantidad, totalLinea;
        Producto producto;
        while(pedidosEntregados.moveToNext()) {
            idPed = pedidosEntregados.getString(0);
            if(idPed.compareTo(idPedido)==0){
                idCliente=pedidosEntregados.getString(1);
                fecha=pedidosEntregados.getString(2);
                precio=pedidosEntregados.getInt(3);
                pedidoEntregado=new Pedido();
                pedidoEntregado.setIdPedido(idPedido);
                //Recuperar el cliente
                Cursor clienteCursor=dataSource.getCliente(idCliente);
                clienteCursor.moveToNext();
                String nombreCliente=clienteCursor.getString(1);
                String direccion=clienteCursor.getString(2);
                String telefono=clienteCursor.getString(3);
                int activo=clienteCursor.getInt(4);
                cliente=new Cliente(nombreCliente,direccion,telefono,vendedor);
                cliente.setIdCliente(idCliente);
                if(activo==1) cliente.setActivo(true);
                else cliente.setActivo(false);

                Log.d("cliente","Cliente:"+cliente.getNombre());
                
                pedidoEntregado.setCliente(cliente);
                pedidoEntregado.setFechaEntrega(fecha);
                pedidoEntregado.setPrecio(precio);
                pedidoEntregado.setEntregado(false);
                pedidoEntregado.setVendedor(vendedor);
                detalles=dataSource.getDetalles(pedidoEntregado);
                while (detalles.moveToNext()) {
                    //Leer desde la BD
                    idDetalle = detalles.getString(0);
                    secuencia = detalles.getInt(1);
                    idProd = detalles.getString(2);
                    cantidad = detalles.getInt(3);
                    totalLinea = detalles.getInt(4);

                    //Crear el detalle
                    detallePedido = new DetallePedido();
                    detallePedido.setIdDetalle(idDetalle+"#"+secuencia);
                    detallePedido.setSecuencia(secuencia);
                    producto=buscarProducto(idProd);

                    detallePedido.setProducto(producto);
                    detallePedido.setCantidad(cantidad);
                    detallePedido.setPrecio(totalLinea);
                    detallePedido.setPedido(pedidoEntregado);

                    //Agregar el detalle a la lista
                    listaDetalles.add(detallePedido);
                }
                pedidoEntregado.setDetalles(listaDetalles);
            }//if
        }//while
        return pedidoEntregado;
    }
    public String getIdPedido(String nombreCliente, int precio, String fecha){
        Cursor consulta=dataSource.buscarPedido(nombreCliente,precio,fecha);
        String idPedido;
        consulta.moveToNext();
        idPedido=consulta.getString(0);
        return idPedido;
    }

    /***** Método usado para llenar la base de datos la primera vez que se ejecuta ******/
public void datosInicales(){
    Cliente c;
    Vendedor v;
    ArrayList<Cliente> clientes;
    try {
        dataSource.getDb().beginTransaction();
        v = new Vendedor();
        v.setNombre("Carlos Rojas");
        v.setLogin("crojas@productosfrescos.com");
        v.setPassword("crojas1234");
        String idv = dataSource.insertVendedor(v);
        v.setIdVendedor(idv);
        clientes = v.getClientes();

        c = new Cliente("Almacen Casi Perfecto", "Arturo Prat 389", "22334455", v);
        String idc = dataSource.insertCliente(c);
        c.setIdCliente(idc);
        clientes.add(c);

        c = new Cliente("La Veguita de Santa Maria", "Arturo Prat 317", "23232323", v);
        idc = dataSource.insertCliente(c);
        c.setIdCliente(idc);
        clientes.add(c);

        v = new Vendedor();
        v.setNombre("Rosa Caro");
        v.setLogin("rcaro@productosfrescos.com");
        v.setPassword("rcaro1234");
        idv = dataSource.insertVendedor(v);
        v.setIdVendedor(idv);
        clientes = v.getClientes();

        c = new Cliente("Almagro Frutas Secas", "San Diego 420", "22442244", v);
        idc = dataSource.insertCliente(c);
        c.setIdCliente(idc);
        clientes.add(c);

        //Productos
        Producto p = new Producto("Flan de Chocolate", 1650);
        String idp = dataSource.insertProducto(p);
        p.setCodigo(idp);
        p = new Producto("Tiramisu de lúcuma", 1890);
        idp = dataSource.insertProducto(p);
        p.setCodigo(idp);
        p = new Producto("Brownie", 1180);
        idp = dataSource.insertProducto(p);
        p.setCodigo(idp);
        p = new Producto("Pie de limón", 1950);
        idp = dataSource.insertProducto(p);
        p.setCodigo(idp);
        p = new Producto("Tutifruti", 950);
        idp = dataSource.insertProducto(p);
        p.setCodigo(idp);
        dataSource.getDb().setTransactionSuccessful();
    }finally {
        dataSource.getDb().endTransaction();

    }

}

}
