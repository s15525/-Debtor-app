package com.example.prm1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import java.util.Objects;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {
    private final int EditActivityID = 1;
    private final int AddActivityID = 2;
    private ArrayAdapter arrayAdapter;
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
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, debtorSet);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        //Edit listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                //Toast.makeText(getApplicationContext(), debtorSet.get(pos).getName(),Toast.LENGTH_SHORT).show();
                openEditDebtor(debtorSet.get(pos).getName(), debtorSet.get(pos).getDebt(), pos);
            }
        });
        //Delete listener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Usun");
                adb.setMessage("Jestes pewien ze chcesz usunac tego dluznika?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        debtorSet.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Usunieto", Toast.LENGTH_SHORT).show();
                        refreshSum();
                    }
                });
                adb.show();
                return true;
            }
        });

        refreshSum();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDebtor();
            }
        });
    }

    private void openEditDebtor(String name, double debt, int id) {
        Intent intent = new Intent(this, EditDebtor.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("debt", debt);
        intent.putExtra("itsEdit",true);
        startActivityForResult(intent, EditActivityID);
    }

    private void openAddDebtor() {
        Intent intent = new Intent(this, EditDebtor.class);
        startActivityForResult(intent, AddActivityID);
    }

    private void refreshSum() {
        //Ustawienie wartosci textview czyli sumy wartosci dlugow
        //                                           x -> x.getDebt()
        double sum = debtorSet.stream().mapToDouble(Debtor::getDebt).sum();
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(Double.toString(sum));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == AddActivityID) {
                Debtor newDebetor = new Debtor(data.getStringExtra("nameOut"), data.getDoubleExtra("debtOut", 0));
                arrayAdapter.add(newDebetor);
                arrayAdapter.notifyDataSetChanged();
            }
            if (requestCode == EditActivityID) {
                Debtor updateDebetor = new Debtor(data.getStringExtra("nameOut"), data.getDoubleExtra("debtOut", 0));
                arrayAdapter.insert(updateDebetor, data.getIntExtra("id", -1));
                arrayAdapter.remove(arrayAdapter.getItem(data.getIntExtra("id", -1) + 1));
                arrayAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        refreshSum();
    }
}

