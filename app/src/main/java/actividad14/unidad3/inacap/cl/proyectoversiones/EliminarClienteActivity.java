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

public class EliminarClienteActivity extends AppCompatActivity {

    private String id_vendedor_string;
    private ListaVendedores listaVendedores;
    private ArrayList<Vendedor> vendedores;
    private int idVendedor;
    private Vendedor vendedor;
    private ArrayAdapter<Cliente> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_cliente);
        //Obtener la lista de todos los vendedores
        listaVendedores= ListaVendedores.getInstancia();
        vendedores=listaVendedores.getListaVendedores();

        //Recoger el id desde el LoginActivity
        Bundle extras=getIntent().getExtras();
        id_vendedor_string=extras.getString("id_vendedor");
        idVendedor=Integer.parseInt(id_vendedor_string);

        //Buscar el vendedor
        vendedor=listaVendedores.getVendedor(idVendedor);

        //Crear un ListView y mostrar los clientes
        ListView lvClientes = (ListView) findViewById(R.id.lvClientes);
        ArrayList<Cliente> clientes = vendedor.getClientes(); //Lista de clientes para ese vendedor

        if (clientes != null) {
            adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
            lvClientes.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(EliminarClienteActivity.this, "NO Tiene clientes", Toast.LENGTH_SHORT).show();
        }

        //Crear el botón Eliminar
        Button cmdEliminar=(Button) findViewById(R.id.cmdEliminar);
        cmdEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCliente();
            }
        });

        //Crear el botón Cancelar
        Button cmdCancelar=(Button) findViewById(R.id.cmdCancelar);
        cmdCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverALaLista();
            }
        });


    }
    //Listener para Eliminar
    public void eliminarCliente(){
        //Recuperar el Id Cliente ingresado
        EditText txtIdCliente=(EditText) findViewById(R.id.txtIdCliente);
        //Pasarlo a int
        int idCliente=Integer.parseInt(txtIdCliente.getText().toString());
        //Marcar el cliente como eliminado
        vendedor.dropCliente(idCliente);
        //Mensaje
        Toast.makeText(EliminarClienteActivity.this,"Cliente Eliminado",Toast.LENGTH_SHORT).show();

        volver();
    }

    //Listener para Cancelar
    public void volverALaLista(){
        volver();
    }

    //Método para volver
    public void volver(){
        Intent intent=new Intent(EliminarClienteActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string);
        EliminarClienteActivity.this.startActivity(intent);
    }


}
