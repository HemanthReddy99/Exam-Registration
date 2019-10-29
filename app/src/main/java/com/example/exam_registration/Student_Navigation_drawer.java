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

public class Student_Navigation_drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    TextView pmail,pname;
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_navigation_drawer);

        //Toast.makeText(getApplicationContext(),db.getusername(),Toast.LENGTH_SHORT).show();


//        pmail = (TextView)findViewById(R.id.student_emailid);
//        pmail.setText(db.getusername());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new StudentExamFragment()).commit();
            navigationView.setCheckedItem(R.id.student_exams);

            NavigationView navigationview = findViewById(R.id.nav_view);
            View headerview = navigationview.getHeaderView(0);
            pname = headerview.findViewById(R.id.student_name);
            pname.setText(db.getusername());
            pmail = headerview.findViewById(R.id.student_emailid);
            pmail.setText(db.getusermail());

            ImageView view= headerview.findViewById(R.id.student_image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(Student_Navigation_drawer.this,StudentProfile.class);
                    startActivity(intent1);
                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.student_exams:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudentExamFragment()).commit();
                break;
            case R.id.student_registered:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudentRegisteredFragment()).commit();
                break;

            case R.id.student_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String shareBody = "Hey!!!\nDownload the new cool app for easily organising exams!!!\nLink to Download the file \n"+"https://drive.google.com/open?id=1wRGNFWaoEKEkKqHPwm1wWgTuDlfhMoOP";
                String sharesub = "Download The App";
                share.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                share.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(share, "Share Using"));
                break;

            case R.id.student_logout:
                Intent student_logout = new Intent(getApplicationContext(), MainActivity.class);
                student_logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                db.logout();
                startActivity(student_logout);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
