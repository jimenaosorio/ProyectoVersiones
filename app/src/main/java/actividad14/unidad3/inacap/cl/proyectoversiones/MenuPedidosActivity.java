package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPedidosActivity extends AppCompatActivity {
    private String idVendedorStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pedidos);

        //Recupero el id del vendedor
        Bundle extras=getIntent().getExtras();
        idVendedorStr=extras.getString("id_vendedor");

        //Botones
        Button cmdIngresrPedido=(Button)findViewById(R.id.cmdIngresarPedido);
        cmdIngresrPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paso1();
            }
        });

        Button cmdRegistrarEntrega=(Button)findViewById(R.id.cmdRegistrarEntrega);
        cmdRegistrarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verEntregas();
            }
        });

        Button cmdResumenDeCaja=(Button)findViewById(R.id.cmdResumenDeCaja);
        cmdResumenDeCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumenDeCaja();
            }
        });

        Button cmdVolver2=(Button)findViewById(R.id.cmdVolver2);
        cmdVolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });


    }
    public void paso1(){
        Intent intent=new Intent(MenuPedidosActivity.this, CrearPedidoPaso1Activity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        MenuPedidosActivity.this.startActivity(intent);
    }
    public void verEntregas(){
        Intent intent=new Intent(MenuPedidosActivity.this, VerPedidosPendientesActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        MenuPedidosActivity.this.startActivity(intent);
    }
    public void resumenDeCaja(){
        Intent intent=new Intent(MenuPedidosActivity.this, ResumenDeCajaActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        MenuPedidosActivity.this.startActivity(intent);
    }
    public void volver(){
        Intent intent=new Intent(MenuPedidosActivity.this, MenuActivity.class);
        intent.putExtra("id_vendedor",idVendedorStr);
        MenuPedidosActivity.this.startActivity(intent);
    }


}
