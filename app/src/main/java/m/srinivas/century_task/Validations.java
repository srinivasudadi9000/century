package m.srinivas.century_task;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by srinivas on 06/04/18.
 */

public class Validations {

    public static void showalert(String alert_msg, Context context) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Authentication Checking..");
        alertDialogBuilder.setIcon(R.drawable.logo);

        // set dialog message
        alertDialogBuilder.setMessage(alert_msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // create alert dialog
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        TextView messageView = (TextView) alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public static  boolean isValidEmail(String Emailid) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(Emailid);
        return matcher.matches();
    }

}
