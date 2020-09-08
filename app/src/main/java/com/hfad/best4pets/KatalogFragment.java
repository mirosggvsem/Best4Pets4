package com.hfad.best4pets;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


public class KatalogFragment extends Fragment {
    private int id1;
    private List<String> keys1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler  = (RecyclerView) inflater.inflate(R.layout.fragment_katalog, container, false);
        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i = 0; i<pizzaNames.length; i++){
            pizzaNames[i]=Pizza.pizzas[i].getName();
        }
        int[] pizzaImages = new int[Pizza.pizzas.length];
        for (int i = 0; i<pizzaImages.length; i++){
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
        }
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent= new Intent(getActivity(), Katalog.class);
                intent.putExtra(Katalog.EXTRA_PIZZA_ID, position);
                startActivity(intent);
            }
        });
        return pizzaRecycler;
    }

}
