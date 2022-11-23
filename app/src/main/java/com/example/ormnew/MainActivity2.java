package com.example.ormnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    public itemAdapter itmAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );

        recyclerView = findViewById(R.id.recyclerView);
        DataBaseController.getInstance().fetchUserData();
        itmAdapter = new itemAdapter ( this);
        recyclerView.setAdapter(itmAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager (MainActivity2.this));
        itmAdapter.notifyDataSetChanged();

    }
}