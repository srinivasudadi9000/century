package m.srinivas.century_task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    TextView fullname, dateofbirth, phonenumber, email_txt;
    private GoogleMap mMap;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fullname = (TextView) findViewById(R.id.fullname);
        dateofbirth = (TextView) findViewById(R.id.dateofbirth);
        phonenumber = (TextView) findViewById(R.id.phonenumber);
        email_txt = (TextView) findViewById(R.id.email);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getlogin_Detatils();
        // Add a marker in Sydney and move the camera

    }

    public void getlogin_Detatils() {
        db = openOrCreateDatabase("TASK", Context.MODE_PRIVATE, null);
        //  Toast.makeText(Install_display.this, "view my db install data ", Toast.LENGTH_SHORT).show();
        //  Cursor c=db.rawQuery("SELECT * FROM recce WHERE recce_id='"+email+"' and resume='"+resumename+"'", null);
        Cursor c = db.rawQuery("SELECT * FROM login WHERE email='" + getIntent().getStringExtra("username").toString() + "'", null);
        String value = String.valueOf(c.getCount());
        Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                String email = c.getString(c.getColumnIndex("email"));
                String name = c.getString(c.getColumnIndex("name"));
                String dob = c.getString(c.getColumnIndex("dob"));
                String phone = c.getString(c.getColumnIndex("phone"));
                String lat = c.getString(c.getColumnIndex("lat"));
                String longitude = c.getString(c.getColumnIndex("longitude"));
                fullname.setText("Fullname  "+name);
                dateofbirth.setText("Date Of Birth "+ dob);
                phonenumber.setText("phone  "+ phone);
                email_txt.setText("email "+email);
                Log.d("email", email);
                Log.d("name", name);
                Log.d("dob", dob);
                Log.d("phone", phone);
                Log.d("lat", lat);
                Log.d("longitude", lat);
                Double lati, longi;
                lati = Double.valueOf(lat);
                longi = Double.valueOf(longitude);
                LatLng sydney = new LatLng(lati, longi);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                c.moveToNext();
            }
            if (c.isAfterLast()) {
                db.close();
            }
        }

    }

}
