package com.agnt45.countdown;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class LoadingScreen extends AppCompatActivity {
    private View view ;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        Context context = this;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork !=null && activeNetwork.isConnectedOrConnecting();
            if(isConnected){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i1 = new Intent(LoadingScreen.this,AuthScreen.class);
                        startActivity(i1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                },3000);
            }
            else{
                Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_LONG).show();

            }
        }



    }
}
