package com.moncefadj.medcare.PatientSearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moncefadj.medcare.R;

import java.util.ArrayList;
import java.util.List;

public class Liste extends AppCompatActivity {
    ListView listView;
    CustomAdapter customAdapter;
    int images[]={R.drawable.ic_baseline_person,R.drawable.ic_baseline_person,R.drawable.ic_baseline_person ,R.drawable.ic_baseline_person};
    String names[]={"Dr.nom","Dr.nom,","Dr.nom,","Dr.nom,"};
    List<itemmodel> list=new ArrayList<>();
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        listView=findViewById(R.id.listvw);
        imageButton=findViewById(R.id.imm);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
            }
        });
        for(int i=0;i<names.length;i++){
            itemmodel itemm=new itemmodel(names[i],images[i]);
            list.add(itemm);
        }
        customAdapter=new CustomAdapter(list,this);
        listView.setAdapter(customAdapter);
    }

    public class CustomAdapter extends BaseAdapter {
        private List<itemmodel> itemmodellist1;
        private List<itemmodel> itemmodellistfile;
        private Context context;

        public CustomAdapter(List<itemmodel> itemmodellist1, Context context) {
            this.itemmodellist1 = itemmodellist1;
            this.itemmodellistfile=itemmodellist1;
            this.context = context;
        }

        @Override
        public int getCount(){
            return itemmodellistfile.size(); }

        @Override
        public Object getItem(int position){
            return null; }

        @Override
        public long getItemId(int position){
            return position ; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view=getLayoutInflater().inflate(R.layout.row,null);
            ImageView imageView=view.findViewById(R.id.imaggeview);
            TextView textView=view.findViewById(R.id.textname);
            imageView.setImageResource(itemmodellistfile.get(position).getImge());
            textView.setText(itemmodellistfile.get(position).getName());
            return view;
        }

    }

}