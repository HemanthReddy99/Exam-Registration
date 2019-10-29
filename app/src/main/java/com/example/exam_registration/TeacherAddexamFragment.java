package com.example.exam_registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TeacherAddexamFragment extends Fragment {

    DatabaseHelper db = new DatabaseHelper(getContext());

    @Nullable
    private int flag =0;
    private String name;
    private String date;
    private String time;
    private String duration;
    private String id;


    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_teacher_addexam, container, false);

        final EditText examdate = view.findViewById(R.id.HostAnExamDate);
        final EditText examname = view.findViewById(R.id.HostAnExamName);
        final EditText examtime = view.findViewById(R.id.HostAnEamTime);
        final EditText examduration = view.findViewById(R.id.HostAnExamDuration);
        final EditText examid = view.findViewById(R.id.HostAnEamID);
        Button host = view.findViewById(R.id.HostButton);



        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(),Teacher_Navigation_drawer.class);
                flag =0;
                name = examname.getText().toString();
                date = examdate.getText().toString();
                time = examtime.getText().toString();
                duration = examduration.getText().toString();
                id = examid.getText().toString();

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
                            Toast.makeText(getContext(), "Exam Added Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Exam did not add, Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        examid.setError("Id Already Present, Use another");
                    }

                }


            }
        });

        return view;
    }
}
