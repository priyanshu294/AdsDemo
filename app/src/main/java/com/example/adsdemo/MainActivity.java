package com.example.adsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    AdView adView;
    Button button;
    TextView textView;

    private InterstitialAd interstitialAd;
    private int adcount = 1;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textfirst);

        button.setOnClickListener(this);

        //Initialize mobileads sdk

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        adView = findViewById(R.id.home_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ads_id));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest2);



        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                startActivity(new Intent(MainActivity.this,AnotherActivity.class));

            }
        });



    }

    @Override
    public void onClick(View v) {


        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
        else {
            startActivity(new Intent(this,AnotherActivity.class));

        }

//        //limit ads
//        if(adcount % 2 == 0){
//            if(interstitialAd.isLoaded()){
//                interstitialAd.show();
//            }
//            else {
//                startActivity(new Intent(this,AnotherActivity.class));
//
//            }
//
//        } else {
//            startActivity(new Intent(this,AnotherActivity.class));
//
//        }
//        adcount++;


    }
}
