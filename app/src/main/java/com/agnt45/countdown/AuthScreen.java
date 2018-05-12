package com.agnt45.countdown;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.hbb20.CountryCodePicker;

public class AuthScreen extends AppCompatActivity {
    private static final String TAG = "AuthScreen";
    private SignInButton signInButton;
    private Button otpauth;
    private FirebaseAuth mAuth;
    private final static int RC_SIGN_IN=2;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignINClient;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);
        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Intent Auth = new Intent(AuthScreen.this,HomeActivity.class);
                    startActivity(Auth);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();

                }
            }
        };
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignINClient = GoogleSignIn.getClient(this,gso);
        signInButton = findViewById(R.id.GSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GSignIN();
            }
        });
        otpauth= findViewById(R.id.otp);
//        otpauth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(getApplicationContext());
//                dialog.setContentView(R.layout.otp_layout);
//                dialog.setTitle("OTP LOGIN");
//                //CountryCodePicker countryCodePicker = dialog.findViewById(R.id.countryPicker);
//                TextInputEditText phno = dialog.findViewById(R.id.phno);
//                Button generateOTP,authenticateOTP;
//                generateOTP = dialog.findViewById(R.id.otpgen);
//                authenticateOTP = dialog.findViewById(R.id.otplogin);
//                PinView pinView = dialog.findViewById(R.id.optinp);
//                dialog.show();
//
//            }
//        });
    }

    private void GSignIN() {
        Intent signInIntent = mGoogleSignINClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthwithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }

    private void firebaseAuthwithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"signINWithCreds:Success");
                        }else{
                            Log.d(TAG,"signINWithCreds:Failure");
                            Toast.makeText(AuthScreen.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(authStateListener);

    }
}
