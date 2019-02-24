package com.example.moon.dailyexpences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Login extends AppCompatActivity {

    EditText pin;
    ImageButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pin = (EditText)findViewById(R.id.editText);
        login = (ImageButton)findViewById(R.id.imageButton);

        login.setVisibility(View.INVISIBLE);

        pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                login.setVisibility(View.VISIBLE);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pass = pin.getText().toString().trim();
                if(Pass.equals("7306")){
                    Intent intent = new Intent(Login.this,Main2Activity.class);
                    startActivity(intent);

                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }
        });

    }
}
