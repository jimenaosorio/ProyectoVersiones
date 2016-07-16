package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.ParcelUuid;
import android.provider.BaseColumns;
import android.text.LoginFilter;

/**
 * Created by Jimena on 02-07-2016.
 */
public class ProductosDataSource {
    //Metainformación de la base de datos

    //Tipos de datos
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Tabla Productos
    public static final String PRODUCTOS_TABLE_NAME = "Productos";


    //Tabla Clientes
    public static final String CLIENTES_TABLE_NAME = "Clientes";

    //Tabla Vendedores
    public static final String VENDEDORES_TABLE_NAME="Vendedores";


    //Campos de la tabla Productos
    public static class ColumnProductos {
        public static final String ID_PRODUCTO = BaseColumns._ID;
        public static final String NOMBRE_PRODUCTO = "nombre";
        public static final String PRECIO_PRODUCTO = "precioVenta";

    }

    //Campos de la tabla Clientes
    public static class ColumnClientes {
        public static final String ID_CLIENTE = BaseColumns._ID;
        public static final String NOMBRE_CLIENTE = "nombre";
        public static final String DIRECCION_CLIENTE = "direccion";
        public static final String TELEFONO_CLIENTE = "telefono";
        public static final String ACTIVO_CLIENTE = "activo";
        public static final String ID_VENDEDOR="id_vendedor";
    }

    //Campos de la tabla Vendedores
    public  static class ColumnVendedores{
        public static final String ID_VENDEDOR=BaseColumns._ID;
        public static final String NOMBRE_VENDEDOR="nombre";
        public static final String LOGIN_VENDEDOR="login";
        public static final String PASSWORD_VENDEDOR="pass";
        public static final String MONTO_PEDIDO="montopedido";
        public static final String MONTO_COBRADO="montocobrado";
        public static final String SALDO="saldo";


    }

    //Script de Creación de la tabla Productos
    public static final String CREAR_PRODUCTOS_SCRIPT =
            "create table "+PRODUCTOS_TABLE_NAME+"(" +
                    ColumnProductos.ID_PRODUCTO+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnProductos.NOMBRE_PRODUCTO+" "+STRING_TYPE+" not null,"+
                    ColumnProductos.PRECIO_PRODUCTO+" "+INT_TYPE+" not null)";

    //Script de creación de la tabla Clientes
    public static final String CREAR_CLIENTES_SCRIPT =
            "create table "+CLIENTES_TABLE_NAME+"(" +
                    ColumnClientes.ID_CLIENTE+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnClientes.NOMBRE_CLIENTE+" "+STRING_TYPE+" not null,"+
                    ColumnClientes.DIRECCION_CLIENTE+" "+STRING_TYPE+" not null,"+
                    ColumnClientes.TELEFONO_CLIENTE+" "+STRING_TYPE+" not null,"+
                    ColumnClientes.ACTIVO_CLIENTE+" "+INT_TYPE+" default 1,"+
                    ColumnClientes.ID_VENDEDOR+" "+INT_TYPE+" NOT NULL)";


    //Script de creación de la tabla vendedor
public static final String CREAR_VENDEDOR_SCRIPT =
            "create table "+VENDEDORES_TABLE_NAME+"("+
                    ColumnVendedores.ID_VENDEDOR+" "+INT_TYPE+" primary key autoincrement,"+
                    ColumnVendedores.NOMBRE_VENDEDOR+" "+STRING_TYPE+" not null,"+
                    ColumnVendedores.LOGIN_VENDEDOR+" "+STRING_TYPE+" not null,"+
                    ColumnVendedores.PASSWORD_VENDEDOR+" "+STRING_TYPE+" not null,"+
                    ColumnVendedores.MONTO_PEDIDO+" "+INT_TYPE+" not null,"+
                    ColumnVendedores.MONTO_COBRADO+" "+INT_TYPE+" not null,"+
                    ColumnVendedores.SALDO+" "+INT_TYPE+" not null)";


    //Scripts de inserción de productos
    public static final String INSERT_PRODUCTOS_SCRIPT ="insert into "+PRODUCTOS_TABLE_NAME
            +" values(null,\"Flan de Chocolate\",650),"
            +"(null,\"Tiramisu de Lucuma\",800),"
            +"(null,\"Brownie\",1100),"
            +"(null,\"Pie de limón\",950),"
            +"(null,\"Tutifruti\",550)";

