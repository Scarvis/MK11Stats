package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Player;
import com.example.myapplication.ViewModels.KombatsListViewModel;
import com.example.myapplication.ViewModels.ProfileViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    private ProfileViewModel profileViewModel;
    private KombatsListViewModel kombatsListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initialize();
    }

    public void gamesStats(View view) {
        Intent intent = new Intent(this, ActivityTwo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KombatsListViewModel.class.getSimpleName(), kombatsListViewModel);
        startActivity(intent);
    }

    public void cancel(View view) {
        goHome();
    }

    private void initialize() {
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            profileViewModel = new ProfileViewModel((ProfileViewModel) Objects.requireNonNull(arguments.getSerializable(ProfileViewModel.class.getSimpleName())));
            kombatsListViewModel = new KombatsListViewModel((KombatsListViewModel) Objects.requireNonNull(arguments.getSerializable(KombatsListViewModel.class.getSimpleName())));
        } else {
            return;
        }
        initializeFields();
    }

    @SuppressLint("SetTextI18n")
    private void initializeFields() {
        TextView tvNick = findViewById(R.id.nicknameTextView);
        TextView tvTotalGames = findViewById(R.id.totalGamesTextView);
        TextView tvCurMMR = findViewById(R.id.currentMMRTextView);
        TextView tvFavChar = findViewById(R.id.favoriteCharacterTextView);
        TextView tvWR = findViewById(R.id.winRateTextView);
        TextView tvRankedWR = findViewById(R.id.winRateRankedTextView);
        Button gamesHistoryButton = findViewById(R.id.gamesHistoryButton);
        ListView listView = findViewById(R.id.kombatLeagueSeasonsListView);

        Player ownPlayer = profileViewModel.getOwnPlayer();

        tvNick.setText(ownPlayer.getNickName());
        tvTotalGames.setText(Integer.toString(ownPlayer.getTotalGames()));
        tvCurMMR.setText(Integer.toString(ownPlayer.getMmr()));
        tvFavChar.setText(ownPlayer.getFavoriteCharacter().getName());
        tvWR.setText(Double.toString(ownPlayer.getWinRate()));
        tvRankedWR.setText(Double.toString(ownPlayer.getRankedWinRate()));
        gamesHistoryButton.setText(kombatsListViewModel.getCountKombats() + " Kombats");

        KombatAdapter kombatAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombatsListViewModel.getKombatArrayList());
        listView.setAdapter(kombatAdapter);
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
