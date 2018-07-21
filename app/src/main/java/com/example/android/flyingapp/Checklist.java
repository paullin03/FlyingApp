package com.example.android.flyingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Checklist extends AppCompatActivity {
    String[] checklistTitles;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    static final int request = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        mRecyclerView=findViewById(R.id.checklist_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        // Set up RecyclerView with current task list
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        checklistTitles=getResources().getStringArray(R.array.checklist_titles);
        mAdapter = new ChecklistAdapter(checklistTitles);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CheckBox checkbox;

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == request){
            if(resultCode == RESULT_OK){
                checkbox=mRecyclerView.findViewHolderForAdapterPosition(data.getIntExtra("TaskNumber",0)).itemView.findViewById(R.id.checklist_checkbox);
                checkbox.setChecked(data.getBooleanExtra("AllChecked",false));
            }
        }
    }

    public void openChecklistItem(View view){
        Intent i = new Intent(Checklist.this, SpecificChecklist.class);
        i.putExtra("TaskNumber", view.getId());
        i.putExtra("TaskTitle", checklistTitles[view.getId()]);
        startActivityForResult(i,request);
    }
}
