package clases;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import actividad14.unidad3.inacap.cl.proyectoversiones.LoginActivity;
import database.OperacionesBaseDatos;
import database.ProductosDataSource;

/**
 * Created by Jimena on 27-06-2016.
 */
public class ListaProductos {
    private static ListaProductos instancia=new ListaProductos();
    private ArrayList<Producto> listaProductos;
    private OperacionesBaseDatos dataSource;
    private Cursor registros;

    public static ListaProductos getInstancia(){
        return instancia;
    }

    //Constructor
    private ListaProductos(){
        String codigo;
        String nombre;
        int precio;
        Producto producto;
        listaProductos=new ArrayList<Producto>();
        dataSource= LoginActivity.getDataSource(); //Leer la BD
        registros=dataSource.getProductos();  //Leer la tabla productos
        while(registros.moveToNext()){
            codigo=registros.getString(1);
            nombre=registros.getString(2);
            precio=registros.getInt(3);
            producto=new Producto(codigo,nombre,precio);
            listaProductos.add(producto);


        }



    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    //Buscar un producto
    public Producto buscarProducto(String codProducto){
        int tam=listaProductos.size();
        Producto p=null;
        for(int i=0;i<tam;i++){
            p=listaProductos.get(i);
            if(p.getCodigo().compareTo(codProducto)==0){
                return p;
            }
        }
        return null;

    }


}
