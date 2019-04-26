package com.example.vmatchu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signInActivity extends AppCompatActivity {

    Button signIn;
    TextView newAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signIn=(Button)findViewById(R.id.signin);
        newAcc=(TextView)findViewById(R.id.createAcc);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signInActivity.this,HomeActivity.class));
            }
        });

        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signInActivity.this,SignUpActivity.class));
            }
        });
    }
}
