package com.example.pasindusenarathne.mobile_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowOrders extends AppCompatActivity {

    ListView list;
    CustomAdapterOrders customAdaptor;
    ArrayList<OrderModel> arrayList;
    ShoppingListDBHelper shoppingListDBHelper;
    OrderModel orderModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);


        list = (ListView) findViewById(R.id.listOfOrders);

        shoppingListDBHelper = new ShoppingListDBHelper(this);

        Intent intent = getIntent();

        final String userLoged = intent.getStringExtra("seller");

        arrayList = new ArrayList<>();
        arrayList = shoppingListDBHelper.getAllOrders(userLoged);

        customAdaptor = new CustomAdapterOrders(this, arrayList);
        list.setAdapter(customAdaptor);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                orderModel = customAdaptor.getItem(i);

                Intent intent = new Intent(ShowOrders.this, ManageOrders.class);

                intent.putExtra("orderid", orderModel.getUserID());
                intent.putExtra("unitcode", orderModel.getUnitCode());
                intent.putExtra("customer", orderModel.getCustomer());
                intent.putExtra("qty", orderModel.getQuantity());
                intent.putExtra("date", orderModel.getDate());
                intent.putExtra("seller", orderModel.getSeller());
                intent.putExtra("total", orderModel.getTotal());
                startActivity(intent);


            }
        });

    }
}
