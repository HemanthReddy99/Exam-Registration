package com.example.exam_registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RegisteredExamRecyclerAdapter extends RecyclerView.Adapter<RegisteredExamRecyclerAdapter.RegisteredExamViewHolder> {

    private ArrayList<RegisteredExam> registeredExamArrayList;
    private Context mContext;
    private ArrayList<RegisteredExam> mFilteredList;
    private OnRegisteredExamClickListener monRegisteredExamClickListener;

    public RegisteredExamRecyclerAdapter(ArrayList<RegisteredExam> exams, Context mContext, OnRegisteredExamClickListener onExamClickListener)
    {
        this.registeredExamArrayList = exams;
        this.mContext = mContext;
        this.mFilteredList = exams;
        this.monRegisteredExamClickListener = onExamClickListener;
    }

    public class RegisteredExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView ename;
        public TextView edate;
        public TextView eduration;
        OnRegisteredExamClickListener onExamClickListener;

        public RegisteredExamViewHolder(View view, OnRegisteredExamClickListener onExamClickListener)
        {
            super(view);
            ename = view.findViewById(R.id.registered_item_exam_name);
            edate = view.findViewById(R.id.registered_item_exam_date);
            eduration = view.findViewById(R.id.registered_item_exam_duration);
            this.onExamClickListener = onExamClickListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onExamClickListener.OnRegisteredExamClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RegisteredExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_registered_exam_list_item,parent,false);


        return  new RegisteredExamRecyclerAdapter.RegisteredExamViewHolder(itemView,monRegisteredExamClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisteredExamViewHolder holder, int position) {

        holder.ename.setText(registeredExamArrayList.get(position).getExamName());
        holder.edate.setText(registeredExamArrayList.get(position).getExamDate());
        holder.eduration.setText(registeredExamArrayList.get(position).getExamDuration());


    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public interface OnRegisteredExamClickListener
    {
        void OnRegisteredExamClick(int position);
    }

}


