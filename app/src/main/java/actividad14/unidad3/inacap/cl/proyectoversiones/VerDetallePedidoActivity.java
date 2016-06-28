package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Cliente;
import clases.DetallePedido;
import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class VerDetallePedidoActivity extends AppCompatActivity {
    private String idVendedorString;
    private ArrayAdapter<DetallePedido> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle_pedido);
        //Recibir ID Vendedor y ID Pedido
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");
        String idPedidoString=extras.getString("id_pedido");
        int idVendedor=Integer.parseInt(idVendedorString);
        int idPedido=Integer.parseInt(idPedidoString);

        //Recuperar los objetos
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        Vendedor vendedor=listaVendedores.getVendedor(idVendedor);
        final Pedido pedido=vendedor.getPedido(idPedido);
        Cliente cliente=pedido.getCliente();
        ArrayList<DetallePedido> detallePedidos=pedido.getDetalles();

        //Rellenar los textos
        TextView txtDetalleIdPedido=(TextView)findViewById(R.id.txtDetalleIdPedido);
        txtDetalleIdPedido.setText(idPedidoString);
        TextView txtDetalleIdCliente=(TextView)findViewById(R.id.txtDetalleIdCliente);
        txtDetalleIdCliente.setText(String.valueOf(cliente.getIdCliente()));
        TextView txtDetalleNombre=(TextView)findViewById(R.id.txtDetalleNombre);
        txtDetalleNombre.setText(cliente.getNombre());
        TextView txtDetalleTelefono=(TextView) findViewById(R.id.txtDetalleTelefono);
        txtDetalleTelefono.setText(cliente.getTelefono());
        TextView txtDetalleDireccion=(TextView) findViewById(R.id.txtDetalleDireccion);
        txtDetalleDireccion.setText(cliente.getDireccion());
        TextView txtDetalleFechaEntrega=(TextView)findViewById(R.id.txtDetalleFechaEntrega);
        txtDetalleFechaEntrega.setText(pedido.getFechaEntrega());
        TextView txtDetalleValorTotal=(TextView)findViewById(R.id.txtDetalleValorTotal);
        txtDetalleValorTotal.setText(String.valueOf(pedido.getPrecio()));

        //Rellenar el detalle
        ListView lvDetalles=(ListView)findViewById(R.id.lvDetalles);
        if(detallePedidos!=null){
            adapter=new ArrayAdapter<DetallePedido>(getApplicationContext(), android.R.layout.simple_spinner_item, detallePedidos);
            lvDetalles.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        //Botón registrar entrega
        Button cmdRegistrarEntrega=(Button)findViewById(R.id.cmdRegistrarEntrega);
        cmdRegistrarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEntrega(pedido);
            }
        });

        //Botón volver
        Button cmdVolverAPedidosPendientes=(Button)findViewById(R.id.cmdVolverAPedidosPendientes);
        cmdVolverAPedidosPendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });


    }
    //Listener del botón registrar entrega
    public void registrarEntrega(Pedido pedido){
        pedido.setEntregado(true);
        Toast.makeText(VerDetallePedidoActivity.this,"La entrega se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
        volver();
    }

    //Listener del botón volver
    public void volver(){
        Intent intent=new Intent(VerDetallePedidoActivity.this,VerPedidosPendientesActivity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        VerDetallePedidoActivity.this.startActivity(intent);
    }

}
