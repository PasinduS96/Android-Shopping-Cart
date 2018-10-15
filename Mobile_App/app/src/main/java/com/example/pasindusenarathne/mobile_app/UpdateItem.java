package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateItem extends AppCompatActivity {

    EditText item_unit_code,item_name,item_price,item_discription,item_yes_no;
    Button details_update_button;
    String unitCode_item,name_item,price_item,dis_item,yes_no_item;
    ShoppingListDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        dbHelper  = new ShoppingListDBHelper(this);

        item_unit_code = (EditText) findViewById(R.id.uniit_code_update);
        item_name = (EditText) findViewById(R.id.item_name_update);
        item_price = (EditText) findViewById(R.id.price_update);
        item_discription = (EditText) findViewById(R.id.disc_update);
        item_yes_no = (EditText) findViewById(R.id.ava_update);

        final Intent intent = getIntent();

        unitCode_item = intent.getStringExtra("unit_code");
        name_item = intent.getStringExtra("item_name");
        price_item = intent.getStringExtra("item_price");
        dis_item = intent.getStringExtra("item_discription");
        yes_no_item = intent.getStringExtra("item_on_off");

        item_unit_code.setText(unitCode_item);
        item_name.setText(name_item);
        item_price.setText(price_item);
        item_discription.setText(dis_item);
        item_yes_no.setText(yes_no_item);

        details_update_button = (Button) findViewById(R.id.updating_button);
        details_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.updateData(item_unit_code.getText().toString(),item_name.getText().toString(),item_price.getText().toString(),item_discription.getText().toString(),item_yes_no.getText().toString());
                finish();

            }
        });
    }

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
            Intent intent = new Intent(UpdateItem.this,ShowAllCustomers.class);
            startActivity(intent);
        }

        if(id == R.id.exit_top){

            Toast.makeText(this,"Back To Main",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateItem.this,MainActivity.class);
            startActivity(intent);
        }

        return true;
    }

    public void alert(View view){

        Toast.makeText(this,"Field Can Not Be Edited",Toast.LENGTH_SHORT).show();

    }
}
