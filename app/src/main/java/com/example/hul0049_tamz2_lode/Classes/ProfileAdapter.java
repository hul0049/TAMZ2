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
        // Get the data item for this position
        Player player = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_row, parent, false);
        }
        // Lookup view for data population
        TextView profileNick = (TextView) convertView.findViewById(R.id.ProfileNick);
        TextView profileScore = (TextView) convertView.findViewById(R.id.ProfileScore);
        // Populate the data into the template view using the data object
        profileNick.setText(player.getNick());
        profileScore.setText(player.getScore());
        // Return the completed view to render on screen
        return convertView;
    }
}