    //Script de inserción de vendedores
    public static final String INSERT_VENDEDORES_SCRIPT="insert into "+VENDEDORES_TABLE_NAME
            +" values(null,\"Carlos Rosas\",\"crosas\",\"crosas1234\",0,0,0),"
            +"(null,\"Rosa Caro\",\"rcaro\",\"rcaro1234\",0,0,0)";

    //Script de inserción de clientes
    public static final String INSERT_CLIENTES_SCRIPT="insert into "+CLIENTES_TABLE_NAME
            +" values(null,\"Almacén Casi Perfecto\",\"Arturo Prat 389\",\"22334455\",1,1),"
            +"(null,\"La Veguita de Sta. Maria\",\"Arturo Prat 317\",\"23232323\",1,1)";

    //Variables para manipulación de datos
    private ProductosReaderDbHelper openHelper;
    private SQLiteDatabase database;

    public ProductosDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new ProductosReaderDbHelper(context);
        database = openHelper.getWritableDatabase();
    }

    //Guardar datos
    public void saveProductoRow(String nombre,int precio){
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando columnas
        values.put(ColumnProductos.NOMBRE_PRODUCTO,nombre);
        values.put(ColumnProductos.PRECIO_PRODUCTO,precio);

        //Insertando en la base de datos
        database.insert(PRODUCTOS_TABLE_NAME,null,values);
    }
    public void saveClienteRow(String nombre, String direccion, String telefono){
        ContentValues values=new ContentValues();
        values.put(ColumnClientes.NOMBRE_CLIENTE,nombre);
        values.put(ColumnClientes.DIRECCION_CLIENTE,direccion);
        values.put(ColumnClientes.TELEFONO_CLIENTE,telefono);
        values.put(ColumnClientes.ACTIVO_CLIENTE,1);
    }
    public void saveVendedor(String nombre, String login, String pass){
        ContentValues values=new ContentValues();
        values.put(ColumnVendedores.NOMBRE_VENDEDOR,nombre);
        values.put(ColumnVendedores.LOGIN_VENDEDOR,login);
        values.put(ColumnVendedores.PASSWORD_VENDEDOR,pass);
        values.put(ColumnVendedores.MONTO_PEDIDO,0);
        values.put(ColumnVendedores.MONTO_COBRADO,0);
        values.put(ColumnVendedores.SALDO,0);
    }


    //Leer datos, seleccionar todos los datos de cada tabla

    public Cursor getProductos(){
        return database.rawQuery(
                "select * from " + PRODUCTOS_TABLE_NAME, null);
    }
    public Cursor getClientes(){
        return database.rawQuery("select * from "+CLIENTES_TABLE_NAME, null);
    }
    public Cursor getVendedores(){
        return database.rawQuery("select * from "+VENDEDORES_TABLE_NAME,null);
    }

    //Desactivar un cliente
    public void desactivarCliente(int idCliente){
        //Contenedor de valores
        ContentValues values=new ContentValues();

        //Seteando activo=FALSE
        values.put(ColumnClientes.ACTIVO_CLIENTE,0); //FALSE=0

        //Where
        String seleccion=ColumnClientes.ID_CLIENTE + " = ?";
        String[] argumentos={String.valueOf(idCliente)};

        //Actualizando
        database.update(CLIENTES_TABLE_NAME,values,seleccion,argumentos);
    }

    //Modificar un cliente
    public void alterCliente(int idCliente, String nombre, String direccion, String telefono){
        //Contenedor de valores
        ContentValues values=new ContentValues();

        //Seteando valores
        values.put(ColumnClientes.NOMBRE_CLIENTE,nombre);
        values.put(ColumnClientes.DIRECCION_CLIENTE,direccion);
        values.put(ColumnClientes.TELEFONO_CLIENTE,telefono);

        //Where
        String seleccion=ColumnClientes.ID_CLIENTE + " = ?";
        String[] argumentos={String.valueOf(idCliente)};

        //Actualizando
        database.update(CLIENTES_TABLE_NAME,values,seleccion,argumentos);

    }

}
