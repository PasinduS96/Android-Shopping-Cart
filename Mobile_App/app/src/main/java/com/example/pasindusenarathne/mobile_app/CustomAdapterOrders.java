package com.example.pasindusenarathne.mobile_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Pasindu Senarathne on 9/24/2018.
 */

public class CustomAdapterOrders extends BaseAdapter {

    private Activity activity;
    private ArrayList<OrderModel> item;
    private LayoutInflater inflater;

    OrderModel orderModel;

    TextView order_id,unit_id,customer_order,quantity_order,date,seller_order,total_pay;

    public CustomAdapterOrders(Activity activity,ArrayList<OrderModel> item) {

        this.activity = activity;
        this.item = item;
    }


    @Override
    public int getCount() {

        return item.size();
    }

    @Override
    public OrderModel getItem(int position) {
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

            view = inflater.inflate(R.layout.order_row,null);
        }

        order_id = (TextView) view.findViewById(R.id.orderid_order);
        unit_id = (TextView) view.findViewById(R.id.itemcode_order);
        customer_order = (TextView) view.findViewById(R.id.customer_order);
        quantity_order = (TextView) view.findViewById(R.id.qty_order);
        date = (TextView) view.findViewById(R.id.date_order);
        seller_order = (TextView) view.findViewById(R.id.seller_order);
        total_pay = (TextView) view.findViewById(R.id.total_order);

        orderModel = item.get(i);
        order_id.setText(orderModel.getUserID());
        unit_id.setText(orderModel.getUnitCode());
        customer_order.setText(orderModel.getCustomer());
        quantity_order.setText(orderModel.getQuantity());
        date.setText(orderModel.getDate());
        seller_order.setText(orderModel.getSeller());
        total_pay.setText("Rs."+orderModel.getTotal());


        return view;
    }
}
