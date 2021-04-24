package com.example.doorstep;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button LogIn_Button;
    private EditText LogIn_Email, LogIn_password;
    private FirebaseAuth auth;
    private static final String TAG = "EmailPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        //set view now
        setContentView(R.layout.login);
        auth = FirebaseAuth.getInstance();
        LogIn_Email = findViewById(R.id.email_edit);
        LogIn_Email = findViewById(R.id.password_edit);
        LogIn_Button = findViewById(R.id.login2Home);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        LogIn_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //username
                String email = LogIn_Email.getText().toString();
                //password
                String password = LogIn_password.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter your email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //authenticate user
                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = auth.getCurrentUser();
                                    startActivity(new Intent(Login.this, Home.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
    public void NavigateSignUp(View v){
        Intent inent = new Intent(this, SignUp.class);
        startActivity(inent);
    }
    public void NavigateForgetPassword(View v){
        Intent inent = new Intent(this, ResetPassword.class);
        startActivity(inent);
    }



}
