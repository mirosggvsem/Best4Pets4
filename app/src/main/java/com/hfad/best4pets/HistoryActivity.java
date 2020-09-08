package com.hfad.best4pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_books_history);
        new FirebaseDatabaseHelper("users").readHistori(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Tovar> tovars, List<String> keys) {
            }
            @Override
            public void DataIsLoaded2(List<ZakazInfo> zakazInfoList, List<String> keys) {
                findViewById(R.id.progressBar_history).setVisibility(View.GONE);
                new Recycler_history_Config().setConfig(mRecyclerView, HistoryActivity.this, zakazInfoList, keys);
            }
            @Override
            public void DataIsInserted() {
            }
            @Override
            public void DataIsUpdated(List<Tovar> tovars, List<String> keys, int prise) {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}
