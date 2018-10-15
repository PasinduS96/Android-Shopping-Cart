package com.example.pasindusenarathne.mobile_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Informer extends AppCompatActivity {

    TextView code,name,price,discrip,ava;
    String uncode,itmname,itmprice,itmdis,itmav;
    Button updateButton,deleteButton;
    ShoppingListDBHelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informer);

        code = (TextView) findViewById(R.id.u_code);
        name = (TextView) findViewById(R.id.item_name);
        price = (TextView) findViewById(R.id.item_price);
        discrip = (TextView) findViewById(R.id.item_dis);
        ava = (TextView) findViewById(R.id.a_item);

        Intent intent = getIntent();
        if(intent != null){

            uncode = intent.getStringExtra("UnitCode");
            itmname = intent.getStringExtra("ItemName");
            itmdis = intent.getStringExtra("Discription");
            itmprice = intent.getStringExtra("Price");
            itmav = intent.getStringExtra("Available");

        }

        code.setText(uncode);
        name.setText(itmname);
        price.setText(itmprice);
        discrip.setText(itmdis);
        ava.setText(itmav);

        updateButton = (Button) findViewById(R.id.update_button_informer);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Informer.this,UpdateItem.class);
                intent1.putExtra("unit_code",uncode);
                intent1.putExtra("item_name",itmname);
                intent1.putExtra("item_price",itmprice);
                intent1.putExtra("item_discription",itmdis);
                intent1.putExtra("item_on_off",itmav);
                startActivity(intent1);


            }
        });

        deleteButton = (Button) findViewById(R.id.delete_button_informer);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog = deleteActionConfirm();
                dialog.show();

            }
        });


    }

    private AlertDialog deleteActionConfirm(){

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Remove Item")
                .setMessage("Do you want to remove this item?")
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ShoppingListDBHelper db = new ShoppingListDBHelper(getApplicationContext());
                        db.deleteData(code.getText().toString());
                        finish();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                })
                .create();

        return alertDialog;
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
            Intent intent = new Intent(Informer.this,ShowAllCustomers.class);
            startActivity(intent);
        }

        if(id == R.id.exit_top){

            Toast.makeText(this,"Back To Main",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Informer.this,MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
