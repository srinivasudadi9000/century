package m.srinivas.century_task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by srinivas on 06/04/18.
 */

public class DBHelper {
     static Context context;
    SQLiteDatabase db;
    public DBHelper(String name, String dob, String lat,String longitude, String phone, String email,String password, Context context) {
        db = context.openOrCreateDatabase("TASK", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS login(email VARCHAR unique,name VARCHAR,dob VARCHAR,phone VARCHAR,lat VARCHAR,longitude VARCHAR,password VARCHAR);");
        db.execSQL("INSERT INTO login VALUES('" + email + "','" + name + "','" + dob + "','"+phone+"','"+lat+"','" + longitude + "','"+password+ "');");
        Toast.makeText(context,"inserted",Toast.LENGTH_SHORT).show();
    }

}
