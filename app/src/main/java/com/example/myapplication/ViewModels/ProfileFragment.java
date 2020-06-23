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

import com.example.myapplication.Adapters.CharacterAdapter;
import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Character;
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
        TextView tvTotalGames = view.findViewById(R.id.profile_totalGamesTextView);
        TextView tvCurMMR = view.findViewById(R.id.profile_playerMMRTextView);
        TextView tvFavChar = view.findViewById(R.id.profile_favoriteCharacterTextView);
        TextView tvWR = view.findViewById(R.id.profile_winRateTextView);
        TextView tvRankedWR = view.findViewById(R.id.profile_winRateRankedTextView);
        Button totalGamesButton = view.findViewById(R.id.profile_totalGamesStatsButton);
        Button totalRankedGamesButton = view.findViewById(R.id.profile_rankedGamesStatsButton);
        Button totalCasualGamesButton = view.findViewById(R.id.profile_casualGamesStatsButton);
        ListView charactersStatsListView = view.findViewById(R.id.profile_charactersStatsListView);

        tvNick.setText(player.getNickName());
        tvTotalGames.setText(Integer.toString(player.getTotalGames()));
        tvCurMMR.setText(Integer.toString(player.getMmr()));
        tvFavChar.setText(player.getFavoriteCharacter().getName());
        tvWR.setText(player.getWinRateString());
        tvRankedWR.setText(player.getRankedWinRateString());

        CharacterAdapter characterAdapter = new CharacterAdapter(
                view.getContext(), R.layout.character_stats_list_item, Character.getCharacterArrayList(),
                new CharacterAdapter.CharacterStats(player));
        charactersStatsListView.setAdapter(characterAdapter);
    }
}
