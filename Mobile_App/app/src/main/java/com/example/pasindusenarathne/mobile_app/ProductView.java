package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductView extends AppCompatActivity {

    private TextView quantity_text;
    private Button inc_button;
    private Button dec_button,cart_btn;
    private int counter;
    TextView title,disPara,price_txt,product_code,cost_view,seller_view;
    String uncode1,itmname1,itmprice1,itmdis1,itmav1,itmseller1;
    private double total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        quantity_text = (TextView) findViewById(R.id.quantity_text_view);
        cost_view = (TextView) findViewById(R.id.cost_text_view);
        inc_button = (Button) findViewById(R.id.increment_button);
        dec_button = (Button) findViewById(R.id.decrement_button);
        inc_button.setOnClickListener(clickListener);
        dec_button.setOnClickListener(clickListener);
        cart_btn = (Button)  findViewById(R.id.cart_button);

        title = (TextView) findViewById(R.id.description);
        disPara = (TextView) findViewById(R.id.discriptionOfItem);
        price_txt = (TextView) findViewById(R.id.priceOfItem);
        product_code = (TextView) findViewById(R.id.codeOfItem);
        seller_view = (TextView) findViewById(R.id.sellerOfItem);

        Intent intent = getIntent();
        if(intent != null){

            uncode1 = intent.getStringExtra("UnitCode");
            itmname1 = intent.getStringExtra("ItemName");
            itmprice1 = intent.getStringExtra("Price");
            itmdis1 = intent.getStringExtra("Discription");
            itmav1 = intent.getStringExtra("Available");
            itmseller1 = intent.getStringExtra("Seller");

        }

        title.setText(itmname1);
        disPara.setText(itmdis1);
        price_txt.setText(itmprice1);
        product_code.setText(uncode1);
        seller_view.setText(itmseller1);


        initCounter();


        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String unitCode = product_code.getText().toString();

                OrderModel order = new OrderModel();

                String order_id = generateOrderID();
                String unit_code = product_code.getText().toString();
                String customer = "Customer Default";
                String qty = quantity_text.getText().toString();
                String dateOrdered = DateFormat.getDateTimeInstance().format(new Date());
                String seller = seller_view.getText().toString();

                Double toyal_pay = total;
                String total1 = Double.toString(toyal_pay);


                ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                dbHelper.insertDataOrders(order_id,unit_code,customer,qty,dateOrdered,seller,total1,database);
                Toast.makeText(getApplication(), "Order Successfully Completed", Toast.LENGTH_SHORT).show();
                dbHelper.close();
            }
        });
    }




    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.decrement_button:

                    decrementValue();
                    break;

                case R.id.increment_button:

                    incrementValue();
                    break;
            }

        }
    };

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
            Intent intent = new Intent(ProductView.this,ShowAllCustomers.class);
            startActivity(intent);
        }

        if(id == R.id.exit_top){

            Toast.makeText(this,"Back To Main",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductView.this,MainActivity.class);
            startActivity(intent);
        }

        return true;
    }




    private void initCounter(){

        counter = 0;
        quantity_text.setText(counter + "");
    }

    private void incrementValue(){

        counter++;
        quantity_text.setText(Integer.toString(counter));
        total = calcTotal(price_txt.getText().toString(),quantity_text.getText().toString());

        cost_view.setText("Rs."+Double.toString(total));
    }

    private void decrementValue(){

        counter--;
        quantity_text.setText(Integer.toString(counter));

        total = calcTotal(price_txt.getText().toString(),quantity_text.getText().toString());

        cost_view.setText("Rs."+Double.toString(total));
    }

    private double calcTotal(String qty,String price){

        qty = quantity_text.getText().toString();
        int quantity = Integer.parseInt(qty);

        price = price_txt.getText().toString();
        double priceItem = Double.parseDouble(price);


        double total = (quantity * priceItem);

        return total;


    }

    public String generateOrderID(){

        ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ArrayList<String> idList = new ArrayList<>();
        idList = dbHelper.getOrderIDs(database);

        String id;
        int next = idList.size();
        next++;
        id = "O10" + next;
        if (idList.contains(id)) {
            next++;
            id = "O100" + next;
        }

        return id;

    }


}
