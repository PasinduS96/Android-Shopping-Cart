package com.example.pasindusenarathne.mobile_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Pasindu Senarathne on 9/21/2018.
 */

public class CustomAdaptor extends BaseAdapter {

    private Activity activity;
    private ArrayList<ItemsModel> item;
    private LayoutInflater inflater;

    ItemsModel itemsModel;

    TextView items,prices,unit,ava,seller;


    public CustomAdaptor(Activity activity,ArrayList<ItemsModel> item) {

        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {

        return item.size();
    }

    @Override
    public ItemsModel getItem(int position) {

        return item.get(position);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(inflater == null){

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){

            view = inflater.inflate(R.layout.row,null);
        }

        items = (TextView) view.findViewById(R.id.itemNames);
        prices = (TextView) view.findViewById(R.id.prices_item);
        unit = (TextView) view.findViewById(R.id.unit);
        ava = (TextView) view.findViewById(R.id.available_item);
        seller = (TextView) view.findViewById(R.id.seller_item);

        itemsModel = item.get(i);
        items.setText(itemsModel.getItemname());
        prices.setText("Rs."+itemsModel.getPrice());
        unit.setText(itemsModel.getUnitCode());
        ava.setText(itemsModel.getAvailable());
        seller.setText("Selling By "+itemsModel.getSeller());


        return view;
    }
}
