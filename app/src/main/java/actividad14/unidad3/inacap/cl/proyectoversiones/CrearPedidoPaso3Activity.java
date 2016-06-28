package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Cliente;
import clases.DetallePedido;
import clases.ListaProductos;
import clases.ListaVendedores;
import clases.Pedido;
import clases.Producto;
import clases.Vendedor;

public class CrearPedidoPaso3Activity extends AppCompatActivity {
    private String idVendedorString;
    private String idClienteString;
    private String fechaEntrega;
    private ArrayAdapter<Producto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido_paso3);
        //ListView de productos
        ListView lvProductosPedidos=(ListView) findViewById(R.id.lvProductosPedido);
        ListaProductos listaProductos=ListaProductos.getInstancia();
        ArrayList<Producto> productos=(ArrayList<Producto>) listaProductos.getListaProductos();

        if(productos!=null){
            adapter=new ArrayAdapter<Producto>(getApplicationContext(),android.R.layout.simple_spinner_item,productos);
            lvProductosPedidos.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(CrearPedidoPaso3Activity.this,"Lista de productos vacía",Toast.LENGTH_SHORT).show();
        }

        //Botón ingresar pedido
        Button cmdIngresarPedido=(Button) findViewById(R.id.cmdIngresarPedido);
        cmdIngresarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresarPedido();
            }
        });

    }
    //Listener para el botón ingresar pedido
    public void ingresarPedido(){
        //Leer cada textbox, calcular el total por línea y sumar el total
        int cod1=0, cod2=0, cod3=0, cod4=0, cod5=0;
        int cant1=0, cant2=0, cant3=0, cant4=0, cant5=0;
        int totalLinea=0, totalPedido=0;
        ListaProductos listaProductos=ListaProductos.getInstancia();
        DetallePedido detallePedido;
        Pedido pedido=new Pedido();
        ArrayList<DetallePedido> detalles=new ArrayList<DetallePedido>();

        //Producto 1
        EditText txtProducto1=(EditText) findViewById(R.id.txtProducto1);
        String cod1Str=txtProducto1.getText().toString();
        EditText txtCantidad1=(EditText) findViewById(R.id.txtCantidad1);
        String cant1Str=txtCantidad1.getText().toString();
        if(cod1Str.compareTo("")!=0 && cant1Str.compareTo("")!=0) { //Si los textbox tienen datos
            try {
                cod1 = Integer.parseInt(cod1Str);  //transformar a entero
            } catch (NumberFormatException e) {
                Toast.makeText(CrearPedidoPaso3Activity.this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
            }
            //Ver si el producto existe
            Producto p1=listaProductos.buscarProducto(cod1);
            if(p1==null){
                Toast.makeText(CrearPedidoPaso3Activity.this,"El código no existe",Toast.LENGTH_SHORT).show();
            }else {
                //Verificar la cantidad
                try{
                    cant1=Integer.parseInt(cant1Str);
                }catch (NumberFormatException e){
                    Toast.makeText(CrearPedidoPaso3Activity.this, "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
                //Calcular el total linea
                totalLinea=p1.getPrecio()*cant1;

                //Sumar
                totalPedido=totalPedido+totalLinea;

                //Crear el detalle del pedido con el producto y la cantidad
                detallePedido=new DetallePedido();
                detallePedido.setProducto(p1);
                detallePedido.setCantidad(cant1);

                //Agregar el detalle a la lista
                detalles.add(detallePedido);
            }
        } //fin del if del producto

        //Producto 2
        EditText txtProducto2=(EditText) findViewById(R.id.txtProducto2);
        String cod2Str=txtProducto2.getText().toString();
        EditText txtCantidad2=(EditText) findViewById(R.id.txtCantidad2);
        String cant2Str=txtCantidad2.getText().toString();
        if(cod2Str.compareTo("")!=0 && cant2Str.compareTo("")!=0) { //Si los textbox tienen datos
            try {
                cod2 = Integer.parseInt(cod2Str);  //transformar a entero
            } catch (NumberFormatException e) {
                Toast.makeText(CrearPedidoPaso3Activity.this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
            }
            //Ver si el producto existe
            Producto p2=listaProductos.buscarProducto(cod2);
            if(p2==null){
                Toast.makeText(CrearPedidoPaso3Activity.this,"El código no existe",Toast.LENGTH_SHORT).show();
            }else {
                //Verificar la cantidad
                try{
                    cant2=Integer.parseInt(cant2Str);
                }catch (NumberFormatException e){
                    Toast.makeText(CrearPedidoPaso3Activity.this, "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
                //Calcular el total linea
                totalLinea=p2.getPrecio()*cant2;

                //Sumar
                totalPedido=totalPedido+totalLinea;

                //Crear el detalle del pedido con el producto y la cantidad
                detallePedido=new DetallePedido();
                detallePedido.setProducto(p2);
                detallePedido.setCantidad(cant2);

                //Agregar el detalle a la lista
                detalles.add(detallePedido);

            }
        } //fin del if del producto

        //Producto 3
        EditText txtProducto3=(EditText) findViewById(R.id.txtProducto3);
        String cod3Str=txtProducto3.getText().toString();
        EditText txtCantidad3=(EditText) findViewById(R.id.txtCantidad3);
        String cant3Str=txtCantidad3.getText().toString();
        if(cod3Str.compareTo("")!=0 && cant3Str.compareTo("")!=0) { //Si los textbox tienen datos
            try {
                cod3 = Integer.parseInt(cod3Str);  //transformar a entero
            } catch (NumberFormatException e) {
                Toast.makeText(CrearPedidoPaso3Activity.this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
            }
            //Ver si el producto existe
            Producto p3=listaProductos.buscarProducto(cod3);
            if(p3==null){
                Toast.makeText(CrearPedidoPaso3Activity.this,"El código no existe",Toast.LENGTH_SHORT).show();
            }else {
                //Verificar la cantidad
                try{
                    cant3=Integer.parseInt(cant3Str);
                }catch (NumberFormatException e){
                    Toast.makeText(CrearPedidoPaso3Activity.this, "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
                //Calcular el total linea
                totalLinea=p3.getPrecio()*cant3;

                //Sumar
                totalPedido=totalPedido+totalLinea;

                //Crear el detalle del pedido con el producto y la cantidad
                detallePedido=new DetallePedido();
                detallePedido.setProducto(p3);
                detallePedido.setCantidad(cant3);

                //Agregar el detalle a la lista
                detalles.add(detallePedido);

            }
        } //fin del if del producto

        //Producto 4
        EditText txtProducto4=(EditText) findViewById(R.id.txtProducto4);
        String cod4Str=txtProducto4.getText().toString();
        EditText txtCantidad4=(EditText) findViewById(R.id.txtCantidad4);
        String cant4Str=txtCantidad4.getText().toString();
        if(cod4Str.compareTo("")!=0 && cant4Str.compareTo("")!=0) { //Si los textbox tienen datos
            try {
                cod4 = Integer.parseInt(cod4Str);  //transformar a entero
            } catch (NumberFormatException e) {
                Toast.makeText(CrearPedidoPaso3Activity.this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
            }
            //Ver si el producto existe
            Producto p4=listaProductos.buscarProducto(cod4);
            if(p4==null){
                Toast.makeText(CrearPedidoPaso3Activity.this,"El código no existe",Toast.LENGTH_SHORT).show();
            }else {
                //Verificar la cantidad
                try{
                    cant4=Integer.parseInt(cant4Str);
                }catch (NumberFormatException e){
                    Toast.makeText(CrearPedidoPaso3Activity.this, "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
                //Calcular el total linea
                totalLinea=p4.getPrecio()*cant4;

                //Sumar
                totalPedido=totalPedido+totalLinea;

                //Crear el detalle del pedido con el producto y la cantidad
                detallePedido=new DetallePedido();
                detallePedido.setProducto(p4);
                detallePedido.setCantidad(cant4);

                //Agregar el detalle a la lista
                detalles.add(detallePedido);

            }
        } //fin del if del producto

        //Producto 5
        EditText txtProducto5=(EditText) findViewById(R.id.txtProducto5);
        String cod5Str=txtProducto5.getText().toString();
        EditText txtCantidad5=(EditText) findViewById(R.id.txtCantidad5);
        String cant5Str=txtCantidad5.getText().toString();
        if(cod5Str.compareTo("")!=0 && cant5Str.compareTo("")!=0) { //Si los textbox tienen datos
            try {
                cod5 = Integer.parseInt(cod5Str);  //transformar a entero
            } catch (NumberFormatException e) {
                Toast.makeText(CrearPedidoPaso3Activity.this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
            }
            //Ver si el producto existe
            Producto p5=listaProductos.buscarProducto(cod5);
            if(p5==null){
                Toast.makeText(CrearPedidoPaso3Activity.this,"El código no existe",Toast.LENGTH_SHORT).show();
            }else {
                //Verificar la cantidad
                try{
                    cant5=Integer.parseInt(cant5Str);
                }catch (NumberFormatException e){
                    Toast.makeText(CrearPedidoPaso3Activity.this, "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
                //Calcular el total linea
                totalLinea=p5.getPrecio()*cant5;

                //Sumar
                totalPedido=totalPedido+totalLinea;

                //Crear el detalle del pedido con el producto y la cantidad
                detallePedido=new DetallePedido();
                detallePedido.setProducto(p5);
                detallePedido.setCantidad(cant5);

                //Agregar el detalle a la lista
                detalles.add(detallePedido);

            }
        } //fin del if del producto

        // Armar el pedido
        //Recuperar lo que viene de antes
        Bundle extras=getIntent().getExtras();

        //Vendedor
        idVendedorString=extras.getString("id_vendedor");
        int idVendedor=Integer.parseInt(idVendedorString);

        ListaVendedores listaVendedores= ListaVendedores.getInstancia();
        Vendedor vendedor=listaVendedores.getVendedor(idVendedor);


        //Cliente
        idClienteString=extras.getString("id_cliente");
        int idCliente=Integer.parseInt(idClienteString);
        Cliente cliente=vendedor.getCliente(idCliente);
        pedido.setCliente(cliente);

        //Detalles
        pedido.setDetalles(detalles);


        //fecha de entrega
        fechaEntrega=extras.getString("fecha_entrega");
        pedido.setFechaEntrega(fechaEntrega);

        //precio
        pedido.setPrecio(totalPedido);


        //Agregar el pedido al vendedor
        vendedor.addPedido(pedido);


        //Mostrar mensaje y volver al menú
        Toast.makeText(CrearPedidoPaso3Activity.this,"Pedido ingresado, vendedor:"+vendedor.getNombre()+", cliente: "+cliente.getNombre()+", total: $"+pedido.getPrecio(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(CrearPedidoPaso3Activity.this,MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        CrearPedidoPaso3Activity.this.startActivity(intent);

    }

}
