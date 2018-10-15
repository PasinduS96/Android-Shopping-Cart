package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAllCustomers extends AppCompatActivity {

    ListView list;
    CustomAdaptor customAdaptor;
    ArrayList<ItemsModel> arrayList;
    ShoppingListDBHelper shoppingListDBHelper;
    ItemsModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_customers);

        list = (ListView) findViewById(R.id.listOfItems1);

        shoppingListDBHelper= new ShoppingListDBHelper(this);

        arrayList = new ArrayList<>();
        arrayList = shoppingListDBHelper.getAllItemsCustomers();

        customAdaptor = new CustomAdaptor(this,arrayList);
        list.setAdapter(customAdaptor);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                item = customAdaptor.getItem(i);
                Intent intent = new Intent(ShowAllCustomers.this,ProductView.class);

                intent.putExtra("UnitCode",item.getUnitCode());
                intent.putExtra("ItemName",item.getItemname());
                intent.putExtra("Price",item.getPrice());
                intent.putExtra("Discription",item.getDiscription());
                intent.putExtra("Available",item.getAvailable());
                intent.putExtra("Seller",item.getSeller());
                startActivity(intent);




            }
        });
    }
}
