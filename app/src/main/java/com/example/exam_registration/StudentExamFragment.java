package com.example.exam_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudentExamFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_exam, container, false);

        //Displaying list of exams in listview
        ListView student_exam_list = view.findViewById(R.id.list_of_exams);

        ExamlistAdapter student_exam_list_adapter = new ExamlistAdapter();

        student_exam_list.setAdapter(student_exam_list_adapter);


        return view;


    }

    //Exam List Adapter class for the list view

    class ExamlistAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 25;  // Total Number of Entries
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //view is the list item we are going to use. List item is defined in student_exam_list_layout.
            //we inflate the view using the components and det properties of individual components in the list item

            view = getLayoutInflater().inflate(R.layout.student_exam_list_layout,null);
            TextView exam_name = view.findViewById(R.id.item_exam_name);
            TextView exam_duration = view.findViewById(R.id.item_exam_duration);
            TextView exam_date = view.findViewById(R.id.item_exam_date);

            exam_name.setText("DBMS");
            exam_duration.setText("120 min");
            exam_date.setText("15/09/2019");


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent view_exam = new Intent(getActivity(), Student_ViewExam.class);
                    startActivity(view_exam);
                }
            });

            return view;
        }
    }
}
