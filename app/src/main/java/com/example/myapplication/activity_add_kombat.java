package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.myapplication.Adapters.DatabaseAdapter;
import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.DataProvider;
import com.example.myapplication.MKCorePack.DatabaseHelper;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Player;
import com.example.myapplication.MKCorePack.Variation;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class activity_add_kombat extends AppCompatActivity {
    private ArrayList<Character> charactersArrayList;

    private EditText leftPlayerNickEditText;
    private EditText rightPlayerNickEditText;
    private EditText leftPlayerMMREditText;
    private EditText rightPlayerMMREditText;
    private Spinner leftPlayerCharacterSpinner;
    private Spinner leftPlayerCharacterVariationSpinner;
    private Spinner rightPlayerCharacterSpinner;
    private Spinner rightPlayerCharacterVariationSpinner;
    private Spinner winnerSideSelectSpinner;
    private CheckBox isRankedCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kombat);
        initializeViews();
        initialize();
    }

    private void initialize() {
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            charactersArrayList = (ArrayList<Character>) arguments.getSerializable(ArrayList.class.getSimpleName());
        }
        else {
            return;
        }
        if(charactersArrayList == null || charactersArrayList.size() == 0) return;
        Collections.sort(charactersArrayList, Character.CharactersNameComparator);

        initializeSpinners(charactersArrayList);
        initializeWinnerSideSpinner();
        editVariationSpinner(charactersArrayList.get(0), (Spinner) findViewById(R.id.leftCharacterVariationSpinner));
        editVariationSpinner(charactersArrayList.get(0), (Spinner) findViewById(R.id.rightCharacterVariationSpinner));
    }

    @SuppressLint("SetTextI18n")
    private void initializeViews() {
        leftPlayerNickEditText = findViewById(R.id.leftPlayerEditText);
        rightPlayerNickEditText = findViewById(R.id.rightPlayerEditText);
        leftPlayerMMREditText = findViewById(R.id.leftPlayerMMREditText);
        rightPlayerMMREditText = findViewById(R.id.rightPlayerMMREditText);
        leftPlayerCharacterSpinner = findViewById(R.id.leftCharacterSpinner);
        leftPlayerCharacterVariationSpinner = findViewById(R.id.leftCharacterVariationSpinner);
        rightPlayerCharacterSpinner = findViewById(R.id.rightCharacterSpinner);
        rightPlayerCharacterVariationSpinner = findViewById(R.id.rightCharacterVariationSpinner);
        winnerSideSelectSpinner = findViewById(R.id.winnerSideSelectSpinner);
        isRankedCheckBox = findViewById(R.id.isRankedCheckBox);

        Player curPlayer = Player.getCurrentOwnPlayer();
        leftPlayerNickEditText.setText(curPlayer.getNickName());
        leftPlayerMMREditText.setText(Integer.toString(curPlayer.getMmr()));
    }

    private void initializeSpinners(ArrayList<Character> characters) {
        ArrayList<String> chars = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            chars.add(characters.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, chars);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftPlayerCharacterSpinner.setAdapter(adapter);
        rightPlayerCharacterSpinner.setAdapter(adapter);

        OnItemSelectedListener litemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editVariationSpinner(charactersArrayList.get(position), leftPlayerCharacterVariationSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        OnItemSelectedListener ritemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editVariationSpinner(charactersArrayList.get(position), rightPlayerCharacterVariationSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        leftPlayerCharacterSpinner.setOnItemSelectedListener(litemSelectedListener);
        rightPlayerCharacterSpinner.setOnItemSelectedListener(ritemSelectedListener);
    }

    private void initializeWinnerSideSpinner() {
        ArrayList<String> winnerValues = Kombat.WINNER_SIDE.getValuesSimpleStrings();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, winnerValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        winnerSideSelectSpinner.setAdapter(adapter);
    }

    private void editVariationSpinner(Character character, Spinner spinner) {
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
        goHome();
    }

    public void addKombat(View view) {
        int leftPlayerMMR = 0;
        int rightPlayerMMR = 0;
        Character leftCharacter = Character.getCharacterById(charactersArrayList.get(leftPlayerCharacterSpinner.getSelectedItemPosition()).getId());
        leftCharacter.setVariation(leftPlayerCharacterVariationSpinner.getSelectedItemPosition() + 1);
        Player leftPlayer = Player.getPlayer(leftPlayerNickEditText.getText().toString(), leftPlayerMMR);
        Character rightCharacter = Character.getCharacterById(charactersArrayList.get(rightPlayerCharacterSpinner.getSelectedItemPosition()).getId());
        rightCharacter.setVariation(rightPlayerCharacterVariationSpinner.getSelectedItemPosition() + 1);
        Player rightPlayer = Player.getPlayer(rightPlayerNickEditText.getText().toString(), rightPlayerMMR);
        int kombatLeagueSeason = 0;
        boolean isRanked = isRankedCheckBox.isEnabled();
        if (isRanked) kombatLeagueSeason = 10;
        int winnerSide = winnerSideSelectSpinner.getSelectedItemPosition() + 1;
        Kombat kombat = new Kombat(
                leftCharacter,
                rightCharacter,
                leftPlayer,
                rightPlayer,
                isRanked,
                kombatLeagueSeason,
                winnerSide
        );
        goHomeWithKombat(kombat);
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void goHomeWithKombat(Kombat kombat) {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(Kombat.class.getSimpleName(), kombat);
        startActivity(intent);
    }
}
