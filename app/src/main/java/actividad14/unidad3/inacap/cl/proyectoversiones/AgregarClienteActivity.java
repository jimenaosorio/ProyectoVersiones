package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import clases.Cliente;
import clases.ListaVendedores;
import clases.Vendedor;

public class AgregarClienteActivity extends AppCompatActivity {
    private String idVendedorStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        //Recoger el id desde el LoginActivity
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        Button cmdIngresarClienteNuevo=(Button)findViewById(R.id.cmdIngresarClienteNuevo);
        cmdIngresarClienteNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente();
            }
        });


    }
    public void guardarCliente(){

        //Buscar el vendedor
        ListaVendedores listaVendedores= ListaVendedores.getInstancia();
        Vendedor vendedor=listaVendedores.getVendedor(idVendedorStr);


        //Leer el cliente nuevo y guardarlo en la lista
        TextView txtClienteNombreNuevo=(TextView)findViewById(R.id.txtClienteNombreNuevo);
        TextView txtClienteDireccionNueva=(TextView)findViewById(R.id.txtClienteDireccionNueva);
        TextView txtTelefonoNuevo=(TextView)findViewById(R.id.txtClienteTelefonoNuevo);
        String nombre=txtClienteNombreNuevo.getText().toString();
        String direccion=txtClienteDireccionNueva.getText().toString();
        String telefono=txtTelefonoNuevo.getText().toString();
        Cliente cliente=new Cliente(nombre,direccion,telefono,vendedor);
        cliente.setActivo(true);
       // vendedor.addCliente(cliente);
        listaVendedores.addCliente(cliente,vendedor);

        //Devolver el id vendedor y volver al menú de clientes
        Intent intent=new Intent(AgregarClienteActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        AgregarClienteActivity.this.startActivity(intent);

    }

}
