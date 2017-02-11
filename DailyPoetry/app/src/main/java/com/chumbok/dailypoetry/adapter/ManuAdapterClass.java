package com.chumbok.dailypoetry.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumbok.dailypoetry.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ManuAdapterClass extends ArrayAdapter<String> {

    Context context;
    private ArrayList<String> menuItemsTextList = new ArrayList<String>();
    private ArrayList<String> menuItemsDrawableList = new ArrayList<String>();

    public ManuAdapterClass(Context context, String[] menuItemArr, String[] menuDrawableArr) {
        super(context, R.layout.row_of_drawer, new ArrayList<String>(Arrays.asList(menuItemArr)));
        this.context = context;
        this.menuItemsTextList = new ArrayList<String>(Arrays.asList(menuItemArr));
        this.menuItemsDrawableList = new ArrayList<String>(Arrays.asList(menuDrawableArr));
    }

    @Override
    public View getView(int position, View coverView, ViewGroup parent) {
        // TODO Auto-generated method stub

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_of_drawer,
                parent, false);

        TextView text1 = (TextView) rowView.findViewById(R.id.txtMenuContent);
        text1.setText(menuItemsTextList.get(position));

        ImageView iv = (ImageView) rowView.findViewById(R.id.menuItemIcon);

        Resources resources = context.getResources();
        int drawableResourceId = resources.getIdentifier(menuItemsDrawableList.get(position), "drawable", context.getPackageName());
        iv.setImageResource(drawableResourceId);

        return rowView;

    }

}
