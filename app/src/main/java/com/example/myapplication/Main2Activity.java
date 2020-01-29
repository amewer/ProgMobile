package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //settings = getSharedPreferences(MainActivity.CYCLEVIEPREFS, Context.MODE_PRIVATE);

        TextView editText = (TextView) findViewById(R.id.textView1);

        String str = (String) getIntent().getSerializableExtra("string");
       // affichage.setText(settings.getString("username", ""));

        editText.setText("Bonjour "+str);

    }
}
