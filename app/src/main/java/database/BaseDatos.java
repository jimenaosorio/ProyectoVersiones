package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import android.support.design.widget.TabLayout;

import clases.Vendedor;
import database.ContratoPedidos.DetallesPedido;
import database.ContratoPedidos.Pedidos;
import database.ContratoPedidos.Productos;
import database.ContratoPedidos.Clientes;
import database.ContratoPedidos.Vendedores;
/**
 * Created by Jimena on 05-07-2016.
 */
public class BaseDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "productosfrescos.db";

    private static final int VERSION_ACTUAL = 1;

    private final Context contexto;

    //Tablas
    interface Tablas {
        String PEDIDO = "pedido";
        String DETALLE_PEDIDO = "detalle_pedido";
        String PRODUCTO = "producto";
        String CLIENTE = "cliente";
        String VENDEDOR = "vendedor";
    }

    //Claves
    interface Referencias {

        String ID_PEDIDO = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.PEDIDO, Pedidos.ID);

        String ID_PRODUCTO = String.format("REFERENCES %s(%s)",
                Tablas.PRODUCTO, ContratoPedidos.Productos.ID);

        String ID_CLIENTE = String.format("REFERENCES %s(%s)",
                Tablas.CLIENTE, Clientes.ID);

        String ID_VENDEDOR = String.format("REFERENCES %s(%s)",
                Tablas.VENDEDOR, Vendedores.ID);

    }
    public BaseDatos(Context contexto){
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabla Vendedor
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,"+
                        "%s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL )",
                Tablas.VENDEDOR, BaseColumns._ID,
                Vendedores.ID,
                Vendedores.NOMBRE,
                Vendedores.LOGIN,
                Vendedores.PASSWORD,
                Vendedores.MONTO_PEDIDO,
                Vendedores.MONTO_COBRADO,
                Vendedores.SALDO));

        //Tabla Cliente
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT,%s TEXT NOT NULL %s,%s INTEGER NOT NULL)",
                Tablas.CLIENTE, BaseColumns._ID,
                Clientes.ID,
                Clientes.NOMBRE,
                Clientes.DIRECCION,
                Clientes.TELEFONO,
                Clientes.ID_VENDEDOR,Referencias.ID_VENDEDOR,
                Clientes.ACTIVO));

        //Tabla producto
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s INTEGER NOT NULL)",
                Tablas.PRODUCTO, BaseColumns._ID,
                Productos.ID,
                Productos.NOMBRE,
                Productos.PRECIO));

        //Tabla pedido
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL %s,%s DATETIME NOT NULL," +
                        "%s INTEGER NOT NULL,%s INTEGER NOT NULL,%s TEXT NOT NULL %s)",
                Tablas.PEDIDO, BaseColumns._ID,
                Pedidos.ID,
                Pedidos.ID_CLIENTE, Referencias.ID_CLIENTE,
                Pedidos.FECHA,
                Pedidos.PRECIO,
                Pedidos.ENTREGADO,
                Pedidos.ID_VENDEDOR, Referencias.ID_VENDEDOR));

        //Tabla detalle_pedido
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL %s,%s INTEGER NOT NULL CHECK (%s>0),%s INTEGER NOT NULL %s," +
                        "%s INTEGER NOT NULL,%s INTEGER NOT NULL,UNIQUE (%s,%s) )",
                Tablas.DETALLE_PEDIDO, BaseColumns._ID,
                DetallesPedido.ID, Referencias.ID_PEDIDO,
                DetallesPedido.SECUENCIA, DetallesPedido.SECUENCIA,
                DetallesPedido.ID_PRODUCTO, Referencias.ID_PRODUCTO,
                DetallesPedido.CANTIDAD,
                DetallesPedido.PRECIO,
                DetallesPedido.ID, DetallesPedido.SECUENCIA));





    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.DETALLE_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.VENDEDOR);

        onCreate(db);
    }


}
