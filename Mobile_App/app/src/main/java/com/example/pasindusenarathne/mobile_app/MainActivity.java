package com.example.pasindusenarathne.mobile_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ViewPager view;
    ViewPagerAdapter adapter;

    ShoppingListDBHelper shopDB;

    static String JSON_STRING;

    Button button1,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view = (ViewPager) findViewById(R.id.viewPager);
        //adapter = new ViewPagerAdapter(this);
        //view.setAdapter(adapter);

        //Button b = findViewById(R.id.button2);
        //b.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {

               // new BackgroundTask().execute();
           // }
       // });

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,ShowAllCustomers.class);
                startActivity(intent);
            }
        });
    }



/*

    public void getJSON(View v) {

        new BackgroundTask().execute();
    }

    class  BackgroundTask extends AsyncTask<Void,Void,String>{

        String json_url;

        @Override
        protected void onPreExecute() {

            json_url = "http://localhost/synctest/jason_get_data.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine())!= null){

                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {

            TextView textView = (TextView) findViewById(R.id.text1);
            textView.setText(s);
        }
    }
*/




}
