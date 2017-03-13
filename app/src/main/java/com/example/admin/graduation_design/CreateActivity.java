package com.example.admin.graduation_design;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/19.
 */
public class CreateActivity extends Activity {
    private MenuSQLiteOpenHelper mOpenHelper;
    ArrayList arraylist = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);
        mOpenHelper = new MenuSQLiteOpenHelper(this, "menu.db", null, 1);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("dish", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final int number = cursor.getInt(cursor.getColumnIndex("D_NUMBER"));
                final String name = cursor.getString(cursor.getColumnIndex("D_NAME"));
                final String price = cursor.getString(cursor.getColumnIndex("D_PRICE"));
                LinearLayout menuLayout = (LinearLayout)findViewById(R.id.menu);
                CheckBox checkbox = new CheckBox(this);
                checkbox.setText(name + "  " + price);
                checkbox.setId(number);
                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) {
                        TextView showPrice = (TextView) findViewById(R.id.price);
                        int num = Integer.parseInt(price);
                        if (ischecked) {
                            arraylist.add(number);
                            TextView textView = (TextView)findViewById(R.id.number);
                            textView.setText(arraylist.get(0).toString());
                        } else {
                            for(int j = 0;j<arraylist.size();j++){
                                if(arraylist.get(j).equals(num)){
                                    arraylist.remove(j);
                                }
                            }
                        }

                    }

                });

                LinearLayout.LayoutParams lp1 = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT);

                menuLayout.addView(checkbox, lp1);

            } while (cursor.moveToNext());
        }
        Button delete = (Button)findViewById(R.id.delete);
        Button updata = (Button)findViewById(R.id.update);
        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                for (int i = 0;i<arraylist.size();i++){

                        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                        String sql = "DELETE FROM `dish` WHERE D_NUMBER = "+String.valueOf(arraylist.get(i));
                        db.execSQL(sql);

                }
            }
        });
        Button button = (Button)findViewById(R.id.create);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                EditText name = (EditText)findViewById(R.id.name);
                EditText price = (EditText)findViewById(R.id.price);
                String Name = name.getText().toString();
                String Price = price.getText().toString();
                ContentValues values = new ContentValues();
                values.put("D_NAME",Name);
                values.put("D_PRICE",Price);
                db.insert("dish", null, values); // 插入第一条数据
                values.clear();

            }


        });
    }

}