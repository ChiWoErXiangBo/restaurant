package com.example.admin.graduation_design;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private MenuSQLiteOpenHelper mOpenHelper;

    /**
 * ATTENTION: This was auto-generated to implement the App Indexing API.
 * See https://g.co/AppIndexing/AndroidStudio for more information.
 */ private GoogleApiClient client;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout_xml);
        mOpenHelper = new MenuSQLiteOpenHelper(this, "menu.db", null, 1);
        SQLiteDatabase db =mOpenHelper.getWritableDatabase();
        Button customer = (Button)findViewById(R.id.customer);
        Button cook = (Button)findViewById(R.id.cook);
        Button admin = (Button)findViewById(R.id.admin);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);

                startActivity(intent);
            }
        });
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,LoginCookActivity.class);

                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginAdminActivity.class);

                startActivity(intent);
            }
        });



            }


}
