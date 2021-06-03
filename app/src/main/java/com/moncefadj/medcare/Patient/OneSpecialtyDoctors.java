package com.moncefadj.medcare.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moncefadj.medcare.R;

public class OneSpecialtyDoctors extends AppCompatActivity {

    //vertical view
    ListView list;
    String[] titles;
    String[] description;
    int[] imgs={R.drawable.doc12,R.drawable.doc12,R.drawable.doc12,R.drawable.doc12
            ,R.drawable.doc12,R.drawable.doc12,R.drawable.doc12
            ,R.drawable.doc12,R.drawable.doc12,R.drawable.doc12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_one_specialty_doctors);
        //vertical view
        Resources res=getResources();
        titles =res.getStringArray(R.array.titles);
        description=res.getStringArray(R.array.description);
        list=(ListView) findViewById(R.id.list1);
        Myadapter adapter=new Myadapter(this,titles,imgs,description);
        list.setAdapter(adapter);


    }
    class Myadapter extends ArrayAdapter<String> {
        Context context;
        int[] imgs;
        String mytitles[];
        String mydescription[];

        Myadapter(Context c, String[] titles, int[] imgs, String[] description) {
            super(c, R.layout.verow, R.id.text1, titles);
            this.context = c;
            this.imgs = imgs;
            this.mydescription = description;
            this.mytitles = titles;
        }

        @Override
        public int getCount(){
            return imgs.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View verow=layoutInflater.inflate(R.layout.verow,parent,false);
            ImageView images;
            images= verow.findViewById(R.id.icon);
            TextView mytitle;
            mytitle=verow.findViewById(R.id.text1);
            TextView mydescription;
            mydescription=verow.findViewById(R.id.text2);
            images.setImageResource(imgs[position]);
            mytitle.setText(titles[position]);
            mydescription.setText(description[position]);
            return verow;
        }
    }
}