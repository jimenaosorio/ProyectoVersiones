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
    private ArrayAdapter<Cliente> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido_paso1);
        //Recupero el id del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");

        int idVendedor=0;
        try {
            idVendedor = Integer.valueOf(idVendedorStr);

        }catch(NumberFormatException e){
            Toast.makeText(CrearPedidoPaso1Activity.this,"El vendedor no está registrado", Toast.LENGTH_SHORT);
        }
        //Recuperar el vendedor
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        vendedor=listaVendedores.getVendedor(idVendedor);
        //Recuperar los clientes
        ArrayList<Cliente> clientes = vendedor.getClientes();
        //Rellenar el spinner
        Spinner spinClientes=(Spinner)findViewById(R.id.spinClientes);
        if(clientes!=null){
            adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
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
        String datosCliente=spinClientes.getSelectedItem().toString();
        String idStr=datosCliente.substring(1,4);
        int idCliente=0;
        try{
            idCliente=Integer.parseInt(idStr);
            Toast.makeText(CrearPedidoPaso1Activity.this,"ID Cliente="+idStr,Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){
            Toast.makeText(CrearPedidoPaso1Activity.this,"Formato de ID incorrecto",Toast.LENGTH_SHORT).show();
        }
        //Reenviar
        Intent intent=new Intent(CrearPedidoPaso1Activity.this,CrearPedidoPaso2Activity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        intent.putExtra("id_cliente",idCliente);
        CrearPedidoPaso1Activity.this.startActivity(intent);

    }


}
