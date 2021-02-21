package com.example.neopixel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;



import static com.example.neopixel.MainActivity.ColorNumber;
import static com.example.neopixel.MainActivity.ColorState;
import static com.example.neopixel.MainActivity.ColorStore;
import static com.example.neopixel.MainActivity.db_name;
import static com.example.neopixel.MainActivity.decodeColor;
import static com.example.neopixel.MainActivity.tb_name;


public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;
    android.database.Cursor c;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name + "(LED VARCHAR(125),NAME VARCHAR(32))";
        db.execSQL(createTable);
    }



    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

       public void LoadLED(View v) {
        c=db.rawQuery("SELECT * FROM " + tb_name,null);
        EditText edit = findViewById(R.id.editText2);
        boolean finded = false;
        c.moveToFirst();
        String Load = "";
        String LoadName = "";
        if (c.getCount()==0);
        else {
            do {
                LoadName = c.getString(1);
                if (!LoadName.equals(edit.getText().toString())) ;
                else {
                    Load = c.getString(0);
                    for (int i = 0; i < 121; i++) {
                        ColorState[i] = String.valueOf(Load.charAt(i));
                        decodeColor(ColorState[i]);
                        ColorStore[i] = ColorNumber;
                    }
                    finded = true;
                }
            } while (c.moveToNext() && finded != true);
        }

        finish();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
