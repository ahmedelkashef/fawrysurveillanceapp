package com.example.cloudypedia.fawrysurveillanceapp.Activites;
/**
 * Created by M-Hadad on 05-Aug-15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.cloudypedia.fawrysurveillanceapp.R;

public class MessageActivity extends Activity {
    private String message = "Test 123";


    public MessageActivity() {
        Log.e("Fawry", " in message activity");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_layout);

        try {
            Bundle extras = getIntent().getExtras();
            message = (String) extras.get("message");
        }catch (Exception e){
            Log.e("Fawry","error in extras " + e.toString());
        }

        TextView tv = (TextView)findViewById(R.id.message_tv);
        tv.setText(message);

    }

}
