package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
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

    KombatsListViewModel kombatsListViewModel;
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
        } else {
            return;
        }
        ListView kombatListView = findViewById(R.id.DKombatListViewId);
        KombatAdapter kombatAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombatsListViewModel.getKombatArrayList());
        kombatListView.setAdapter(kombatAdapter);
    }

    public void butClick(View view) {
        ImageView liv = findViewById(R.id.leftCharacterImageView);
        ImageView riv = findViewById(R.id.rightCharacterImageView);

        if(liv.getVisibility() == View.VISIBLE) {
            liv.setVisibility(View.INVISIBLE);
            riv.setVisibility(View.INVISIBLE);
        }
        else if(liv.getVisibility() == View.INVISIBLE){
            liv.setVisibility(View.VISIBLE);
            riv.setVisibility(View.VISIBLE);
        }
    }
}
