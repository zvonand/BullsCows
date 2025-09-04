package com.bullscows;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bullscows.bullscows_app.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter {
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        HistoryItem currentItem = (HistoryItem) getItem(position);
        TextView countTextView = (TextView) listItemView.findViewById(R.id.count_text_view);
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.number_text_view);
        TextView bullsTextView = (TextView) listItemView.findViewById(R.id.bulls_text_view);
        TextView cowsTextView = (TextView) listItemView.findViewById(R.id.cows_text_view);

        countTextView.setText(String.valueOf(currentItem.getCount()));
        numberTextView.setText(String.valueOf(currentItem.getNumber()));
        bullsTextView.setText(String.valueOf(currentItem.getBulls())+" bulls");
        cowsTextView.setText(String.valueOf(currentItem.getCows())+" cows");
        return listItemView;
    }

    public ItemAdapter(Activity context, ArrayList<HistoryItem> items){
        super(context, 0, items);
    }
}
