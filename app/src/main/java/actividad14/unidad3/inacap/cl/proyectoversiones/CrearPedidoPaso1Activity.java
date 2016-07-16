package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Cliente;
import clases.ListaVendedores;
import clases.Vendedor;

public class CrearPedidoPaso1Activity extends AppCompatActivity {
    private String idVendedorStr;
    private Vendedor vendedor;
    private ArrayAdapter<String> adapter;
    ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido_paso1);
        //Recupero el id del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");


        //Recuperar el vendedor
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        vendedor=listaVendedores.getVendedor(idVendedorStr);
        //Recuperar los clientes
        clientes = vendedor.getClientes();
        ArrayList<String> nombresClientes=new ArrayList<String>();
        int tamCli=clientes.size();
        for(int i=0;i<tamCli;i++){
            nombresClientes.add(clientes.get(i).getNombre());
        }
        //Rellenar el spinner
        Spinner spinClientes=(Spinner)findViewById(R.id.spinClientes);
        if(clientes!=null){
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombresClientes);
            spinClientes.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else{
            Toast.makeText(CrearPedidoPaso1Activity.this,"No posee clientes",Toast.LENGTH_SHORT).show();
        }
        //Botón siguiente
        Button cmdSiguiente1=(Button) findViewById(R.id.cmdSiguiente1);
        cmdSiguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente();
            }
        });



    }


    public void siguiente(){
        //Leer el cliente seleccionado
        Spinner spinClientes=(Spinner)findViewById(R.id.spinClientes);
        //Recuperar el id del cliente
        String nombreCliente=spinClientes.getSelectedItem().toString();
        int tam=clientes.size();
        String idCliente="";
        Cliente c=vendedor.getClientePorNombre(nombreCliente);
        idCliente=c.getIdCliente();


        //Reenviar
        Intent intent=new Intent(CrearPedidoPaso1Activity.this,CrearPedidoPaso2Activity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        intent.putExtra("id_cliente",idCliente);
        CrearPedidoPaso1Activity.this.startActivity(intent);

    }


}
