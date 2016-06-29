package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Botón de clientes
        Button cmdClientes=(Button) findViewById(R.id.cmdClientes);
        cmdClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuVendedores();
            }
        });

        //Botón de pedidos
        Button cmdPedidos=(Button) findViewById(R.id.cmdPedidos);
        cmdPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPedidos();
            }
        });
        //Botón Salir
        Button cmdSalir=(Button)findViewById(R.id.cmdSalir);
        cmdSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });


    }
    public void menuVendedores(){
        //Recupero el id del vendedor
        Bundle extras=getIntent().getExtras();
        String id_vendedor_string=extras.getString("id_vendedor");

        //Lo envío a mostrar los clientes
        Intent intent=new Intent(MenuActivity.this,ListaClientesActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string); //Reenvío el id del vendedor
        MenuActivity.this.startActivity(intent);
    }
    public void menuPedidos(){
        //Recupero el id del vendedor
        Bundle extras=getIntent().getExtras();
        String id_vendedor_string=extras.getString("id_vendedor");

        //Lo envío a mostrar los pedidos
        Intent intent=new Intent(MenuActivity.this,MenuPedidosActivity.class);
        intent.putExtra("id_vendedor",id_vendedor_string); //Reenvío el id del vendedor
        MenuActivity.this.startActivity(intent);
    }
    public void salir(){
        Intent intent=new Intent(MenuActivity.this,LoginActivity.class);
        MenuActivity.this.startActivity(intent);
    }



}
