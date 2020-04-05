package com.example.prm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class EditDebtor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_debtor);
        //edit
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double debt = intent.getDoubleExtra("debt",0);
        int id = intent.getIntExtra("id",-1);
        TextInputEditText nameFiled = (TextInputEditText) findViewById(R.id.textInputEditText);
        TextInputEditText debtFiled = (TextInputEditText) findViewById(R.id.textInputEditText2);
        nameFiled.setText(name);
        debtFiled.setText(Double.toString(debt));
        //add


    }
}
