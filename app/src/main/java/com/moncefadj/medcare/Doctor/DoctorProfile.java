package com.moncefadj.medcare.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.Common.LoginActivity;
import com.moncefadj.medcare.DataClasses.DoctorData;
import com.moncefadj.medcare.R;

import java.util.Calendar;
import java.util.HashMap;

public class DoctorProfile extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String[] days;
    TextInputLayout daysInput;
    ArrayAdapter arrayAdapter;

    String uidDoctor;
    DatabaseReference doctorRef;
    DatabaseReference doctorTimeRef;
    DatabaseReference doctorDayRef;

    String currentDay;
    int currentDayIndex;
    String selectedDay;

    // Doctor profile Data
    TextView name,fullSpecialty,address,desc,rate,phone;
    ImageView profileImg;

    Dialog dialog;
    EditText hourEditTxt,minuteEditTxt;
    Button confirmBtn;
    ImageView cancelBtn;
    String hour,minute;
    ImageButton backImgBtn;

    FlexboxLayout flexboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        // profile data Hooks
        name = findViewById(R.id.profile_doctor_name);
        fullSpecialty = findViewById(R.id.profile_doctor_specialty);
        address = findViewById(R.id.profile_doctor_address);
        desc = findViewById(R.id.profile_doc_desc);
        rate = findViewById(R.id.rate_txt);
        phone = findViewById(R.id.profile_phone_txt);
        profileImg = findViewById(R.id.profile_doc_image);




        uidDoctor = FirebaseAuth.getInstance().getCurrentUser().getUid();
        doctorRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Doctors").child(uidDoctor);

        ImageView settingsBtn = findViewById(R.id.profile_doc_settings_btn); // just for testing signOut
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DoctorProfile.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.doctor_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit_item:
                                Intent intentLoadNewActivity = new Intent(DoctorProfile.this, EditDoctorProfile.class);
                                startActivity(intentLoadNewActivity);
                                return true;
                            case R.id.logout_item:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(DoctorProfile.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        daysAutoCompleteTxt();

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String d = daysInput.getEditText().getText().toString();
                daysInput.setError(null);
                removeFlexBoxViews();
                showAllDayButtons(d);

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ImageButton addBtn = findViewById(R.id.add_rdv_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                daysInput.setError(null);

                selectedDay = daysInput.getEditText().getText().toString();

                if (selectedDay.isEmpty()) {
                    daysInput.setError("Choisissez d'abord le jour");
                } else {

                    showTimePicker();

                }
            }
        });

        // ** Dialog ** to ask user if he is a doctor or patient when he want to signUp
        dialog = new Dialog(DoctorProfile.this);
        dialog.setContentView(R.layout.custom_time_picker_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner_white));
        }
        dialog.setCancelable(false);
        // we can mnake animation for Dialog by mentioned it in Style
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        confirmBtn = dialog.findViewById(R.id.confirm_time_btn);
        cancelBtn = dialog.findViewById(R.id.cancel_dialog);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hourEditTxt = dialog.findViewById(R.id.dialog_hour);
                minuteEditTxt = dialog.findViewById(R.id.dialog_minute);
                hour = hourEditTxt.getText().toString();
                minute = minuteEditTxt.getText().toString();

                if ((hour != "") && (minute != "")) {
                    addButtonToDB(selectedDay, hour + ":" + minute);
                    removeFlexBoxViews();
                    showAllDayButtons(selectedDay);
                    dialog.dismiss();
                }
            }
        });

        backImgBtn = (ImageButton) findViewById(R.id.profile_doc_back_btn);
        backImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(DoctorProfile.this, DoctorDashboard.class);
                startActivity(intentLoadNewActivity);
            }
        });



    }




    @Override
    protected void onStart() {
        super.onStart();

        setProfileData();

        currentDay = getCurrentDay();
        Log.i("Current Day", currentDay);

    }

    private void showAllDayButtons(String day) {

        DatabaseReference dayRef = doctorRef.child("rdvTimes").child(day);
        dayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot day : snapshot.getChildren()) {

                        String time = day.child("time").getValue(String.class);
                        String available = day.child("available").getValue(String.class);

                        showButton(time, available);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }



    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String currentDay = "Samedi";

        switch (day) {
            case Calendar.SATURDAY:
                currentDay = "Samedi";
                break;
            case Calendar.SUNDAY:
                currentDay = "Dimanche";
                break;
            case Calendar.MONDAY:
                currentDay = "Lundi";
                break;
            case Calendar.TUESDAY:
                currentDay = "Mardi";
                break;
            case Calendar.WEDNESDAY:
                currentDay = "Mercredie";
                break;
            case Calendar.THURSDAY:
                currentDay = "Jeudi";
                break;
            case Calendar.FRIDAY:
                currentDay = "Vendredi";
                break;
        }
        return currentDay;
    }

    private void addButtonToDB(String day, String time) {
        // add time to DB
        doctorTimeRef = doctorRef.child("rdvTimes").child(day).child(time);
        HashMap<String, Object> map = new HashMap<>();
        map.put("time", time);
        map.put("available", "yes");

        doctorTimeRef.updateChildren(map);
    }

    private void showButton(String time, String available) {
        Button button = new Button(DoctorProfile.this);
        FlexboxLayout flexboxLayout = findViewById(R.id.flex_box);

        button.setText(time);
        button.setTextColor(getResources().getColor(R.color.white));
        if (available.equals("yes")) {
            button.setBackground(getResources().getDrawable(R.drawable.circle_button));
        } else button.setBackground(getResources().getDrawable(R.drawable.circle_button_grey));

        // we want to convert dp to pixel cause setWith and setHeight use only pixels
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int heightPixel = (int) (60 * scale + 0.5f);
        int widthPixel = (int) (65 * scale + 0.5f);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthPixel,heightPixel);
        params.setMargins(15,0,0,15);

        button.setLayoutParams(params);
        flexboxLayout.addView(button);

    }


    private void setProfileData() {

        doctorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    DoctorData doctorData = snapshot.getValue(DoctorData.class);

                    name.setText(doctorData.getName());
                    fullSpecialty.setText(doctorData.getFullSpecialty());
                    address.setText(doctorData.getAddress());
                    desc.setText(doctorData.getDesc());
                    rate.setText(doctorData.getRate());
                    phone.setText(doctorData.getPhone());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void daysAutoCompleteTxt() {

        daysInput = findViewById(R.id.days_input);

        autoCompleteTextView = findViewById(R.id.autoCompleteText);
        days = getResources().getStringArray(R.array.days);
        arrayAdapter = new ArrayAdapter(this, R.layout.drop_down_item, days);
        autoCompleteTextView.setAdapter(arrayAdapter);

        // search index of current day in days(array) cause autoCompleteTextView.setText(take only char or index)


    }

    public void showTimePicker() {
        dialog.show();
    }

    public void removeFlexBoxViews() {
        FlexboxLayout flexboxLayout = findViewById(R.id.flex_box);
        flexboxLayout.removeAllViews();
    }



}