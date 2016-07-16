package database;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import clases.*;

/**
 * Created by Jimena on 05-07-2016.
 */
public final class OperacionesBaseDatos {
    private static BaseDatos baseDatos;
    private static OperacionesBaseDatos instancia=new OperacionesBaseDatos();

    private OperacionesBaseDatos(){

    }
    public static OperacionesBaseDatos getInstancia(Context context){
        if(baseDatos==null) baseDatos=new BaseDatos(context);
        return instancia;
    }
/*****************  Operaciones CRUD   ****************************/
    //Productos
    //Leer la tabla
    public Cursor getProductos(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", BaseDatos.Tablas.PRODUCTO);

        return db.rawQuery(sql, null);
    }
    //Insertar un registro
    public String insertProducto(Producto producto){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        //Generar la clave
        String idProducto= ContratoPedidos.Productos.generarIdProducto();
        //Ingresar las columnas
        valores.put(ContratoPedidos.Productos.ID,idProducto);
        valores.put(ContratoPedidos.Productos.NOMBRE,producto.getNombreProducto());
        valores.put(ContratoPedidos.Productos.PRECIO,producto.getPrecio());
        //Ingresar la fila
        db.insertOrThrow(BaseDatos.Tablas.PRODUCTO,null,valores);
        return idProducto;
    }

    //Vendedores
    //Leer la tabla
    public  Cursor getVendedores(){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        String sql=String.format("SELECT * FROM %s",BaseDatos.Tablas.VENDEDOR);
        return db.rawQuery(sql,null);
    }
    //Insertar un vendedor
    public String insertVendedor(Vendedor vendedor){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //Clave
        String idVendedor=ContratoPedidos.Vendedores.generarIdVendedor();
        //Ingresar las columnas
        valores.put(ContratoPedidos.Vendedores.ID,idVendedor);
        valores.put(ContratoPedidos.Vendedores.NOMBRE,vendedor.getNombre());
        valores.put(ContratoPedidos.Vendedores.LOGIN,vendedor.getLogin());
        valores.put(ContratoPedidos.Vendedores.PASSWORD,vendedor.getPassword());
        valores.put(ContratoPedidos.Vendedores.MONTO_PEDIDO,vendedor.getMontoPedidos());
        valores.put(ContratoPedidos.Vendedores.MONTO_COBRADO,vendedor.getMontoCobrado());
        valores.put(ContratoPedidos.Vendedores.SALDO,vendedor.getSaldo());
        //Ingresar la fila
        db.insertOrThrow(BaseDatos.Tablas.VENDEDOR,null,valores);
        return idVendedor;
    }
    //Modificar caja
    public boolean modificarCaja(Vendedor vendedor){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //Where
        String condicion=String.format("%s=?", ContratoPedidos.Vendedores.ID);
        String[] argumentos={vendedor.getIdVendedor()};
        //Columnas a modificar
        valores.put(ContratoPedidos.Vendedores.MONTO_PEDIDO,vendedor.getMontoPedidos());
        valores.put(ContratoPedidos.Vendedores.MONTO_COBRADO,vendedor.getMontoCobrado());
        valores.put(ContratoPedidos.Vendedores.SALDO,vendedor.getSaldo());
        //update
        int resultado=db.update(BaseDatos.Tablas.VENDEDOR,valores,condicion,argumentos);
        return resultado>0;
    }
    //Hacer entrega de un pedido
    public boolean hacerEntrega(String idPedido){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //Where
        String condicion=String.format("%s=?", ContratoPedidos.Pedidos.ID);
        String[] argumentos={idPedido};
        //Columnas a modificar
        valores.put(ContratoPedidos.Pedidos.ENTREGADO,1);
        //update
        int resultado=db.update(BaseDatos.Tablas.PEDIDO,valores,condicion,argumentos);
        return resultado>0;
    }


    //Clientes

