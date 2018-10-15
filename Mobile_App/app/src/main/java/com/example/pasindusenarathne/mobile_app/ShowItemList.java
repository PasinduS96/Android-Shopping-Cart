package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowItemList extends AppCompatActivity {

    ListView list;
    CustomAdaptor customAdaptor;
    ArrayList<ItemsModel> arrayList;
    ShoppingListDBHelper shoppingListDBHelper;
    ItemsModel item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_item_list);

        list = (ListView) findViewById(R.id.listOfItems);

        shoppingListDBHelper= new ShoppingListDBHelper(this);

        Intent intent = getIntent();

        final String userLoged = intent.getStringExtra("seller");

        arrayList = new ArrayList<>();
        arrayList = shoppingListDBHelper.getAllItems(userLoged);

        customAdaptor = new CustomAdaptor(this,arrayList);
        list.setAdapter(customAdaptor);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                item = customAdaptor.getItem(i);
                Intent intent = new Intent(ShowItemList.this,Informer.class);

                intent.putExtra("UnitCode",item.getUnitCode());
                intent.putExtra("ItemName",item.getItemname());
                intent.putExtra("seller",item.getSeller());
                intent.putExtra("Discription",item.getDiscription());
                intent.putExtra("Price",item.getPrice());
                intent.putExtra("Available",item.getAvailable());
                startActivity(intent);




            }
        });


    }
}
