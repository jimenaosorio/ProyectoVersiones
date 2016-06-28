package actividad14.unidad3.inacap.cl.proyectoversiones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import clases.ListaVendedores;
import clases.Vendedor;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Crear un botón
        Button cmdIngresar=(Button) findViewById(R.id.cmdIngresar);
        //Listener para el botón
        cmdIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validar el login del vendedor
                validarLoginVendedor();
            }
        });


    }
    //Método para validar login y password del vendedor
    //Método para validar login y password del vendedor
    public void validarLoginVendedor(){
        //Recuperar el login y password escritos en el formulario
        EditText txtLogin=(EditText)findViewById(R.id.txtLogin);
        EditText txtPassword=(EditText)findViewById(R.id.txtPassword);

        //Validar el login y la passsword
        ListaVendedores vendedores= ListaVendedores.getInstancia();
        int idV=vendedores.validarLogin(txtLogin.getText().toString(),txtPassword.getText().toString());
        if(idV!=0){
            //El usuario y contraseña son correctos, redirecciona
            Toast.makeText(LoginActivity.this, "Usuario correcto ID="+String.valueOf(idV), Toast.LENGTH_SHORT).show();
            txtLogin.setText("");
            txtPassword.setText("");

            Intent intent=new Intent(LoginActivity.this, MenuActivity.class);
            intent.putExtra("id_vendedor",String.valueOf(idV)); //pasar el id del vendedor al otro activity
            LoginActivity.this.startActivity(intent);


        }else{   //Si el usuario no existe o la contraseña es incorrecta muestra un mensaje de error
            Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos",Toast.LENGTH_SHORT).show();
        }
    }



}
