package m.srinivas.century_task;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends Activity {
   ImageView logo_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent signup = new Intent(SplashScreen.this,Signup.class);
                startActivity(signup);
            }
        },2000);


        logo_img = (ImageView) findViewById(R.id.logo_img);
        logo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SplashScreen.this,Login.class);
                startActivity(login);
            }
        });
    }
}