    //Leer los clientes de un vendedor si están activos
    public Cursor getClientes(String idVendedor){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //Select
        String[] proyeccion={
                ContratoPedidos.Clientes.ID,
                ContratoPedidos.Clientes.NOMBRE,
                ContratoPedidos.Clientes.DIRECCION,
                ContratoPedidos.Clientes.TELEFONO,
                ContratoPedidos.Clientes.ACTIVO
        };
        //Where
        String seleccion=String.format("%s=? and %s=?", ContratoPedidos.Clientes.ID_VENDEDOR, ContratoPedidos.Clientes.ACTIVO);
        String[] seleccionArgs={idVendedor,"1"};

        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(BaseDatos.Tablas.CLIENTE);
        return builder.query(db,proyeccion,seleccion,seleccionArgs,null,null,null);


    }
    //Devolver un cliente por ID
    public Cursor getCliente(String idCliente){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //Select
        String[] proyeccion={
                ContratoPedidos.Clientes.ID,
                ContratoPedidos.Clientes.NOMBRE,
                ContratoPedidos.Clientes.DIRECCION,
                ContratoPedidos.Clientes.TELEFONO,
                ContratoPedidos.Clientes.ACTIVO
        };
        //Where
        String seleccion=String.format("%s=?", ContratoPedidos.Clientes.ID);
        String[] seleccionArgs={idCliente};
        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(BaseDatos.Tablas.CLIENTE);
        return builder.query(db,proyeccion,seleccion,seleccionArgs,null,null,null);


    }

    //Insertar un cliente
    public String insertCliente(Cliente cliente){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //Clave
        String idCliente= ContratoPedidos.Clientes.generarIdCliente();
        //Ingresar las columnas
        valores.put(ContratoPedidos.Clientes.ID,idCliente);
        valores.put(ContratoPedidos.Clientes.NOMBRE,cliente.getNombre());
        valores.put(ContratoPedidos.Clientes.DIRECCION,cliente.getDireccion());
        valores.put(ContratoPedidos.Clientes.TELEFONO,cliente.getTelefono());
        valores.put(ContratoPedidos.Clientes.ID_VENDEDOR,cliente.getVendedor().getIdVendedor());
        valores.put(ContratoPedidos.Clientes.ACTIVO,1); //Todos los clientes se ingresan como activos
        //Ingresar la fila
        db.insertOrThrow(BaseDatos.Tablas.CLIENTE,null,valores);
        return idCliente;
    }

    //Desactivar un cliente
    public boolean desactivarCliente(Cliente cliente){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //where
        String condicion=String.format("%s=?", ContratoPedidos.Clientes.ID);
        String[] argumentos={cliente.getIdCliente()};
        //Columna a modificar
        valores.put(ContratoPedidos.Clientes.ACTIVO,0);
        //update
        int resultado=db.update(BaseDatos.Tablas.CLIENTE,valores,condicion,argumentos);
        return resultado>0;

    }

    //Modificar un cliente
    public boolean updateCliente(Cliente cliente){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //where
        String condicion=String.format("%s=?", ContratoPedidos.Clientes.ID);
        String[] argumentos={cliente.getIdCliente()};
        //Columnas a modificar
        valores.put(ContratoPedidos.Clientes.NOMBRE,cliente.getNombre());
        valores.put(ContratoPedidos.Clientes.DIRECCION,cliente.getDireccion());
        valores.put(ContratoPedidos.Clientes.TELEFONO,cliente.getTelefono());
        //update
        int resultado=db.update(BaseDatos.Tablas.CLIENTE,valores,condicion,argumentos);
        return resultado>0;
    }
    // Pedidos

    public String insertPedido(Pedido pedido){

        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //Clave
        String idPedido=ContratoPedidos.Pedidos.generarIdPedido();
        //Insertar valores
        valores.put(ContratoPedidos.Pedidos.ID,idPedido);
        valores.put(ContratoPedidos.Pedidos.ID_CLIENTE,pedido.getCliente().getIdCliente());
        valores.put(ContratoPedidos.Pedidos.FECHA,pedido.getFechaEntrega());
        valores.put(ContratoPedidos.Pedidos.PRECIO,pedido.getPrecio());
        valores.put(ContratoPedidos.Pedidos.ENTREGADO,0);
        valores.put(ContratoPedidos.Pedidos.ID_VENDEDOR,pedido.getVendedor().getIdVendedor());
        db.insertOrThrow(BaseDatos.Tablas.PEDIDO,null,valores);


        return idPedido;
    }
    public Cursor getPedidosPendientes(Vendedor vendedor){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //Where
        String seleccion=String.format("%s=? and %s=?",ContratoPedidos.Pedidos.ID_VENDEDOR, ContratoPedidos.Pedidos.ENTREGADO);
        String[] argumento={vendedor.getIdVendedor(),"0"};
        //Select
        String[] proyeccion={
                ContratoPedidos.Pedidos.ID,
                ContratoPedidos.Pedidos.ID_CLIENTE,
                ContratoPedidos.Pedidos.FECHA,
                ContratoPedidos.Pedidos.PRECIO
        };
        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(BaseDatos.Tablas.PEDIDO);
        return builder.query(db,proyeccion,seleccion,argumento,null,null,null);

    }
    public Cursor getPedidosEntregados(Vendedor vendedor){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //Where
        String seleccion=String.format("%s=? and %s=?",ContratoPedidos.Pedidos.ID_VENDEDOR, ContratoPedidos.Pedidos.ENTREGADO);
        String[] argumento={vendedor.getIdVendedor(),"1"};
        //Select
        String[] proyeccion={
                ContratoPedidos.Pedidos.ID,
                ContratoPedidos.Pedidos.ID_CLIENTE,
                ContratoPedidos.Pedidos.FECHA,
                ContratoPedidos.Pedidos.PRECIO
        };
        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(BaseDatos.Tablas.PEDIDO);
        return builder.query(db,proyeccion,seleccion,argumento,null,null,null);
    }

