package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
    private String idVendedorStr;
    private int idCliente;
    private String fechaEntrega;
    private ArrayList<Producto> productos;
    private ArrayList<CheckBox> casillas;
    private ArrayList<EditText> cantidades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido_paso3);
        //Recuperar los datos que vienen
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        idCliente=extras.getInt("id_cliente");
        fechaEntrega=extras.getString("fecha_entrega");

        //Tabla de productos
        TableLayout tlDetalles=(TableLayout)findViewById(R.id.tlDetalles);

        //Recuperar la lista de productos
        ListaProductos listaProductos=ListaProductos.getInstancia();
        productos=(ArrayList<Producto>) listaProductos.getListaProductos();
        int tam=productos.size();
        Producto producto;

        //ArrayList para acceder a los datos
        casillas=new ArrayList<CheckBox>();

        //  ArrayList para acceder a los checkbox
        cantidades=new ArrayList<EditText>();

        //Recorrer la lista de productos
        if(productos!=null) {
            //Encabezados
            TableRow encabezado=new TableRow(this);
            TextView seleccione=new TextView(this);
            seleccione.setText("Seleccione  ");
            encabezado.addView(seleccione);
            TextView prod=new TextView(this);
            prod.setText("  Producto  ");
            encabezado.addView(prod);
            TextView cant=new TextView(this);
            cant.setText("  Cantidad");
            encabezado.addView(cant);
            tlDetalles.addView(encabezado);

            for (int i = 0; i < tam; i++) {
                //Leo el producto
                producto=productos.get(i);
                //Creo la fila de la tabla
                TableRow fila=new TableRow(this);
                //Checkbox
                CheckBox chkb=new CheckBox(this);
                chkb.setId(producto.getCodigo());
                fila.addView(chkb);
                casillas.add(chkb);
                //Producto
                TextView txtProducto=new TextView(this);
                txtProducto.setText(producto.toString());
                fila.addView(txtProducto);
                //Cantidades
                EditText cantidad=new EditText(this);
                cantidad.setId(producto.getCodigo());
                fila.addView(cantidad);
                cantidades.add(cantidad);

                tlDetalles.addView(fila);

            }
            final Button cmdIngresarPedido=(Button) findViewById(R.id.cmdIngresarPedido);
            cmdIngresarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ingresarPedido();
                }
            });
        }



    }
    //Listener para el botón ingresar pedido
    public void ingresarPedido(){
        //Recupero el vendedor
        int idVendedor=Integer.parseInt(idVendedorStr);
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        Vendedor vendedor=listaVendedores.getVendedor(idVendedor);

        //Leer la tabla y armar el detalle
        int totalLinea=0, totalPedido=0;
        DetallePedido detallePedido;
        ArrayList<DetallePedido> detalles=new ArrayList<DetallePedido>();
        int tam=productos.size();
        for(int i=0;i<tam;i++){
            CheckBox casilla=(CheckBox)casillas.get(i);
            if(casilla.isChecked()){
                detallePedido=new DetallePedido();
                Producto p=productos.get(i);
                int precio=p.getPrecio();
                EditText txtCant=(EditText)cantidades.get(i);
                int cant=0;
                try {
                    cant = Integer.parseInt(txtCant.getText().toString());
                }catch (NumberFormatException e){
                    Toast.makeText(CrearPedidoPaso3Activity.this,"Formato de cantidad incorrecto",Toast.LENGTH_SHORT).show();
                }
                totalLinea=precio*cant;
                totalPedido=totalPedido+totalLinea;
                detallePedido.setProducto(p);
                detallePedido.setCantidad(cant);
                detalles.add(detallePedido);
            } //fin if
        } //fin for
        //Armar el pedido
        Pedido pedido=new Pedido();
        //Agregar el cliente
        Cliente cliente=vendedor.getCliente(idCliente);
        pedido.setCliente(cliente);
        //Detalles
        pedido.setDetalles(detalles);
        //Fecha de entrega
        pedido.setFechaEntrega(fechaEntrega);
        //Precio
        pedido.setPrecio(totalPedido);
        //Agregar el pedido al vendedor
        vendedor.addPedido(pedido);

        Toast.makeText(CrearPedidoPaso3Activity.this,"Pedido ingresado, cliente: "+pedido.getCliente().getNombre()+", fecha de entrega: "+pedido.getFechaEntrega()+", Total: $"+pedido.getPrecio(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(CrearPedidoPaso3Activity.this,MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        CrearPedidoPaso3Activity.this.startActivity(intent);

    }

}
