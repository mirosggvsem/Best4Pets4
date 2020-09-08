package com.hfad.best4pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import java.util.List;

public class HistoryDetailsActivity extends AppCompatActivity {
    private String key;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        key= getIntent().getStringExtra("key");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_history_details);
        new FirebaseDatabaseHelper("users").readHistoryIems(key,new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Tovar> items, List<String> keys) {
                findViewById(R.id.progressBar_history_details).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView, HistoryDetailsActivity.this, items, keys);
            }

            @Override
            public void DataIsLoaded2(List<ZakazInfo> zakazInfoList, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated(List<Tovar> items, List<String> keys, int prise) {

            }


            @Override
            public void DataIsDeleted() {

            }
        });

    }
}
