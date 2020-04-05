package com.example.prm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Debtor> debtorSet = new ArrayList<Debtor>() {
        {
            add(new Debtor("Wiktoria Sternik", 1000));
            add(new Debtor("Krzysztof Sternik", 2000));
            add(new Debtor("Maria Skoczylas", 6000));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Wyswietlenie listy z jej objektami
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, debtorSet);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                //Toast.makeText(getApplicationContext(), debtorSet.get(pos).getName(),Toast.LENGTH_SHORT).show();
                openEditDebtor(debtorSet.get(pos).getName(),debtorSet.get(pos).getDebt());
            }
        });
        //Ustawienie wartosci textview czyli sumy wartosci dlugow
        //                                           x -> x.getDebt()
        double sum = debtorSet.stream().mapToDouble(Debtor::getDebt).sum();
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(Double.toString(sum));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDebtor();
            }
        });
    }

    private void openEditDebtor(String name,double debt) {
        Intent intent = new Intent(this, EditDebtor.class);
        intent.putExtra("name",name);
        intent.putExtra("debt",debt);
        startActivityForResult(intent,1);
    }
    private void openAddDebtor() {
        Intent intent = new Intent(this, EditDebtor.class);
        startActivity(intent);
    }
}
