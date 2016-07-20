package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Cliente;
import clases.ListaVendedores;
import clases.Vendedor;

public class ListaClientesActivity extends AppCompatActivity {
    private String idVendedorStr;
    private ArrayList<Cliente> clientes;
    private Vendedor vendedor;
    private ListaVendedores vendedores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        //Recuperar la lista de clientes del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");
        vendedores=ListaVendedores.getInstancia();
        vendedor=vendedores.getVendedor(idVendedorStr);
        clientes=vendedor.getClientes();
        if(clientes!=null) {
            int tam = clientes.size();

            //Radio Group

            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            for (int i = 1; i <= tam; i++) {
                RadioButton rdbtn = new RadioButton(this);

                Cliente c = clientes.get(i - 1);
                rdbtn.setId(i*2);
                rdbtn.setText(c.getNombre());
                ll.addView(rdbtn);

            }
            ((RadioGroup) findViewById(R.id.radiogroup)).addView(ll);
        }
        else{
            Toast.makeText(ListaClientesActivity.this,getResources().getString(R.string.vendedor_sin_clientes),Toast.LENGTH_SHORT).show();
        }


        //Listeners de los botones

        Button cmdNuevo=(Button)findViewById(R.id.cmdNuevo);
        cmdNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clienteNuevo();
            }
        });
        Button cmdEliminar=(Button)findViewById(R.id.cmdEliminar);
        cmdEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCliente();
            }
        });

        Button cmdModificar=(Button)findViewById(R.id.cmdModificar);
        cmdModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarCliente();
            }
        });

        Button cmdVolver=(Button)findViewById(R.id.cmdVolver);
        cmdVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();

            }
        });



    }
    public void clienteNuevo(){
        Intent intent=new Intent(ListaClientesActivity.this,AgregarClienteActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        ListaClientesActivity.this.startActivity(intent);
    }
    public void eliminarCliente(){
        Cliente c=clienteSeleccionado();
        Toast.makeText(ListaClientesActivity.this,getResources().getString(R.string.eliminando_cliente)+": "+c.getNombre(),Toast.LENGTH_SHORT).show();
        //Desactivarlo en la lista
        vendedor.dropCliente(c.getIdCliente());
        //Desactivarlo en la base de datos
        vendedores.dropCliente(c);
        //Reenviar
        Intent intent=new Intent(ListaClientesActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        ListaClientesActivity.this.startActivity(intent);
    }
    public void modificarCliente(){
        Cliente c=clienteSeleccionado();
        Intent intent=new Intent(ListaClientesActivity.this, ModificarClienteActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        intent.putExtra("id_cliente",c.getIdCliente());
        ListaClientesActivity.this.startActivity(intent);
    }
    //Rescatar el cliente seleccionado
    public Cliente clienteSeleccionado(){
        Cliente cliente=null;
        int tam=clientes.size();
        for(int i = 1; i <=tam; i++) {

            RadioButton rdbtn =(RadioButton)findViewById(i*2);
            cliente=vendedor.getClientePorNombre(rdbtn.getText().toString());
            if(rdbtn.isChecked()){

                return cliente;
            }
        }

        return null;
    }
    public void volver(){
        Intent intent=new Intent(ListaClientesActivity.this,MenuActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        ListaClientesActivity.this.startActivity(intent);
    }

}