    public Cursor buscarPedido(String nombreCliente, int precio, String fecha){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //JOIN
        String mezcla=BaseDatos.Tablas.CLIENTE+" INNER JOIN "+BaseDatos.Tablas.PEDIDO+
                " ON "+BaseDatos.Tablas.CLIENTE+"."+ContratoPedidos.Clientes.ID+"="+ContratoPedidos.Pedidos.ID_CLIENTE;
        //Where
        String seleccion=String.format("%s=? and %s=? and %s=?",ContratoPedidos.Clientes.NOMBRE,
                ContratoPedidos.Pedidos.PRECIO,ContratoPedidos.Pedidos.FECHA);
        String[] argumento={nombreCliente,String.valueOf(precio),fecha};
        //Select
        String[] proyeccion={
                BaseDatos.Tablas.PEDIDO+"."+ContratoPedidos.Pedidos.ID
        };
        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(mezcla);
        return builder.query(db,proyeccion,seleccion,argumento,null,null,null);
    }


    //Detalles
    public String insertDetallePedido(DetallePedido detallePedido){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(ContratoPedidos.DetallesPedido.ID,detallePedido.getPedido().getIdPedido());
        valores.put(ContratoPedidos.DetallesPedido.SECUENCIA,detallePedido.getSecuencia());
        valores.put(ContratoPedidos.DetallesPedido.ID_PRODUCTO,detallePedido.getProducto().getCodigo());
        valores.put(ContratoPedidos.DetallesPedido.CANTIDAD,detallePedido.getCantidad());
        valores.put(ContratoPedidos.DetallesPedido.PRECIO,detallePedido.getPrecio());
        db.insertOrThrow(BaseDatos.Tablas.DETALLE_PEDIDO,null,valores);
        return String.format("%s#%d", detallePedido.getPedido().getIdPedido(), detallePedido.getSecuencia());
    }
    public Cursor getDetalles(Pedido pedido){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //Where
        String seleccion=String.format("%s=?",ContratoPedidos.DetallesPedido.ID);
        String[] argumento={pedido.getIdPedido()};

        //Select
        String[] proyeccion={
                ContratoPedidos.DetallesPedido.ID,
                ContratoPedidos.DetallesPedido.SECUENCIA,
                ContratoPedidos.DetallesPedido.ID_PRODUCTO,
                ContratoPedidos.DetallesPedido.CANTIDAD,
                ContratoPedidos.DetallesPedido.PRECIO
        };
        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(BaseDatos.Tablas.DETALLE_PEDIDO);
        return builder.query(db,proyeccion,seleccion,argumento,null,null,null);

    }


    public Cursor getProducto(String idProducto){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //Where
        String seleccion=String.format("%s=?",ContratoPedidos.Productos.ID);
        String[] argumento={idProducto};
        //Select
        String[] proyeccion={
                ContratoPedidos.Productos.ID,
                ContratoPedidos.Productos.NOMBRE,
                ContratoPedidos.Productos.PRECIO
        };
        //Consulta
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(BaseDatos.Tablas.PRODUCTO);
        return builder.query(db,proyeccion,seleccion,argumento,null,null,null);

    }


    //Obtener toda la Base
    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }

}
