package com.moncefadj.medcare.Underbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.moncefadj.medcare.R;

public class underbar extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underbar);

        bottomNavigation = (MeowBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_med));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profil));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override

            public void onShowItem(MeowBottomNavigation.Model item) {
                Intent intent = null;
                switch (item.getId()) {
                    /*case 1:
                        intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                        break;
                    case 2: intent = new Intent(getApplicationContext(),Medimaner.class);
                        startActivity(intent);
                        break;
                    case 4: intent = new Intent(getApplicationContext(),Profil.class);
                        startActivity(intent);
                        break;*/


                    //  case 4: fragment=new ProfilFragment();
                    //  break;
                }

            }

        });
        boolean enableAnimation;
        //set home fragment initialy selected
        bottomNavigation.show(3, enableAnimation = true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext(), "you clicked" + item.getId(), Toast.LENGTH_SHORT).show();
            }

            ;
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext(), "YOU reslected" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}