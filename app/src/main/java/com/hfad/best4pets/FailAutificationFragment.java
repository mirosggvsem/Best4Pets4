package com.hfad.best4pets;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FailAutificationFragment extends Fragment {
    private Button mButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout =inflater.inflate(R.layout.fragment_fail_autification, container, false);
        mButton=(Button)layout.findViewById(R.id.aut_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(layout.getContext(),EnteredActivity.class);
                startActivity(intent);
            }
        });

        return layout;
    }
}
