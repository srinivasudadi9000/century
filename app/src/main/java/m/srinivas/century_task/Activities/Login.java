package m.srinivas.century_task.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import m.srinivas.century_task.R;

public class Login extends Activity implements View.OnClickListener {
    Button submit;
    EditText email_et, password_et;
    TextView login_txt;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        password_et = (EditText) findViewById(R.id.password_et);
        email_et = (EditText) findViewById(R.id.email_et);
        submit = (Button) findViewById(R.id.submit);
        login_txt = (TextView) findViewById(R.id.login_txt);
        login_txt.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    public void getlogin_Detatils() {
        db = openOrCreateDatabase("TASK", Context.MODE_PRIVATE, null);
        //  Toast.makeText(Install_display.this, "view my db install data ", Toast.LENGTH_SHORT).show();
        //  Cursor c=db.rawQuery("SELECT * FROM recce WHERE recce_id='"+email+"' and resume='"+resumename+"'", null);
        Cursor c = db.rawQuery("SELECT * FROM login WHERE email='" + email_et.getText().toString()
                + "' and password='" + password_et.getText().toString() + "'", null);
        String value = String.valueOf(c.getCount());
        if (value.equals("1")) {
            Intent home = new Intent(Login.this,MapsActivity.class);
            home.putExtra("username",email_et.getText().toString());
            startActivity(home);
            Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    String email = c.getString(c.getColumnIndex("email"));

                    c.moveToNext();
                }
                if (c.isAfterLast()) {
                    db.close();
                }
            }
        } else {
            Toast.makeText(getBaseContext(), "no user", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_txt:
                Intent signup = new Intent(Login.this, Signup.class);
                startActivity(signup);
                break;
            case R.id.submit:
                getlogin_Detatils();
                break;
        }
    }
}
