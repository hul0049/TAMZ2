package com.example.hul0049_tamz2_lode.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hul0049_tamz2_lode.R;

import java.util.ArrayList;

public class ProfileAdapter extends ArrayAdapter<Player> {
    public ProfileAdapter(Context context, ArrayList<Player> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Player player = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_row, parent, false);
        }

        TextView profileNick = (TextView) convertView.findViewById(R.id.ProfileNick);
        TextView profileScore = (TextView) convertView.findViewById(R.id.ProfileScore);
        profileNick.setText(player.getNick());
        profileScore.setText(player.getScore());

        return convertView;
    }
}