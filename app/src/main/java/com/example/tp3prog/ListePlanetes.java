package com.example.tp3prog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListePlanetes extends AppCompatActivity {


    ListView listview;
    PlaneteAdapter adapter;
    private ArrayList<String> planetes;
    Button btnVerifier=null;
    CheckBox checkbox=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data d=new Data();
        listview = (ListView) findViewById(R.id.listView);
        adapter = new PlaneteAdapter(d.planetes, getApplicationContext());
        listview.setAdapter(adapter);

        btnVerifier=findViewById(R.id.btnVerifier);
        btnVerifier.setEnabled(false);





        btnVerifier.setOnClickListener(verifPlanetes);
    }



    private View.OnClickListener verifPlanetes = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


        }
    };






}
