package com.example.pasindusenarathne.mobile_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class ManageOrders extends AppCompatActivity {

    private TextView id,code,customer,dateorder,totalpay,seller,qty;
    private String oid,ucode,ocus,odate,ototal,oseller,oqty;
    private Button msgButton,deleteButton;
    ShoppingListDBHelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_orders);

        id = (TextView) findViewById(R.id.order_id_view);
        code = (TextView) findViewById(R.id.unit_code_view);
        customer = (TextView) findViewById(R.id.customer_name_view);
        dateorder = (TextView) findViewById(R.id.order_date_view);
        totalpay = (TextView) findViewById(R.id.total_pay_view);
        seller = (TextView) findViewById(R.id.seller_view);
        qty = (TextView) findViewById(R.id.order_qty_view);


        Intent intent = getIntent();
        if(intent != null){

            oid = intent.getStringExtra("orderid");
            ucode = intent.getStringExtra("unitcode");
            ocus = intent.getStringExtra("customer");
            odate = intent.getStringExtra("date");
            ototal = intent.getStringExtra("total");
            oseller = intent.getStringExtra("seller");
            oqty = intent.getStringExtra("qty");

        }


        id.setText(oid);
        code.setText(ucode);
        customer.setText(ocus);
        dateorder.setText(odate);
        totalpay.setText(ototal);
        seller.setText(oseller);
        qty.setText(oqty);

        deleteButton = (Button) findViewById(R.id.delete_button_view);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog = deleteActionConfirm();
                dialog.show();

            }
        });

        msgButton = (Button) findViewById(R.id.msg_button_view);

        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "Hello Customer! Your order has been confirmed by " +seller.getText().toString()+ ". Our agent will be contact you soon. You have to pay " +totalpay.getText().toString()+" amount." +
                        " Delivery methods and other information will be infrom by our agents as soon as posible. Thank You!";

                String date = DateFormat.getDateTimeInstance().format(new Date());

                String cus = customer.getText().toString();

                ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                dbHelper.insertMessage(message,date,cus,database);
                Toast.makeText(getApplication(), "Order Confirmed,Confirmation Message Sucessfully Send To Customer", Toast.LENGTH_SHORT).show();
                dbHelper.close();
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
                        db.deleteOrder(id.getText().toString());
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
}