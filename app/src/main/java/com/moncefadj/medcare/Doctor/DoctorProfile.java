package com.moncefadj.medcare.Doctor;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.moncefadj.medcare.R;

public class DoctorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        ImageButton addBtn = findViewById(R.id.add_rdv_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = new Button(DoctorProfile.this);
                FlexboxLayout flexboxLayout = findViewById(R.id.flex_box);


                button.setText("00:00");
                button.setTextColor(getResources().getColor(R.color.white));
                button.setBackground(getResources().getDrawable(R.drawable.circle_button));
                //button.setBackgroundResource(R.drawable.circle_button);
                //button.setBackgroundColor(getResources().getColor(R.color.transparent));

                // we want to convert dp to pixel cause setWith and setHeight use only pixels
                final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                int heightPixel = (int) (60 * scale + 0.5f);
                int widthPixel = (int) (65 * scale + 0.5f);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthPixel,heightPixel);
                params.setMargins(15,0,0,15);


                button.setLayoutParams(params);
                flexboxLayout.addView(button);
            }
        });


    }
}