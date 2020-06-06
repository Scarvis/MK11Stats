package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.DataProvider;
import com.example.myapplication.MKCorePack.Variation;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class activity_add_kombat extends AppCompatActivity {
    ArrayList<Character> charactersArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kombat);
        initialize();

    }

    public void initialize() {
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            charactersArrayList = (ArrayList<Character>) arguments.getSerializable(ArrayList.class.getSimpleName());
        }
        else {
            return;
        }
        if(charactersArrayList == null) return;
        initializeSpinners(charactersArrayList);
        editVariationSpinner(charactersArrayList.get(0), (Spinner) findViewById(R.id.leftCharacterVariationSpinner));
        editVariationSpinner(charactersArrayList.get(0), (Spinner) findViewById(R.id.rightCharacterVariationSpinner));
    }

    public void initializeSpinners(ArrayList<Character> characters) {
        ArrayList<String> chars = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            chars.add(characters.get(i).getName());
        }
        Spinner lspinner = (Spinner) findViewById(R.id.leftCharacterSpinner);
        Spinner rspinner = (Spinner) findViewById(R.id.rightCharacterSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, chars);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lspinner.setAdapter(adapter);
        rspinner.setAdapter(adapter);

        OnItemSelectedListener litemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                editVariationSpinner(charactersArrayList.get(position), (Spinner) findViewById(R.id.leftCharacterVariationSpinner));
                TextView tv = (TextView) findViewById(R.id.logTextView);
                tv.setText(Long.toString(id) + " "  + Integer.toString(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        OnItemSelectedListener ritemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                editVariationSpinner(charactersArrayList.get(position), (Spinner) findViewById(R.id.rightCharacterVariationSpinner));
                TextView tv = (TextView) findViewById(R.id.logTextView);
                tv.setText(Long.toString(id) + " "  + Integer.toString(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        lspinner.setOnItemSelectedListener(litemSelectedListener);
        rspinner.setOnItemSelectedListener(ritemSelectedListener);
    }

    public void initializeSpinnerListeners() {

    }

    public void editVariationSpinner(Character character, Spinner spinner) {
        ArrayList<String> vars = new ArrayList<>();
        ArrayList<Variation> res = character.getVariationsList();
        for (int i = 0; i < res.size(); i++) {
            vars.add(res.get(i).getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vars);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void cancel(View view) {

    }

    public void addKombat(View view) {
//        DataProvider dataProvider = new DataProvider();
//        String res = dataProvider.openText();
//        TextView tv = (TextView) findViewById(R.id.logTextView);
//        tv.setText(res);
    }
}
