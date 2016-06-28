package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import clases.Cliente;
import clases.ListaVendedores;
import clases.Vendedor;

public class AgregarClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        //Crear el botón
        Button cmdAgregarCliente=(Button)findViewById(R.id.cmdAgregarCliente);
        cmdAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarClienteNuevo();
            }
        });

    }
    public void agregarClienteNuevo(){
        //Recuperar los datos de los textbox

        EditText txtNombreClienteNuevo=(EditText)findViewById(R.id.txtNombreClienteNuevo);
        EditText txtDireccionClienteNuevo=(EditText)findViewById(R.id.txtDireccionClienteNuevo);
        EditText txtTelefonoClienteNuevo=(EditText) findViewById(R.id.txtTelefonoClienteNuevo);


        //Recuperar el vendedor
        Bundle extras=getIntent().getExtras();
        String id_vendedor_string=extras.getString("id_ven");
        int idVendedor=0;
        try {
            idVendedor = Integer.parseInt(id_vendedor_string);
        }catch(NumberFormatException e){
            Toast.makeText(AgregarClienteActivity.this,"id vendedor incorrecto",Toast.LENGTH_SHORT).show();
        }
        //Crear el cliente
        Cliente cliente=new Cliente();
        cliente.setNombre(txtNombreClienteNuevo.getText().toString());
        cliente.setDireccion(txtDireccionClienteNuevo.getText().toString());
        cliente.setTelefono(txtTelefonoClienteNuevo.getText().toString());
        cliente.setActivo(true);
        //Agregar el cliente a la lista del vendedor
        ListaVendedores listaV=ListaVendedores.getInstancia();
        Vendedor vendedor=listaV.getVendedor(idVendedor);
        vendedor.addCliente(cliente);
        //Volver a la lista de clientes
        Toast.makeText(AgregarClienteActivity.this,"Cliente ingresado",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(AgregarClienteActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string);
        AgregarClienteActivity.this.startActivity(intent);

    }

}
