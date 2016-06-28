package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class CrearPedidoPaso2Activity extends AppCompatActivity {
    private String idVendedorString;
    private String idClienteString;
    private String fechaEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido_paso2);
        //Recuperar idVendedor e IdCliente
        Bundle extras=getIntent().getExtras();
        idVendedorString=extras.getString("id_vendedor");
        idClienteString=extras.getString("id_cliente");

        //Calendar view
        CalendarView cvFechaEntrega=(CalendarView) findViewById(R.id.cvFechaEntrega);
        cvFechaEntrega.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                fechaEntrega=String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
            }
        });

        //Botón siguiente
        Button cmdSiguiente2=(Button) findViewById(R.id.cmdSiguiente2);
        cmdSiguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente2();
            }
        });

    }
    //Listener para el botón siguiente
    public void siguiente2(){

        Toast.makeText(CrearPedidoPaso2Activity.this,"Fecha: "+fechaEntrega,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(CrearPedidoPaso2Activity.this,CrearPedidoPaso3Activity.class);
        intent.putExtra("id_vendedor",idVendedorString);
        intent.putExtra("id_cliente",idClienteString);
        intent.putExtra("fecha_entrega",fechaEntrega);
        CrearPedidoPaso2Activity.this.startActivity(intent);

    }

}
