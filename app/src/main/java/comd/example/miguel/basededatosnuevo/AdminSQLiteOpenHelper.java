package comd.example.miguel.basededatosnuevo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by miguel on 15/04/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios (id_usuario integer primary key unique, user text unique, nombre text, apellido_p text, apellido_m text, localidad text, sexo text, edad text)");
    }

    // borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios");
        db.execSQL("create table usuarios (id_usuario integer primary key unique, user text unique, nombre text, apellido_p text, apellido_m text, localidad text, sexo text, edad text)");
    }

}

