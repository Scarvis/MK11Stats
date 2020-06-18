package com.example.myapplication.ViewModels;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Player;
import com.example.myapplication.R;

public class ProfileFragment extends Fragment {
    private Player player;
    public ProfileFragment(Player player) {
        this.player = player;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        initializeFields(view);
        return view;
    }
    @SuppressLint("SetTextI18n")
    private void initializeFields(View view) {
        TextView tvNick = view.findViewById(R.id.nicknameTextView);
        TextView tvTotalGames = view.findViewById(R.id.totalGamesTextView);
        TextView tvCurMMR = view.findViewById(R.id.currentMMRTextView);
        TextView tvFavChar = view.findViewById(R.id.favoriteCharacterTextView);
        TextView tvWR = view.findViewById(R.id.winRateTextView);
        TextView tvRankedWR = view.findViewById(R.id.winRateRankedTextView);
        Button gamesHistoryButton = view.findViewById(R.id.gamesHistoryButton);
        ListView listView = view.findViewById(R.id.kombatLeagueSeasonsListView);

        tvNick.setText(player.getNickName());
        tvTotalGames.setText(Integer.toString(player.getTotalGames()));
        tvCurMMR.setText(Integer.toString(player.getMmr()));
        tvFavChar.setText(player.getFavoriteCharacter().getName());
        tvWR.setText(Double.toString(player.getWinRate()));
        tvRankedWR.setText(Double.toString(player.getRankedWinRate()));
        //gamesHistoryButton.setText(kombatsListViewModel.getCountKombats() + " Kombats");
    }
}
