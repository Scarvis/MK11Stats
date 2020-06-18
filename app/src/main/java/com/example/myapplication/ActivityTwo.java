package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.ViewModels.KombatsListViewModel;
import com.example.myapplication.ViewModels.MKViewModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityTwo extends AppCompatActivity {
    private KombatsListViewModel kombatsListViewModel;
    private KombatAdapter kombatAdapter;
    private ListView kombatListView;
    boolean changeVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initialize();
    }

    public void initialize() {
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            kombatsListViewModel = (KombatsListViewModel) arguments.getSerializable(KombatsListViewModel.class.getSimpleName());
            if(kombatsListViewModel == null) return;
        } else {
            return;
        }
        kombatListView = findViewById(R.id.DKombatListViewId);
        kombatAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombatsListViewModel.getKombatArrayList());
        kombatAdapter.setColor(true);
        kombatListView.setAdapter(kombatAdapter);


    }

    public void butClick(View view) {
        goHome();
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
