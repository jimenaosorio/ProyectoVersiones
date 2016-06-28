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

import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class VerPedidosPendientesActivity extends AppCompatActivity {
    private ArrayAdapter<Pedido> adapter;
    private String idVendedorString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos_pendientes);
        //Recuperar el vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");
        int idVendedor=Integer.parseInt(idVendedorString);
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        Vendedor vendedor=listaVendedores.getVendedor(idVendedor);

        //Recuperar los pedidos
        ArrayList<Pedido> pedidosPendientes=vendedor.getPedidos();


        //Rellenar el list view
        ListView lvPedidosPendientes=(ListView)findViewById(R.id.lvPedidosPendientes);
        if(pedidosPendientes!=null){
            adapter=new ArrayAdapter<Pedido>(getApplicationContext(), android.R.layout.simple_spinner_item, pedidosPendientes);
            lvPedidosPendientes.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            //No tiene pedidos pendientes
            Toast.makeText(VerPedidosPendientesActivity.this,"No tiene pedidos pendientes", Toast.LENGTH_SHORT).show();

        }

        //Botón Ver detalle
        Button cmdVerDetalle=(Button)findViewById(R.id.cmdVerDetalle);
        cmdVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verDetalle();
            }
        });

        //Botón Volver
        Button cmdVolverAlMenuPedidos=(Button)findViewById(R.id.cmdVolverAlMenuPedidos);
        cmdVolverAlMenuPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAlMenuPedidos();
            }
        });

    }
    //Listener del botón ver detalle
    public void verDetalle(){
        //Recuperar el ID del pedido ingresado
        EditText txtIdPedido=(EditText)findViewById(R.id.txtIdPedido);
        String idPedido=txtIdPedido.getText().toString();

        //Enviar a ver el detalle
        Intent intent=new Intent(VerPedidosPendientesActivity.this, VerDetallePedidoActivity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        intent.putExtra("id_pedido",idPedido);
        VerPedidosPendientesActivity.this.startActivity(intent);

    }
    //Listener del botón volver
    public void volverAlMenuPedidos(){
        Intent intent=new Intent(VerPedidosPendientesActivity.this,MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        VerPedidosPendientesActivity.this.startActivity(intent);
    }

}
