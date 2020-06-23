package com.example.myapplication.ViewModels;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapters.CharacterAdapter;
import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.Player;
import com.example.myapplication.MKCorePack.PlayerStats;
import com.example.myapplication.R;

public class SettingsFragment extends Fragment {
    private Player player;
    public SettingsFragment(Player player) {
        this.player = player;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_stats, container, false);

        CharacterAdapter characterAdapter = new CharacterAdapter(
                view.getContext(), R.layout.character_stats_list_item, Character.getCharacterArrayList(),
                new CharacterAdapter.ForCharactersStats(player));
        ListView charactersStatsListView = view.findViewById(R.id.charactersStatsInfo_charactersStatsListView);
        charactersStatsListView.setAdapter(characterAdapter);

        TextView totalGamesTv = view.findViewById(R.id.charactersStatsInfo_charactersStatsInfoTotalGamesTextView);
        TextView totalGamesStatsTv = view.findViewById(R.id.charactersStatsInfo_charactersStatsInfoTotalGamesStatsTextView);
        TextView totalRankedGamesTv = view.findViewById(R.id.charactersStatsInfo_charactersStatsInfoTotalRankedGamesTextView);
        TextView totalRankedStatsTv = view.findViewById(R.id.charactersStatsInfo_charactersStatsInfoTotalRankedGamesStatsTextView);

        int characterId= 3;
        totalGamesTv.setText(Integer.toString(player.getTotalGames(characterId)));
        totalGamesStatsTv.setText(
                player.getTotalGamesWins(characterId) + " / " + player.getTotalGamesLose(characterId)
                + " / " + player.getTotalGamesDisconnects(characterId)
        );
        totalRankedGamesTv.setText(Integer.toString(player.getPlayerStats().getTotalRankedGames(characterId)));
        totalRankedStatsTv.setText(
                player.getPlayerStats().getTotalRankedGamesWin(characterId) + " / " + player.getPlayerStats().getTotalRankedGamesLose(characterId)
                        + " / " + player.getTotalGamesDisconnects(characterId)
        );

        return view;
    }
}
