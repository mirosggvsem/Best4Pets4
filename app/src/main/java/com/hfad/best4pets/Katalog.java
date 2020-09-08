package com.hfad.best4pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


import java.util.List;

public class Katalog extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public static final String EXTRA_PIZZA_ID = "pizzaId";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);

       int pizzaId = (Integer) getIntent().getExtras().get(EXTRA_PIZZA_ID);
            String name = Pizza.pizzas[pizzaId].getName();
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_books_katalog);

            new FirebaseDatabaseHelper(name).readItems(new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void DataIsLoaded(List<Tovar> items, List<String> keys) {
                    findViewById(R.id.progressBar_katalog).setVisibility(View.GONE);
                    new RecyclerView_Config().setConfig(mRecyclerView, Katalog.this, items, keys);
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
