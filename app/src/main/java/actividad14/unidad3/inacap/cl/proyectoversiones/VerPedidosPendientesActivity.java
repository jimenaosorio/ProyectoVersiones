package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import clases.ListaVendedores;
import clases.Pedido;
import clases.Vendedor;

public class VerPedidosPendientesActivity extends AppCompatActivity {
    private String idVendedorStr;
    private ArrayList<Pedido> pedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos_pendientes);
        //Recuperar el vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        int idVendedor=Integer.parseInt(idVendedorStr);
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        Vendedor vendedor=listaVendedores.getVendedor(idVendedor);

        //Recuperar los pedidos
        pedidos=vendedor.getPedidos();
        int tam=pedidos.size();

        //Radio buttons
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        for(int i=0;i<tam;i++){
            RadioButton rdbtn = new RadioButton(this);
            Pedido p=pedidos.get(i);
            rdbtn.setId(p.getIdPedido());
            rdbtn.setText(p.toString());
            ll.addView(rdbtn);
        }
        ((RadioGroup)findViewById(R.id.rgPedidosPendientes)).addView(ll);

        //Botón
        Button cmdVerPedidoPendiente=(Button)findViewById(R.id.cmdVerPedidoPendiente);
        cmdVerPedidoPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verPedidoPendiente();
            }
        });

    }
    public void verPedidoPendiente(){
        Pedido pedido=pedidoSeleccionado();
        Intent intent=new Intent(VerPedidosPendientesActivity.this,VerDetallePedidoActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        intent.putExtra("id_pedido",pedido.getIdPedido());
        VerPedidosPendientesActivity.this.startActivity(intent);
    }

    //Leer el pedido seleccionado
    public Pedido pedidoSeleccionado(){
        Pedido pedido=null;
        int tam=pedidos.size();
        for(int i=0;i<tam;i++){
            pedido=pedidos.get(i);
            RadioButton rbn=(RadioButton)findViewById(pedido.getIdPedido());
            if(rbn.isChecked()){
                return pedido;
            }
        }
        return pedido;
    }


}
