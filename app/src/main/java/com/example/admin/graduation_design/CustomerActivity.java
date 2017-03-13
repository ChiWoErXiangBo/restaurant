package com.example.admin.graduation_design;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by admin on 2016/10/25.
 */
public class CustomerActivity extends Activity {
    private static final String m[]={"1号","2号","3号","4号","5号"};
    private MenuSQLiteOpenHelper mOpenHelper;
    Cursor model = null;
    int init = 0;
    String menu = "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */ private GoogleApiClient client;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenHelper = new MenuSQLiteOpenHelper(this, "menu.db", null, 1);
        SQLiteDatabase db =mOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("dish", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int number = cursor.getInt(cursor.getColumnIndex("D_NUMBER"));
                final String name = cursor.getString(cursor.getColumnIndex("D_NAME"));
                final String price = cursor.getString(cursor.getColumnIndex("D_PRICE"));
                LinearLayout menuLayout = (LinearLayout)findViewById(R.id.menu);
                CheckBox checkbox = new CheckBox(this);
                checkbox.setText(name + " " + price);
                checkbox.setId(number);
                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) {
                        TextView showPrice = (TextView) findViewById(R.id.price);
                        int num = Integer.parseInt(price);
                        if (ischecked) {
                            init += num;

                            String a = String.valueOf(init);
                            showPrice.setText(a);
                            menu= menu+"/"+name;
                        } else {
                            if (init > 0) {
                                init -= num;
                                String b = String.valueOf(init);
                                showPrice.setText(b);
                                menu = menu.substring(1,menu.length()-name.length());
                            }
                        }

                    }

                });

                LinearLayout.LayoutParams lp1 = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT);

                menuLayout.addView(checkbox, lp1);
                Log.d("MainActivity", " number is " + number);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
//        Button createDatabase = (Button) findViewById(R.id.button2);
//        createDatabase.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                mOpenHelper.getWritableDatabase();
//            }
//        });
        final String table;
        Spinner spinner = (Spinner)findViewById(R.id.table);
        final TextView textView = (TextView)findViewById(R.id.tablenumber);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(m[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button addData = (Button) findViewById(R.id.button);
        final int i =3;
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                TextView textView = (TextView)findViewById(R.id.price);
                TextView textView2 = (TextView)findViewById(R.id.tablenumber);
                String table = textView2.getText().toString();
                String price = textView.getText().toString();
                // 开始组装第一条数据
                // values.put("O_NUMBER", i);
                values.put("O_TABLE",table);
                values.put("O_NAME",menu);
                values.put("O_PRICE", price);
                db.insert("orders", null, values); // 插入第一条数据
                values.clear();


                // 开始组装第二条数据
//                values.put("D_NUMBER", 2);
//                values.put("D_NAME", "VEGETABLE");
//                values.put("D_PRICE", "10");
                // db.insert("dish", null, values); // 插入第二条数据
                //EditText editText = (EditText)findViewById(R.id.table);
                //TextView textView = (TextView)findViewById(R.id.price);
//                CheckBox checkBox1 = (CheckBox)findViewById('1');
//                CheckBox checkBox2 = (CheckBox)findViewById('2');
                // String dish = "";
//                if (checkBox1.isChecked()){
//                    dish+=checkBox1.getText().toString();
//                }
//                if (checkBox2.isChecked()){
//                    dish+=checkBox2.getText().toString();
//                }
                //
                // String table = editText.getText().toString();
                Intent intent = new Intent(CustomerActivity.this, SecondActivity.class);

                startActivity(intent);

            }
        });

    }






    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicksks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
