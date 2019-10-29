package com.example.exam_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudentRegisteredFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_exam_registered, container, false);

        TextView exam_name = v.findViewById(R.id.item_exam_name);
        TextView exam_duration = v.findViewById(R.id.item_exam_duration);
        TextView exam_date = v.findViewById(R.id.item_exam_date);

        exam_name.setText("DBMS");
        exam_duration.setText("120 min");
        exam_date.setText("15/09/2019");

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent view_exam = new Intent(getActivity(), Student_ViewExam.class);
                startActivity(view_exam);
            }
        });


        return v;
    }
}
