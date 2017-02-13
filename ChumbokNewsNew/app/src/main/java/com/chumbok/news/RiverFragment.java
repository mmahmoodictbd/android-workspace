package com.chumbok.news;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RiverFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        int position = getArguments().getInt("position");

        // List of rivers
        String[] rivers = getResources().getStringArray(R.array.menu_item);

        // Creating view correspoding to the fragment
        View v = inflater.inflate(R.layout.fragment_layout, container, false);

        // Getting reference to the TextView of the Fragment
        TextView tv = (TextView) v.findViewById(R.id.tv_content);

        // Setting currently selected river name in the TextView
        tv.setText(rivers[position]);

        // Updating the action bar title
        getActivity().getActionBar().setTitle(rivers[position]);

        return v;
    }
}