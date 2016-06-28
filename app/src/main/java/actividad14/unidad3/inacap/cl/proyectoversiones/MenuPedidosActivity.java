package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPedidosActivity extends AppCompatActivity {
    private String idVendedorString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pedidos);
        //Botón Crear pedido
        Button cmdCrearPedido=(Button) findViewById(R.id.cmdCrearPedido);
        cmdCrearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearPedido();
            }
        });

        //Botón Ver pedidos
        Button cmdVerPedidos=(Button) findViewById(R.id.cmdVerPedidos);
        cmdVerPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verPedidos();
            }
        });

        //Botón volver al menú
        Button cmdVolverAlMenu=(Button) findViewById(R.id.cmdVolverAlMenu);
        cmdVolverAlMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAlMenu();
            }
        });

    }
    //Listener para crear un pedido
    public void crearPedido(){
        //Recuperar el ID del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");

        //Redireccionar al paso 1
        Intent intent=new Intent(MenuPedidosActivity.this,CrearPedidoPaso1Activity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        MenuPedidosActivity.this.startActivity(intent);
    }

    //Listener para ver los pedidos pendientes
    public void verPedidos(){
        //Recuperar el ID del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");

        Intent intent=new Intent(MenuPedidosActivity.this,VerPedidosPendientesActivity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        MenuPedidosActivity.this.startActivity(intent);
    }


    //Listener para volver al menú
    public void volverAlMenu(){
        //Recuperar el ID del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");
        Intent intent=new Intent(MenuPedidosActivity.this,MenuActivity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        MenuPedidosActivity.this.startActivity(intent);
    }


}
