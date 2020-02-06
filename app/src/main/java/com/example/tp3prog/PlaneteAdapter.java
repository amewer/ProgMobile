package com.example.tp3prog;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class PlaneteAdapter extends BaseAdapter {

    private final Context context;
    ArrayList<String> planetes;
    Data data=new Data();




    PlaneteAdapter(ArrayList<String> planetes, Context c){
        this.planetes=planetes;
        this.context = c;



    }

    @Override
    public int getCount() {
        return planetes.size();
    }

    @Override
    public Object getItem(int arg0) {
        return planetes.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                     this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listitem, null);
        }
        TextView nomPlanete = (TextView) itemView.findViewById(R.id.textView);
        final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

        nomPlanete.setText(planetes.get(position));




        final ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, data.taillePlanetes);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox = (CheckBox) compoundButton.findViewById(R.id.checkbox);
                spinner.setEnabled(!checkBox.isChecked());
                spinadapter.notifyDataSetChanged();

                ViewGroup listview= (ViewGroup) checkBox.getParent().getParent();

                boolean allChecked = true;
                boolean isRight=true;

                for(int i=0;i<9;i++) {
                    ViewGroup x = (ViewGroup) listview.getChildAt(i);
                    CheckBox c = x.findViewById(R.id.checkbox);
                    Spinner spn=x.findViewById(R.id.spinner);

                    if(!c.isChecked()){
                        allChecked= false;
                        break;
                    }
                    String itemSpinner = spn.getSelectedItem().toString();
                    if(itemSpinner!=data.taillePlanetes[i]) {
                        isRight=false;

                    }




                }
                if(allChecked) {
                    ViewGroup root = (ViewGroup) checkBox.getParent().getParent().getParent();
                    Button btnVerif=root.findViewById(R.id.btnVerifier);
                    btnVerif.setEnabled(true);

                    if(isRight){
                        btnVerif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(context, "Bravo !", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    else{
                        btnVerif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(context, "Il y a des erreurs !", Toast.LENGTH_SHORT).show();


                            }
                        });

                    }


                }
            }
        });

        return itemView;
    }



}