package comd.example.miguel.basededatosnuevo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText serie, telefono, nombre_propietario,
            apellido_paterno, apellido_materno, localidad,sexo, edad ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serie = (EditText) findViewById(R.id.idusuario);
      telefono = (EditText) findViewById(R.id.user);

       nombre_propietario = (EditText) findViewById(R.id.nombre);
        apellido_paterno = (EditText) findViewById(R.id.apellidop);
        apellido_materno = (EditText) findViewById(R.id.apellidom);
        localidad = (EditText) findViewById(R.id.localidadd);
        sexo = (EditText) findViewById(R.id.sexoo);
        edad = (EditText) findViewById(R.id.años);


    }

    public void alta (View v) {
try   {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = serie.getText().toString();
        String user = telefono.getText().toString();

        String nombre = nombre_propietario.getText().toString();
        String apellidop = apellido_paterno.getText().toString();
        String apellidom = apellido_materno.getText().toString();
        String  localidadd = localidad.getText().toString();

    String sexoo= sexo.getText().toString();
    String  años = edad.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("id_usuario", idusuario);
        registro.put("user", user);

        registro.put("localidad", localidadd);
        registro.put("sexo",sexoo);
        registro.put("edad",años);
        registro.put("nombre", nombre);
        registro.put("apellido_p", apellidop);
        registro.put("apellido_m", apellidom);


        bd.insert("usuarios", null, registro);
        bd.close();

        serie.setText("");
        telefono.setText("");
        nombre_propietario.setText("");
        apellido_paterno.setText("");
        apellido_materno.setText("");
        localidad.setText("");
        sexo.setText("");
        edad.setText("");


        Toast.makeText(this, "Se agrego un nuevo usuario", Toast.LENGTH_SHORT).show();
}
catch(Exception e){
    Toast.makeText(this, "error " + e.getMessage(), Toast.LENGTH_SHORT).show();
}

    }
    public void consulta(View v)  {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String idusuario = serie.getText().toString();
            String nombre = nombre_propietario.getText().toString();

            Cursor fila = bd.rawQuery("select user, nombre, apellido_p, apellido_m, localidad, sexo, edad from usuarios where id_usuario = " + idusuario, null);
            if (fila.moveToFirst()) {
                telefono.setText(fila.getString(0));
                nombre_propietario.setText(fila.getString(1));
                apellido_paterno.setText(fila.getString(2));
                apellido_materno.setText(fila.getString(3));
                localidad.setText(fila.getString(4));
                sexo.setText(fila.getString(5));
                edad.setText(fila.getString(6));

            }
            else {
                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }
        catch(Exception ex)
        {
            Toast.makeText(this, "ERROR: "+ex, Toast.LENGTH_SHORT).show();
        }
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = serie.getText().toString();
        int cant = bd.delete("usuarios","id_usuario=" + idusuario, null);
        bd.close();

        serie.setText("");
       telefono.setText("");
        nombre_propietario.setText("");
        apellido_paterno.setText("");
       apellido_materno.setText("");
        localidad.setText("");
        sexo.setText("");
        edad.setText("");



        if (cant == 1) {
            Toast.makeText(this, "Se borró el usuario",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = serie.getText().toString();
        String user =telefono.getText().toString();
        String nombre = nombre_propietario.getText().toString();
        String apellidop = apellido_paterno.getText().toString();
        String apellidom = apellido_materno.getText().toString();
        String localidadd = localidad.getText().toString();

        String sexoo= sexo.getText().toString();
        String años = edad.getText().toString();



        ContentValues registro = new ContentValues();

        registro.put("id_usuario", idusuario);
        registro.put("user", user);
        registro.put("nombre", nombre);
        registro.put("apellido_p", apellidop);
        registro.put("apellido_m", apellidom);
        registro.put("localidad", localidadd);

        registro.put("sexo", sexoo);
        registro.put("edad", años);


        int cant = bd.update("usuarios", registro, "id_usuario=" + idusuario, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del usuario",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario",Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiar (View v) {
        serie.setText("");
        telefono.setText("");
        nombre_propietario.setText("");
        apellido_paterno.setText("");
        apellido_materno.setText("");
        localidad.setText("");
        sexo.setText("");
        edad.setText("");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
