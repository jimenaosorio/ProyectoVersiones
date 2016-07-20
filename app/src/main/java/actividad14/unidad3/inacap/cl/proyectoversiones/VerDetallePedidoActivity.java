package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

import clases.Cliente;
import clases.DetallePedido;
import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class VerDetallePedidoActivity extends AppCompatActivity {
    private String idVendedorStr;
    private String idPedido;
    private Vendedor vendedor;
    private Pedido pedido;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ListaVendedores listaVendedores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle_pedido);
        //Recuperar el pedido
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");

        listaVendedores=ListaVendedores.getInstancia();
        vendedor=listaVendedores.getVendedor(idVendedorStr);
        idPedido=extras.getString("id_pedido");
        ArrayList<Pedido>pedidos=listaVendedores.getPedidosPendientes(vendedor);
        int tam=pedidos.size();
        Pedido p=null;
        for(int i=0;i<tam;i++){
            p=pedidos.get(i);
            if(p.getIdPedido().compareTo(idPedido)==0){
                pedido=p;
            }
        }


        //Tabla de datos del pedido
        GridView gvDatosPedido=(GridView)findViewById(R.id.gvDatosPedido);
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
        gvDatosPedido.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        //Recuperar detalle
        ArrayList<DetallePedido> detallePedido=pedido.getDetalles();

        //LLenar la tabla
        GridView gvDetalle=(GridView)findViewById(R.id.gvDetalle);
        ArrayList<String> datos2=new ArrayList<String>();

        datos2.add(getResources().getString(R.string.id_producto)+" ");
        datos2.add(getResources().getString(R.string.txt_producto)+" ");
        datos2.add(getResources().getString(R.string.txt_cantidad)+" ");
        int tamD=detallePedido.size();

        for(int i=0;i<tamD;i++){
            DetallePedido detalle=detallePedido.get(i);
            datos2.add(String.valueOf(detalle.getProducto().getCodigo()));
            datos2.add(detalle.getProducto().getNombreProducto());
            datos2.add(String.valueOf(detalle.getCantidad()));
        }

        //Tabla de detalle
        adapter2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,datos2);
        gvDetalle.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        //Registrar Entrega
        Button cmdRegistrarEntrega=(Button)findViewById(R.id.cmdRegistrarEntrega);
        cmdRegistrarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEntrega();
            }
        });


    }
    public void registrarEntrega(){

        listaVendedores.hacerEntrga(vendedor,pedido);
        Toast.makeText(VerDetallePedidoActivity.this,getResources().getString(R.string.entrega_registrada),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(VerDetallePedidoActivity.this, MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        VerDetallePedidoActivity.this.startActivity(intent);
    }



}
