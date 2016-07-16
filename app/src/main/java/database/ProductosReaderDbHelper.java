package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jimena on 02-07-2016.
 */
public class ProductosReaderDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Productos.db";
    public static final int DATABASE_VERSION = 1;

    public ProductosReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear las tablas
        db.execSQL(ProductosDataSource.CREAR_PRODUCTOS_SCRIPT);
        db.execSQL(ProductosDataSource.CREAR_CLIENTES_SCRIPT);
        db.execSQL(ProductosDataSource.CREAR_VENDEDOR_SCRIPT);

        //Insertar registros iniciales
        db.execSQL(ProductosDataSource.INSERT_PRODUCTOS_SCRIPT);
        db.execSQL(ProductosDataSource.INSERT_CLIENTES_SCRIPT);
        db.execSQL(ProductosDataSource.INSERT_VENDEDORES_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /* cambios que se realizarán en el esquema
                en la proxima versión
             */
    }



}
