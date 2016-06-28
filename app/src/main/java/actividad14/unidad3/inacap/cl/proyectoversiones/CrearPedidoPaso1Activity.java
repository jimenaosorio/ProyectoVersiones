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
import clases.ListaVendedores;
import clases.Vendedor;

public class CrearPedidoPaso1Activity extends AppCompatActivity {
    private String idVendedorString;
    private String idClienteString;
    private Vendedor vendedor;
    private ArrayAdapter<Cliente> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido_paso1);
        ListView lvClientesPedido=(ListView) findViewById(R.id.lvClientesPedido);
        ListaVendedores listaVendedores= ListaVendedores.getInstancia();


        //Recuperar el vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");


        int idVendedor=0;
        try {
            idVendedor = Integer.valueOf(idVendedorString);

        }catch(NumberFormatException e){
            Toast.makeText(CrearPedidoPaso1Activity.this,"vendedor="+idVendedorString,Toast.LENGTH_SHORT);
        }
        vendedor=listaVendedores.getVendedor(idVendedor);
        //  Toast.makeText(CrearPedidoPaso1Activity.this,"vendedor="+idVendedorString,Toast.LENGTH_SHORT);


        ArrayList<Cliente> clientes = vendedor.getClientes();
        //Rellenar el ListView

        if (clientes != null) {

            adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
            lvClientesPedido.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(CrearPedidoPaso1Activity.this, "NO Tiene clientes", Toast.LENGTH_SHORT).show();
        }


        //Botón siguiente
        Button cmdSiguiente1=(Button)findViewById(R.id.cmdSiguiente1);
        cmdSiguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente1();
            }
        });



    }
    public void siguiente1(){
        EditText txtIdClientePedido=(EditText)findViewById(R.id.txtIdClientePedido);
        idClienteString=txtIdClientePedido.getText().toString();
        int idCliente=0;


        //Validar el número ingresado
        try{
            idCliente=Integer.parseInt(idClienteString);

        }catch (NumberFormatException e){
            Toast.makeText(CrearPedidoPaso1Activity.this,"El ID del cliente debe ser un número",Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(CrearPedidoPaso1Activity.this,MenuPedidosActivity.class);
            intent1.putExtra("id_vendedor",idVendedorString);
            CrearPedidoPaso1Activity.this.startActivity(intent1);
        }

        //Validar que el número del cliente exista
        Cliente cliente=vendedor.getCliente(idCliente);
        // Toast.makeText(CrearPedidoPaso1Activity.this,"id cliente="+cliente.getIdCliente(),Toast.LENGTH_SHORT).show();

        if(cliente==null){
            Toast.makeText(CrearPedidoPaso1Activity.this,"ID de cliente incorrecto",Toast.LENGTH_SHORT).show();
            Intent intent2=new Intent(CrearPedidoPaso1Activity.this,MenuPedidosActivity.class);
            intent2.putExtra("id_vendedor",idVendedorString);
            CrearPedidoPaso1Activity.this.startActivity(intent2);
        }else {

            //Reenviar el ID cliente e ID vendedor al paso 2
            Intent intent=new Intent(CrearPedidoPaso1Activity.this,CrearPedidoPaso2Activity.class);
            intent.putExtra("id_vendedor",idVendedorString);
            intent.putExtra("id_cliente",idClienteString);
            CrearPedidoPaso1Activity.this.startActivity(intent);
        }

    }

}
