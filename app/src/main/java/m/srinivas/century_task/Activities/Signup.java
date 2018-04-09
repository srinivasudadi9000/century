package m.srinivas.century_task.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import m.srinivas.century_task.helper.GPSTracker;
import m.srinivas.century_task.R;
import m.srinivas.century_task.helper.Validations;
import m.srinivas.century_task.helper.DBHelper;

import static android.content.ContentValues.TAG;

public class Signup extends Activity implements View.OnClickListener {
    EditText etFullName, dateofbirth_et, etemail, etmobile,etpassword;
    Button registration_btn;
    ImageView calendar_img;
    TextView login_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etmobile = (EditText) findViewById(R.id.etmobile);
        dateofbirth_et = (EditText) findViewById(R.id.dateofbirth_et);
        etemail = (EditText) findViewById(R.id.etemail);
        etpassword = (EditText) findViewById(R.id.etpassword);
        login_txt = (TextView) findViewById(R.id.login_txt);
        calendar_img = (ImageView) findViewById(R.id.calendar_img);
        registration_btn = (Button) findViewById(R.id.registration_btn);
        registration_btn.setOnClickListener(this);
        calendar_img.setOnClickListener(this);
        login_txt.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_NETWORK_STATE}, 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_txt:
                Intent login = new Intent(Signup.this,Login.class);
                startActivity(login);
                break;
            case R.id.registration_btn:
                GPSTracker gpsTracker = new GPSTracker(Signup.this);
                if (etFullName.getText().toString().length() == 0) {
                    etFullName.setError("Enter Name");
                    Validations.showalert("Name should not be empty", Signup.this);
                } else if (etmobile.getText().toString().length() == 0 || etmobile.getText().toString().length() < 10) {
                    etmobile.setError("Mobile Invalid");
                    Validations.showalert(" Mobile numbe Invalid ", Signup.this);

                } else if (dateofbirth_et.getText().toString().length() == 0) {
                    dateofbirth_et.setError("D.O.B Invalid");
                    Validations.showalert("D.O.B should not be empty", Signup.this);

                } else if (etemail.getText().toString().length() == 0) {
                    etemail.setError("Email Invalid");
                    Validations.showalert("Email should not be empty", Signup.this);

                }else if (etpassword.getText().toString().length() == 0){
                    etpassword.setError("Email Invalid");
                    Validations.showalert("password should not be empty", Signup.this);
                }else  if (etpassword.getText().toString().length() <6){
                    etpassword.setError("Email Invalid");
                    Validations.showalert("Password should not be < 6 characters", Signup.this);
                }else if (!Validations.isValidEmail(etemail.getText().toString())) {
                    etemail.setError("Invalid Email");
                    Validations.showalert("Email Invalid", Signup.this);
                } else if (!gpsTracker.canGetLocation()) {
                    Validations.showalert("Please Turn ON Your GPS", Signup.this);
                } else {
                    Location locatio = gpsTracker.getLocation();
                    String lat = "0", lat_long = "0";
                    if (locatio != null) {
                        lat = String.valueOf(locatio.getLatitude());
                        lat_long = String.valueOf(locatio.getLongitude());
                        Toast.makeText(getBaseContext(),lat+lat_long,Toast.LENGTH_SHORT).show();
                    }
                    new DBHelper(etFullName.getText().toString(),dateofbirth_et.getText().toString(),lat,lat_long,etmobile.getText().toString(),
                            etemail.getText().toString(),etpassword.getText().toString(),Signup.this);
                    Intent login_sign = new Intent(Signup.this,Login.class);
                    startActivity(login_sign);
                }
                break;
            case R.id.calendar_img:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
