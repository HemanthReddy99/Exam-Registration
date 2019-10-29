package com.example.exam_registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class StudentAllExams extends AppCompatActivity implements ExamRecyclerAdapter.OnExamClickListener {

    private AppCompatActivity activity = StudentAllExams.this;
    Context context = StudentAllExams.this;
    private RecyclerView recyclerViewExam;
    private ArrayList<Exam> listExam;
    private ExamRecyclerAdapter examRecyclerAdapter;
    private DatabaseHelper db;
    private ArrayList<Exam> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_all_exams);

        initViews();
        initObjects();
    }

    private void initViews()
    {
        recyclerViewExam = findViewById(R.id.list_of_exams);
    }

    private void initObjects()
    {
        listExam = new ArrayList<>();
        examRecyclerAdapter = new ExamRecyclerAdapter(listExam,this,this);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewExam.setLayoutManager(mlayoutManager);
        recyclerViewExam.setItemAnimator(new DefaultItemAnimator());
        recyclerViewExam.setHasFixedSize(true);
        recyclerViewExam.setAdapter(examRecyclerAdapter);
        db = new DatabaseHelper(activity);

        getDataFromSQLite();

    }


    private void getDataFromSQLite()
    {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                listExam.clear();
                listExam.addAll(db.getAllExams());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                examRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void OnExamClick(int position) {
         Exam temp = listExam.get(position);
        Intent intent = new Intent(this,Student_ViewExam.class);
        intent.putExtra("EID", temp.getEid());
        intent.putExtra("AID", temp.getAid());
        intent.putExtra("Ename", temp.getEname());
        intent.putExtra("Edate", temp.getEdate());
        intent.putExtra("Eduration", temp.getEduration());
        startActivity(intent);

    }
}



//////Exam List Adapter class for the list view
////
////class ExamlistAdapter extends BaseAdapter {
////
////    @Override
////    public int getCount() {
////        return 25;  // Total Number of Entries
////    }
////
////    @Override
////    public Object getItem(int i) {
////        return null;
////    }
////
////    @Override
////    public long getItemId(int i) {
////        return 0;
////    }
////
////    @Override
////    public View getView(int i, View view, ViewGroup viewGroup) {
////
////        //view is the list item we are going to use. List item is defined in student_exam_list_layout.
////        //we inflate the view using the components and det properties of individual components in the list item
////
////        view = getLayoutInflater().inflate(R.layout.student_exam_list_layout,null);
////        TextView exam_name = (TextView)view.findViewById(R.id.item_exam_name);
////        TextView exam_duration = (TextView)view.findViewById(R.id.item_exam_duration);
////        TextView exam_date = (TextView)view.findViewById(R.id.item_exam_date);
////
////        exam_name.setText("DBMS");
////        exam_duration.setText("120 min");
////        exam_date.setText("15/09/2019");
////
////
////        view.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent view_exam = new Intent(view.getContext(), Student_ViewExam.class);
////                startActivity(view_exam);
////            }
////        });
////
////        return view;
////    }
//}
