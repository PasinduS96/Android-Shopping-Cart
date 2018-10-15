package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText username, password, email;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.user_name_register);
        password = (EditText) findViewById(R.id.password_text_register);
        email = (EditText) findViewById(R.id.email_address);

        signup = (Button) findViewById(R.id.register_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SellerUserContract user = new SellerUserContract();

                user.setUserID(generateUserID());
                user.setPassword(password.getText().toString());
                user.setUserName(username.getText().toString());
                user.setEmail(email.getText().toString());



                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){

                    Toast.makeText(getApplication(), "User Name And Password Can Not Be Empty", Toast.LENGTH_LONG).show();

                }
                else {


                    ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dbHelper.userSignup(user, database);
                    dbHelper.close();

                    Toast.makeText(getApplication(), "Signed Up. Please Login", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    email.setText("");
                    Intent intent = new Intent(Register.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        });
    }

    public String generateUserID(){

        ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ArrayList<String> idList = new ArrayList<>();
        idList = dbHelper.getUserIDs(database);

        String id;
        int next = idList.size();
        next++;
        id = "U10" + next;
        if (idList.contains(id)) {
            next++;
            id = "C100" + next;
        }

        return id;

    }
}
