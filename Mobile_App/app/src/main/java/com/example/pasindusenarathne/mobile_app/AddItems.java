package com.example.pasindusenarathne.mobile_app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddItems extends AppCompatActivity {

    EditText unitC, itemName, discription, price, avil;
    Button add_btn1 , show_btn,order_btn;
    Context context;
    TextView userText;
    SQLiteDatabase sqLiteDatabase;

    ShoppingListDBHelper shoppingListDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        unitC = (EditText) findViewById(R.id.itemUnitCode);
        itemName = (EditText) findViewById(R.id.addItemName);
        discription = (EditText) findViewById(R.id.itemDiscription);
        price = (EditText) findViewById(R.id.itemPrice);
        avil = (EditText) findViewById(R.id.yes_no);

        userText = (TextView) findViewById(R.id.banner);

        add_btn1 = (Button) findViewById(R.id.add_itemes_btn);
        show_btn = (Button) findViewById(R.id.view_btn);
        order_btn = (Button) findViewById(R.id.order_view_btn);


        Intent intent = getIntent();

        final String userLoged = intent.getStringExtra("user_name");

        userText.setText("Hello " + userLoged+"!" );

        add_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemsModel item = new ItemsModel();

                String unit = unitC.getText().toString();
                String name = itemName.getText().toString();
                String dis = discription.getText().toString();
                String prices = price.getText().toString();
                String availability = avil.getText().toString();
                String seller = userLoged;

                ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                dbHelper.insertData(unit,name,dis,prices,availability,seller,database);
                Toast.makeText(getApplication(), "Record Added", Toast.LENGTH_SHORT).show();
                dbHelper.close();

                unitC.setText("");
                itemName.setText("");
                discription.setText("");
                price.setText("");
                avil.setText("");


            }
        });

        /*
        add_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String unit = unitC.getText().toString();
                String name = itemName.getText().toString();
                String dis = discription.getText().toString();
                String prices = price.getText().toString();
                String availability = avil.getText().toString();


                if(unit.isEmpty()){

                    Toast.makeText(getApplicationContext(), "Please Enter Value", Toast.LENGTH_SHORT);
                }

                if (unit.isEmpty() && name.isEmpty() && dis.isEmpty() && prices.isEmpty() && availability.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Values", Toast.LENGTH_SHORT);
                    //unitC.setText("");
                    //itemName.setText("");
                    //discription.setText("");
                    //price.setText("");
                    //avil.setText("");
                } else {

                    //shoppingListDBHelper.insertData(unit, name, dis, prices, availability);
                    //unitC.setText("");
                    //itemName.setText("");
                    //discription.setText("");
                    //price.setText("");
                    //avil.setText("");
                    Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT);
                }

            }
        });*/

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddItems.this,ShowItemList.class);
                intent.putExtra("seller",userLoged);
                startActivity(intent);
            }
        });

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddItems.this,ShowOrders.class);
                intent.putExtra("seller",userLoged);
                startActivity(intent);

            }
        });

    }
/*
    public void addRecord(View view){

        String unit = unitC.getText().toString();
        String name = itemName.getText().toString();
        String dis = discription.getText().toString();
        String prices = price.getText().toString();
        String availability = avil.getText().toString();

        shoppingListDBHelper = new ShoppingListDBHelper(context);
        sqLiteDatabase = shoppingListDBHelper.getWritableDatabase();
        shoppingListDBHelper.insertData(unit,name,dis,prices,availability,sqLiteDatabase);
        shoppingListDBHelper.close();
        Toast.makeText(getBaseContext(), "Item Added", Toast.LENGTH_SHORT);

    }
*/
/*
    public void AddData() {
        add_btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String unit = unitC.getText().toString();
                        String name = itemName.getText().toString();
                        String dis = discription.getText().toString();
                        String prices = price.getText().toString();
                        String availability = avil.getText().toString();

                        if (unit.isEmpty() && name.isEmpty() && dis.isEmpty() && prices.isEmpty() && availability.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Please Enter Values", Toast.LENGTH_SHORT);
                            unitC.setText("");
                            itemName.setText("");
                            discription.setText("");
                            price.setText("");
                            avil.setText("");
                        }
                        else {

                            boolean isInserted = shoppingListDBHelper.insertData(unit, name, dis, prices, availability);

                            if (isInserted == true)
                                Toast.makeText(AddItems.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(AddItems.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }*/

    public boolean onCreateOptionMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_shopcart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu){


        ShoppingListDBHelper shopDB = new ShoppingListDBHelper(this);

        getMenuInflater().inflate(R.menu.menu_shopcart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.shop_cart_top){

            Toast.makeText(this,"Shopping List Opening",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddItems.this,ShowAllCustomers.class);
            startActivity(intent);
        }

        if(id == R.id.exit_top){

            Toast.makeText(this,"Back To Main",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddItems.this,MainActivity.class);
            startActivity(intent);
        }

        return true;
    }

}


