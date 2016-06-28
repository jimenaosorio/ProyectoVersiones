package clases;

import java.util.ArrayList;

/**
 * Created by Jimena on 27-06-2016.
 */
public class ListaProductos {
    private static ListaProductos instancia=new ListaProductos();
    private ArrayList<Producto> listaProductos;

    public static ListaProductos getInstancia(){
        return instancia;
    }

    //Constructor
    private ListaProductos(){
        listaProductos=new ArrayList<Producto>();
        Producto p=new Producto();
        p.setCodigo(1001);
        p.setNombreProducto("Pera");
        p.setPrecio(600);
        listaProductos.add(p);
        p=new Producto();
        p.setCodigo(1002);
        p.setNombreProducto("Melon");
        p.setPrecio(1000);
        listaProductos.add(p);
        p=new Producto();
        p.setCodigo(1003);
        p.setNombreProducto("Piña");
        p.setPrecio(1500);
        listaProductos.add(p);
        p=new Producto();
        p.setCodigo(1004);
        p.setNombreProducto("Uva");
        p.setPrecio(800);
        listaProductos.add(p);
        p=new Producto();
        p.setCodigo(1005);
        p.setNombreProducto("Kiwi");
        p.setPrecio(600);
        listaProductos.add(p);

    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    //Buscar un producto
    public Producto buscarProducto(int codProducto){
        int tam=listaProductos.size();
        Producto p=null;
        for(int i=0;i<tam;i++){
            p=listaProductos.get(i);
            if(p.getCodigo()==codProducto){
                return p;
            }
        }
        return null;

    }

}
