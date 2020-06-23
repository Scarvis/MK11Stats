package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.IPlayerCharacterStats;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Player;
import com.example.myapplication.MKCorePack.PlayerStats;
import com.example.myapplication.R;

import java.util.List;

public class CharacterAdapter extends ArrayAdapter<Character> {
    private LayoutInflater inflater;
    private int layout;
    private List<Character> characterList;
    private IPlayerCharacterStats iPlayerCharacterStats;
    public CharacterAdapter(Context context, int resource, List<Character> characterList, IPlayerCharacterStats iPlayerCharacterStats) {
        super(context, resource, characterList);
        this.characterList = characterList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.iPlayerCharacterStats = iPlayerCharacterStats;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CharacterAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new CharacterAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CharacterAdapter.ViewHolder) convertView.getTag();
        }
        Character character = characterList.get(position);

        viewHolder.characterImageView.setImageResource(character.getCharacterResource());
        viewHolder.characterTextView.setText(character.getName());
        viewHolder.characterWinRateTextView.setText(
                (iPlayerCharacterStats.getTotalGamesWins(character.getId()) + " / " + iPlayerCharacterStats.getTotalGamesLose(character.getId()) + " / " +
                        iPlayerCharacterStats.getTotalGamesDisconnects(character.getId())));
//        viewHolder.characterWinRateTextView.setText(
//                (player.getTotalGamesWins(character.getId()) + " / " + player.getTotalGamesLose(character.getId()) + " / " +
//                        player.getTotalGamesDisconnects()));
        return convertView;
    }

    public static class CharacterStats implements IPlayerCharacterStats {
        private Player player;
        public CharacterStats(Player player) {
            this.player = player;
        }

        @Override
        public int getTotalGamesWins(int characterId) {
            return player.getTotalGamesWins(characterId);
        }

        @Override
        public int getTotalGamesLose(int characterId) {
            return player.getTotalGamesLose(characterId);
        }

        @Override
        public int getTotalGamesDisconnects(int characterId) {
            return player.getTotalGamesDisconnects();
        }
    }

    public static class ForCharactersStats implements IPlayerCharacterStats {
        private PlayerStats playerStats;

        public ForCharactersStats(Player player) {
            this.playerStats = player.getPlayerStats();
        }

        @Override
        public int getTotalGamesWins(int characterId) {
            return playerStats.getTotalGamesWin(characterId);
        }

        @Override
        public int getTotalGamesLose(int characterId) {
            return playerStats.getTotalGamesLose(characterId);
        }

        @Override
        public int getTotalGamesDisconnects(int characterId) {
            return playerStats.getTotalGamesDisconnects(characterId);
        }
    }

    private class ViewHolder {
        final ImageView characterImageView;
        final TextView characterTextView, characterWinRateTextView;

        ViewHolder(View view) {
            characterImageView = (ImageView) view.findViewById(R.id.charactersStats_CharacterImageView);
            characterTextView = (TextView) view.findViewById(R.id.charactersStats_CharacterTextView);
            characterWinRateTextView = (TextView) view.findViewById(R.id.charactersStats_CharacterWinRateTextView);
        }


    }
}
