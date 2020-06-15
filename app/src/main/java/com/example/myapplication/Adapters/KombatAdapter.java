package com.example.myapplication.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.R;

import java.util.List;


public class KombatAdapter extends ArrayAdapter<Kombat> {
    private LayoutInflater inflater;
    private int layout;
    private List<Kombat> kombats;
    private boolean isColor;
    private boolean isImageVisible;

    public KombatAdapter(Context context, int resource, List<Kombat> kombats) {
        super(context, resource, kombats);
        this.kombats = kombats;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public void setColor(boolean isColor){
        this.isColor = isColor;
    }
    public void setImageVisible(boolean isImageVisible){
        this.isImageVisible = isImageVisible;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Kombat kombat = kombats.get(position);

        viewHolder.leftCharacterImageView.setImageResource(kombat.getResourceLeftCharacter());
        viewHolder.leftCharacterTextView.setText(kombat.getLeftCharacter().toString());
        viewHolder.rightCharacterTextView.setText(kombat.getRightCharacter().toString());
        viewHolder.rightCharacterImageView.setImageResource(kombat.getResourceRightCharacter());
        viewHolder.leftPlayerTextView.setText(kombat.getLeftPlayer().toString());
        viewHolder.rightPlayerTextView.setText(kombat.getRightPlayer().toString());
        if(isColor) {
            setColor(viewHolder, kombat, convertView);
        }

        return convertView;
    }

    private void setColor(ViewHolder viewHolder, Kombat kombat, View view) {
        //String colorLeft, colorRight;
        if (kombat.getWinnerSide() == Kombat.WINNER_SIDE.OWN || kombat.getWinnerSide() == Kombat.WINNER_SIDE.OPPONENT_LEAVE) {
            view.setBackgroundColor(Color.parseColor("green"));
        } else if (kombat.getWinnerSide() == Kombat.WINNER_SIDE.OWN_LEAVE || kombat.getWinnerSide() == Kombat.WINNER_SIDE.OPPONENT) {
            view.setBackgroundColor(Color.parseColor("red"));
        }
        else {
            view.setBackgroundColor(Color.parseColor("#FFA500"));
        }
//        viewHolder.leftPlayerTextView.setBackgroundColor(Color.parseColor(colorLeft));
//        viewHolder.leftCharacterTextView.setBackgroundColor(Color.parseColor(colorLeft));
//        viewHolder.rightPlayerTextView.setBackgroundColor(Color.parseColor(colorRight));
//        viewHolder.rightCharacterTextView.setBackgroundColor(Color.parseColor(colorRight));
    }

    private void changeVisibleImageView(ViewHolder viewHolder) {
        if(viewHolder.leftCharacterImageView.getVisibility() == View.VISIBLE) {
            viewHolder.leftCharacterImageView.setVisibility(View.INVISIBLE);
            viewHolder.rightCharacterImageView.setVisibility(View.INVISIBLE);
        }
        else if(viewHolder.leftCharacterImageView.getVisibility() == View.INVISIBLE) {
            viewHolder.leftCharacterImageView.setVisibility(View.VISIBLE);
            viewHolder.rightCharacterImageView.setVisibility(View.VISIBLE);
        }
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