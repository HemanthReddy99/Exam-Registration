package com.example.exam_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Student_registration extends AppCompatActivity {

    DatabaseHelper myDb;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mobilePattern = "[0-9]{10}";

    EditText Smail,Sfname,Smname,Slname,Sdob,Sphno,Sdept,SID,Scgpa,Sadd1,Sadd2,Sadd3,Spass,Srepass;
    Button Sregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        myDb = new DatabaseHelper(this);

        Smail = findViewById(R.id.email_student_registration_text_view);
        Sfname = findViewById(R.id.first_name_student_registration_text_view);
        Smname = findViewById(R.id.middle_name_student_registration_text_view);
        Slname = findViewById(R.id.last_name_student_registration_text_view);
        Sdob = findViewById(R.id.dob_student_registration_text_view);
        Sphno = findViewById(R.id.phone_student_registration_text_view);
        Sdept = findViewById(R.id.department_student_registration_text_view);
        SID = findViewById(R.id.rollno_student_registration_text_view);
        Scgpa = findViewById(R.id.cgpa_student_registration_text_view);
        Sadd1 = findViewById(R.id.addr1_student_registration_text_view);
        Sadd2 = findViewById(R.id.addr2_student_registration_text_view);
        Sadd3 = findViewById(R.id.addr3_student_registration_text_view);
        Spass = findViewById(R.id.password_student_registration_text_view);
        Srepass = findViewById(R.id.password_reenter_student_registration_text_view);

        Sregister = findViewById(R.id.register_student_button);

        Sregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Smail.getText().toString();
                String fname = Sfname.getText().toString();
                String mname = Smname.getText().toString();
                String lname = Slname.getText().toString();
                String dob = Sdob.getText().toString();
                String phno = Sphno.getText().toString();
                String dept = Sdept.getText().toString();
                String rollno = SID.getText().toString();
                String cgpa  = Scgpa.getText().toString();
                String add1 = Sadd1.getText().toString();
                String add2 = Sadd2.getText().toString();
                String add3 = Sadd3.getText().toString();
                String pass = Spass.getText().toString();
                String repass = Srepass.getText().toString();

                if(email.equals("") || fname.equals("") || mname.equals("") || lname.equals("") || dob.equals("") || phno.equals("") || dept.equals("") || rollno.equals("") || cgpa.equals("") || add1.equals("") || add2.equals("") || add3.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "One or more fields empty", Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(), "Email Format not correct", Toast.LENGTH_SHORT).show();
                }
                else if(!phno.matches(mobilePattern))
                {
                    Toast.makeText(getApplicationContext(), "Enter Mobile number correctly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean chkidmail = myDb.chkidmail(rollno, email);

                        if(chkidmail==true)
                        {
                            Boolean insert = myDb.insertData(rollno,email,pass,fname,mname,lname,dob,phno,dept,cgpa,add1,add2,add3);

                            if(insert==true)
                            {
                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent suc_reg =new Intent(getBaseContext(), MainActivity.class);
                                startActivity(suc_reg);
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Email or Student already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Passwords do not Match", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });


    }
}
