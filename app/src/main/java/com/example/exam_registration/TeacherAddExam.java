package com.example.exam_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherAddExam extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    int flag =0;
    String name;
    String date;
    String time;
    String duration;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_exam);

        final EditText examdate = findViewById(R.id.HostAnExamDate);
        final EditText examname = findViewById(R.id.HostAnExamName);
        final EditText examtime = findViewById(R.id.HostAnEamTime);
        final EditText examduration = findViewById(R.id.HostAnExamDuration);
        final EditText examid = findViewById(R.id.HostAnEamID);
        Button host = findViewById(R.id.HostButton);



        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getBaseContext(),TeacherProfile.class);
                flag =0;
                name = examname.getText().toString();
                date = examdate.getText().toString();
                time = examtime.getText().toString();
                duration = examduration.getText().toString();
                id = examid.getText().toString();
                matcher = Pattern.compile(DATE_PATTERN).matcher(date);

                if(!matcher.matches()){
                    examdate.setError("Enter Valid Date");
                    flag = 1;
                }

                if (TextUtils.isEmpty(name)) {
                    examname.setError("Enter Name");
                    flag=1;
                }
                if(TextUtils.isEmpty(date)) {
                    examdate.setError("Enter Date");
                    flag=1;
                }
                if(TextUtils.isEmpty(time))
                {
                    examtime.setError("Enter Time");
                    flag=1;
                }
                if(TextUtils.isEmpty(id)) {
                    examid.setError("Enter ID");
                    flag=1;
                }
                if(TextUtils.isEmpty(duration))
                {
                    examduration.setError("Enter Duration");
                    flag=1;
                }

                if(flag==0){

                    boolean insert = false;
                    Boolean chkeid = db.checkExamId(id);

                    if(chkeid==true)
                    {
                        insert = db.insertExamData(id,name,date,duration);

                        if(insert)
                        {
                            Toast.makeText(getApplicationContext(), "Exam Added Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Exam did not add, Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        examid.setError("Id Already Present, Use another");
                    }

                }


            }
        });


    }

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((2019|2020))";


    /**
     * Validate date format with regular expression
     * @param date date address for validation
     * @return true valid date format, false invalid date format
     */
    public boolean validate(final String date){

        matcher = pattern.matcher(date);

        if(matcher.matches()){
            matcher.reset();

            if(matcher.find()){
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                }

                else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }

                else{
                    return true;
                }
            }

            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
