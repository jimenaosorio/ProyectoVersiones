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
import clases.ListaVendedores;
import clases.Vendedor;

public class ListaClientesActivity extends AppCompatActivity {
    private ArrayAdapter<Cliente> adapter;
    private String id_vendedor_string="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        //Crear un TextView y mostrar el nombre del vendedor
        TextView txtVendedor=(TextView)findViewById(R.id.txtVendedor);

        //Obtener la lista de todos los vendedores
        ListaVendedores listaVendedores= ListaVendedores.getInstancia();
        ArrayList<Vendedor> vendedores=listaVendedores.getListaVendedores();

        //Recoger el id desde el LoginActivity
        Bundle extras=getIntent().getExtras();
        id_vendedor_string=extras.getString("id_vendedor");
        int idVendedor=Integer.parseInt(id_vendedor_string);

        //Buscar el vendedor
        Vendedor vendedor=listaVendedores.getVendedor(idVendedor);

        txtVendedor.setText("Clientes de " + vendedor.getNombre());

        //Crear un ListView y mostrar los clientes
        ListView lvClientes = (ListView) findViewById(R.id.lvClientes);
        ArrayList<Cliente> clientes = vendedor.getClientes(); //Lista de clientes para ese vendedor
        if (clientes != null) {

            adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
            lvClientes.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ListaClientesActivity.this, "NO Tiene clientes", Toast.LENGTH_SHORT).show();
        }

        //Botón Ingresar

        Button cmdAgregarCliente=(Button) findViewById(R.id.cmdAgregarCliente);
        cmdAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCliente();
            }
        });

        // Botón Eliminar

        Button cmdEliminar=(Button) findViewById(R.id.cmdEliminar);
        cmdEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCliente();
            }
        });

        //Botón Modificar

        Button cmdModificar=(Button) findViewById(R.id.cmdModificar);
        cmdModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });


    }
    //Listener para llamar al Activity que agrega un cliente
    public void agregarCliente(){


        Intent intent=new Intent(ListaClientesActivity.this,AgregarClienteActivity.class);
        intent.putExtra("id_ven",id_vendedor_string);
        ListaClientesActivity.this.startActivity(intent);
    }

    //Listener para llamar al activity para eliminar un cliente
    public void eliminarCliente(){
        Intent intent=new Intent(ListaClientesActivity.this,EliminarClienteActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string);
        ListaClientesActivity.this.startActivity(intent);
    }

    //Listener para modificar un cliente
    public void modificar(){
        Intent intent=new Intent(ListaClientesActivity.this, ModificarClienteActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string);
        ListaClientesActivity.this.startActivity(intent);
    }


}
