package com.example.android.flyingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.lang.reflect.Field;

public class SpecificChecklist extends AppCompatActivity {
    String[] items;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    int taskNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Class<R.array> res;
        Field field;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_checklist);

        Intent intent = getIntent();

        setTitle(intent.getStringExtra("TaskTitle"));

        try {
            res = R.array.class;
            taskNumber=intent.getIntExtra("TaskNumber",0);
            field = res.getField("checklist_items_" + Integer.toString(taskNumber));

            //set myString to the string resource myArray[x,y]
            items = getResources().getStringArray(field.getInt(null));
        }
        catch (Exception e) {
                e.printStackTrace();
        }

        mRecyclerView=findViewById(R.id.specific_checklist_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        // Set up RecyclerView with current task list
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter = new SpecificChecklistAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void returnBack(View view){
        Intent i = new Intent();
        CheckBox checkbox;
        boolean allChecked = true;
        int index = 0;

        while(index<items.length&&allChecked==true){
            checkbox=mRecyclerView.findViewHolderForAdapterPosition(index).itemView.findViewById(R.id.specific_checklist_checkbox);
            allChecked=checkbox.isChecked();
            index++;
        }
        i.putExtra("AllChecked", allChecked);
        i.putExtra("TaskNumber", taskNumber);
        setResult(RESULT_OK, i);

        finish();
    }
}
