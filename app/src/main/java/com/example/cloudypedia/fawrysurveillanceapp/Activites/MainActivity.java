package com.example.cloudypedia.fawrysurveillanceapp.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cloudypedia.fawrysurveillanceapp.DataFetcher.FetchLocationTask;
import com.example.cloudypedia.fawrysurveillanceapp.Fragments.MainFragment;
import com.example.cloudypedia.fawrysurveillanceapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new MainFragment()).commit();
    }
}
