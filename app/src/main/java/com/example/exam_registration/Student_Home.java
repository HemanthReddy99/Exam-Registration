package com.example.exam_registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Student_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Toolbar Settings
        setContentView(R.layout.activity_student_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        //Displaying list of exams in listview
//        ListView student_exam_list = (ListView)findViewById(R.id.list_of_exams);
//
//        ExamlistAdapter student_exam_list_adapter = new ExamlistAdapter();
//
//        student_exam_list.setAdapter(student_exam_list_adapter);


    }


//    //Exam List Adapter class for the list view
//
//    class ExamlistAdapter extends BaseAdapter{
//
//        @Override
//        public int getCount() {
//            return 25;  // Total Number of Entries
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            //view is the list item we are going to use. List item is defined in student_exam_list_layout.
//            //we inflate the view using the components and det properties of individual components in the list item
//
//            view = getLayoutInflater().inflate(R.layout.student_exam_list_layout,null);
//            TextView exam_name = (TextView)view.findViewById(R.id.item_exam_name);
//            TextView exam_duration = (TextView)view.findViewById(R.id.item_exam_duration);
//            TextView exam_date = (TextView)view.findViewById(R.id.item_exam_date);
//
//            exam_name.setText("DBMS");
//            exam_duration.setText("120 min");
//            exam_date.setText("15/09/2019");
//
//            return view;
//        }
//    }

}
