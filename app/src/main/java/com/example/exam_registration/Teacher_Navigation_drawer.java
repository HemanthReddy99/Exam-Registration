package com.example.exam_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Teacher_Navigation_drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer1;
    TextView pmail,pname;
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_navigation_drawer);


        Toolbar toolbar1 = findViewById(R.id.teacher_toolbar);
        setSupportActionBar(toolbar1);

        drawer1 = findViewById(R.id.teacher_drawer_layout);
        NavigationView navigationView = findViewById(R.id.teacher_nav_view);
       navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer1,toolbar1,
                R.string.navigation_drawer_open_teacher,R.string.navigation_drawer_close_teacher);
        drawer1.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.teacher_fragment_container,
                    new TeacherYourexamFragment()
            ).commit();
            navigationView.setCheckedItem(R.id.teacher_exams);

            NavigationView navigationview = findViewById(R.id.teacher_nav_view);
            View headerview = navigationview.getHeaderView(0);
            pname = headerview.findViewById(R.id.teacher_name);
            pname.setText(db.admingetusername());
            pmail = headerview.findViewById(R.id.teacher_emailid);
            pmail.setText(db.admingetusermail());

            ImageView view= headerview.findViewById(R.id.teacher_image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(Teacher_Navigation_drawer.this,TeacherProfile.class);
                    startActivity(intent1);
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.teacher_exams:
                getSupportFragmentManager().beginTransaction().replace(R.id.teacher_fragment_container,
                        new TeacherYourexamFragment()).commit();
                break;
            case R.id.teacher_add_exam:
                getSupportFragmentManager().beginTransaction().replace(R.id.teacher_fragment_container,
                        new TeacherAddexamFragment()).commit();
                break;
            case R.id.teacher_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String shareBody = "Hey!!!\nDownload the new cool app for easily organising exams!!!\nLink to Download the file \n"+"still building";
                String sharesub = "Download The App";
                share.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                share.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(share, "Share Using"));
                break;
            case R.id.teacher_logout:
                Intent teacher_logout = new Intent(getApplicationContext(), MainActivity.class);
                db.adminlogout();
                teacher_logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(teacher_logout);
                break;
        }

        drawer1.closeDrawer(GravityCompat.START);
        return true;
    }
}
