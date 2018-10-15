package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userName, password;
    private int counter = 3;
    private Button signIn;
    private TextView registerLink;
    private String uID, uname, pass;
    ShoppingListDBHelper helper;
    public static SharedPreferences loginPref;
    public static SharedPreferences currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*
        loginPref = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        SellerUserContract.loginStatus = loginPref.getBoolean("loginStatus",false);

        currentUser = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        SellerUserContract.userID = loginPref.getString("loginStatus",null);

        System.out.println(SellerUserContract.loginStatus);
*/
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password_text);
        signIn = (Button) findViewById(R.id.login_button);
        registerLink = (TextView) findViewById(R.id.register_link);
        helper = new ShoppingListDBHelper(this);


/*
        if(SellerUserContract.loginStatus){
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, AddItems.class);
            startActivity(intent);
            finish();
        }


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validateUser(userName.getText().toString(), password.getText().toString());


            }
        });
*/

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uname = userName.getText().toString();
                pass = password.getText().toString();
                Boolean check = helper.isUserLogged(uname,pass);

                if(check == true){

                    Toast.makeText(getApplicationContext(),"Hello "+uname+"!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Start Your Sales Here!",Toast.LENGTH_SHORT).show();
                    userName.setText("");
                    password.setText("");
                    Intent intent = new Intent(LoginActivity.this, AddItems.class);
                    intent.putExtra("user_name",uname);
                    startActivity(intent);

                }
                else {

                    Toast.makeText(getApplicationContext(),"Not A Valid User, Please Try Again Or You Can Register As Seller",Toast.LENGTH_LONG).show();;
                }
            }
        });


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });




    }
/*
    public static void setLoginPref(String key, Boolean status){

        SharedPreferences.Editor editor = loginPref.edit();
        SellerUserContract.loginStatus = status;
        editor.putBoolean(key, status);
        editor.apply();

    }


    public static void setCurrentUser(String key, String userID){

        SharedPreferences.Editor editor = currentUser.edit();
        SellerUserContract.userID = userID;
        editor.putString(key, userID);
        editor.apply();

    }


    private void validateUser(String username, String password){

        ShoppingListDBHelper dbHelper = new ShoppingListDBHelper(getApplicationContext());

        if(dbHelper.isUserLogged(username, password)){

            setLoginPref("loginStatus", true);


            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, AddItems.class);
            startActivity(intent);
            finish();


        }else{
            counter --;
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();

            if(counter == 0){
                signIn.setEnabled(false);
                Toast.makeText(this, "Login Blocked!", Toast.LENGTH_SHORT).show();
            }
        }
    }
*/
}
