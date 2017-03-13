package com.example.admin.graduation_design;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 2016/10/25.
 */
public class LoginCookActivity extends Activity {
    private MenuSQLiteOpenHelper mOpenHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cook_layout);
        mOpenHelper = new MenuSQLiteOpenHelper(this, "menu.db", null, 1);

        final EditText editText1 = (EditText)findViewById(R.id.userId);
        final EditText editText2 = (EditText)findViewById(R.id.password);
        Button button =(Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db =mOpenHelper.getWritableDatabase();
               String id =  editText1.getText().toString();
                String password = editText2.getText().toString();
                Cursor cursor = db.query("cookid", null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do{
                        String userid = cursor.getString(cursor.getColumnIndex("C_ID"));
                        String passWord = cursor.getString(cursor.getColumnIndex("C_PASSWORD"));
                        if(id.equals(userid)&&password.equals(passWord)){
                            Intent intent = new Intent(LoginCookActivity.this, cookActivity.class);
                            startActivity(intent);
                        }
                    }while (cursor.moveToNext());
                }

            }
        });
    }
}
