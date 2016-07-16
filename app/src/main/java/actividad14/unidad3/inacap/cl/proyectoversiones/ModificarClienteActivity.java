package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Cliente;
import clases.ListaVendedores;
import clases.Vendedor;

public class ModificarClienteActivity extends AppCompatActivity {
    private String idVendedorStr;
    private ListaVendedores listaVendedores;
    private Vendedor vendedor;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);
        //Recuperar el vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        listaVendedores=ListaVendedores.getInstancia();
        vendedor=listaVendedores.getVendedor(idVendedorStr);
        //Recuperar el cliente
        String idCliente=extras.getString("id_cliente");
        cliente=vendedor.getCliente(idCliente);
        //Mostrar los datos
        TextView txtId=(TextView)findViewById(R.id.txtIdClienteModificar);
        txtId.setText(String.valueOf(cliente.getIdCliente()));
        EditText txtNombre=(EditText)findViewById(R.id.txtNombreClienteModificar);
        txtNombre.setText(cliente.getNombre());
        EditText txtDir=(EditText)findViewById(R.id.txtDireccionClienteModificar);
        txtDir.setText(cliente.getDireccion());
        EditText txtTel=(EditText)findViewById(R.id.txtTelefonoClienteModificar);
        txtTel.setText(cliente.getTelefono());

        //Listener de los botones
        Button cmdModificarCliente=(Button)findViewById(R.id.cmdModificarCliente);
        cmdModificarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });
        Button cmdCancelarModificar=(Button)findViewById(R.id.cmdCancelarModificar);
        cmdCancelarModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });


    }
    public void modificar(){
        TextView txtId=(TextView) findViewById(R.id.txtIdClienteModificar);
        String id=txtId.getText().toString();
        EditText txtNombre=(EditText)findViewById(R.id.txtNombreClienteModificar);
        String nombre=txtNombre.getText().toString();
        EditText txtDir=(EditText)findViewById(R.id.txtDireccionClienteModificar);
        String direccion=txtDir.getText().toString();
        EditText txtTel=(EditText)findViewById(R.id.txtTelefonoClienteModificar);
        String tel=txtTel.getText().toString();
        //Modificarlo en la lista
        Cliente cliente=vendedor.alterCliente(id,nombre,direccion,tel);
        //Modificarlo en la base de datos
        listaVendedores.alterCliente(cliente);
        volver();

    }
    public void volver(){
        Intent intent=new Intent(ModificarClienteActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        ModificarClienteActivity.this.startActivity(intent);
    }

}
