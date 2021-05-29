package com.moncefadj.medcare.PatientHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;



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
        setContentView(R.layout.activity_home);
        //vertical view
        Resources res=getResources();
        titles =res.getStringArray(R.array.titles);
        description=res.getStringArray(R.array.description);
        list=(ListView) findViewById(R.id.list1);
        Myadapter adapter=new Myadapter(this,titles,imgs,description);
        list.setAdapter(adapter);











        //assign varible
        recyclerView=findViewById(R.id.recycler_view);

        //creat integer array
        Integer[] categorieLogo={R.drawable.eye1,R.drawable.heart3
                ,R.drawable.lung2,R.drawable.tooth};

        //creat string array
        String[] categorieName={"Ophtalmomogie","Cardiologie"
                ,"Pneumoligie","Dentiste"};

        //initilize arraylist
        mainModels =new ArrayList<>();
        for (int i=0;i<categorieLogo.length;i++){
            MainModel model=new MainModel(categorieLogo[i],categorieName[i]);
            mainModels.add(model);

        }

        //Design Horizontal lyout
        LinearLayoutManager layoutManager=new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL, false

        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //initialize mainAdapter
        mainAdapter=new MainAdapter(MainActivity.this,mainModels);
        //set mainAdapter to recyclerview
        recyclerView.setAdapter(mainAdapter);
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
        public int getCount() {
            return imgs.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View verow = layoutInflater.inflate(R.layout.verow,parent,false);
            ImageView images;
            images =  verow.findViewById(R.id.icon);
            TextView mytitle;
            mytitle= verow.findViewById(R.id.text1);
            TextView mydescription;
            mydescription=  verow.findViewById(R.id.text2);
            images.setImageResource(imgs[position]);
            mytitle.setText(titles[position]);
            mydescription.setText(description[position]);
            return verow;
        }
    }



}