package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.R;

import java.util.List;

public class CharacterAdapter extends ArrayAdapter<Character> {
    private LayoutInflater inflater;
    private int layout;
    private List<Character> characterList;

    public CharacterAdapter(Context context, int resource, List<Character> characterList) {
        super(context, resource, characterList);
        this.characterList = characterList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
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

//        viewHolder.leftCharacterImageView.setImageResource(kombat.getResourceLeftCharacter());
//        viewHolder.leftCharacterTextView.setText(kombat.getLeftCharacter().toString());
//        viewHolder.rightCharacterTextView.setText(kombat.getRightCharacter().toString());
//        viewHolder.rightCharacterImageView.setImageResource(kombat.getResourceRightCharacter());
//        viewHolder.leftPlayerTextView.setText(kombat.getLeftPlayer().toString());
//        viewHolder.rightPlayerTextView.setText(kombat.getRightPlayer().toString());
        return convertView;
    }

    private class ViewHolder {
        final ImageView leftCharacterImageView, rightCharacterImageView;
        final TextView leftCharacterTextView, rightCharacterTextView;
        final TextView leftPlayerTextView, rightPlayerTextView;

        ViewHolder(View view) {
            leftCharacterImageView = (ImageView) view.findViewById(R.id.leftCharacterImageView);
            leftCharacterTextView = (TextView) view.findViewById(R.id.leftCharacterTextView);
            rightCharacterTextView = (TextView) view.findViewById(R.id.rightCharacterTextView);
            rightCharacterImageView = (ImageView) view.findViewById(R.id.rightCharacterImageView);
            leftPlayerTextView = (TextView) view.findViewById(R.id.leftPlayerTextView);
            rightPlayerTextView = (TextView) view.findViewById(R.id.rightPlayerTextView);
        }


    }
}
