package com.example.prm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class EditDebtor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_debtor);
        Button addButton = (Button) findViewById(R.id.zatwierdz);
        Button anulujButton = (Button) findViewById(R.id.anuluj);
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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOut = nameFiled.getText().toString();
                double debtOut = Double.parseDouble(String.valueOf(debtFiled.getText()));

                Intent intentAdd = new Intent();
                intentAdd.putExtra("id",id);
                intentAdd.putExtra("nameOut",nameOut);
                intentAdd.putExtra("debtOut",debtOut);
                setResult(RESULT_OK,intentAdd);
                finish();
            }
        });

        // return changes
        anulujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
