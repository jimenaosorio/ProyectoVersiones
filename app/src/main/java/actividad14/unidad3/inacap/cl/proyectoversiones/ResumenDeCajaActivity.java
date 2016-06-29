package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class ResumenDeCajaActivity extends AppCompatActivity {
    private  String idVendedorStr;
    private Vendedor vendedor;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_de_caja);

        //Llenar la tabla con el resumen
        //Obtener el vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        int idVendedor=Integer.parseInt(idVendedorStr);
        vendedor=listaVendedores.getVendedor(idVendedor);

        //GridView
        GridView gvResumenDeCaja=(GridView)findViewById(R.id.gvResumenDeCaja);
        ArrayList<String> datos=new ArrayList<String>();
        datos.add("Monto Total Entregado");
        int totalEntregas=vendedor.getMontoCobrado();
        datos.add(String.valueOf(totalEntregas));
        datos.add("Monto Total Pedidos");
        int totalPedidos=vendedor.getMontoPedidos();
        datos.add(String.valueOf(totalPedidos));
        datos.add("Saldo");
        int saldo=vendedor.getSaldo();
        datos.add(String.valueOf(saldo));
        adapter1=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,datos);
        gvResumenDeCaja.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        //Spinner
        ArrayList<Pedido> entregados=vendedor.getEntregas();
        ArrayList<String> idPe=new ArrayList<String>();
        int cant=entregados.size();
        for(int i=0;i<cant;i++){
            idPe.add(String.valueOf(entregados.get(i).getIdPedido()));
        }
        Spinner spEntregas=(Spinner)findViewById(R.id.spEntregas);
        if(entregados!=null){
            adapter2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,idPe);
            spEntregas.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();
        }

        //Botón
        Button cmdVerificar=(Button)findViewById(R.id.cmdVerificar);
        cmdVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detalleEntrega();
            }
        });

        Button cmdVolver3=(Button)findViewById(R.id.cmdVolver3);
        cmdVolver3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });


    }
    public void detalleEntrega(){
        //Obtener el pedido seleccionado
        Spinner spEntregas=(Spinner)findViewById(R.id.spEntregas);
        //Recuperar el ID del pedido
        String idPedido=spEntregas.getSelectedItem().toString();
        Intent intent=new Intent(ResumenDeCajaActivity.this,DetalleEntregaActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        intent.putExtra("id_pedido",idPedido);
        ResumenDeCajaActivity.this.startActivity(intent);

    }
    public void volver(){
        Intent intent=new Intent(ResumenDeCajaActivity.this,MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        ResumenDeCajaActivity.this.startActivity(intent);
    }


}
