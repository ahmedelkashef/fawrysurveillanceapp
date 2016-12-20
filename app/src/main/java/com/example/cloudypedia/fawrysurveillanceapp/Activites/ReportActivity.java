package com.example.cloudypedia.fawrysurveillanceapp.Activites;

import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cloudypedia.fawrysurveillanceapp.Classes.GPSHandller;
import com.example.cloudypedia.fawrysurveillanceapp.Classes.Merchant;
import com.example.cloudypedia.fawrysurveillanceapp.Classes.Report;
import com.example.cloudypedia.fawrysurveillanceapp.R;

public class ReportActivity extends AppCompatActivity {

    TextView gisLocation ;
    TextView locationtxt ;
    TextView name ;
    TextView merchantId ;
    TextView salesId ;
    TextView salesName ;
    TextView salesEmail;
    TextView terminalID ;
     Button range;
    Report report;
    private  double Range;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportlayout);

        Intialize_view();
    }

    void Intialize_view()
    {
         Intialize_data();
        name = (TextView) findViewById(R.id.name_txt);
        name.setText("Name : " + report.getName());
        gisLocation = (TextView) findViewById(R.id.GISLocation_txt);
         gisLocation.setText( "GisLocation : "+report.getGISLocation());

         locationtxt = (TextView)  findViewById(R.id.location_txt);
         locationtxt.setText( "Location : "+report.getLocation());

         merchantId = (TextView)  findViewById(R.id.MerchantID_txt);
         merchantId.setText( "Merchant ID : "+report.getMerchantID());

         salesId =     (TextView)  findViewById(R.id.salesId_txt);
         salesId.setText( "Sales ID : "+report.getSalesID());

         salesName = (TextView)   findViewById(R.id.salesName_txt) ;
         salesName.setText( "Sales Name: "+report.getSalesName());

         salesEmail = (TextView) findViewById(R.id.salesEmail_txt) ;
         salesEmail.setText( "Sales Email : "+report.getSalesEmail());

         terminalID = (TextView) findViewById(R.id.TerminalID);
        terminalID.setText( "Terminal ID : "+report.getTerminalID());

        range = (Button) findViewById(R.id.toggleButton);
        if(Range > 100.0)
        {
            range.setBackgroundColor(Color.RED);
        }
        range.setText( "Range : " + report.getRange());
    }
    void Intialize_data()
    {
         report = new Report();
        GPSHandller gpsHandller = new GPSHandller(this);
        Location location = gpsHandller.getLocation();
        Bundle extras = getIntent().getExtras();
        Merchant current_merchant = extras.getParcelable("merchant");

         Range = extras.getDouble("distance");

        report .setName(current_merchant.getName());
        report.setLocation( Double.toString(current_merchant.getLatitude()) + " , "+ Double.toString(current_merchant.getLongitude()));
        report.setGISLocation(Double.toString(location.getLatitude()) + " , " + Double.toString(location.getLongitude()));
        report.setMerchantID("12345");
        report.setRange(Double.toString(Range) + " meters");
        report.setSalesID("123456");
        report.setSalesName("kashef");
        report.setSalesEmail("kashef@gmail.com");
        report.setTerminalID(current_merchant.getTerminalID());
    }
}
