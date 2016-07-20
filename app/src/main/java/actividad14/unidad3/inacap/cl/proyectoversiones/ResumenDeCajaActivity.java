package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import java.text.NumberFormat;
import java.util.ArrayList;

import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class ResumenDeCajaActivity extends AppCompatActivity {
    private  String idVendedorStr;
    private Vendedor vendedor;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ListaVendedores listaVendedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_de_caja);

        //Llenar la tabla con el resumen
        //Obtener el vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        listaVendedores=ListaVendedores.getInstancia();

        vendedor=listaVendedores.getVendedor(idVendedorStr);

        //Moneda
        Double monto;
        NumberFormat formato=NumberFormat.getCurrencyInstance();;
        String salida;

        //GridView
        GridView gvResumenDeCaja=(GridView)findViewById(R.id.gvResumenDeCaja);
        ArrayList<String> datos=new ArrayList<String>();
        datos.add(getResources().getString(R.string.monto_total_entregado)+": ");
        int totalEntregas=vendedor.getMontoCobrado();
        salida=formato.format(totalEntregas);
        datos.add(salida);

        datos.add(getResources().getString(R.string.monto_total_pedidos)+": ");
        int totalPedidos=vendedor.getMontoPedidos();
        salida=formato.format(totalPedidos);
        datos.add(salida);

        datos.add(getResources().getString(R.string.total)+": ");
        int saldo=vendedor.getSaldo();
        salida=formato.format(saldo);
        datos.add(salida);

        adapter1=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,datos);
        gvResumenDeCaja.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        //Spinner
        ArrayList<Pedido> entregados=listaVendedores.getPedidosEntregados(vendedor);
        ArrayList<String> idPe=new ArrayList<String>();
        int cant=entregados.size();


        for(int i=0;i<cant;i++){

            idPe.add(String.valueOf(entregados.get(i).getCliente().getNombre()+","+entregados.get(i).getPrecio()+","+entregados.get(i).getFechaEntrega()));
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
        String datosPedido=spEntregas.getSelectedItem().toString();
        String[] separarDatos=datosPedido.split(",");
        String nombreCliente=separarDatos[0];
        String precioStr=separarDatos[1];
        int precio=Integer.parseInt(precioStr);
        String fecha=separarDatos[2];

        String idPedido=listaVendedores.getIdPedido(nombreCliente,precio,fecha);
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
