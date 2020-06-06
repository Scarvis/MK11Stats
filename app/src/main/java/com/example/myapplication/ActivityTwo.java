package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.ViewModels.MKViewModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initialize();

    }

    public void initialize() {
        Bundle arguments = getIntent().getExtras();
        final ArrayList<Kombat> kombatArrayList;
        //ArrayList<Kombat> kombatArrayList;

        if (arguments != null) {
            kombatArrayList = (ArrayList<Kombat>) arguments.getSerializable(ArrayList.class.getSimpleName());
        }
        else {
            return;
        }
        ListView kombatListView = findViewById(R.id.DKombatListViewId);
        KombatAdapter kombatAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombatArrayList);
        kombatListView.setAdapter(kombatAdapter);
    }
}
