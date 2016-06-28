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

public class ModificarClienteActivity extends AppCompatActivity {
    private String id_vendedor_string;
    private ListaVendedores listaVendedores;
    private ArrayList<Vendedor> vendedores;
    private int idVendedor;
    private Vendedor vendedor;
    private ArrayAdapter<Cliente> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);
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
            Toast.makeText(ModificarClienteActivity.this, "NO Tiene clientes", Toast.LENGTH_SHORT).show();
        }

        //Crear el botón modificar
        Button cmdModificar=(Button) findViewById(R.id.cmdModificar);
        cmdModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });

        //Crear el botón cancelar
        Button cmdCancelar=(Button) findViewById(R.id.cmdCancelar);
        cmdCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

    }
    public void modificar(){
        //Recuperar el ID ingresado
        EditText txtIdClienteActual=(EditText) findViewById(R.id.txtIdClienteActual);
        //Pasarlo a int
        int idCliente=Integer.parseInt(txtIdClienteActual.getText().toString());
        //Leer los campos de texto
        EditText txtNombreNuevo=(EditText) findViewById(R.id.txtNombreNuevo);
        String nombreNuevo=txtNombreNuevo.getText().toString();
        EditText txtDireccionNueva=(EditText) findViewById(R.id.txtDireccionNueva);
        String direccionNueva=txtDireccionNueva.getText().toString();
        EditText txtTelefonoNuevo=(EditText) findViewById(R.id.txtTelefonoNuevo);
        String telefonoNuevo=txtTelefonoNuevo.getText().toString();

        //Modificar el cliente
        vendedor.alterCliente(idCliente,nombreNuevo,direccionNueva,telefonoNuevo);

        //Mensaje y volver
        Toast.makeText(ModificarClienteActivity.this,"Cliente modificado",Toast.LENGTH_SHORT).show();

        volver();
    }
    public void cancelar(){
        volver();
    }
    public void volver(){
        Intent intent=new Intent(ModificarClienteActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string);
        ModificarClienteActivity.this.startActivity(intent);
    }

}
