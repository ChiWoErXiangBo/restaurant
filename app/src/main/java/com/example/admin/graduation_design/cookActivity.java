package com.example.admin.graduation_design;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by admin on 2016/10/19.
 */
public class cookActivity extends Activity {
    private MenuSQLiteOpenHelper mOpenHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_layout);
        mOpenHelper = new MenuSQLiteOpenHelper(this, "menu.db", null, 1);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("orders", null, null, null, null, null, null);
        int j = 0;
        if (cursor.moveToFirst()) {
            do {
                ;
                j++;
            } while (cursor.moveToNext());
        }
        String[] container = new String[j];
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                int number = cursor.getInt(cursor.getColumnIndex("O_NUMBER"));
                String table = cursor.getString(cursor.getColumnIndex("O_TABLE"));
                String name = cursor.getString(cursor.getColumnIndex("O_NAME"));
                String price = cursor.getString(cursor.getColumnIndex("O_PRICE"));
                LinearLayout menuLayout = (LinearLayout) findViewById(R.id.main);
                container[i] = "编号：" + String.valueOf(number) + "桌号：" + table + "菜肴:" + name;
                i++;
            } while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    cookActivity.this, android.R.layout.simple_list_item_1, container);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);
        }
    }
}

