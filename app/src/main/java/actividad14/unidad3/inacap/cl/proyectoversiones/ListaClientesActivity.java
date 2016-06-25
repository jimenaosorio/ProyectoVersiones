package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Cliente;
import clases.Vendedor;

public class ListaClientesActivity extends AppCompatActivity {
    private ArrayAdapter<Cliente> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        //Crear un TextView y mostrar el nombre del vendedor
        TextView txtVendedor=(TextView)findViewById(R.id.txtVendedor);


        //Obtener la lista de todos los vendedores
        Vendedor vendedor=new Vendedor();
        ArrayList<Vendedor> vendedores=vendedor.listaVendedores();

        //Buscar el vendedor
        //Recoger el id desde el LoginActivity
        Bundle extras=getIntent().getExtras();
        String id_vendedor_string=extras.getString("id_vendedor");
        int idVendedor=Integer.parseInt(id_vendedor_string);
        vendedor=vendedor.getVendedor(idVendedor);

        txtVendedor.setText("Bienvenido Vendedor "+vendedor.getNombre());

        //Crear un ListView y mostrar los clientes
        ListView lvClientes=(ListView)findViewById(R.id.lvClientes);
        ArrayList<Cliente> clientes=vendedor.getClientes(); //Lista de clientes para ese vendedor
        if(clientes!=null){
            Toast.makeText(ListaClientesActivity.this,"Tiene clientes",Toast.LENGTH_SHORT).show();
            adapter=new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item,clientes);
            lvClientes.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(ListaClientesActivity.this,"NO Tiene clientes", Toast.LENGTH_SHORT).show();
        }


    }

}
