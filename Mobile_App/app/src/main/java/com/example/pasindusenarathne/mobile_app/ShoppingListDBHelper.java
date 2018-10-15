package com.example.pasindusenarathne.mobile_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Pasindu Senarathne on 9/19/2018.
 */

public class ShoppingListDBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "shoppingAppDB";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_USER1 = "CREATE TABLE " + SellerUserContract.UserEntry.TABLE_NAME +
            "("+ SellerUserContract.UserEntry.USER_ID + " text, "
            + SellerUserContract.UserEntry.USERNAME + " text, "
            + SellerUserContract.UserEntry.PASSWORD + " text, "
            + SellerUserContract.UserEntry.EMAIL + " text);";

    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " +SellerUserContract.UserEntry.TABLE_NAME;


    public static final String TABLE1_NAME = "shoppingItems";
    public static final String T1COL1 = "unitCode";
    public static final String T1COL2 = "name";
    public static final String T1COL3 = "seller";
    public static final String T1COL4 = "discription";
    public static final String T1COL5 = "price";
    public static final String T1COL6 = "availability";


    public static final String TABLE2_NAME = "orders";
    public static final String T2COL1 = "orderID";
    public static final String T2COL2 = "itemUnitCode";
    public static final String T2COL3 = "customer";
    public static final String T2COL4 = "quantity";
    public static final String T2COL5 = "date";
    public static final String T2COL6 = "seller";
    public static final String T2COL7 = "total_payment";

    public static final String TABLE3_NAME = "messages";
    public static final String T3COL1 = "message";
    public static final String T3COL2 = "date";
    public static final String T3COL3 = "customer";



    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE1_NAME +
            "("+ ItemsModel.ItemEntry.T1COL1 + " text primary key, "
            + ItemsModel.ItemEntry.T1COL2 + " text, "
            + ItemsModel.ItemEntry.T1COL3+ " text, "
            + ItemsModel.ItemEntry.T1COL4 + " text, "
            + ItemsModel.ItemEntry.T1COL5+ " text, "
            + ItemsModel.ItemEntry.T1COL6 + " text);";

    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE2_NAME +
            "("+ T2COL1 + " text primary key, "
            + T2COL2 + " text, "
            + T2COL3+ " text, "
            + T2COL4 + " text, "
            + T2COL5+ " text,"
            + T2COL6 + " text,"
            + T2COL7 + " text);";

    public static final String CREATE_TABLE_MESSAGES = "CREATE TABLE " + TABLE3_NAME +
            "("+ T3COL1 + " text, "
            + T3COL2 + " text, "
            + T3COL3+ " text);";

    public ShoppingListDBHelper(Context context) {

        super(context, DATABASE_NAME, null,1);
        Log.e("DATABASE OPERATIONS","Databse Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER1);
        sqLiteDatabase.execSQL(CREATE_TABLE_ORDERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_MESSAGES);

        Log.e("DATABASE OPERATIONS","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE3_NAME);
        sqLiteDatabase.execSQL(DROP_TABLE_USER);
        onCreate(sqLiteDatabase);
        onCreate(sqLiteDatabase);

    }

    public void insertData(String uCode, String name, String dis, String price, String av,String seller, SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL1,uCode);
        contentValues.put(T1COL2,name);
        contentValues.put(T1COL3,seller);
        contentValues.put(T1COL4,dis);
        contentValues.put(T1COL5,price);
        contentValues.put(T1COL6,av);


        long result = database.insert( TABLE1_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","Data Inserted");

    }

    public void insertDataOrders(String orderCode, String uCode, String customer, String qty, String date,String seller,String total, SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put(T2COL1,orderCode);
        contentValues.put(T2COL2,uCode);
        contentValues.put(T2COL3,customer);
        contentValues.put(T2COL4,qty);
        contentValues.put(T2COL5,date);
        contentValues.put(T2COL6,seller);
        contentValues.put(T2COL7,total);


        long result = database.insert( TABLE2_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","Order Inserted");

    }

    public void insertMessage(String msg, String date, String cus, SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put(T3COL1,msg);
        contentValues.put(T3COL2,date);
        contentValues.put(T3COL3,cus);

        long result = database.insert( TABLE3_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","Data Inserted");

    }


    public ArrayList<ItemsModel> getAllItems(String user){

        ArrayList<ItemsModel> list = new ArrayList<>();
        String sql = "SELECT * FROM shoppingItems WHERE seller = '"+ user +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            do {

                ItemsModel itemsModel = new ItemsModel();
                itemsModel.setUnitCode(cursor.getString(0));
                itemsModel.setItemname(cursor.getString(1));
                itemsModel.setSeller(cursor.getString(2));
                itemsModel.setDiscription(cursor.getString(3));
                itemsModel.setPrice(cursor.getString(4));
                itemsModel.setAvailable(cursor.getString(5));
                list.add(itemsModel);

            }while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<OrderModel> getAllOrders(String user){

        ArrayList<OrderModel> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE seller = '"+ user +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            do {

                OrderModel orderModel = new OrderModel();
                orderModel.setUserID(cursor.getString(0));
                orderModel.setUnitCode(cursor.getString(1));
                orderModel.setCustomer(cursor.getString(2));
                orderModel.setQuantity(cursor.getString(3));
                orderModel.setDate(cursor.getString(4));
                orderModel.setSeller(cursor.getString(5));
                orderModel.setTotal(cursor.getString(6));
                list.add(orderModel);

            }while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<ItemsModel> getAllItemsCustomers(){

        ArrayList<ItemsModel> list = new ArrayList<>();
        String sql = "select * from " + TABLE1_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            do {

                ItemsModel itemsModel = new ItemsModel();
                itemsModel.setUnitCode(cursor.getString(0));
                itemsModel.setItemname(cursor.getString(1));
                itemsModel.setSeller(cursor.getString(2));
                itemsModel.setDiscription(cursor.getString(3));
                itemsModel.setPrice(cursor.getString(4));
                itemsModel.setAvailable(cursor.getString(5));
                list.add(itemsModel);

            }while (cursor.moveToNext());
        }

        return list;
    }

    public void updateData(String unitcode,String name,String price,String discription,String yesNo){

        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL1,unitcode);
        contentValues.put(T1COL2,name);
        contentValues.put(T1COL4,discription);
        contentValues.put(T1COL5,price);
        contentValues.put(T1COL6,yesNo);

        SQLiteDatabase database = this.getWritableDatabase();
        /*database.query(true, TABLE1_NAME,
                new String[] {T1COL1,T1COL2,T1COL4,T1COL5,T1COL5,T1COL6},
                T1COL1 + " LIKE ?",
                new String[] { "%" + unitcode + "%" },
                null, null, null, null);*/

        database.update(TABLE1_NAME,contentValues,"unitCode = ?",new String[]{unitcode});
        database.close();

        Log.e("DATABASE OPERATIONS","Table Updated");
    }

    public void deleteData(String code){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE1_NAME, "unitCode = ?",new String[]{code});
        db.close();

    }

    public void deleteOrder(String code){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE2_NAME, "orderID = ?",new String[]{code});
        db.close();

    }

    public ArrayList<String> getUserIDs(SQLiteDatabase database){

        ArrayList<String> idList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT userID FROM user", null);

        while(cursor.moveToNext()){
            idList.add(cursor.getString(cursor.getColumnIndex(SellerUserContract.UserEntry.USER_ID)));
        }

        return idList;
    }


    public ArrayList<String> getOrderIDs(SQLiteDatabase database){

        ArrayList<String> idList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT orderID FROM orders", null);

        while(cursor.moveToNext()){
            idList.add(cursor.getString(cursor.getColumnIndex(T2COL1)));
        }

        return idList;
    }


    public void userSignup(SellerUserContract user, SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SellerUserContract.UserEntry.USER_ID, user.getUserID());
        contentValues.put(SellerUserContract.UserEntry.USERNAME,user.getUserName());
        contentValues.put(SellerUserContract.UserEntry.PASSWORD, user.getPassword());
        contentValues.put(SellerUserContract.UserEntry.EMAIL, user.getEmail());

        database.insert(SellerUserContract.UserEntry.TABLE_NAME, null, contentValues);
        Log.d("DB operation","One row inserted to user...");


    }


    public SellerUserContract getUserByID(){


        return null;
    }


    public Cursor getUsers(SQLiteDatabase database){

        Cursor cursor = database.rawQuery("SELECT * FROM user", null);

        return cursor;

    }


    public boolean isUserLogged(String username, String password){

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM user WHERE username = '"+ username +"' AND password = '"+ password +"'",
                null);


        if(cursor.getCount() > 0){

            return true;
        }

        return false;

    }

    public Boolean ckeckmail(String userName){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username = '"+ userName +"'",null);
        if(cursor.getCount() > 0){
            return  false;
        }
        else{
            return true;
        }
    }


}
