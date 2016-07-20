package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

import clases.DetallePedido;
import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class DetalleEntregaActivity extends AppCompatActivity {
    private String idVendedorStr;
    private String idPedido;
    private Vendedor vendedor;
    private Pedido pedido;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrega);

        //Recuperar el pedido
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");

        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        vendedor=listaVendedores.getVendedor(idVendedorStr);
        idPedido=extras.getString("id_pedido");
        pedido=listaVendedores.getPedidoEntregado(vendedor,idPedido);



        //Tabla de datos del pedido
        GridView gvDatosPedidos=(GridView)findViewById(R.id.gvDatosPedido);
        ArrayList<String> datos1=new ArrayList<String>();

        //Nombre
        datos1.add(getResources().getString(R.string.nombre_cliente)+": ");
        datos1.add(pedido.getCliente().getNombre());

        //Dirección
        datos1.add(getResources().getString(R.string.direccion_cliente)+": ");
        datos1.add(pedido.getCliente().getDireccion());

        //Teléfono
        datos1.add(getResources().getString(R.string.telefono_cliente)+": ");
        datos1.add(pedido.getCliente().getTelefono());

        //IDPedido
        datos1.add(getResources().getString(R.string.id_pedido)+": ");
        datos1.add(String.valueOf(pedido.getIdPedido()));

        //Fecha Entrega
        datos1.add(getResources().getString(R.string.fecha_entrega)+": ");
        datos1.add(pedido.getFechaEntrega());

        //Total
        datos1.add(getResources().getString(R.string.total)+": ");
        //Formato de moneda
        Double total=new Double(pedido.getPrecio());
        NumberFormat formato;
        String salidaTotal;
        formato=NumberFormat.getCurrencyInstance();
        salidaTotal=formato.format(total);
        datos1.add(String.valueOf(salidaTotal));


        //Tabla del pedido
        adapter1=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, datos1);
        gvDatosPedidos.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        //Recuperar detalle
        ArrayList<DetallePedido> detallePedido=pedido.getDetalles();

        //LLenar la tabla
        GridView gvDetalleEntrega=(GridView)findViewById(R.id.gvDetalleEntrega);
        ArrayList<String> datos2=new ArrayList<String>();

        datos2.add(getResources().getString(R.string.id_producto)+" ");
        datos2.add(getResources().getString(R.string.txt_producto)+" ");
        datos2.add(getResources().getString(R.string.txt_cantidad)+" ");
        int tam=detallePedido.size();
        for(int i=0;i<tam;i++){
            DetallePedido detalle=detallePedido.get(i);
            datos2.add(String.valueOf(detalle.getProducto().getCodigo()));
            datos2.add(detalle.getProducto().getNombreProducto());
            datos2.add(String.valueOf(detalle.getCantidad()));
        }
        //Tabla de detalle
        adapter2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,datos2);
        gvDetalleEntrega.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        //Botón volver
        Button cmdVolverAPedidos=(Button)findViewById(R.id.cmdVolverAPedidos);
        cmdVolverAPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });


    }
    public void volver(){
        Intent intent=new Intent(DetalleEntregaActivity.this, MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        DetalleEntregaActivity.this.startActivity(intent);
    }

}
