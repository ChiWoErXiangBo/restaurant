package com.example.admin.graduation_design;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by admin on 2016/10/17.
 */
public class MenuSQLiteOpenHelper extends SQLiteOpenHelper {


    private Context mContext;

    public MenuSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
//        String sql1 = "create table dish (\n" +
//                "   D_NUMBER INTEGER PRIMARY KEY autoincrement,   \n" +
//                "   D_NAME VARCHAR(20),   \n" +
//                "   D_PRICE VARCHAR(10)\n" +
//                ");";
//     db.execSQL(sql1);
        String sql2 = "CREATE TABLE orders(\n" +
                "    O_NUMBER INTEGER PRIMARY KEY autoincrement,\n" +
                "    O_TABLE VARCHAR(20),\n" +
                "    O_NAME VARCHAR(50),\n" +
                "    O_PRICE VARCHAR(20)\n" +
                ");";
        db.execSQL(sql2);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion ==1 && newVersion==2){

        }

    }
//    public Cursor getAll(String where,String orderBy){
//        StringBuilder buf = new StringBuilder("SELECT D_NUMBER,D_NAME,D_PRICE FROM dish");
//        if (where!=null){
//            buf.append("WHERE");
//            buf.append("where");
//        }
//        if(orderBy!=null){
//            buf.append("ORDERBY");
//            buf.append("orderby");
//        }
//        return (getReadableDatabase().rawQuery(buf.toString(),null));
//    }
//
//    public Cursor getByNumber(String number){
//        String[] args = {number};
//        return (getReadableDatabase().rawQuery("SELECT D_NUMBER,D_NAME,D_PRICE FROM dish WHERE D_NUMBER =?",args));
//    }
//
//    public void insert(String name,String price){
//        ContentValues  cv = new ContentValues();
//        cv.put("D_NAME",name);
//        cv.put("D_PRICE",price);
//        getWritableDatabase().insert("dish","D_NAME",cv);
//    }
//
//    public void update(String number,String name,String price){
//        ContentValues cv = new ContentValues();
//        String[] args = {number};
//        cv.put("D_NAME",name);
//        cv.put("D_PRICE",price);
//        getWritableDatabase().update("dish",cv,"D_NUMBER=?",args);
//    }
//
//    public  String getNumber(Cursor c){
//        return (c.getString(1));
//    }
//    public String getName (Cursor c){
//        return (c.getString(2));
//
//    }
//    public  String getPrice(Cursor c){
//        return (c.getString(3));
//    }

}



//}
