package com.example.cloudypedia.fawrysurveillanceapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cloudypedia.fawrysurveillanceapp.Activites.MapsActivity;
import com.example.cloudypedia.fawrysurveillanceapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    Button searchByNearest;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_main, container, false);


         searchByNearest = (Button) rootView.findViewById(R.id.search_btn);
        searchByNearest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
        return rootView ;
    }

}
